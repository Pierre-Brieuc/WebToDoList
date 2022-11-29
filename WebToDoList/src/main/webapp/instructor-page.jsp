<%@ page import="com.example.webtodolist.Todo" %>
<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 27/11/2022
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>

<html>
    <head>
        <title>Title</title>
    </head>

    <% List<Todo> theTodos = (List<Todo>)request.getAttribute("TODO_LIST"); %>

    <body>
        <h1>Instructor</h1>
        <form action="logout-servlet" method="get">
            <input type="submit" value="Log out"/>
        </form>
        <form action="create-todo-servlet">
            <input type="submit" value="create new todo" formmethod="get"/>
        </form>
        <!-- ${TODO_LIST}-->
        <% if (theTodos != null) {%>
            <%for (int i=0; i<theTodos.size(); i++) {%>
                <form>
                Todo <%= theTodos.get(i).getId_todo() %>
                <%= theTodos.get(i).getDescription() %>
                    <input type="submit" value="edit" formmethod="post" formaction="edit-todo-servlet"/>
                    <input type="submit" value="delete" formmethod="post" formaction="delete-todo-servlet"/>
                </form>
                <br>
            <%};%>
        <%} else {%>
            <p>No todo</p>
        <%};%>
    </body>
</html>
