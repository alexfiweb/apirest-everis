package com.car.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Model {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@ManyToOne
	private Brand brand;
	
	public Model() {
		
	}
	
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", brand=" + brand;
	}
	
	
	
}
