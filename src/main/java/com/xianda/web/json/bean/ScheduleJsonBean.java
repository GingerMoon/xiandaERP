/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.CommonTool;
import com.xianda.domain.Employee;
import com.xianda.domain.Schedule;

public class ScheduleJsonBean {
	private String id = "-1";
	private String date = "";
	private String volumn = "0";
	private String description = "";
	private String state = "0";
	private ProjectJsonBean project = new ProjectJsonBean();
	private TruckJsonBean truck = new TruckJsonBean();
	private List<EmployeeJsonBean> employees = new ArrayList<EmployeeJsonBean>();
	private RouteJsonBean route = new RouteJsonBean();
	
	public ScheduleJsonBean() {
		
	}
	
	public ScheduleJsonBean(String Id) {
		this.id = id;
	}

	public ScheduleJsonBean(Schedule schedule) {
		if(schedule == null) return;
		this.id = new Long(schedule.getId()).toString();
		this.date = CommonTool.dateFormat.format(schedule.getDate());
		this.volumn = new Integer(schedule.getVolumn()).toString();
		this.description = schedule.getDescription();
		this.state = Integer.toString(schedule.getState());
		this.project = new ProjectJsonBean(schedule.getProject());
		this.truck = new TruckJsonBean(schedule.getTruck());
		
		for(Employee e : schedule.getEmployees()) {
			EmployeeJsonBean e_json = new EmployeeJsonBean(e);
			this.employees.add(e_json);
		}
		this.route = new RouteJsonBean(schedule.getRoute());
	}
	
	public Schedule schedule() {
		Schedule schedule = new Schedule();
		schedule.setId(Long.parseLong(this.id));
		try {
			schedule.setDate(CommonTool.dateFormat.parse(this.date));
		} catch (ParseException e) {
			schedule.setDate(new Date());
		}
		schedule.setVolumn(Integer.parseInt(this.volumn));
		schedule.setDescription(description);
		schedule.setState(new Integer(state));
		schedule.setProject(this.project.project());
		schedule.setTruck(this.truck.truck());
		
		List<Employee> employees = new ArrayList<Employee>();
		for(EmployeeJsonBean e_json : this.employees) {
			employees.add(e_json.employee());
		}
		schedule.setEmployees(employees);
		
		schedule.setRoute(this.route.route());
		return schedule;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id==""?"-1":id;
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

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@JsonProperty("volumn")
	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	@JsonProperty("project")
	public ProjectJsonBean getProject() {
		return project;
	}

	public void setProject(ProjectJsonBean project) {
		this.project = project;
	}

	@JsonProperty("truck")
	public TruckJsonBean getTruck() {
		return truck;
	}

	public void setTruck(TruckJsonBean truck) {
		this.truck = truck;
	}

	@JsonProperty("employees")
	public List<EmployeeJsonBean> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeJsonBean> employees) {
		this.employees = employees;
	}

	@JsonProperty("route")
	public RouteJsonBean getRoute() {
		return route;
	}

	public void setRoute(RouteJsonBean route) {
		this.route = route;
	}
	
}
