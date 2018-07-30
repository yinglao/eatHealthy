<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Nutrition</title>
</head>
<body>
<h1>Search for the Nutrition Value in our database!!!</h1>
<form action="findnutrientbyfoodid" method="post">
    <h2>Search the nutrition for a food by its ID</h2>
    <p>
        <label for="foodid">FoodId</label>
        <input id="foodid" name="foodid" value="${fn:escapeXml(param.foodid)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>

<br/>
<div id="addFood"><a href="AddFood.jsp">Add your own food to our databse!!!</a></div>
<div id="deleteFood"><a href="DeleteFood.jsp">Delete food from the database!!!</a></div>
<div id="findFood"><a href="FindFood.jsp">Find food from the database!!!</a></div>
<div id="updateFood"><a href="UpdateFood.jsp">Update the food description!!!</a></div>
<br/>
<h1>Matching Nutrition</h1>
<table border="1">
    <tr>
        <th>FoodId</th>
        <th>Nutrient Description</th>
        <th>Nutrient Value(unit/100g)</th>
        <th>Unit</th>
    </tr>


     <c:forEach items="${nutrientDataList}" var="nutrientData" >
        <tr>
            <td><c:out value="${nutrientData.getFood().getFoodId()}" /></td>
            <td><c:out value="${nutrientData.getNutrient().getDescription()}" /></td>
            <td><c:out value="${nutrientData.getNutrientValue()}" /></td>
            <td><c:out value="${nutrientData.getNutrient().getUnits()}" /></td>
        </tr>
     </c:forEach>
</table>
</body>
</html>
