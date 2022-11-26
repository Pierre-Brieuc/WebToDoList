<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
    <form action="sign-up-servlet" method="post">
        Username: <input type="text" name="name" />
        Password:<input type="password" name="password" />
        Role:<input type="select" name="role" />
        <input type="submit" value="OK" />
    </form>
        <a href="test-servlet">Hello Servlet</a>
    </body>
</html>
