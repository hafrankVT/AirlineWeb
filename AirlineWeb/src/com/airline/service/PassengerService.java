package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public List<Passenger> getPassengers() {
    	TypedQuery<Passenger> pQuery = em.createQuery("Select p From Passenger p", Passenger.class);
    	
    	List<Passenger> pList = pQuery.getResultList();
    	
    	return pList;
    }

}
