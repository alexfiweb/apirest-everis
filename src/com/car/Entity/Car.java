package com.car.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.sun.xml.ws.security.opt.api.tokens.Timestamp;


@Entity
public class Car {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/*private Timestamp created;
	private Timestamp LastUpdate;
	private Timestamp registration;
	/*
	@ManyToOne
	private Brand brand;
	@ManyToOne
	private Country country;
	*/
	private String brand, country;
	public int getId() {
		return id;
	}
	
	public Car() {
		
	}
	
	public Car(String brand, String country) {
		super();
		this.brand = brand;
		this.country = country;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/*public Timestamp getCreated() {
		return created;
	}
	
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public Timestamp getLastUpdate() {
		return LastUpdate;
	}
	
	public void setLastUpdate(Timestamp lastUpdate) {
		LastUpdate = lastUpdate;
	}
	
	public Timestamp getRegistration() {
		return registration;
	}
	
	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}
*/
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	/*
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}*/
	
}
