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
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "testServlet", value = "/test-servlet")
public class TestServlet extends HttpServlet {
    private DataSource dataSource;
    private DataSource getDataSource() throws NamingException {
        String jndi = "java:comp/env/jdbc/webtodolist_db";
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
//Step1: set up the printwriter
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
//Step2: Get a connection to the database
        Connection myConn=null;
        Statement myStmt= null;
        ResultSet myRs=null;
        try{
            dataSource= getDataSource();
            myConn= dataSource.getConnection();
//Step3: create SQL statements
            String sql = "select * from account";
            myStmt= myConn.createStatement();
//Step4: Execute SQL query
            myRs=myStmt.executeQuery(sql);
//Step5: Process the ResultSet
            while(myRs.next()){
                String email = myRs.getString("accountname");
                out.println("<html><body>");
                out.println("<h1>"+ email +"</h1>");
                out.println("</body></html>");
            }
        }catch(Exception exc){
            System.out.println(exc.getMessage());
        }
    }
}

