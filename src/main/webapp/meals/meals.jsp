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
                <span class="form-control" id=<%=meals[i].getId()%>>0</span>
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
        let increment = document.getElementById(id);
        increment.textContent++;
    }

    function decrement(id) {
        let decrement = document.getElementById(id);
        if(decrement.textContent > 0)
            decrement.textContent--;
        postMeals();
    }

    function fetchSelectedMeals() {
        let spanElements = document.getElementsByTagName("span");
        const myMeals = [];
        console.log("span elements " + spanElements.length);
        for(let i = 0; i < spanElements.length; i++) {
            let count = parseInt(spanElements[i].textContent);
            if(count > 0) {
                let id = parseInt(spanElements[i].id);
                myMeals.push({id: id, count: count});
            }
        }
        return myMeals;
    }

    async function postMeals() {
        const myMeals = [...fetchSelectedMeals()];
        const data = JSON.stringify({
            meals: myMeals
        });
        const url = 'http://localhost:8080/MarioBrothers_war_exploded/meals';
        let res = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });
        if (res.ok) {
            let ret = await res.json();
            return JSON.parse(ret.data);

        } else {
            return `HTTP error: ${res.status}`;
        }
    }

    async function doRequest() {

        let url = 'http://localhost:8080/MarioBrothers_war_exploded/meals';
        let data = {'name': 'John Doe', 'occupation': 'John Doe'};

        let res = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        });

        if (res.ok) {

            // let text = await res.text();
            // return text;

            let ret = await res.json();
            return JSON.parse(ret.data);

        } else {
            return `HTTP error: ${res.status}`;
        }
    }
</script>
</body>
</html>