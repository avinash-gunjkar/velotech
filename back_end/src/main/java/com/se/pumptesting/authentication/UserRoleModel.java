
package com.se.pumptesting.authentication;

public class UserRoleModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String role;

	private boolean getData;

	private boolean addData;

	private boolean editData;

	private boolean deleteData;

	public String getRole() {

		return role;
	}

	public void setRole(String role) {

		this.role = role;
	}

	public boolean isGetData() {

		return getData;
	}

	public void setGetData(boolean getData) {

		this.getData = getData;
	}

	public boolean isAddData() {

		return addData;
	}

	public void setAddData(boolean addData) {

		this.addData = addData;
	}

	public boolean isEditData() {

		return editData;
	}

	public void setEditData(boolean editData) {

		this.editData = editData;
	}

	public boolean isDeleteData() {

		return deleteData;
	}

	public void setDeleteData(boolean deleteData) {

		this.deleteData = deleteData;
	}

}
