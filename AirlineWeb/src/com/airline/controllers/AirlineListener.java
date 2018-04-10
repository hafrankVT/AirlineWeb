package com.airline.controllers;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.airline.models.Passenger;

/**
 * Application Lifecycle Listener implementation class AirlineListener
 *
 */
@WebListener //This Annotation is what makes a listener.
public class AirlineListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AirlineListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent startup)  { 
         //This is a listener to run when the application is first started.
    	ServletContext sc = startup.getServletContext();
    	
    	//Double check to make sure there is no arrayList still floating around.
    	ArrayList<Passenger> pList = (ArrayList<Passenger>) sc.getAttribute("passengers");
    	if (pList == null) {
    		System.out.println("There is no passenger list yet. Creating one, foo.");
    		pList = new ArrayList<Passenger>();
    	}
    }
	
}
