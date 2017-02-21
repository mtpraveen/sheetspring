<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
<body>
<p align="right"><jsp:include page="search.jsp"></jsp:include>
<div class="container">
	<!-- <a href="sheetDetails"><h3>View Sheet Data</h3></a> -->
	<center><h3>List of Engineers</h3></center>
	<!-- <table width="500" height="600" bordercolor="black" cellpadding="20px" bgcolor="FloralWhite"
		cellspacing="20px" align="center" border="1"> -->
		 <table class="table table-hover">
    <thead>
		<tr>
			
			<th>Picture</th>
			<th>Name</th>
			<th>Details</th>
		</tr>
		  </thead>
  
				<!-- Iterating over the list sent from Controller -->
		<c:forEach var="data" items="${sheetDetails}">
	  <tbody>
			 <tr class="success">
			<td><img src="${data.profilePicture}" alt="No Image....!" height="200" width="200"></td>
			<td>${data.name}</td>
			<td><a href="<c:url value="sheetList"/>?email=${data.email}"><button type="button" class="btn btn-default">View Details</button></a></td>
			</tr>
		</c:forEach>
		  </tbody>
		 </table>
		   </div>
		   <script>
$(document).ready(function(){
    $(".btn").click(function(){
        $(this).button('loading');
    });   
});
</script>
</body>
</html>