<%--
  Created by IntelliJ IDEA.
  User: Zaur
  Date: 02-Feb-24
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
</head>
<body>

<h1>Register</h1>

<form action="RegisterServlet" method="post">
    <label for="registerUsername">Username:</label>
    <input type="text" id="registerUsername" name="registerUsername" required><br>

    <label for="registerPassword">Password:</label>
    <input type="password" id="registerPassword" name="registerPassword" required><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br>

    <input type="submit" value="Register">
</form>

<%--<c:if test="${not empty param.error}">--%>
<%--    <p style="color: red;">Username is already taken. Please choose another one.</p>--%>
<%--</c:if>--%>

<%--<c:if test="${not empty param.success}">--%>
<%--    <p style="color: green;">Registration successful! You can now <a href="login.jsp">login</a>.</p>--%>
<%--</c:if>--%>

<p>Already registered? <a href="login.jsp">Login</a></p>

</body>
</html>

