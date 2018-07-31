<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<br/>
<div id="addFood" class="btn btn-default"><a href="AddFood.jsp">Add your own food to our databse</a></div>
<div id="deleteFood" class="btn btn-default"><a href="DeleteFood.jsp">Delete food from the database</a></div>
<div id="findFood" class="btn btn-default"><a href="FindFood.jsp">Find food from the database</a></div>
<div id="updateFood" class="btn btn-default"><a href="UpdateFood.jsp">Update the food description</a></div>
<div id="clearList" class="btn btn-default"><form action="clearlist" method="get"><button>Clear the list</button></form></div>
<br/>
<div class="jumbotron">
<h1>Search for the Nutrition Value in our database!!!</h1>
</div>

<div class="jumbotron">
<h1>Customer List</h1>
</div>
<table border="1" class="table table-striped">
    <tr>
        <th>FoodId</th>
        <th>Food Description</th>
        <th>Amount/100g</th>
        <th>Protein(g)</th>
        <th>Fat(g)</th>
        <th>Carbohydrate(g)</th>
        <th>Calorie(kCal)</th>
    </tr>


     <c:forEach items="${foodNutrientList}" var="foodNutrient" >
        <tr>
            <td><c:out value="${foodNutrient.getFood().getFoodId()}" /></td>
            <td><c:out value="${foodNutrient.getFood().getDescription()}" /></td>
            <td><c:out value="${foodNutrient.getAmount()}" /></td>
            <td><c:out value="${foodNutrient.getProtein()}" /></td>
            <td><c:out value="${foodNutrient.getFat()}" /></td>
            <td><c:out value="${foodNutrient.getCarbohydrate()}" /></td>
            <td><c:out value="${foodNutrient.getCalorie()}" /></td>
        </tr>
     </c:forEach>
     
        <tr>
            <td><c:out value="Total" /></td>
            <td></td>
            <td><c:out value="${totalAmount}" /></td>
            <td><c:out value="${totalProtein}" /></td>
            <td><c:out value="${totalFat}" /></td>
            <td><c:out value="${totalCarbohydrate}" /></td>
            <td><c:out value="${totalCalorie}" /></td>
        </tr>
</table>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
