/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.domain.Customer;

public class CustomerJsonBean {
	private String id = "-1";
	private String name = "";
	private String description = "";
	private String state = "0";
	
	public CustomerJsonBean() {
		
	}
	
	public CustomerJsonBean(Customer customer) {
		this.id = new Long(customer.getId()).toString();
		this.name = customer.getName();
		this.description = customer.getDescription();
		this.state = Integer.toString(customer.getState());
	}
	
	public Customer customer() {
		Customer customer = new Customer();
		customer.setId(Long.parseLong(this.id));
		customer.setName(name);
		customer.setDescription(description);
		customer.setState(new Integer(state));
		return customer;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id==""?"-1":id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
