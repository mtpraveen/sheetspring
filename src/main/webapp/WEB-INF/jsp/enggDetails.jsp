<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  </head>
<body>

<center><h1> ${data.name}</h1></center>
<table width="600" height="600" bordercolor="black" cellpadding="10px" bgcolor="FloralWhite"
	cellspacing="10px" align="center">
	
	
	<c:forEach var="data" items="${sheetinfo}">
	
	<tr>
			
		<td><center><img src="${data.profilePicture}" class="img-rounded" alt="No Image....!" height="243" width="241"/></center></td>
		</tr>
		<tr>
			<th>EnggId</th>
			<td>${data.enggId}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${data.name}</td>
		</tr>
		
		<tr> 
			<th>Email</th>
			<td>${data.email}</td>
		</tr>
		<tr>
			<th>Company</th>
			<td>${data.company}</td>
		</tr>
		<tr>
			<th>Hiring City</th>
			<td>${data.hiringCity}</td>
		</tr>
		<tr>
			<th>Domain</th>
			<td>${data.domain}</td>
		</tr>
		
		<tr> 
			<th>Engineer Status</th>
			<td>${data.enggStatus}</td>
		</tr>
		<tr>
			<th>Company Location</th>
			<td>${data.companyLocation}</td>
		</tr>
		<tr>
		<tr>

	<div class="container">		<td><a href="sheetDetails"><button type="button" class="btn btn-info">back</button></a></td>

		 </div></tr>
	</c:forEach>
</table>
 
</body>
</html>