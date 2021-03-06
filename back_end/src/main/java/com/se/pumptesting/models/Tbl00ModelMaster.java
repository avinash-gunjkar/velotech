
package com.se.pumptesting.models;
// Generated Jan 3, 2019 10:30:58 AM by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Tbl00ModelMaster generated by hbm2java
 */
@Entity
@Table(name = "tbl_00_ModelMaster", schema = "dbo", catalog = "PumpTesting_SE")
/* @Filter(name = "ClientIdCheck", condition = ":clientIdParam= CLIENT_ID ") */
public class Tbl00ModelMaster implements java.io.Serializable {

	private int id;

	private String pumpModel;

	private Double suction;

	private Double discharge;

	private String pumpType;

	private String stageType;

	private String series;

	private Double factor;

	private Double minStage;

	private Double maxStage;

	private String mounting;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public Tbl00ModelMaster() {
	}

	public Tbl00ModelMaster(int id) {
		this.id = id;
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

	@Column(name = "PUMP_MODEL", nullable = false)
	public String getPumpModel() {

		return this.pumpModel;
	}

	public void setPumpModel(String pumpModel) {

		this.pumpModel = pumpModel;
	}

	@Column(name = "SUCTION", precision = 53, scale = 0)
	public Double getSuction() {

		return this.suction;
	}

	public void setSuction(Double suction) {

		this.suction = suction;
	}

	@Column(name = "DISCHARGE", precision = 53, scale = 0)
	public Double getDischarge() {

		return this.discharge;
	}

	public void setDischarge(Double discharge) {

		this.discharge = discharge;
	}

	@Column(name = "PUMP_TYPE")
	public String getPumpType() {

		return this.pumpType;
	}

	public void setPumpType(String pumpType) {

		this.pumpType = pumpType;
	}

	@Column(name = "STAGE_TYPE", length = 50)
	public String getStageType() {

		return this.stageType;
	}

	public void setStageType(String stageType) {

		this.stageType = stageType;
	}

	@Column(name = "SERIES")
	public String getSeries() {

		return this.series;
	}

	public void setSeries(String series) {

		this.series = series;
	}

	@Column(name = "FACTOR", precision = 53, scale = 0)
	public Double getFactor() {

		return this.factor;
	}

	public void setFactor(Double factor) {

		this.factor = factor;
	}

	@Column(name = "MIN_STAGE", precision = 53, scale = 0)
	public Double getMinStage() {

		return this.minStage;
	}

	public void setMinStage(Double minStage) {

		this.minStage = minStage;
	}

	@Column(name = "MAX_STAGE", precision = 53, scale = 0)
	public Double getMaxStage() {

		return this.maxStage;
	}

	public void setMaxStage(Double maxStage) {

		this.maxStage = maxStage;
	}

	@Column(name = "MOUNTING")
	public String getMounting() {

		return this.mounting;
	}

	public void setMounting(String mounting) {

		this.mounting = mounting;
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
