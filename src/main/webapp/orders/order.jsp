<%@ page import="dbcontext.models.Order" %>
<%@ page import="dbcontext.enums.OrderStatus" %>
<%@ page import="models.MealReport" %>
<%@ page import="dbcontext.models.User" %>
<%@ page import="java.util.List" %><%--
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
    <%User user = (User)request.getAttribute("user");%>
    <title>Order#<%=order.getId()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="m-2 d-flex align-items-center justify-content-center">
<div class="card mb-3" style="max-width: 36rem;">
    <div class="card-header">
        <div class="d-flex justify-content-between">
            <div>
                <a href="/my-orders">My Orders</a>
            </div>
            <div>
                <a href="/meals">Meals Menu</a>
            </div>
        </div>
        <div>
            <p>Order: <%=order.getId()%> - <%=OrderStatus.values()[order.getStatus() - 1].name()%></p>
        </div>
    </div>
    <div class="card-body">
        <h5 class="card-title">Date: <%=order.getCreatedAt()%></h5>
        <h5 class="card-title">Customer: <%=user.getUserName()%></h5>
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
            <%List<MealReport> reports = (List<MealReport>)request.getAttribute("reports");%>
            <%for(int i = 0; i < reports.size(); i++) {%>
            <%double subtotal = reports.get(i).getCount()*reports.get(i).getPrice();%>
            <tr>
                <th scope="col"><%=reports.get(i).getMealName()%></th>
                <td><%=reports.get(i).getCount()%></td>
                <td>X</td>
                <td><%=reports.get(i).getPrice()%>$</td>
                <td><%=subtotal%></td>
            </tr>
            <%}%>
            <tr>
                <th>Total</th>
                <td></td>
                <td></td>
                <td></td>
                <td><%=order.getTotal()%>$</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
