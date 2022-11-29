<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <% String errorMessage = (String)request.getAttribute("ERROR"); %>
    <body>
    <!-- ${ERROR}-->
    <%if (errorMessage != null){%>
        <%=errorMessage%>
    <%};%>
    <form action="login-servlet" method="post">
            Username: <input type="text" name="name" />
            Password:<input type="password" name="password" />
            <input type="submit" value="OK" />
        </form>
        <a href="registration-servlet">Registration ?</a>
    </body>
</html>
