
package com.se.pumptesting.models;
// default package

// Generated Mar 16, 2019 5:19:20 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tbl00MotorNoLoadDetails generated by hbm2java
 */
@Entity
@Table(name = "tbl_00_MotorNoLoadDetails", schema = "dbo", catalog = "PumpTesting_SE")
public class Tbl00MotorNoLoadDetails implements java.io.Serializable {

	private int id;

	private Tbl00MotorMaster tbl00MotorMaster;

	private Double speed;

	private Double noLoadPower;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public Tbl00MotorNoLoadDetails() {
	}

	public Tbl00MotorNoLoadDetails(int id) {
		this.id = id;
	}

	public Tbl00MotorNoLoadDetails(int id, Tbl00MotorMaster tbl00MotorMaster, Double speed, Double noLoadPower, String createdBy, Date createdDate,
			String modifiedBy, Date modifiedDate, String clientId) {
		this.id = id;
		this.tbl00MotorMaster = tbl00MotorMaster;
		this.speed = speed;
		this.noLoadPower = noLoadPower;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.clientId = clientId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {

		return this.id;
	}

	public void setId(int id) {

		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOTOR_MASTER_ID")
	public Tbl00MotorMaster getTbl00MotorMaster() {

		return this.tbl00MotorMaster;
	}

	public void setTbl00MotorMaster(Tbl00MotorMaster tbl00MotorMaster) {

		this.tbl00MotorMaster = tbl00MotorMaster;
	}

	@Column(name = "SPEED", precision = 53, scale = 0)
	public Double getSpeed() {

		return this.speed;
	}

	public void setSpeed(Double speed) {

		this.speed = speed;
	}

	@Column(name = "NO_LOAD_POWER", precision = 53, scale = 0)
	public Double getNoLoadPower() {

		return this.noLoadPower;
	}

	public void setNoLoadPower(Double noLoadPower) {

		this.noLoadPower = noLoadPower;
	}

	@Column(name = "CREATED_BY", length = 50)
	public String getCreatedBy() {

		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", length = 10)
	public Date getCreatedDate() {

		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_BY", length = 50)
	public String getModifiedBy() {

		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {

		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFIED_DATE", length = 10)
	public Date getModifiedDate() {

		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {

		this.modifiedDate = modifiedDate;
	}

	@Column(name = "CLIENT_ID", length = 50)
	public String getClientId() {

		return this.clientId;
	}

	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

}
