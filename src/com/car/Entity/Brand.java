package com.car.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Brand {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	//@OneToMany
	//private Collection<Model> model = new ArrayList<Model>();
	/*
	public Collection<Model> getModel() {
		return model;
	}

	public void setModel(Collection<Model> model) {
		this.model = model;
	}*/

	public Brand() {
		
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
		return "id=" + id + ", name=" + name;
	}
	
	
	
}
