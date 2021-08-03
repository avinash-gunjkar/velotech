
package com.se.pumptesting.dtos;

import java.util.Date;

public class TestbedInstrumentDto {

	private int id;

	private int testbedMasterId;

	private String testbedMasterNo;

	private int instrumentMasterId;

	private String instrumentMasterName;

	private String parameter;

	private String columnName;

	private Boolean isActive;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public int getTestbedMasterId() {

		return testbedMasterId;
	}

	public void setTestbedMasterId(int testbedMasterId) {

		this.testbedMasterId = testbedMasterId;
	}

	public int getInstrumentMasterId() {

		return instrumentMasterId;
	}

	public void setInstrumentMasterId(int instrumentMasterId) {

		this.instrumentMasterId = instrumentMasterId;
	}

	public Boolean getIsActive() {

		return isActive;
	}

	public void setIsActive(Boolean isActive) {

		this.isActive = isActive;
	}

	public String getCreatedBy() {

		return createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {

		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public String getModifiedBy() {

		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {

		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {

		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {

		this.modifiedDate = modifiedDate;
	}

	public String getClientId() {

		return clientId;
	}

	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

	public String getTestbedMasterNo() {

		return testbedMasterNo;
	}

	public void setTestbedMasterNo(String testbedMasterNo) {

		this.testbedMasterNo = testbedMasterNo;
	}

	public String getInstrumentMasterName() {

		return instrumentMasterName;
	}

	public void setInstrumentMasterName(String instrumentMasterName) {

		this.instrumentMasterName = instrumentMasterName;
	}

	public String getParameter() {

		return parameter;
	}

	public void setParameter(String parameter) {

		this.parameter = parameter;
	}

	public String getColumnName() {

		return columnName;
	}

	public void setColumnName(String columnName) {

		this.columnName = columnName;
	}

}
