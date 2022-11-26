<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 26/11/2022
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="sign-up-servlet" method="post">
            Username: <input type="text" name="name" />
            Password:<input type="password" name="password" />
            Role:<input type="select" name="role" />
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
