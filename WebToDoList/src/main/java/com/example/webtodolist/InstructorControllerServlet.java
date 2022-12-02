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

@WebServlet(name = "instructorControllerServlet", value = "/instructor-controller-servlet")
public class InstructorControllerServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_account = req.getParameter("name");
        req.setAttribute("name",name_account);
        doGet(req,resp);
    }
}
