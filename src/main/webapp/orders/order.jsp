<%@ page import="dbcontext.models.Order" %>
<%@ page import="models.MealReport" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <h1>Order <%=order.getId()%></h1>
    <h1><%=order.getCreatedAt()%></h1>
    <h1><%=order.getTotal()%></h1>
<div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Quantity</th>
            <th scope="col"></th>
            <th scope="col">Price</th>
            <th scope="col">Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <%MealReport[] reports = (MealReport[])request.getAttribute("reports");%>
        <%double total = 0;%>
        <%for(int i = 0; i < reports.length; i++) {%>
        <%double subtotal = reports[i].getCount()*reports[i].getPrice();%>
        <%total += subtotal;%>
        <tr>
            <th scope="col"><%=reports[i].getMealName()%></th>
            <td><%=reports[i].getCount()%></td>
            <td>X</td>
            <td><%=reports[i].getPrice()%>$</td>
            <td><%=subtotal%></td>
        </tr>
        <%}%>
        <tr>
            <th>Total</th>
            <td></td>
            <td></td>
            <td></td>
            <td><%=total%>$</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
