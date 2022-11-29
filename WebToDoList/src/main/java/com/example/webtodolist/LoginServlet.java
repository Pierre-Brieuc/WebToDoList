package com.example.webtodolist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
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

            Cookie [] cookies = req.getCookies();
            if(cookies!= null){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals("accountname"))
                        req.setAttribute("accountname", cookie.getValue()) ;
                }
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameToCheck = req.getParameter("name");
        String passwordToCheck = req.getParameter("password");
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            dataSource= getDataSource();
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from account";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                if (nameToCheck.equals(myRs.getString("accountname")) &&
                        passwordToCheck.equals(myRs.getString("account_password"))){

                    Cookie cookie = new Cookie("accountname", nameToCheck);
                    cookie.setMaxAge(60*60*24) ; // in seconds, here for 24 hours
                    resp.addCookie(cookie) ;

                    if (myRs.getString("account_role").equals("instructor")) {
                        close(myConn,myStmt,null,myRs);
                        HttpSession session = req.getSession();
                        req.getRequestDispatcher("instructor-controller-servlet").forward(req, resp);
                    } else{
                        close(myConn,myStmt,null,myRs);
                        HttpSession session = req.getSession();
                        req.getRequestDispatcher("student-controller-servlet").forward(req, resp);
                    }

                }
            }
            String errorMessage = "wrong username or password";
            req.setAttribute("ERROR",errorMessage);
            doGet(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
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

    public void destroy() {
    }
}
