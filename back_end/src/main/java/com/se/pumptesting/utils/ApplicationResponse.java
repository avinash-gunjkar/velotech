/**
 * 
 */
package com.se.pumptesting.utils;

import java.util.List;

/**
 * @author SHARAD
 *
 */
public class ApplicationResponse {

	private boolean success;

	private String message;

	private Object data;

	private long total;

	public boolean isSuccess() {

		return success;
	}

	public void setSuccess(boolean success) {

		this.success = success;
	}

	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	public long getTotal() {

		return total;
	}

	public void setTotal(long total) {

		this.total = total;
	}

	public ApplicationResponse getResponseToGetData(boolean isSuccess, String dataLoadSuccessMsg, List<?> models,
			long total2) {

		this.setSuccess(isSuccess);
		this.setMessage(message);
		return this;
	}

}
