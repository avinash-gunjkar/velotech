
package com.se.pumptesting.models;

// default package
// Generated Jun 25, 2019 12:57:49 PM by Hibernate Tools 4.3.1.Final

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
 * Tbl04TestingMaster generated by hbm2java
 */
@Entity
@Table(name = "tbl_04_TestingMaster", schema = "dbo", catalog = "PumpTesting_SE")
public class Tbl04TestingMaster implements java.io.Serializable {

	private int id;

	private Tbl00ModelMaster tbl00ModelMaster;

	private Tbl00MotorMaster tbl00MotorMaster;

	private Tbl03TestbedMaster tbl03TestbedMaster;

	private Integer runningno;

	private String lognumber;

	private String testNo;

	private Date date;

	private Double suctionPipeSize;

	private Double dischargePipeSize;

	private String pipesizeUom;

	private Double fluidTemp;

	private String remarks;

	private String accepted;

	private String authorised;

	private Integer headDegree;

	private Integer powerDegree;

	private Integer efficiencyDegree;

	private Integer npshDegree;

	private Double trimDia;

	private String testProcess;

	private String testingStd;

	private Double noiseLevel;

	private Double vibration;

	private Double ambientTemp;

	private String motorSrNo;

	private String workorderNo;

	private Date woDate;

	private String customerName;

	private String projectName;

	private String poReference;

	private String pumpSerialNo;

	private Integer stage;

	private String rotation;

	private Double dpCapacity;

	private Double dpHead;

	private Double dpSpeed;

	private Double dpEfficiency;

	private Double dpPower;

	private String sealType;

	private String sealingMaterial;

	private String sealingSize;

	private String testedBy;

	private String witnessedBy;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public Tbl04TestingMaster() {
	}

	public Tbl04TestingMaster(int id, Tbl03TestbedMaster tbl03TestbedMaster, Date date) {
		this.id = id;
		this.tbl03TestbedMaster = tbl03TestbedMaster;
		this.date = date;
	}

	public Tbl04TestingMaster(int id) {
		this.id = id;

	}

