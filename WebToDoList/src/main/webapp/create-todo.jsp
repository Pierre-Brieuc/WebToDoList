<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 29/11/2022
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="creator-todo-servlet">
            Write a description of the todo: <input type="text" value=""/>
            <input type="submit" value="create" formmethod="post"/>
        </form>
        <a href="/instructor-controller-servlet">Back to the list</a>
    </body>
</html>
