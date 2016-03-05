package com.xianda.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Truck {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	private String name = "";
	private Date date = new Date();
	private String description = "";
	private int state = 0; // by default the state is active(0) 

	public Truck() {
	}

	protected Truck(long id) {
		this.id = id;
	}

	public Truck(String name, Date date, String description) {
		this.name = name;
		this.description = description;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}



}
