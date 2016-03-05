/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.domain.Employee;

public class EmployeeJsonBean {
	private String id = "-1";
	private String name = "";
	private String phone = "";
	private String description = "";
	private String state = "0";
	
	public EmployeeJsonBean() {
		
	}
	
	public EmployeeJsonBean(Employee employee) {
		this.id = new Long(employee.getId()).toString();
		this.name = employee.getName();
		this.phone = employee.getPhone();
		this.description = employee.getDescription();
		this.state = Integer.toString(employee.getState());
	}
	
	public Employee employee() {
		Employee employee = new Employee();
		employee.setId(Long.parseLong(this.id));
		employee.setName(name);
		employee.setPhone(phone);
		employee.setDescription(description);
		employee.setState(new Integer(state));
		return employee;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
