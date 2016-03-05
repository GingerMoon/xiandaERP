package com.xianda.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.dom4j.tree.AbstractEntity;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = -1;
	private String name = "";
	private String responsiblePersonName = "";
	private String responsiblePersonPhone = "";
	private String structure = "";
	private String tongKind = "";
	private int planVolumn = 0;
	private int completedVolumn = 0;
	private int utPrice = 0;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name="customer_id")
	private Customer customer = new Customer();
	private String description = "";
	private int state = 0; // by default the state is active(0) 

	public Project() {
	}

	protected Project(long id) {
		this.id = id;
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

	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	public String getResponsiblePersonPhone() {
		return responsiblePersonPhone;
	}

	public void setResponsiblePersonPhone(String responsiblePersonPhone) {
		this.responsiblePersonPhone = responsiblePersonPhone;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getTongKind() {
		return tongKind;
	}

	public void setTongKind(String tongKind) {
		this.tongKind = tongKind;
	}

	public int getPlanVolumn() {
		return planVolumn;
	}

	public void setPlanVolumn(int planVolumn) {
		this.planVolumn = planVolumn;
	}

	public int getCompletedVolumn() {
		return completedVolumn;
	}

	public void setCompletedVolumn(int completedVolumn) {
		this.completedVolumn = completedVolumn;
	}

	public int getUtPrice() {
		return utPrice;
	}

	public void setUtPrice(int utPrice) {
		this.utPrice = utPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
