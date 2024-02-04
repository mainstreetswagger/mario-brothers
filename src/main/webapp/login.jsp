<%--
  Created by IntelliJ IDEA.
  User: Zaur
  Date: 02-Feb-24
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>

<h1>Login</h1>

<form action="LoginServlet" method="post">
    <label for="loginUsername">Username:</label>
    <input type="text" id="loginUsername" name="loginUsername" required><br>

    <label for="loginPassword">Password:</label>
    <input type="password" id="loginPassword" name="loginPassword" required><br>

    <input type="submit" value="Login">
</form>

<%--<c:if test="${not empty param.error}">--%>
<%--    <p style="color: red;">Invalid username or password. Please try again.</p>--%>
<%--</c:if>--%>

<p>Not registered? <a href="register.jsp">Register</a></p>

</body>
</html>
