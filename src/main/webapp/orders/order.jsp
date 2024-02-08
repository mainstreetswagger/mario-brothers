<%@ page import="dbcontext.models.Order" %><%--
  Created by IntelliJ IDEA.
  User: KWalker
  Date: 2/8/2024
  Time: 4:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%Order order = (Order)request.getAttribute("order");%>
    <title>Order <%=order.getId()%></title>
</head>
<body>
    <h1><%=order.getCreatedAt()%></h1>
    <h1><%=order.getTotal()%></h1>
</body>
</html>
