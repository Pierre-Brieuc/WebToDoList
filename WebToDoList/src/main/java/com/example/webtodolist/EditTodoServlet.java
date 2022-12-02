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

@WebServlet(name = "editTodoServlet", value = "/edit-todo-servlet")
public class EditTodoServlet extends HttpServlet {
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
        String tempId = req.getParameter("id");
        String tempDesc = req.getParameter("description");
        String name_account = req.getParameter("name");

        req.setAttribute("id",tempId);
        req.setAttribute("description",tempDesc);
        req.setAttribute("name",name_account);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/edit-todo.jsp");
        dispatcher.forward(req, resp);
    }

    // update todo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTodo = req.getParameter("idTodo");
        String newDescription = req.getParameter("description");
        Connection myConn=null;
        PreparedStatement preparedStmt = null;
        try{
            dataSource= getDataSource();
            myConn= dataSource.getConnection();
            String query = "UPDATE todo SET todo_description=? WHERE id_todo=?";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setString(1, newDescription);
            preparedStmt.setString(2, idTodo);
            preparedStmt.executeUpdate(query);
            String name_account = req.getParameter("name");
            req.setAttribute("name",name_account);
            req.getRequestDispatcher("instructor-controller-servlet").forward(req, resp);
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        } finally {
            this.close(myConn, null, preparedStmt, null);
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
