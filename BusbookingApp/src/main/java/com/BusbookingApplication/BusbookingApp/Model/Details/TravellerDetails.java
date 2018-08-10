package com.BusbookingApplication.BusbookingApp.Model.Details;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name = "travellerdetails")
public class TravellerDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int travellerId;
	
	private String name;
	
	private String lastname;
	
	private String seatNumber;
	
	private int age;
	
	private String gender;
	
	private long fare;
	
	private String etstNumber;
	
	private String seat_status;
	
	//Getter and Setter methods
	
	public int gettravellerId() {
		return travellerId;
	}

	public void settravellerId(int travellerId) {
		this.travellerId = travellerId;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getseatNumber() {
		return seatNumber;
	}

	public void setseatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getFare() {
		return fare;
	}

	public void setFare(long fare) {
		this.fare = fare;
	}

	public String getEtstNumber() {
		return etstNumber;
	}

	public void setEtstNumber(String etstNumber) {
		this.etstNumber = etstNumber;
	}

	public String getseatStatus() {
		return seat_status;
	}

	public void setseatStatus(String seat_status) {
		this.seat_status = seat_status;
	}
	
	

}
