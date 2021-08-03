
package com.se.pumptesting.models;

// default package
// Generated Mar 2, 2019 3:16:43 PM by Hibernate Tools 4.3.1.Final

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
 * Tbl07TestbedInstrument generated by hbm2java
 */
@Entity
@Table(name = "tbl_07_TestbedInstrument", schema = "dbo", catalog = "PumpTesting_SE")
public class Tbl07TestbedInstrument implements java.io.Serializable {

	private int id;

	private Tbl03TestbedMaster tbl03TestbedMaster;

	private Tbl08InstrumentMaster tbl08InstrumentMaster;

	private Boolean isActive;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public Tbl07TestbedInstrument() {
	}

	public Tbl07TestbedInstrument(int id, String clientId) {
		this.id = id;
		this.clientId = clientId;
	}

	public Tbl07TestbedInstrument(int id, Tbl03TestbedMaster tbl03TestbedMaster, Tbl08InstrumentMaster tbl08InstrumentMaster, Boolean isActive,
			String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String clientId) {
		this.id = id;
		this.tbl03TestbedMaster = tbl03TestbedMaster;
		this.tbl08InstrumentMaster = tbl08InstrumentMaster;
		this.isActive = isActive;
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
	@JoinColumn(name = "TESTBED_ID")
	public Tbl03TestbedMaster getTbl03TestbedMaster() {

		return this.tbl03TestbedMaster;
	}

	public void setTbl03TestbedMaster(Tbl03TestbedMaster tbl03TestbedMaster) {

		this.tbl03TestbedMaster = tbl03TestbedMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTRUMENT_MASTER_ID")
	public Tbl08InstrumentMaster getTbl08InstrumentMaster() {

		return this.tbl08InstrumentMaster;
	}

	public void setTbl08InstrumentMaster(Tbl08InstrumentMaster tbl08InstrumentMaster) {

		this.tbl08InstrumentMaster = tbl08InstrumentMaster;
	}

	@Column(name = "IS_ACTIVE")
	public Boolean getIsActive() {

		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {

		this.isActive = isActive;
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

	@Column(name = "CLIENT_ID", nullable = false, length = 50)
	public String getClientId() {

		return this.clientId;
	}

	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

}