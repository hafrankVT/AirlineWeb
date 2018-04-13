package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.CounterBean;
import com.airline.service.CounterStatefulBean;

/**
 * Servlet implementation class AddOne
 */
@WebServlet("/AddOne")
public class AddOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	CounterBean cb;
	
	@EJB
	CounterStatefulBean csb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		//Print and advance the SINGLETON bean
		out.println("The Current Count Value SINGLETON BEAN is : " + cb.getCount());
		out.println("The Current Count Value STATEFUL BEAN is : " + csb.getCount());
		
		cb.addOneToCount();
		csb.addOneToCount();
		
		out.println("The count was incremented by 1. Current value SINGLETON BEAN is : " + cb.getCount());
		out.println("The count was incremented by 1. Current value STATEFUL BEAN is : " + csb.getCount());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
