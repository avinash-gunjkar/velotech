
package com.se.pumptesting.dtos;

import java.util.Date;

public class MotorMasterDto {

	private int id;

	private String motorNo;

	private String make;

	private int phase;

	private Double power;

	private Double powerHp;

	private Double speed;

	private Double efficiency;

	private String frame;

	private Double voltage;

	private Double current;

	private Double frequency;

	private Integer pole;

	private String efficiencyClass;

	private String specification;

	private Double resistanceTemprature;

	private Double resistanceR;

	private Double resistanceY;

	private Double resistanceB;

	private Double noloadPower;

	private Double noloadCurrent;

	private String connectionType;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public int getPhase() {

		return phase;
	}

	public void setPhase(int phase) {

		this.phase = phase;
	}

	public String getFrame() {

		return frame;
	}

	public void setFrame(String frame) {

		this.frame = frame;
	}

	public Double getVoltage() {

		return voltage;
	}

	public void setVoltage(Double voltage) {

		this.voltage = voltage;
	}

	public Double getCurrent() {

		return current;
	}

	public void setCurrent(Double current) {

		this.current = current;
	}

	public String getEfficiencyClass() {

		return efficiencyClass;
	}

	public void setEfficiencyClass(String efficiencyClass) {

		this.efficiencyClass = efficiencyClass;
	}

	public String getSpecification() {

		return specification;
	}

	public void setSpecification(String specification) {

		this.specification = specification;
	}

	public String getClientId() {

		return clientId;
	}

	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getMotorNo() {

		return motorNo;
	}

	public void setMotorNo(String motorNo) {

		this.motorNo = motorNo;
	}

	public String getMake() {

		return make;
	}

	public void setMake(String make) {

		this.make = make;
	}

	public Double getPower() {

		return power;
	}

	public void setPower(Double power) {

		this.power = power;
	}

	public Double getSpeed() {

		return speed;
	}

	public void setSpeed(Double speed) {

		this.speed = speed;
	}

	public Double getEfficiency() {

		return efficiency;
	}

	public void setEfficiency(Double efficiency) {

		this.efficiency = efficiency;
	}

	public Double getFrequency() {

		return frequency;
	}

	public void setFrequency(Double frequency) {

		this.frequency = frequency;
	}

	public Integer getPole() {

		return pole;
	}

	public void setPole(Integer pole) {

		this.pole = pole;
	}

	public Double getResistanceR() {

		return resistanceR;
	}

	public void setResistanceR(Double resistanceR) {

		this.resistanceR = resistanceR;
	}

	public Double getResistanceY() {

		return resistanceY;
	}

	public void setResistanceY(Double resistanceY) {

		this.resistanceY = resistanceY;
	}

	public Double getResistanceB() {

		return resistanceB;
	}

	public void setResistanceB(Double resistanceB) {

		this.resistanceB = resistanceB;
	}

	public Double getNoloadPower() {

		return noloadPower;
	}

	public void setNoloadPower(Double noloadPower) {

		this.noloadPower = noloadPower;
	}

	public Double getNoloadCurrent() {

		return noloadCurrent;
	}

	public void setNoloadCurrent(Double noloadCurrent) {

		this.noloadCurrent = noloadCurrent;
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

	public Double getResistanceTemprature() {

		return resistanceTemprature;
	}

	public void setResistanceTemprature(Double resistanceTemprature) {

		this.resistanceTemprature = resistanceTemprature;
	}

	public Double getPowerHp() {

		return powerHp;
	}

	public void setPowerHp(Double powerHp) {

		this.powerHp = powerHp;
	}

	public String getConnectionType() {

		return connectionType;
	}

	public void setConnectionType(String connectionType) {

		this.connectionType = connectionType;
	}

}
