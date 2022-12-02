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
    </head>
    <body>
    <div align="center">
        <h1>Create your todo</h1>
    </div>
        <form method="POST" action="create-todo-servlet">
            <input type="hidden" name="name" value="${name}">
            <table align="center">
                <tr>
                    <td>Description :</td>
                    <td><input type="text" name="description" value=""></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Valider"></td>
                    <td><input type="reset" value="Annuler"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
