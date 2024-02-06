<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Pizza Order Register</h1>
    <form action="<%= request.getContextPath() %>/RegisterServlet" method="post">
        <table style="with: 80%">
            <tr>
                <td>User Name</td>
                <td><input type="text" name="userName" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
        <p>Already registered? <a href="login.jsp">Login</a></p>

    </form>
</div>
</body>
</html>