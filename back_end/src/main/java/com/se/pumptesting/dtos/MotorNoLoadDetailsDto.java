
package com.se.pumptesting.dtos;

import java.util.Date;

public class MotorNoLoadDetailsDto {

	private int id;

	private int motorMasterId;

	private Double speed;

	private Double noLoadPower;

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

	public int getMotorMasterId() {

		return motorMasterId;
	}

	public void setMotorMasterId(int motorMasterId) {

		this.motorMasterId = motorMasterId;
	}

	public Double getSpeed() {

		return speed;
	}

	public void setSpeed(Double speed) {

		this.speed = speed;
	}

	public Double getNoLoadPower() {

		return noLoadPower;
	}

	public void setNoLoadPower(Double noLoadPower) {

		this.noLoadPower = noLoadPower;
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

}
