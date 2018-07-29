<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find a Food</title>
</head>
<body>
<h1>Search for a food in our database!!!</h1>
<form action="findfood" method="post">
    <h2>Search for a food by ID</h2>
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
<form action="findfoodbykeyword" method="post">
    <h2>Search for a food by keyword!!!</h2>
    <p>
        <label for="keyword">Keyword</label>
        <input id="keyword" name="keyword" value="${fn:escapeXml(param.keyword)}">
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
<h1>Matching Food</h1>
<table border="1">
    <tr>
        <th>FoodId</th>
        <th>Description</th>
        <th>NitrogenFactor</th>
        <th>ProteinFactor</th>
        <th>FatFactor</th>
        <th>CarbohydrateFactor</th>
    </tr>


     <c:forEach items="${foods}" var="food" >
        <tr>
            <td><c:out value="${food.getFoodId()}" /></td>
            <td><c:out value="${food.getDescription()}" /></td>
            <td><c:out value="${food.getNitrogenFactor()}" /></td>
            <td><c:out value="${food.getProteinFactor()}" /></td>
            <td><c:out value="${food.getFatFactor()}" /></td>
            <td><c:out value="${food.getCarbohydrateFactor()}" /></td>
           
        </tr>
     </c:forEach>
</table>
</body>
</html>
