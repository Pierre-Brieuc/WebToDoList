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
    <title>Instructor ${name}</title>
    <link type="text/css" rel="stylesheet" href="css/instructor-page.css">
</head>

<body>
        <h1>Instructor ${name}</h1>
        <form action="logout-servlet" method="get">
            <input type="submit" value="Log out"/>
        </form>
        <form action="create-todo-servlet">
            <input type="hidden" name="name" value="${name}">
            <input type="submit" value="create new todo" formmethod="get"/>
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
                        <form>
                            <input type="hidden" name="name" value="${name}">
                            <td><input type="hidden" class="input-instructor" name="id" value="${tempTodo.id_todo}"/>${tempTodo.id_todo}</td>
                            <td><input type="hidden" class="input-instructor" name="description" value="${tempTodo.description}"/>${tempTodo.description}</td>
                            <td colspan="2"><input type="submit" value="Edit" formmethod="get" formaction="edit-todo-servlet"></td>
                            <td><input type="submit" value="Delete" formmethod="post" formaction="delete-todo-servlet"></td>
                        </form>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
