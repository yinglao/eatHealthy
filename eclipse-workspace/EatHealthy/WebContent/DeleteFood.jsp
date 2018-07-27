<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Food</title>
</head>
<body>
	<h1>Delete Food from Database</h1>
	<form action="deletefood" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label for="foodid">FoodId</label>
				<input id="foodid" name="foodid" value="${fn:escapeXml(param.foodid)}">
			</div>
		</p>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
<br/>
<div id="addFood"><a href="AddFood.jsp">Add your own food to our databse!!!</a></div>
<div id="deleteFood"><a href="DeleteFood.jsp">Delete food from the database!!!</a></div>
<div id="findFood"><a href="FindFood.jsp">Find food from the database!!!</a></div>
<div id="updateFood"><a href="UpdateFood.jsp">Update the food description!!!</a></div>
<br/>
</body>
</html>