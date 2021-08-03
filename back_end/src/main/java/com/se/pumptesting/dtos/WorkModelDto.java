
package com.se.pumptesting.dtos;

import java.util.Date;

public class WorkModelDto {

	private int id;

	private int workOrderId;

	private String workorderNo;

	private String customerName;

	private String pumpSerialNo;

	private Integer stage;

	private String rotation;

	private Double trimDia;

	private Double dpCapacity;

	private Double dpHead;

	private Double dpSpeed;

	private Double dpEfficiency;

	private Double dpPower;

	private Double dpNpsh;

	private Integer modelMasterId;

	private String pumpModel;

	private String performanceCurve;

	private Date dispachedDate;

	private String dispachedBy;

	private String sealType;

	private String sealingMaterial;

	private String sealingSize;

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

	public int getWorkOrderId() {

		return workOrderId;
	}

	public void setWorkOrderId(int workOrderId) {

		this.workOrderId = workOrderId;
	}

	public String getPumpSerialNo() {

		return pumpSerialNo;
	}

	public void setPumpSerialNo(String pumpSeriealNo) {

		this.pumpSerialNo = pumpSeriealNo;
	}

	public Integer getStage() {

		return stage;
	}

	public void setStage(Integer stage) {

		this.stage = stage;
	}

	public String getRotation() {

		return rotation;
	}

	public void setRotation(String rotation) {

		this.rotation = rotation;
	}

	public Double getTrimDia() {

		return trimDia;
	}

	public void setTrimDia(Double trimDia) {

		this.trimDia = trimDia;
	}

	public Double getDpCapacity() {

		return dpCapacity;
	}

	public void setDpCapacity(Double dpCapacity) {

		this.dpCapacity = dpCapacity;
	}

	public Double getDpHead() {

		return dpHead;
	}

	public void setDpHead(Double dpHead) {

		this.dpHead = dpHead;
	}

	public Double getDpSpeed() {

		return dpSpeed;
	}

	public void setDpSpeed(Double dpSpeed) {

		this.dpSpeed = dpSpeed;
	}

	public Double getDpEfficiency() {

		return dpEfficiency;
	}

	public void setDpEfficiency(Double dpEfficiency) {

		this.dpEfficiency = dpEfficiency;
	}

	public Double getDpPower() {

		return dpPower;
	}

	public void setDpPower(Double dpPower) {

		this.dpPower = dpPower;
	}

	public Double getDpNpsh() {

		return dpNpsh;
	}

	public void setDpNpsh(Double dpNpsh) {

		this.dpNpsh = dpNpsh;
	}

	public Integer getModelMasterId() {

		return modelMasterId;
	}

	public void setModelMasterId(Integer modelMasterId) {

		this.modelMasterId = modelMasterId;
	}

	public String getPumpModel() {

		return pumpModel;
	}

	public void setPumpModel(String pumpModel) {

		this.pumpModel = pumpModel;
	}

	public String getPerformanceCurve() {

		return performanceCurve;
	}

	public void setPerformanceCurve(String performanceCurve) {

		this.performanceCurve = performanceCurve;
	}

	public Date getDispachedDate() {

		return dispachedDate;
	}

	public void setDispachedDate(Date dispachedDate) {

		this.dispachedDate = dispachedDate;
	}

	public String getDispachedBy() {

		return dispachedBy;
	}

	public void setDispachedBy(String dispachedBy) {

		this.dispachedBy = dispachedBy;
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

	public String getSealType() {

		return sealType;
	}

	public void setSealType(String sealType) {

		this.sealType = sealType;
	}

	public String getSealingMaterial() {

		return sealingMaterial;
	}

	public void setSealingMaterial(String sealingMaterial) {

		this.sealingMaterial = sealingMaterial;
	}

	public String getSealingSize() {

		return sealingSize;
	}

	public void setSealingSize(String sealingSize) {

		this.sealingSize = sealingSize;
	}

	public String getWorkorderNo() {

		return workorderNo;
	}

	public void setWorkorderNo(String workorderNo) {

		this.workorderNo = workorderNo;
	}

	public String getCustomerName() {

		return customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

}
