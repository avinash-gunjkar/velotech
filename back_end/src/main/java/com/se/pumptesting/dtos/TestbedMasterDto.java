package com.se.pumptesting.dtos;

import java.util.Date;

public class TestbedMasterDto {

	private int id;

	private String testbedNo;

	private String desciption;

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

	public String getTestbedNo() {

		return testbedNo;
	}

	public void setTestbedNo(String testbedNo) {

		this.testbedNo = testbedNo;
	}

	public String getDesciption() {

		return desciption;
	}

	public void setDesciption(String desciption) {

		this.desciption = desciption;
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
