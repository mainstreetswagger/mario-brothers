<%--
  Created by IntelliJ IDEA.
  User: KWalker
  Date: 1/29/2024
  Time: 12:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dbcontext.models.Meal" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title><%=request.getAttribute("title").toString()%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <div class="m-2 d-flex align-items-center justify-content-center">
    <div class="container m-2">
        <a href="login.jsp" class="btn btn-danger">Logout</a>
        <div><h2>Mario Brothers</h2></div>
        <div class="w-75 border border-dark rounded">
            <div class="float-lg-end m-2">
                <button class="btn btn-success" type="button" onclick="postMeals()">Order!</button>
        <div><h2><%=request.getAttribute("header").toString()%></h2></div>
        <div class="border border-dark rounded">
            <div class="d-flex justify-content-between">
                <div class="m-2">
                    <a href="/my-orders">My Orders</a>
                </div>
                <div class="m-2">
                    <button class="btn btn-success" type="button" onclick="postMeals()">Order!</button>
                </div>
            </div>
                <table class="table">
                <thead>
                <tr>
                    <th class="col-1">#</th>
                    <th class="col-3">Name</th>
                    <th class="col-1">Price</th>
                    <th class="col-2">Quantity</th>
                </tr>
                </thead>
                <tbody>
                <%ArrayList<Meal> meals = (ArrayList<Meal>)request.getAttribute("meals");%>
                <%for(int i = 0; i < meals.size(); i++) {%>
                <tr>
                    <th class="col-1"><%=meals.get(i).getId()%></th>
                    <td class="col-3"><%=meals.get(i).getName()%></td>
                    <td class="col-1"><%=meals.get(i).getPrice()%>$</td>
                    <td class="col-2">
                        <div class="input-group form-group">
                            <button class="btn btn-outline-secondary" type="button" onclick="decrement(<%=meals.get(i).getId()%>)">-</button>
                            <span class="form-control" id=<%=meals.get(i).getId()%>>0</span>
                            <button class="btn btn-outline-secondary" type="button" onclick="increment(<%=meals.get(i).getId()%>)">+</button>
                        </div>
                    </td>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div></div>
<script type="text/javascript">
    function increment(id) {
        let increment = document.getElementById(id);
        increment.textContent++;
    }

    function decrement(id) {
        let decrement = document.getElementById(id);
        if(decrement.textContent > 0)
            decrement.textContent--;
    }

    function fetchSelectedMeals() {
        let spanElements = document.getElementsByTagName("span");
        const myMeals = [];
        for(let i = 0; i < spanElements.length; i++) {
            let count = parseInt(spanElements[i].textContent);
            if(count > 0) {
                let id = parseInt(spanElements[i].id);
                myMeals.push({mealId: id, count: count});
            }
        }
        return myMeals;
    }

    async function postMeals() {
        const myMeals = [...fetchSelectedMeals()];
        const data = JSON.stringify(myMeals);
        const url = window.location.href;

        let response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: data,
        }).then(response => {
            if (response.redirected) {
                window.location.replace(response.url);
            }
        }).catch(function(err) {
            console.info(err + " url: " + url);
        });
    }
</script>
</body>
</html>