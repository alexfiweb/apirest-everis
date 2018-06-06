package com.car.Entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Car {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date created = new Date();
	private Date lastUpdate = new Date();
	private Date registration;

	@ManyToOne
	private Model model;
	@Size(min = 4, max = 20, message = "The field 'country' must be between 4 and 20 characters")
	@NotEmpty(message= "The field 'country' can not be empty")
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

	public Date getRegistration(){
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "id=" + id + ", created=" + created + ", lastUpdate=" + lastUpdate + ", registration=" + registration + ", model=" + model + ", country=" + country;
	}
	
	
	
}
