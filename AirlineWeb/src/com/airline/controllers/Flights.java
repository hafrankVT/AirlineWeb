package com.airline.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.airline.models.Flight;
import com.airline.service.FlightService;

/**
 * Servlet implementation class Flights
 */
@WebServlet("/Flights")
public class Flights extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	FlightService fs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Flights() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Flight> flightList = fs.getFlights();
		HttpSession session = request.getSession();
		session.setAttribute("flight_list", flightList);
		//Print the list!
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/flights_list.jsp");
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
