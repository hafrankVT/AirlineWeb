package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {

	@PersistenceContext(unitName="airline")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(Pilot p) {
    	em.persist(p);
    }

    public void addNewPilotToFlight(Pilot p, String flightId) {

    	// Persist new pilot
    	addPilot(p);
		// Query can be complex, let's check it out.
		// Create a named Query
		// First argument is the name of our named query (What we put in the annotation)
		// Second is the actual Class type of the object.
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);

		// Set the parameters for the query
		fQuery.setParameter("id", Integer.parseInt(flightId));

		// Assign the query result to a flight object.
		Flight f = fQuery.getSingleResult();

		// Now we should have both a pilot and a flight object.
		// Add Flight to the Pilot object, and persist?

		List<Pilot> pList = f.getPilots(); // This is the listing of pilots currently associated with the flight
		pList.add(p);
		f.setPilots(pList);
		p.setFlightForPilot(f);

		// This adds the pilots to a list, updates the list on the Flight.
	}
}
