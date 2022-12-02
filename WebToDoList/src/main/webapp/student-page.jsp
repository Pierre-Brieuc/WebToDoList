<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 27/11/2022
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Student page</title>
    </head>
    <body>
        <h1>Student ${name}</h1>
        <form action="logout-servlet" method="get">
            <input type="submit" value="Log out"/>
        </form>

        <div id="wrapper">
            <div id="header">
                <h2>List of todos</h2>
            </div>
        </div>
        <div id="container">
            <div id="content">
                <table>
                    <tr>
                        <th>ID </th>
                        <th>Description</th>
                    </tr>
                    <c:forEach var="tempTodo" items="${TODO_LIST}" >
                        <tr>
                            <td><input type="hidden" class="input-instructor" name="id" value="${tempTodo.id_todo}"/>${tempTodo.id_todo}</td>
                            <td><input type="hidden" class="input-instructor" name="description" value="${tempTodo.description}"/>${tempTodo.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
