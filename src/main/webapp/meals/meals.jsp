<%@ page import="models.Meal" %><%--
  Created by IntelliJ IDEA.
  User: KWalker
  Date: 1/29/2024
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Meal" %>
<html>
<head>
    <title>Meals</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% Meal[] meals = (Meal[])request.getAttribute("meals");%>
    <%for(int i = 0; i < meals.length; i++) {%>
    <tr>
        <th scope="col"><%=meals[i].getId()%></th>
        <td><%=meals[i].getName()%></td>
        <td><%=meals[i].getPrice()%>$</td>
        <td>
            <div class="input-group form-group">
                <button class="btn btn-outline-secondary" type="button" name="minusButton_<%=meals[i].getId()%>">-</button>
                <input type="text" value="0" min="0" max="100" class="form-control" name="quantity_<%=meals[i].getId()%>">
                <button class="btn btn-outline-secondary" type="button" name="plusButton_<%=meals[i].getId()%>">+</button>
            </div>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>