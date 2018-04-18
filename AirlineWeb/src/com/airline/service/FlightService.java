package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean
public class FlightService {

	/**
	 * Default constructor.
	 */
	public FlightService() {
		// TODO Auto-generated constructor stub
	}

	// Declare our persistence unit EntityManager
	@PersistenceContext(unitName = "airline")
	EntityManager em;

	public void addFlight(Flight f, Airplane a) {
		em.persist(f);
		//em.persist(a); -- Propogated and cascaded from Flight and saved automatically.

	}

	public void addPilotToFlight(String pilotId, String flightId) {

		// Query can be complex, let's check it out.
		// Create a named Query
		// First argument is the name of our named query (What we put in the annotation)
		// Second is the actual Class type of the object.
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

		// Set the parameters for the query
		fQuery.setParameter("id", Integer.parseInt(flightId));

		// Assign the query result to a flight object.
		Flight f = fQuery.getSingleResult();

		// Do the same for the Pilot object.
		TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class);
		pQuery.setParameter("id", Integer.parseInt(pilotId));
		Pilot p = pQuery.getSingleResult();

		// Now we should have both a pilot and a flight object.
		// Add Flight to the Pilot object, and persist?

		List<Pilot> pList = f.getPilots(); // This is the listing of pilots currently associated with the flight
		pList.add(p);
		f.setPilots(pList);
		p.setFlightForPilot(f);

		// This adds the pilots to a list, updates the list on the Flight.
	}
	
	public void addPassengerToFlight(String passengerId, String flightId) {
		
		//Use a "CRITERIA QUERY"
		// Need to get a passenger by ID. Did in a named query way before, but let's learn a new one.
		
		//This crazy ass query builder is done with classes and objects, so if there is an error it won't compile correctly.
		//This is opposite of the SQL queries where it's just a string, so its greater error chance at runtime.
		
		//This is for getting the passenger
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

		TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
		
		Passenger p = pQuery.getSingleResult();
		
		//This is for getting the flight
		CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);
		Root<Flight> fRoot = cqFlight.from(Flight.class);
		
		cqPassenger.select(pRoot).where(builder.equal(fRoot.get("id").as(Integer.class), flightId));

		TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
		
		Flight f = fQuery.getSingleResult();
		
		//Now we can do the associations.
		
		//DEBUG
		System.out.println("Attemping to add passenger to flight!");
		System.out.println("Passenger SHOULD be: " + p);
		System.out.println("FLIGHT SHOULD be: " + f);
		List<Passenger> pList = f.getPassengers();
		pList.add(p);
		
		List<Flight> fList = p.getFlights();
		fList.add(f);
	}

	public List<Flight> getFlights() {

		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f", Flight.class); //Some confusion still on the naming in these queries.
		List<Flight> result = query.getResultList(); //This executes the query and gets the result list.
		return result;
	}

}
