package com.example.webtodolist;

import java.io.*;
import java.sql.*;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "todoControllerServlet", value = "/todo-controller-servlet")
public class TodoControllerServlet extends HttpServlet {
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
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
            out.println("Bonjour");
            listTodos(req,resp);
            out.println("Au revoir");
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

    // Create todo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDescription = req.getParameter("description");
        resp.setContentType("text/html");
        Connection myConn=null;
        PreparedStatement preparedStmt = null;
        try{
            dataSource= getDataSource();
            myConn= dataSource.getConnection();
            String query = "INSERT INTO todo(todo_description) VALUES (?)";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, newDescription);
            preparedStmt.execute();
            this.close(myConn, null, preparedStmt, null);
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }

    // Update todo
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTodo = req.getParameter("idTodo");
        String newDescription = req.getParameter("description");
        resp.setContentType("text/html");
        Connection myConn=null;
        PreparedStatement preparedStmt = null;
        try{
            dataSource= getDataSource();
            myConn= dataSource.getConnection();
            String query = "UPDATE todo SET todo_description=? WHERE id_todo=?";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, newDescription);
            preparedStmt.setString(2, idTodo);
            preparedStmt.execute();
            this.close(myConn, null, preparedStmt, null);
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }

    // Delete todo
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTodo = req.getParameter("idTodo");
        resp.setContentType("text/html");
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
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
