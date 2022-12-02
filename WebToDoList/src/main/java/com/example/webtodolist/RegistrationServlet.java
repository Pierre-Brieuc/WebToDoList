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

@WebServlet(name = "registrationUpServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet {
    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/webtodolist_db";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }

    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            dataSource = getDataSource();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/registration.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newPassword = req.getParameter("password");
        String newRole = req.getParameter("role");

        try {
            if (isNameAvailable(newName)) {
                if (!newPassword.isEmpty()) {
                    Connection myConn=null;
                    PreparedStatement preparedStmt = null;
                    try{
                        dataSource= getDataSource();
                        myConn= dataSource.getConnection();
                        String query = "INSERT INTO account(accountname,account_password,account_role) VALUES (?,?,?)";
                        preparedStmt = myConn.prepareStatement(query);
                        preparedStmt.setString(1, newName);
                        preparedStmt.setString(2, newPassword);
                        preparedStmt.setString(3, newRole);
                        preparedStmt.execute();
                        this.close(myConn, null, preparedStmt, null);

                        if (newRole.equals("instructor")){
                            req.getRequestDispatcher("/instructor-controller-servlet").forward(req, resp);
                        } else {
                            req.getRequestDispatcher("/student-controller-servlet").forward(req, resp);
                        }
                    }catch(Exception exc){
                        System.out.println(exc.getMessage());
                    }
                } else {
                    // The password cannot be empty
                    String errorMessage = "wrong username or password";
                    req.setAttribute("ERROR",errorMessage);
                    doGet(req,resp);
                }
            } else {
                // The name is not available
                String errorMessage = "The name is not available";
                req.setAttribute("ERROR",errorMessage);
                doGet(req,resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNameAvailable (String newName) throws Exception {
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            dataSource = getDataSource();
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from account";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                String accountname = myRs.getString("accountname");
                if (accountname.equals(newName)){
                    return false;
                }
            }
            return true;
        } finally{
            close(myConn,myStmt,null,myRs);
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
