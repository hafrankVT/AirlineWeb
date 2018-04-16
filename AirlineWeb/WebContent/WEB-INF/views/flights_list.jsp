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
			<th>Id</th>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Seating</th>
			<th>Number of Pilots</th>
			<th>Pilot Names</th>
		</tr>

		<%
			List<Flight> fList = (List<Flight>) request.getAttribute("flight_list");

			for (int i = 0; i < fList.size(); i++) {
		%>
		<tr>
			<td><%=fList.get(i).getId() %></td>
			<td><%=fList.get(i).getFlightOrigin()%></td>
			<td><%=fList.get(i).getFlightDestination()%></td>
			<td><%=fList.get(i).getFlightTime()%></td>
			<td><%=fList.get(i).getPrice()%></td>
			<td><%=fList.get(i).getAirplaneDetail().getPlaneMake() + " "
						+ fList.get(i).getAirplaneDetail().getPlaneModel()%></td>
			<td><%=fList.get(i).getAirplaneDetail().getSeatingCapacity()%></td>
			<td>
				<%
					if (fList.get(i).getPilots().size() != 0) {
				%> 
					<%=fList.get(i).getPilots().size()%> pilots 
				<%
			 	} else {
			 		%> No pilots assigned yet. <%
			 	}
			 %>
			</td>
			<td>
				<%
					if (fList.get(i).getPilots().size() != 0) {
						List<Pilot> pList = fList.get(i).getPilots();
						for (int j = 0; j < pList.size(); j++){
						%>
							<%=pList.get(j).getLastName() + ", " + pList.get(j).getFirstName() %>
						<%
						} //for
					} else {
				%>
				No Pilots Yet
				<%} %>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>