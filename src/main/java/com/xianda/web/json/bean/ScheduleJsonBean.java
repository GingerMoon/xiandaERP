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
	private String projectId = "-1";
	private TruckJsonBean truck = new TruckJsonBean();
	private String truckId = "-1";
	private EmployeeJsonBean employee1 = new EmployeeJsonBean();
	private String employee1Id = "-1";
	private EmployeeJsonBean employee2 = new EmployeeJsonBean();
	private String employee2Id = "-1";
	private List<EmployeeJsonBean> employees = new ArrayList<EmployeeJsonBean>();
	
	public ScheduleJsonBean() {
		
	}
	
	public ScheduleJsonBean(Schedule schedule) {
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
		this.employee1 = this.employees.get(0);
		this.employee2 = this.employees.get(1);
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

	@JsonProperty("employee1")
	public EmployeeJsonBean getEmployee1() {
		return employee1;
	}

	public void setEmployee1(EmployeeJsonBean employee1) {
		this.employee1 = employee1;
	}

	@JsonProperty("employee2")
	public EmployeeJsonBean getEmployee2() {
		return employee2;
	}

	public void setEmployee2(EmployeeJsonBean employee2) {
		this.employee2 = employee2;
	}

	
}
