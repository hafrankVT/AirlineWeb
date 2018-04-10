<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>World Adventures Airlines</title>
<link rel="stylesheet" href="resources/css/normalize.css">
<link rel="stylesheet" href="resources/css/theme.css">
</head>
<body>
	<h2>Welcome to World Adventures Airlines!</h2>
	<div class="container">
		<div class="title">New Passenger Form</div>

		<%
			if (request.getAttribute("errors") != null) {
		%>
		<!-- This will only be displayed if there are errors -->
		<!-- Could probably use tags like this to just add a tag or label outside the actual form, or change the CSS to highlight it. -->
		<fieldset>
			<legend>Errors</legend>
			<ul>
				<%
					if (request.getAttribute("firstNameError") != null) {
				%>
				<li class="error">First Name Error</li>
				<%
					}
				%>

				<%
					if (request.getAttribute("lastNameError") != null) {
				%>
				<li class="error">Last Name Error</li>
				<%
					}
				%>

				<%
					if (request.getAttribute("dateFormatError") != null) {
				%>
				<li class="error">Date of Birth Invalid</li>
				<%
					}
				%>

			</ul>
		</fieldset>
		<%
			}
		%>

		<fieldset>
			<legend>Passenger Details</legend>
			<form action="AddPassenger" method="post">
				<div class="inputField">
					<label for="first-name" class="inputlabel">First Name:</label> <input
						name="first-name" type="text" />
				</div>

				<div class="inputField">
					<label for="last-name" class="inputlabel">Last Name:</label> <input
						name="last-name" type="text" />
				</div>

				<div class="inputField">
					<label for="dob" class="inputlabel">Date of Birth:</label> <input
						name="dob" type="text" />
				</div>

				<div class="inputField">
					<label for="gender" class="inputlabel">Gender:</label> <select
						name="gender">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</div>

				<div class="inputField" id="submitField">
					<input type="submit" id="submitBtn" value="Add new passenger" />
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>