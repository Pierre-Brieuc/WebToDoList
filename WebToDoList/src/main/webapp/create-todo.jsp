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
        <title>${name}/Create</title>
        <link href="css/forms.css" rel="stylesheet" type="text/css">
    </head>

    <% String errorMessage = (String)request.getAttribute("ERROR"); %>

    <body>
    <center>
        <div>
            <h1>Create your todo</h1>
        </div>
        <form method="POST" action="create-todo-servlet">
            <input type="hidden" name="name" value="${name}">
            <table align="center">
                <tr>
                    <td>Description :</td>
                    <td><input type="text" name="description" value=""></td>
                </tr>
                <!-- ${ERROR}-->
                <%if (errorMessage != null){%>
                <p><%=errorMessage%></p>
                <%};%>
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                    <td><input type="reset" value="Cancel"></td>
                </tr>
            </table>
        </form>
        <form action="instructor-controller-servlet" method="get">
            <input type="hidden" name="name" value="${name}">
            <input type="submit" value="Back">
        </form>
    </center>
    </body>
</html>
