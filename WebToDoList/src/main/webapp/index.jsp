<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login / Registration</title>
        <link href="css/index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Welcome, please login or register</h1>
        <form method="get">
            <input type="submit" value="Login" formaction="login-servlet" id="login"/>
            <input type="submit" value="Register" formaction="registration-servlet"/>
        </form>
    </body>
</html>
