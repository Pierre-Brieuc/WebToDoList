<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <body>
        <div align="center">
            <h1>Edit your todo</h1>
        </div>
        <form method="POST" action="edit-todo-servlet">
            <table align="center">
                <tr>
                    <td align="center">To do ${id}</td>
                </tr>
                <tr>
                    <td>Description :</td>
                    <td><input type="text" name="password" value="${description}"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Valider"></td>
                    <td><input type="reset" value="Annuler"></td>
                </tr>
            </table>
            <a href="registration-servlet">Registration ?</a>
        </form>
    </body>
</html>
