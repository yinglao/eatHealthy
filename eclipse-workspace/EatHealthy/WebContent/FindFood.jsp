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
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<br/>
<div id="addFood" class="btn btn-default"><a href="AddFood.jsp">Add your own food to our databse</a></div>
<div id="deleteFood" class="btn btn-default"><a href="DeleteFood.jsp">Delete food from the database</a></div>
<div id="findFood" class="btn btn-default"><a href="FindFood.jsp">Find food from the database</a></div>
<div id="updateFood" class="btn btn-default"><a href="UpdateFood.jsp">Update the food description</a></div>
<br/>
<div class="jumbotron">
<h1>Search for a food in our database</h1>
</div>
<form class="form-horizontal" action="findfood" method="post">
    <h2>Search for a food by ID</h2>
    <div class="form-group">
        <label for="foodid" class="col-sm-2 control-label">FoodId</label>
                <div class="col-sm-10">
        			<input id="foodid" class="form-control" name="foodid" value="${fn:escapeXml(param.foodid)}" placeholder="FoodId">
        		</div>
    </div>
    <div class="form-group">
    	 <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Find Food</button>
        </div>
    </div>
        <br/><br/><br/>
        <span class="label label-warning" id="successMessage"><b>${messages.success}</b></span>
</form>
<form  class="form-horizontal" action="findfoodbykeyword" method="post">
    <h2>Search for a food by keyword</h2>
    <div class="form-group" >
        <label for="keyword" class="col-sm-2 control-label">Keyword</label>
                <div class="col-sm-10">
        			<input id="keyword" class="form-control" name="keyword" value="${fn:escapeXml(param.keyword)}" placeholder="Keyword">
        		</div>
    </div>
    <div class="form-group">
        	<div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Find Food</button>
        </div>
    </div>
        <br/><br/><br/>
        <span class="label label-warning" id="successMessage"><b>${messages.success}</b></span>

</form>
<div class="jumbotron">
<h1>Matching Food</h1>
</div>
<table border="1" class="table table-striped">
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
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
