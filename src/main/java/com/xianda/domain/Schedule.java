package com.xianda.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	private int volumn = 0;
	private Date date = new Date();
	private String description = "";
	private int state = 0; // by default the state is active(0)
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Project project = new Project();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Truck truck = new Truck();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<Employee>();
	
	public Schedule() {
	}

	protected Schedule(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getVolumn() {
		return volumn;
	}

	public void setVolumn(int volumn) {
		this.volumn = volumn;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	
}
