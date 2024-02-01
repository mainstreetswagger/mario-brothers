<%--
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
<div>
    <button class="btn-primary" type="button" >Order</button>
</div>
<div>
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
                <button class="btn btn-outline-secondary" type="button" onclick="decrement(<%=meals[i].getId()%>)">-</button>
                <span class="form-control" name="<%=meals[i].getId()%>">0</span>
                <button class="btn btn-outline-secondary" type="button" onclick="increment(<%=meals[i].getId()%>)">+</button>
            </div>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
</div>
<script type="text/javascript">
    function increment(id) {
        let increment = document.getElementsByName(id);
        increment[0].textContent++;
    }
    function decrement(id) {
        let decrement = document.getElementsByName(id);
        if(decrement[0].textContent > 0)
            decrement[0].textContent--;
    }
</script>
</body>
</html>