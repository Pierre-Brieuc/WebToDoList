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
        <title>Student ${name}</title>
        <link href="css/student.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <center>
            <div class="titre">
                <span class="titre1">Bienvenue M./Mme ${name}</span>
            </div>
            <form class="logout" action="logout-servlet" method="get">
                <input type="submit" value="Log out"/>
            </form>

            <div id="wrapper">
                <div id="header">
                    <h2 class="liste">List of todos</h2>
                </div>
            </div>
            <div id="container">
                <div id="content">
                    <table>
                        <tr>
                            <th class="id">ID </th>
                            <th class="description">Description</th>
                        </tr>
                        <c:forEach var="tempTodo" items="${TODO_LIST}" >
                            <tr>
                                <td><input type="hidden" class="input-instructor" name="id" value="${tempTodo.id_todo}"/><center>${tempTodo.id_todo}</center></td>
                                <td><input type="hidden" class="description1" name="description" value="${tempTodo.description}"/><center>${tempTodo.description}</center></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </center>
    </body>
</html>

