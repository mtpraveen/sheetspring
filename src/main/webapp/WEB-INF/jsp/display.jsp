<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body>
	Data Saved Successfully...
	<!-- <a href="sheetDetails"><h3>View Sheet Data</h3></a> -->
	<h3>List of Engineers</h3>
	<table bordercolor="black" cellpadding="10px" bgcolor="FloralWhite"
		cellspacing="10px" align="center" border="1">
		<tr>
			<th>EnggId</th>
			<th>Name</th>
			<th>Email</th>
			<th>Company</th>
			<th>Picture</th>
		</tr>
				<!-- Iterating over the list sent from Controller -->
		<c:forEach var="data" items="${sheetDetails}">

			<tr>

				<td>${data.enggId}</td>


				<td>${data.name}</td>



				<td>${data.email}</td>



				<td>${data.company}</td>




				<td><img src="${data.faceBookURL}" alt="No Image....!" height="243" width="241"></td>
			</tr>
		</c:forEach>
		 </table>
		   
</body>
</html> --%>
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
<div class="container">
	Data Saved Successfully...
	<!-- <a href="sheetDetails"><h3>View Sheet Data</h3></a> -->
	<center><h3>List of Engineers</h3></center>
	<!-- <table width="500" height="600" bordercolor="black" cellpadding="20px" bgcolor="FloralWhite"
		cellspacing="20px" align="center" border="1"> -->
		 <table class="table table-hover">
    <thead>
		<tr>
			
			<th>Name</th>
			<th>Picture</th>
			<th>Details</th>
		</tr>
		  </thead>
  
				<!-- Iterating over the list sent from Controller -->
		<c:forEach var="data" items="${sheetDetails}">
	  <tbody>
			 <tr class="success">
			<td><img src="${data.faceBookURL}" alt="No Image....!" height="200" width="200"></td>
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