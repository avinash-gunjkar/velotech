
package com.se.pumptesting.models;
// Generated Jun 26, 2019 11:58:53 AM by Hibernate Tools 4.3.1.Final

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
import javax.persistence.UniqueConstraint;

/**
 * Tbl00UserMaster generated by hbm2java
 */
@Entity
@Table(name = "tbl_00_UserMaster", schema = "dbo", catalog = "PumpTesting_SE", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class Tbl00UserMaster implements java.io.Serializable {

	private int id;

	private Tbl00RoleMaster tbl00RoleMaster;

	private String userName;

	private String password;

	private String emailId;

	private Boolean isActive;

	private String createdBy;

	private Date createdDate;

	private String modifiedBy;

	private Date modifiedDate;

	private String clientId;

	public Tbl00UserMaster() {
	}

	public Tbl00UserMaster(int id, String password) {
		this.id = id;
		this.password = password;
	}

	public Tbl00UserMaster(int id, Tbl00RoleMaster tbl00RoleMaster, String userName, String password, String emailId, Boolean isActive,
			String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, String clientId) {
		this.id = id;
		this.tbl00RoleMaster = tbl00RoleMaster;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
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
	@JoinColumn(name = "ROLE_ID")
	public Tbl00RoleMaster getTbl00RoleMaster() {

		return this.tbl00RoleMaster;
	}

	public void setTbl00RoleMaster(Tbl00RoleMaster tbl00RoleMaster) {

		this.tbl00RoleMaster = tbl00RoleMaster;
	}

	@Column(name = "USERNAME", unique = true)
	public String getUserName() {

		return this.userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {

		return this.password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	@Column(name = "EMAIL_ID")
	public String getEmailId() {

		return this.emailId;
	}

	public void setEmailId(String emailId) {

		this.emailId = emailId;
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

	@Column(name = "CLIENT_ID", length = 50)
	public String getClientId() {

		return this.clientId;
	}

	public void setClientId(String clientId) {

		this.clientId = clientId;
	}

}
