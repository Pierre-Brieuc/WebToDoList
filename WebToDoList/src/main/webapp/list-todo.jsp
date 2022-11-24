<%--
  Created by IntelliJ IDEA.
  User: PierreB
  Date: 24/11/2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="com.example.webtodolist.ToDo" %>
<html>
    <head>
        <title>Web Student Book</title>
    </head>
        <% List<ToDo> theStudents = (List<ToDo>)request.getAttribute("STUDENT_LIST"); %>
    <body>
        <!-- ${STUDENT_LIST}-->
        <%= theStudents %>
    </body>
</html>
