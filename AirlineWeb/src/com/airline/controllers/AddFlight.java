package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.FlightDestinations;
import com.airline.service.FlightService;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FlightService fs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		out.println(
				"Hello! You have reached this page's GET Method instead of the POST. So would you kindly make like a tree... and GTFO of here?");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//PERSONAL NOTE FOR THE NEXT TIME I THINK ABOUT THIS
		//Trying to do validation IN THIS BLOCK is super sloppy. Maybe have a bean, servlet or class
		//like validate.java which contains all our validation methods? If good, returns true, if false
		//maybe returns a session variable with error codes? Trying to validate in here is sloppy as fuck.
		// Make a Flight Object
		Flight f = new Flight();
		f.setFlightOrigin(FlightDestinations.The_Shire);
		f.setFlightDestination(FlightDestinations.Mordor);
		f.setPrice(400);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 4);
		cal.set(Calendar.HOUR_OF_DAY, 19);
		cal.set(Calendar.MINUTE, 0);

		Date flightTime = cal.getTime();
		f.setFlightTime(flightTime);

		// Make an Airplane Object
		Airplane a = new Airplane();
		a.setPlaneMake("Boeing");
		a.setPlaneModel("787");
		a.setSeatingCapacity(250);

		// Now that we have both objects, we need to connect them.
		// Set the Airplane into the airplaneDetail on flight.
		f.setAirplaneDetail(a);

		System.out.println("The Flight object is:" + f);
		System.out.println("The Airplane object is:" + a);

		fs.addFlight(f, a);
	}

}
