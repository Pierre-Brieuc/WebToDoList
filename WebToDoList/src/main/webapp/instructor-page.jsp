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
        <form action="instructor-controller-servlet">
            <input type="submit" value="create new todo" formmethod="post"/>
        </form>
        <!-- ${TODO_LIST}-->
        <%for (int i=0; i<theTodos.size(); i++) {%>
            Todo <%= theTodos.get(i).getId_todo() %>
            <%= theTodos.get(i).getDescription() %>
            <form action="instructor-controller-servlet">
                <input type="submit" value="edit" formmethod="put"/>
                <input type="submit" value="delete" formmethod="delete"/>
            </form>
            <br>
        <%};%>
    </body>
</html>
