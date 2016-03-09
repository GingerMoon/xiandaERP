/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xianda.web.json.bean;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xianda.domain.Customer;
import com.xianda.domain.Project;

public class ProjectJsonBean { // 13 properties
	private String id = "-1";
	private String name = "";
	private String responsiblePersonName = "";
	private String responsiblePersonPhone = "";
	private String address = "";
	private String structure = "";
	private String tongKind = "";
	private String planVolumn = "0";
	private String completedVolumn = "0";
	private String utPrice = "0";
	private CustomerJsonBean customer = new CustomerJsonBean();
	private String description = "";
	private String state = "0";


	public ProjectJsonBean() {
	}
	
	public ProjectJsonBean(String id) {
		this.id = id;
	}
	
	public ProjectJsonBean(Project project) {
		this.id = new Long(project.getId()).toString();
		this.name = project.getName();
		this.responsiblePersonName = project.getResponsiblePersonName();
		this.responsiblePersonPhone = project.getResponsiblePersonPhone();
		this.address = project.getAddress();
		this.structure = project.getStructure();
		this.tongKind = project.getTongKind();
		this.planVolumn = new Integer(project.getPlanVolumn()).toString();
		this.completedVolumn = new Integer(project.getCompletedVolumn()).toString();
		this.utPrice = new Integer(project.getUtPrice()).toString();
		this.customer = new CustomerJsonBean(project.getCustomer());
		this.description = project.getDescription();
		this.state = Integer.toString(project.getState());
	}
	
	public Project project() {
		Project project = new Project();
		project.setId(new Long(id));
		project.setName(name);
		project.setResponsiblePersonName(responsiblePersonName);
		project.setResponsiblePersonPhone(responsiblePersonPhone);
		project.setAddress(address);
		project.setStructure(structure);
		project.setTongKind(tongKind);
		project.setPlanVolumn(new Integer(planVolumn));
		project.setCompletedVolumn(new Integer(completedVolumn));
		project.setUtPrice(new Integer(utPrice));
		project.setCustomer(customer.customer());
		project.setDescription(description);
		project.setState(new Integer(state));
		return project;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == "" ? "-1": id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("responsiblePersonName")
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	@JsonProperty("responsiblePersonPhone")
	public String getResponsiblePersonPhone() {
		return responsiblePersonPhone;
	}

	public void setResponsiblePersonPhone(String responsiblePersonPhone) {
		this.responsiblePersonPhone = responsiblePersonPhone;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("structure")
	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	@JsonProperty("tongKind")
	public String getTongKind() {
		return tongKind;
	}

	public void setTongKind(String tongKind) {
		this.tongKind = tongKind;
	}

	@JsonProperty("planVolumn")
	public String getPlanVolumn() {
		return planVolumn;
	}

	public void setPlanVolumn(String planVolumn) {
		this.planVolumn = planVolumn == "" ? "0": planVolumn;
	}

	@JsonProperty("completedVolumn")
	public String getCompletedVolumn() {
		return completedVolumn;
	}

	public void setCompletedVolumn(String completedVolumn) {
		this.completedVolumn = completedVolumn == "" ? "0": completedVolumn;
	}

	@JsonProperty("utPrice")
	public String getUtPrice() {
		return utPrice;
	}

	public void setUtPrice(String utPrice) {
		this.utPrice = utPrice == "" ? "0": utPrice;
	}

	@JsonProperty("customer")
	public CustomerJsonBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJsonBean customer) {
		this.customer = customer;
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
