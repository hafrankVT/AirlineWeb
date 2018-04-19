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

import com.airline.models.Flight;
import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
public class PassengerService {

    /**
     * Default constructor. 
     */
    public PassengerService() {
        // TODO Auto-generated constructor stub
    }
    
    //Inject persistencecontext (unit name is what we specified in the persistence.xml file)
    @PersistenceContext(unitName="airline")
    private EntityManager em;
    
    public void addPassenger(Passenger p) {
    	em.persist(p); //This is what saves a passenger row into the table of the DB.
    }
    
    public void addTicketToPassenger(String fid, String pid) {
    	
    	//Use a "CRITERIA QUERY"
    			// Need to get a passenger by ID. Did in a named query way before, but let's learn a new one.
    			
    			//This crazy ass query builder is done with classes and objects, so if there is an error it won't compile correctly.
    			//This is opposite of the SQL queries where it's just a string, so its greater error chance at runtime.
    			
    			//This is for getting the passenger
    			CriteriaBuilder builder = em.getCriteriaBuilder();
    			
    			CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
    			Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
    			
    			cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), pid));

    			TypedQuery<Passenger> pQuery = em.createQuery(cqPassenger);
    			
    			Passenger p = pQuery.getSingleResult();
    			
    			//This is for getting the flight
    			CriteriaQuery<Flight> cqFlight = builder.createQuery(Flight.class);
    			Root<Flight> fRoot = cqFlight.from(Flight.class);
    			
    			cqFlight.select(fRoot).where(builder.equal(fRoot.get("id").as(Integer.class), fid));

    			TypedQuery<Flight> fQuery = em.createQuery(cqFlight);
    			
    			Flight f = fQuery.getSingleResult();
//    			
    			//Associate flight with pasenger
    			
    			List<Flight> fList = p.getFlights();
    			fList.add(f);
    			p.setFlights(fList);
    			
    			f.getPassengers().add(p);
    			
    	
    }
    
    public List<Passenger> getPassengers() {
    	TypedQuery<Passenger> pQuery = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
    	
    	List<Passenger> pList = pQuery.getResultList();
    	
    	return pList;
    }

}
