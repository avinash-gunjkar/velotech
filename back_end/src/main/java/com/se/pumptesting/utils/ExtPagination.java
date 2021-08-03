package com.se.pumptesting.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtPagination {

	public Integer start;

	public Integer limit;

	public String sort;

	public String sortProperty;

	public String sortDirection;

	public ExtPagination(Integer start, Integer limit, String sort) {
		this.start = start;
		this.limit = limit;
		setSort(sort);
	}

	public Integer getStart() {

		return start;
	}

	public void setStart(Integer start) {

		if (start < 0)
			this.start = 0;
		else
			this.start = start;
	}

	public Integer getLimit() {

		return limit;
	}

	public void setLimit(Integer limit) {

		if (limit < 0)
			this.limit = 0;
		else
			this.limit = limit;
	}

	public String getSortProperty() {

		return sortProperty;
	}

	public void setSortProperty(String sortProperty) {

		this.sortProperty = sortProperty;
	}

	public String getSortDirection() {

		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {

		this.sortDirection = sortDirection;
	}

	public String getSort() {

		return sort;
	}

	public void setSort(String sort) {

		this.sort = sort;
		JSONArray sortArray;
		try {
			if (sort != null) {
				sortArray = new JSONArray(sort);
				JSONObject obj = sortArray.getJSONObject(0);
				this.sortProperty = (String) obj.get("property");
				this.sortDirection = (String) obj.get("direction");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
