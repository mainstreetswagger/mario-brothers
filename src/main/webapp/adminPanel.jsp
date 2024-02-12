<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dbcontext.models.Order" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container m-2">
    <a href="login.jsp" class="btn btn-danger">Logout</a>
    <div><h2>Admin Panel</h2></div>
    <div class="my-3">
        <!-- Add buttons for changing order status and adding a new item to the menu -->
        <a href="AdminServlet?action=changeOrderStatus" class="btn btn-primary me-2">Change Order Status</a>
        <a href="addNewItem.jsp" class="btn btn-success">Add New Item to Menu</a>
    </div>
    <div class="w-75 border border-dark rounded">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>User ID</th>
                <th>Created At</th>
                <th>Total Price</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% ArrayList<Order> orders = (ArrayList<Order>)request.getAttribute("orders"); %>
            <% for (Order order : orders) { %>
            <tr>
                <td><%= order.getId() %></td>
                <td><%= order.getUserId() %></td>
                <td><%= order.getCreatedAt() %></td>
                <td><%= order.getTotal() %></td>
                <td>
                    <%
                        String statusText = "";
                        switch (order.getStatus()) {
                            case 1:
                                statusText = "New";
                                break;
                            case 2:
                                statusText = "In Progress";
                                break;
                            case 3:
                                statusText = "Ready";
                                break;
                            default:
                                statusText = "Unknown";
                        }
                    %>
                    <%= statusText %>
                </td>
                <td>
                    <!-- Add form for updating status -->
                    <form action="AdminServlet" method="POST">
                        <input type="hidden" name="orderId" value="<%= order.getId() %>">
                        <select name="status">
                            <option value="">Choose Status</option>
                            <option value="1">New</option>
                            <option value="2">In Progress</option>
                            <option value="3">Ready</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Update Status</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
