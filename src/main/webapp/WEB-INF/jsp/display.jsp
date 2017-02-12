<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	Data Saved Successfully...
	<a href="sheetDetails"><h3>View Sheet Data</h3></a>

	<table bordercolor="black" cellpadding="10px" bgcolor="FloralWhite"
		cellspacing="10px" align="center" border="1">
		<tr>
			<th>EnggId</th>
			<th>Name</th>
			<th>Email</th>
			<th>Company</th>
			<th>Picture</th>
		</tr>
		<c:forEach var="data" items="${sheetDetails}">

			<tr>

				<td>${data.enggId}</td>


				<td>${data.name}</td>



				<td>${data.email}</td>



				<td>${data.company}</td>




				<td><img src="${data.faceBookURL}" alt="No Image....!"></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>