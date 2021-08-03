
package com.se.pumptesting.authentication;

import java.io.Serializable;

public class UserMenuModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer treeMenuMasterId;

	private boolean getData;

	private boolean addData;

	private boolean editData;

	private boolean deleteData;

	public Integer getTreeMenuMasterId() {

		return treeMenuMasterId;
	}

	public void setTreeMenuMasterId(Integer treeMenuMasterId) {

		this.treeMenuMasterId = treeMenuMasterId;
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
