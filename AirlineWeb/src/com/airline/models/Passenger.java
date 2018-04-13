package com.airline.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity

public class Passenger implements Serializable {

	// Property related to the Serializable interface. We do not want this to
	// represent a column into our DB, so we need to annotate as Transient
	@Transient
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	// This sets the primary key as ID.
	// We also would like the DB to assign a unique Id, rather than us coming up
	// with one on our own.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // This auto generates an ID for us.
	private Integer id;

	private String firstName;

	private String lastName;

	// Temporal allows the DB to know this is a type of DATE or TIME.
	@Temporal(TemporalType.DATE)
	private Date dob;

	// EnumType.STRING allows the actual string to be saved into the DB, rather than
	// the ordinal position of the enum
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

}
