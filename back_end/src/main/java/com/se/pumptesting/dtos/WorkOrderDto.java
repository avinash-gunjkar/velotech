package com.se.pumptesting.dtos;

import java.util.Date;

public class WorkOrderDto {

	private int id;

	private String workorderNo;

	private Date date;

	private String customerName;

	private String projectName;

	private String poReference;

	private Date startDate;

	private Date endDate;

	private boolean completed;

	private String createdby;

	private Date createdDate;

	private String modifiedby;

	private Date modifiedDate;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getWorkorderNo() {

		return workorderNo;
	}

	public void setWorkorderNo(String workorderNo) {

		this.workorderNo = workorderNo;
	}

	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	public String getProjectName() {

		return projectName;
	}

	public void setProjectName(String projectName) {

		this.projectName = projectName;
	}

	public String getPoReference() {

		return poReference;
	}

	public void setPoReference(String poReference) {

		this.poReference = poReference;
	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	public boolean isCompleted() {

		return completed;
	}

	public void setCompleted(boolean completed) {

		this.completed = completed;
	}

	public String getCreatedby() {

		return createdby;
	}

	public void setCreatedby(String createdby) {

		this.createdby = createdby;
	}

	public Date getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public String getModifiedby() {

		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {

		this.modifiedby = modifiedby;
	}

	public Date getModifiedDate() {

		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {

		this.modifiedDate = modifiedDate;
	}

}
