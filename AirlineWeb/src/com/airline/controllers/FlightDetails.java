package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightLocal;

/**
 * Servlet implementation class FlightDetails
 */
@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private FlightService fs = new FlightService();
	// We don't do this because it will just get a new object, but will strip out
	// all the EJB features. So, we use annotations to inject.

	@EJB(beanName = "flightStateless")
	private FlightLocal fs;
	
	@EJB(beanName = "flightStateful")
	private FlightLocal fsStateful;
	//Now we are using an interface instead of the regular bean.
	



//	private FlightService fs = null;

	// This asks the container to give us an object from its "pool" of bean objects.

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlightDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("The flight details servlet has been called!");
		out.println("Flight Details: \n From: " + fs.getFrom() + " " + fs.getTo());

		/* This is example of using JNDI lookup instead of plain ol injection.
		 * try {
			// JNDI --> Java Name and Directory Interface holds references to resources on
			// the server, including EJB resources.
			Context context = new InitialContext(); // This holds the reference to JDNI
			Object fObj = context.lookup("java:global/AirlineWeb/FlightService!com.airline.service.FlightService");
			
			 * java:global is the global context. 
			 * AirlineWeb is the context root where our application is deployed FlightService is the name of the EJB class
			 * com.airline.service.FlightService is the fully qualified path to the object.
			 
			// Now need to cast the object to the FlightService object.
			fs = (FlightService) fObj;
		} catch (NamingException e) {
			System.out.println("Naming Exception has occured when trying to lookup FlightService Object.");
			e.printStackTrace();
		}

		if (fs != null) {
			out.println("Flight Details: \n From: " + fs.getFrom() + " " + fs.getTo());
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
