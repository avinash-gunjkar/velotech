
package com.se.pumptesting.user.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.se.pumptesting.utils.AppException;

public interface UserService {

	JSONObject userLogin(HttpServletRequest request, String userName, String password) throws AppException;

	void setUserSessionData(HttpServletRequest request) throws AppException;

	JSONObject getUserSessionData(HttpServletRequest request) throws AppException;

}
