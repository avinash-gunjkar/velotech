
package com.se.pumptesting.dtos;

import java.util.Date;

public class UserMasterDto {

	private int id;

	private String userName;

	private String password;

	private String emailId;

	private int roleId;

	private String roleName;

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

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getEmailId() {

		return emailId;
	}

	public void setEmailId(String emailId) {

		this.emailId = emailId;
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

	public int getRoleId() {

		return roleId;
	}

	public void setRoleId(int roleId) {

		this.roleId = roleId;
	}

	public String getRoleName() {

		return roleName;
	}

	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

}
