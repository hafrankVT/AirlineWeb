package com.airline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Gender;
import com.airline.models.Passenger;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger") // This is the URL path to our servlet.
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPassenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Forward to the .jsp to display the passenger add form.

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
		view.forward(request, response); // This forwards the request to the add_passenger.jsp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// This will allow us to process the submitted form info.

		request.setAttribute("errors", false);

		Passenger p = new Passenger();

		String firstName = request.getParameter("first-name");
		if (firstName.length() == 0) {
			// Print error to system.out (or logger?) and set an error flag in the
			// attributes.
			System.out.println("Empty first name error.");
			request.setAttribute("errors", true);
			request.setAttribute("firstNameError", true);
		} else {
			p.setFirstName(firstName);
		}

		String lastName = request.getParameter("last-name");

		if (lastName.length() == 0) {
			// Print error to system.out (or logger?) and set an error flag in the
			// attributes.
			System.out.println("Empty last name error.");
			request.setAttribute("errors", true);
			request.setAttribute("lastNameError", true);
		} else {
			p.setLastName(lastName);
		}

		String dob_raw = request.getParameter("dob");
		String dob_pattern = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$"; // RegExp for checking that date is in MM/DD/YYY format
		Pattern r = Pattern.compile(dob_pattern);
		System.out.println("New Passenger Information:");
		System.out.println("First Name:" + firstName);
		System.out.println("Last Name: " + lastName);

		Matcher m = r.matcher(dob_raw);
		if (m.find()) {
			// Split the DoB string into individual parts.
			String dobArray[] = dob_raw.split("\\/");

			// Assign the different parts of the array to individual Strings.
			String month = dobArray[0];
			String day = dobArray[1];
			String year = dobArray[2];

			// Convert to Date object
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			cal.set(Calendar.MONTH, Integer.parseInt(month));
			cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

			// Now, get the date from the calendar
			Date dob = cal.getTime();
			System.out.println(dob);
			p.setDob(dob);
		} else {
			System.out.println("Date Formatting Error.");
			request.setAttribute("errors", true);
			request.setAttribute("dateFormatError", true);
		}

		// From our form, gender shouldn't be empty so no need to verify (For this
		// project. Best practice: Verify EVERYTHING.)
		String gender = request.getParameter("gender");
		p.setGender(Gender.valueOf(gender));

		System.out.println("Gender: " + gender);

		// NOW, check for errors (if flag == true).
		if ((boolean) request.getAttribute("errors")) {
			// Forward back to the form jsp
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
			view.forward(request, response);
		} else {
			System.out.println("New Passenger Object: " + p);

			// Here we mess with ServletContext. ServletContext is a call to the application
			// scope.
			ServletContext sc = this.getServletContext();

			//Synchronized block which will allow only one user at a time to use this section of code.
			//Prevents issues with both users pulling a list, adding a passenger and overwriting the values.
			synchronized (this) {
				ArrayList<Passenger> pList = (ArrayList<Passenger>) sc.getAttribute("passengers");
				pList.add(p);

				sc.setAttribute("passengers", pList); //Add the user to the list

				// Redirect to the results page.
				// This will create a brand new request, so we are no longer in Request scope!
				// Need to create a new servlet for this to redirect to!
			}
			response.sendRedirect("");
		}

	}

}
