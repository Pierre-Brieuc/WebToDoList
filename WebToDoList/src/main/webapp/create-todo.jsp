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
        <form action="instructor-controller-servlet">
            <input type="submit" value="edit" formmethod="put"/>
            <input type="submit" value="delete" formmethod="delete"/>
            <input type="submit" value="create new todo" formmethod="post"/>
        </form>
    </body>
    <a href="">Back to the list</a>
</html>
