<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, com.airline.models.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/jpaStyles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listing of Flights</title>
</head>
<body>
	<H1>List of Flights</H1>
	<table>
		<tr>
			<th>ID Number</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of Birth</th>
			<th>Gender</th>
		</tr>

		<%
			List<Passenger> pList = (List<Passenger>) request.getAttribute("passenger_list");

			for (int i = 0; i < pList.size(); i++) {
		%>
		<tr>
			<td><%=pList.get(i).getId() %></td>
			<td><%=pList.get(i).getFirstName()%></td>
			<td><%=pList.get(i).getLastName()%></td>
			<td><%=pList.get(i).getDob()%></td>
			<td><%=pList.get(i).getGender()%></td>
		</tr>
		<tr>
			<td colspan="4">No flight tickets yet.</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>