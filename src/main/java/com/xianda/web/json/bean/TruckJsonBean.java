/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;

import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.CommonTool;
import com.xianda.domain.Truck;

public class TruckJsonBean {
	
	private String id = "-1";
	private String name = "";
	private String date = "";
	private String description = "";
	private String state = "0";
	
	public TruckJsonBean() {
		
	}
	
	public TruckJsonBean(Truck truck) {
		this.id = new Long(truck.getId()).toString();
		this.name = truck.getName();
		this.date = CommonTool.dateFormat.format(truck.getDate());
		this.description = truck.getDescription();
		this.state = Integer.toString(truck.getState());
	}
	
	public Truck truck() throws ParseException {
		Truck truck = new Truck();
		truck.setId(Long.parseLong(this.id));
		truck.setName(this.name);
		truck.setDate(CommonTool.dateFormat.parse(this.date));
		truck.setDescription(description);
		truck.setState(new Integer(state));
		return truck;
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

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
