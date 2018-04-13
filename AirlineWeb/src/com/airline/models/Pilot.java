package com.airline.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Pilot
 *
 */
@NamedQuery(name="Pilot.findById", query="SELECT p FROM Pilot P WHERE p.id = :id") //The name is completely arbitrary, but should probably have a solid convention.
@Entity

public class Pilot implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public Pilot() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer licenseNumber;
	
	@Enumerated(EnumType.STRING)
	private PilotRank pilotRank;
	
	@ManyToOne
	@JoinColumn(name="flight_fk")
	private Flight flightForPilot;

	public Flight getFlightForPilot() {
		return flightForPilot;
	}

	public void setFlightForPilot(Flight flightForPilot) {
		this.flightForPilot = flightForPilot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(Integer licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public PilotRank getPilotRank() {
		return pilotRank;
	}

	public void setPilotRank(PilotRank pilotRank) {
		this.pilotRank = pilotRank;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", licenseNumber="
				+ licenseNumber + ", pilotRank=" + pilotRank + ", flightForPilot=" + flightForPilot + "]";
	}
	
   
}
