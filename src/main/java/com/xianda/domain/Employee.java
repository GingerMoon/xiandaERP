package com.xianda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	private String name = "";
	private String phone = "";
	private String description = "";
	private int state = 0; // by default the state is active(0) 

	public Employee() {
	}

	protected Employee(long id) {
		this.id = id;
	}

	public Employee(String firstName, String lastName, String phone) {
		this.name = firstName;
		this.description = lastName;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
