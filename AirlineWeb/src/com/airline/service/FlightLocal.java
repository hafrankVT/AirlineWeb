package com.airline.service;

import javax.ejb.Local;

@Local
public interface FlightLocal {

	public Integer getId();

	public void setId(Integer id);

	public String getFrom();

	public void setFrom(String from);

	public String getTo();

	public void setTo(String to);

	public Integer getPrice();

	public void setPrice(Integer price);

	public Integer getNumSeats();

	public void setNumSeats(Integer numSeats);

	public String getAirplaneModel();

	public void setAirplaneModel(String airplaneModel);

	public String toString();

}
