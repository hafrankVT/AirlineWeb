package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightService;

/**
 * Servlet implementation class AddPassengerToFlight
 */
@WebServlet("/AddPassengerToFlight")
public class AddPassengerToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FlightService fs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPassengerToFlight() {
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//response.getWriter().append("This is the AddPassengerToFlight Servlet.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String fid = request.getParameter("fid");
		String pid = request.getParameter("pid");
		PrintWriter out = response.getWriter();
		
		out.println("Adding passenger " + pid + " to flight " + fid);
		fs.addPassengerToFlight(pid, fid);
		
		response.sendRedirect("Flights");
		
	}

}