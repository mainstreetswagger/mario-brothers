<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Menu Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container m-2">
    <div class="d-flex justify-content-between mb-3">
        <a href="login.jsp" class="btn btn-danger">Logout</a>
    </div>
    <div><h2>Add New Menu Item</h2></div>
    <div class="w-50 border border-dark rounded p-3">
        <form action="AddMenuItemServlet" method="post">
            <div class="form-group">
                <label for="itemName">Item Name:</label>
                <input type="text" class="form-control" id="itemName" name="itemName" required>
            </div>
            <div class="form-group">
                <label for="itemPrice">Item Price:</label>
                <input type="number" class="form-control" id="itemPrice" name="itemPrice" step="0.01" required>
            </div>
            <div class="form-group">
                <label for="itemType">Item Type:</label>
                <select class="form-control" id="itemType" name="itemType">
                    <option value="">Choose type</option>
                    <option value="meal">Meal</option>
                    <option value="extra">Extra</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Item</button>
        </form>
    </div>
</div>
</body>
</html>
