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

<!DOCTYPE html>
<html>
    <head>
        <title>Instructor ${name}</title>
        <link href="css/instructor.css" type="text/css" rel="stylesheet">
    </head>

    <body>
        <center>
            <div class="titre">
                <span class="titre1">Bienvenue M./Mme ${name}</span>
            </div>

            <div class="logout">
                <form action="logout-servlet" method="get">
                    <input type="submit" value="Log out"/>
                </form>
            </div>

            <div id="wrapper">
                <div id="header">
                    <h2 class="liste">List of todos</h2>
                </div>
            </div>
            <div id="container">
                <div id="content">
                    <table>
                        <tr>
                            <th class="id">ID</th>
                            <th class="description">DESCRIPTION</th>
                        </tr>
                        <c:forEach var="tempTodo" items="${TODO_LIST}" >
                            <tr>
                                <form>
                                    <input type="hidden" name="name" value="${name}">
                                    <td><input type="hidden" class="input-instructor" name="id" value="${tempTodo.id_todo}"/><center>${tempTodo.id_todo}</center></td>

                                    <td><input type="hidden" class="description1" name="description" value="${tempTodo.description}"/><center>${tempTodo.description}</center></td>

                                    <td colspan="2"><input type="submit" class="edit" value="Edit" formmethod="get" formaction="edit-todo-servlet"></td>
                                    <td><input type="submit" class="delete" value="Delete" formmethod="post" formaction="delete-todo-servlet"></td>
                                </form>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <form action="create-todo-servlet">
                <input type="hidden" name="name" value="${name}">
                <input class="newtodo" type="submit" value="create new todo" formmethod="get"/>
            </form>
        </center>
    </body>
</html>
