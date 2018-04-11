package com.airline.service;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class FlightService
 */
@Stateless // Declared as a stateless session bean
//@LocalBean // Declares this to be local, can only be accessed from applications on the same
			// environment.
			// cannot access from any other computers or anything.
		//Can be removed now that the flightLocal interface is what's being implemented.
public class FlightServiceStatelessBean implements FlightLocal {

	/**
	 * Default constructor.
	 */
	public FlightServiceStatelessBean() {
		// TODO Auto-generated constructor stub
	}

	private Integer id = 234659;
	private String from = "Los Angeles";
	private String to = "London";
	private Integer price = 400;
	private Integer numSeats = 250;
	private String airplaneModel = "Boeing 787";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(Integer numSeats) {
		this.numSeats = numSeats;
	}

	public String getAirplaneModel() {
		return airplaneModel;
	}

	public void setAirplaneModel(String airplaneModel) {
		this.airplaneModel = airplaneModel;
	}

	@Override
	public String toString() {
		return "FlightService [id=" + id + ", from=" + from + ", to=" + to + ", price=" + price + ", numSeats="
				+ numSeats + ", airplaneModel=" + airplaneModel + "]";
	}

}
