package com.example.webtodolist;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoDBUtil {
    private DataSource dataSource;
    public TodoDBUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<Todo> getTodos() throws Exception {
        List<Todo> todos = new ArrayList<Todo>();
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "SELECT * FROM todo";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                int idTodo = myRs.getInt("id_todo");
                String todoDescription = myRs.getString("todo_description");
                Todo tempTodo = new Todo(idTodo,todoDescription);
                todos.add(tempTodo);
            }
            return todos;
        } finally{
            close(myConn,myStmt,myRs);
        }
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
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
