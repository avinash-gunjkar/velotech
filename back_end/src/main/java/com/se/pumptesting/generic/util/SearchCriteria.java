package com.se.pumptesting.generic.util;

import java.util.Date;

public class SearchCriteria {

	private String searchProperty;

	private Object searchValue;

	private Date startDate;

	private Date endDate;

	/**
	 * @return the searchProperty
	 */
	public String getSearchProperty() {

		return searchProperty;
	}

	/**
	 * @param searchProperty
	 *            the searchProperty to set
	 */
	public void setSearchProperty(String searchProperty) {

		this.searchProperty = searchProperty;
	}

	/**
	 * @return the searchValue
	 */
	public Object getSearchValue() {

		return searchValue;
	}

	/**
	 * @param searchValue
	 *            the searchValue to set
	 */
	public void setSearchValue(Object searchValue) {

		this.searchValue = searchValue;
	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

}
