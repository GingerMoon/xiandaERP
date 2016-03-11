/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.domain.Route;

public class RouteJsonBean {
	private String id = "-1";
	private String departure = "";
	private String destination = "";
	private String distance = "";
	private String description = "";
	private String state = "0";
	
	public RouteJsonBean() {
		
	}
	
	public RouteJsonBean(String id) {
		this.id = id;
	}
	
	public RouteJsonBean(Route route) {
		if(route == null) return;
		this.id = new Long(route.getId()).toString();
		this.departure = route.getDeparture();
		this.destination = route.getDestination();
		this.distance = route.getDistance();
		this.description = route.getDescription();
		this.state = Integer.toString(route.getState());
	}
	
	public Route route() {
		Route route = new Route();
		route.setId(Long.parseLong(this.id));
		route.setDeparture(departure);
		route.setDestination(destination);
		route.setDistance(distance);
		route.setDescription(description);
		route.setState(new Integer(state));
		return route;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id==""?"-1":id;
	}

	@JsonProperty("departure")
	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	@JsonProperty("destination")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@JsonProperty("distance")
	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
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