	public Tbl04TestingMaster(int id, Tbl00ModelMaster tbl00ModelMaster, Tbl00MotorMaster tbl00MotorMaster, Tbl03TestbedMaster tbl03TestbedMaster,
			Integer runningno, String lognumber, Date date, Double suctionPipeSize, Double dischargePipeSize, String pipesizeUom, Double fluidTemp,
			String remarks, String accepted, String authorised, Integer headDegree, Integer powerDegree, Integer efficiencyDegree, Integer npshDegree,
			Double trimDia, String testProcess, String testingStd, Double noiseLevel, Double vibration, Double ambientTemp, String motorSrNo,
			String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String clientId, String workorderNo, Date woDate,
			String customerName, String projectName, String poReference, String pumpSerialNo, Integer stage, String rotation, Double dpCapacity,
			Double dpHead, Double dpSpeed, Double dpEfficiency, Double dpPower, String sealType, String sealingMaterial, String sealingSize) {
		this.id = id;
		this.tbl00ModelMaster = tbl00ModelMaster;
		this.tbl00MotorMaster = tbl00MotorMaster;
		this.tbl03TestbedMaster = tbl03TestbedMaster;
		this.runningno = runningno;
		this.lognumber = lognumber;
		this.date = date;
		this.suctionPipeSize = suctionPipeSize;
		this.dischargePipeSize = dischargePipeSize;
		this.pipesizeUom = pipesizeUom;
		this.fluidTemp = fluidTemp;
		this.remarks = remarks;
		this.accepted = accepted;
		this.authorised = authorised;
		this.headDegree = headDegree;
		this.powerDegree = powerDegree;
		this.efficiencyDegree = efficiencyDegree;
		this.npshDegree = npshDegree;
		this.trimDia = trimDia;
		this.testProcess = testProcess;
		this.testingStd = testingStd;
		this.noiseLevel = noiseLevel;
		this.vibration = vibration;
		this.ambientTemp = ambientTemp;
		this.motorSrNo = motorSrNo;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.clientId = clientId;
		this.workorderNo = workorderNo;
		this.woDate = woDate;
		this.customerName = customerName;
		this.projectName = projectName;
		this.poReference = poReference;
		this.pumpSerialNo = pumpSerialNo;
		this.stage = stage;
		this.rotation = rotation;
		this.dpCapacity = dpCapacity;
		this.dpHead = dpHead;
		this.dpSpeed = dpSpeed;
		this.dpEfficiency = dpEfficiency;
		this.dpPower = dpPower;
		this.sealType = sealType;
		this.sealingMaterial = sealingMaterial;
		this.sealingSize = sealingSize;

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

	@Column(name = "TEST_NO", length = 50)
	public String getTestNo() {

		return testNo;
	}

	public void setTestNo(String testNo) {

		this.testNo = testNo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODEL_MASTER_ID")
	public Tbl00ModelMaster getTbl00ModelMaster() {

		return this.tbl00ModelMaster;
	}

	public void setTbl00ModelMaster(Tbl00ModelMaster tbl00ModelMaster) {

		this.tbl00ModelMaster = tbl00ModelMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOTOR_ID")
	public Tbl00MotorMaster getTbl00MotorMaster() {

		return this.tbl00MotorMaster;
	}

	public void setTbl00MotorMaster(Tbl00MotorMaster tbl00MotorMaster) {

		this.tbl00MotorMaster = tbl00MotorMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TESTBED_ID", nullable = false)
	public Tbl03TestbedMaster getTbl03TestbedMaster() {

		return this.tbl03TestbedMaster;
	}

	public void setTbl03TestbedMaster(Tbl03TestbedMaster tbl03TestbedMaster) {

		this.tbl03TestbedMaster = tbl03TestbedMaster;
	}

	@Column(name = "RUNNINGNO")
	public Integer getRunningno() {

		return this.runningno;
	}

	public void setRunningno(Integer runningno) {

		this.runningno = runningno;
	}

	@Column(name = "LOGNUMBER", length = 50)
	public String getLognumber() {

		return this.lognumber;
	}

	public void setLognumber(String lognumber) {

		this.lognumber = lognumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE", nullable = false, length = 10)
	public Date getDate() {

		return this.date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	@Column(name = "SUCTION_PIPE_SIZE", precision = 53, scale = 0)
	public Double getSuctionPipeSize() {

		return this.suctionPipeSize;
	}

	public void setSuctionPipeSize(Double suctionPipeSize) {

		this.suctionPipeSize = suctionPipeSize;
	}

	@Column(name = "DISCHARGE_PIPE_SIZE", precision = 53, scale = 0)
	public Double getDischargePipeSize() {

		return this.dischargePipeSize;
	}

	public void setDischargePipeSize(Double dischargePipeSize) {

		this.dischargePipeSize = dischargePipeSize;
	}

	@Column(name = "PIPESIZE_UOM", length = 50)
	public String getPipesizeUom() {

		return this.pipesizeUom;
	}

	public void setPipesizeUom(String pipesizeUom) {

		this.pipesizeUom = pipesizeUom;
	}

	@Column(name = "FLUID_TEMP", precision = 53, scale = 0)
	public Double getFluidTemp() {

		return this.fluidTemp;
	}

	public void setFluidTemp(Double fluidTemp) {

		this.fluidTemp = fluidTemp;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {

		return this.remarks;
	}

	public void setRemarks(String remarks) {

		this.remarks = remarks;
	}

	@Column(name = "ACCEPTED", length = 50)
	public String getAccepted() {

		return this.accepted;
	}

	public void setAccepted(String accepted) {

		this.accepted = accepted;
	}

	@Column(name = "AUTHORISED", length = 50)
	public String getAuthorised() {

		return this.authorised;
	}

	public void setAuthorised(String authorised) {

		this.authorised = authorised;
	}

	@Column(name = "HEAD_DEGREE")
	public Integer getHeadDegree() {

		return this.headDegree;
	}

	public void setHeadDegree(Integer headDegree) {

		this.headDegree = headDegree;
	}

	@Column(name = "POWER_DEGREE")
	public Integer getPowerDegree() {

		return this.powerDegree;
	}

	public void setPowerDegree(Integer powerDegree) {

		this.powerDegree = powerDegree;
	}

	@Column(name = "EFFICIENCY_DEGREE")
	public Integer getEfficiencyDegree() {

		return this.efficiencyDegree;
	}

	public void setEfficiencyDegree(Integer efficiencyDegree) {

		this.efficiencyDegree = efficiencyDegree;
	}

	@Column(name = "NPSH_DEGREE")
	public Integer getNpshDegree() {

		return this.npshDegree;
	}

	public void setNpshDegree(Integer npshDegree) {

		this.npshDegree = npshDegree;
	}

	@Column(name = "TRIM_DIA", precision = 53, scale = 0)
	public Double getTrimDia() {

		return this.trimDia;
	}

	public void setTrimDia(Double trimDia) {

		this.trimDia = trimDia;
	}

	@Column(name = "TEST_PROCESS", length = 50)
	public String getTestProcess() {

		return this.testProcess;
	}

	public void setTestProcess(String testProcess) {

		this.testProcess = testProcess;
	}

	@Column(name = "TESTING_STD")
	public String getTestingStd() {

		return this.testingStd;
	}

	public void setTestingStd(String testingStd) {

		this.testingStd = testingStd;
	}

	@Column(name = "NOISE_LEVEL", precision = 53, scale = 0)
	public Double getNoiseLevel() {

		return this.noiseLevel;
	}

	public void setNoiseLevel(Double noiseLevel) {

		this.noiseLevel = noiseLevel;
	}

	@Column(name = "VIBRATION", precision = 53, scale = 0)
	public Double getVibration() {

		return this.vibration;
	}

	public void setVibration(Double vibration) {

		this.vibration = vibration;
	}

	@Column(name = "AMBIENT_TEMP", precision = 53, scale = 0)
	public Double getAmbientTemp() {

		return this.ambientTemp;
	}

	public void setAmbientTemp(Double ambientTemp) {

		this.ambientTemp = ambientTemp;
	}

	@Column(name = "MOTOR_SR_NO", length = 50)
	public String getMotorSrNo() {

		return this.motorSrNo;
	}

	public void setMotorSrNo(String motorSrNo) {

		this.motorSrNo = motorSrNo;
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

	@Column(name = "WORKORDER_NO", length = 50)
	public String getWorkorderNo() {

		return this.workorderNo;
	}

	public void setWorkorderNo(String workorderNo) {

		this.workorderNo = workorderNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "WO_DATE", length = 10)
	public Date getWoDate() {

		return this.woDate;
	}

	public void setWoDate(Date woDate) {

		this.woDate = woDate;
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {

		return this.customerName;
	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;
	}

	@Column(name = "PROJECT_NAME")
	public String getProjectName() {

		return this.projectName;
	}

	public void setProjectName(String projectName) {

		this.projectName = projectName;
	}

	@Column(name = "PO_REFERENCE")
	public String getPoReference() {

		return this.poReference;
	}

	public void setPoReference(String poReference) {

		this.poReference = poReference;
	}

	@Column(name = "PUMP_SERIAL_NO", length = 50)
	public String getPumpSerialNo() {

		return this.pumpSerialNo;
	}

	public void setPumpSerialNo(String pumpSerialNo) {

		this.pumpSerialNo = pumpSerialNo;
	}

	@Column(name = "STAGE")
	public Integer getStage() {

		return this.stage;
	}

	public void setStage(Integer stage) {

		this.stage = stage;
	}

	@Column(name = "ROTATION", length = 50)
	public String getRotation() {

		return this.rotation;
	}

	public void setRotation(String rotation) {

		this.rotation = rotation;
	}

	@Column(name = "DP_CAPACITY", precision = 53, scale = 0)
	public Double getDpCapacity() {

		return this.dpCapacity;
	}

	public void setDpCapacity(Double dpCapacity) {

		this.dpCapacity = dpCapacity;
	}

	@Column(name = "DP_HEAD", precision = 53, scale = 0)
	public Double getDpHead() {

		return this.dpHead;
	}

	public void setDpHead(Double dpHead) {

		this.dpHead = dpHead;
	}

	@Column(name = "DP_SPEED", precision = 53, scale = 0)
	public Double getDpSpeed() {

		return this.dpSpeed;
	}

	public void setDpSpeed(Double dpSpeed) {

		this.dpSpeed = dpSpeed;
	}

	@Column(name = "DP_EFFICIENCY", precision = 53, scale = 0)
	public Double getDpEfficiency() {

		return this.dpEfficiency;
	}

	public void setDpEfficiency(Double dpEfficiency) {

		this.dpEfficiency = dpEfficiency;
	}

	@Column(name = "DP_POWER", precision = 53, scale = 0)
	public Double getDpPower() {

		return this.dpPower;
	}

	public void setDpPower(Double dpPower) {

		this.dpPower = dpPower;
	}

	@Column(name = "SEAL_TYPE", length = 50)
	public String getSealType() {

		return this.sealType;
	}

	public void setSealType(String sealType) {

		this.sealType = sealType;
	}

	@Column(name = "SEALING_MATERIAL", length = 50)
	public String getSealingMaterial() {

		return this.sealingMaterial;
	}

	public void setSealingMaterial(String sealingMaterial) {

		this.sealingMaterial = sealingMaterial;
	}

	@Column(name = "SEALING_SIZE", length = 50)
	public String getSealingSize() {

		return this.sealingSize;
	}

	public void setSealingSize(String sealingSize) {

		this.sealingSize = sealingSize;
	}

	@Column(name = "TESTED_BY", length = 50)
	public String getTestedBy() {

		return testedBy;
	}

	public void setTestedBy(String testedBy) {

		this.testedBy = testedBy;
	}

	@Column(name = "WITNESSED_BY", length = 50)
	public String getWitnessedBy() {

		return witnessedBy;
	}

	public void setWitnessedBy(String witnessedBy) {

		this.witnessedBy = witnessedBy;
	}

}