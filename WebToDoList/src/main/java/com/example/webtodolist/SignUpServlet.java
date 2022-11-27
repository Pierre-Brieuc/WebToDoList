package com.example.webtodolist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "signUpServlet", value = "/sign-up-servlet")
public class SignUpServlet extends HttpServlet {
    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/webtodolist_db";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newPassword = req.getParameter("password");
        String newRole = req.getParameter("role");

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
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
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }

    public void destroy() {
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
