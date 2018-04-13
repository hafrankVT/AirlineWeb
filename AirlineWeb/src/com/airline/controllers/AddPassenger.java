package com.airline.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.FlightClass;
import com.airline.models.Gender;
import com.airline.models.Passenger;
import com.airline.service.PassengerService;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger") // This is the URL path to our servlet.
public class AddPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	PassengerService ps;

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
		
//		//Initialize form values (I know there has to be a better way of doing this.)
//		request.setAttribute("first_name", "");
//		request.setAttribute("last_name", "");
//		request.setAttribute("dob", "");
//
//		// Forward to the .jsp to display the passenger add form.
//
//		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
//		view.forward(request, response); // This forwards the request to the add_passenger.jsp
		
		//Temporarily removed the doGet stuff so we can play with persistence.
		
		Passenger p = new Passenger();
		//Arbitrary setting just to demonstrate.
		p.setFirstName("Harry");
		p.setLastName("Frank");
		
		//Create the Date object and set to a date.
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1985);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		
		Date dob = cal.getTime();
		
		p.setDob(dob);
		
		p.setGender(Gender.Male);
		p.setFlightClass(FlightClass.First);
		
		System.out.println("Created new Persistable Passenger");
		System.out.println(p);
		
		//This is where we persist by passing the passenger to the PassengerService entity which will then persist it.
		ps.addPassenger(p);
		
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
			request.setAttribute("first_name", "");
		} else {
			p.setFirstName(firstName);
			request.setAttribute("first_name", firstName);
		}

		String lastName = request.getParameter("last-name");

		if (lastName.length() == 0) {
			// Print error to system.out (or logger?) and set an error flag in the
			// attributes.
			System.out.println("Empty last name error.");
			request.setAttribute("errors", true);
			request.setAttribute("lastNameError", true);
			request.setAttribute("last_name", ""); //Store the attribute for error purposes.
		} else {
			p.setLastName(lastName);
			request.setAttribute("last_name", lastName); //Store the attribute for error purposes.
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
			request.setAttribute("dob", dob_raw);
		} else {
			System.out.println("Date Formatting Error.");
			request.setAttribute("errors", true);
			request.setAttribute("dateFormatError", true);
			request.setAttribute("dob", dob_raw);
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
