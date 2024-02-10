<%--
  Created by IntelliJ IDEA.
  User: KWalker
  Date: 1/29/2024
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dbcontext.models.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dbcontext.enums.OrderStatus" %>
<html>
<head>
    <title>Meals</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="m-2 d-flex align-items-center justify-content-center">
    <div class="container m-2">
        <div><h2>My Orders</h2></div>
        <div class="border border-dark rounded">
            <div class="m-2">
                <a href="/meals">Meals Menu</a>
            </div>
                <table class="table">
                <thead>
                <tr>
                    <th class="col-1">#</th>
                    <th class="col-3">Date</th>
                    <th class="col-1">Total</th>
                    <th class="col-2">Status</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <%ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders");%>
                <%for(int i = 0; i < orders.size(); i++) {%>
                <tr>
                    <th class="col-1"><%=orders.get(i).getId()%></th>
                    <td class="col-3"><%=orders.get(i).getCreatedAt()%></td>
                    <td class="col-1"><%=orders.get(i).getTotal()%>$</td>
                    <td class="col-1"><%=OrderStatus.values()[orders.get(i).getStatus() - 1].name()%></td>
                    <td class="col-2">
                        <div>
                            <a href="/orders?id=<%=orders.get(i).getId()%>">Details</a>
                        </div>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>