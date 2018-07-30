<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update a Food</title>
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
<h1>Update a Food</h1>
</div>
<form  class="form-horizontal" action="foodupdate" method="post">
    <div class="form-group">
        <label for="foodid" class="col-sm-2 control-label">FoodId</label>
                <div class="col-sm-10">
        			<input id="foodid" class="form-control" name="foodid" value="${fn:escapeXml(param.foodid)}" placeholder="FoodId">
        			</div>
    </div>
    <div class="form-group">
        <label for="description" class="col-sm-2 control-label">New Description</label>
                <div class="col-sm-10">
        			<input id="description" class="form-control" name="description" value="" placeholder="New Description">
        			</div>
    </div>
    <div class="form-group">
        	<div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Update Food</button>
        </div>
    </div>
</form>
<br/><br/>
<p>
    <span class="label label-warning" id="successMessage"><b>${messages.success}</b></span>
</p>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>