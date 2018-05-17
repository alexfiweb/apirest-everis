package com.car.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Car {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date created = new Date();
	private Date lastUpdate = new Date();
	private Date registration;

	@ManyToOne
	private Brand brand;
	private String country;
	
	public int getId() {
		return id;
	}
	
	public Car() {
		
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "id=" + id + ", created=" + created + ", lastUpdate=" + lastUpdate + ", registration=" + registration + ", brand=" + brand + ", country=" + country;
	}
	
	
	
}
