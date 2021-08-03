
package com.se.pumptesting.dtos;

import java.util.Date;

public class ModelMasterDto {

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

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getPumpModel() {

		return pumpModel;
	}

	public void setPumpModel(String pumpModel) {

		this.pumpModel = pumpModel;
	}

	public Double getSuction() {

		return suction;
	}

	public void setSuction(Double suction) {

		this.suction = suction;
	}

	public Double getDischarge() {

		return discharge;
	}

	public void setDischarge(Double discharge) {

		this.discharge = discharge;
	}

	public String getPumpType() {

		return pumpType;
	}

	public void setPumpType(String pumpType) {

		this.pumpType = pumpType;
	}

	public String getStageType() {

		return stageType;
	}

	public void setStageType(String stageType) {

		this.stageType = stageType;
	}

	public String getSeries() {

		return series;
	}

	public void setSeries(String series) {

		this.series = series;
	}

	public Double getFactor() {

		return factor;
	}

	public void setFactor(Double factor) {

		this.factor = factor;
	}

	public Double getMinStage() {

		return minStage;
	}

	public void setMinStage(Double minStage) {

		this.minStage = minStage;
	}

	public Double getMaxStage() {

		return maxStage;
	}

	public void setMaxStage(Double maxStage) {

		this.maxStage = maxStage;
	}

	public String getMounting() {

		return mounting;
	}

	public void setMounting(String mounting) {

		this.mounting = mounting;
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
}
