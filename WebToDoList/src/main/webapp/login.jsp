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
<div align="center">
  <h1>Veuillez vous identifier</h1>
</div>
<form method="POST" action="j_security_check">
  <table align="center">
    <tr>
      <td>Login :</td>
      <td><input type="text" name="j_username"></td>
    </tr>
    <tr>
      <td>Mot de passe :</td>
      <td><input type="password" name="j_password" value=""></td>
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
