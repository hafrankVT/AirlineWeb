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
		<div class="title">Add a passenger!</div>
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