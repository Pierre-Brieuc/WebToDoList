package com.example.webtodolist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


@WebServlet(name = "deleteTodoServlet", value = "/delete-todo-servlet")
public class DeleteTodoServlet extends HttpServlet {
    private DataSource dataSource;
    private TodoDBUtil todoDBUtil;

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            dataSource = getDataSource();
            todoDBUtil = new TodoDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/webtodolist_db";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }

    // Get todolist from server
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            listTodos(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listTodos(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<Todo> todos = todoDBUtil.getTodos();
        request.setAttribute("TODO_LIST", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/instructor-page.jsp");
        dispatcher.forward(request, response);
    }

    // Delete todo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTodo = req.getParameter("id");
        Connection myConn=null;
        PreparedStatement preparedStmt = null;
        try{
            dataSource= getDataSource();
            myConn= dataSource.getConnection();
            String query = "DELETE FROM todo WHERE id_todo=?";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, idTodo);
            preparedStmt.execute();
            this.close(myConn, null, preparedStmt, null);
            doGet(req,resp);
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }

    private void close(Connection myConn, Statement myStmt, PreparedStatement preparedStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
            if(preparedStmt!=null)
                preparedStmt.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

