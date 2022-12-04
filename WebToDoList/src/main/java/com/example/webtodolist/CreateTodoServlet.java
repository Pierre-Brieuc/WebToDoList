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

@WebServlet(name = "createTodoServlet", value = "/create-todo-servlet")
public class CreateTodoServlet extends HttpServlet {
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
            String name_account = req.getParameter("name");
            req.setAttribute("name",name_account);
            req.getRequestDispatcher("/create-todo.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Create todo
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newDescription = req.getParameter("description");
        if (!newDescription.equals("")) {
            Connection myConn=null;
            PreparedStatement preparedStmt = null;
            try{
                dataSource = getDataSource();
                myConn = dataSource.getConnection();
                String query = "INSERT INTO todo(todo_description) VALUES (?)";
                preparedStmt = myConn.prepareStatement(query);
                preparedStmt.setString(1, newDescription);
                preparedStmt.execute();
                String name_account = req.getParameter("name");
                req.setAttribute("name",name_account);
                req.getRequestDispatcher("instructor-controller-servlet").forward(req, resp);
            }catch(Exception exc){
                System.out.println(exc.getMessage());
            } finally {
                this.close(myConn, null, preparedStmt, null);
            }
        } else {
            String errorMessage = "The description cannot be empty";
            req.setAttribute("ERROR",errorMessage);
            doGet(req, resp);
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
