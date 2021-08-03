
package com.se.pumptesting.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.user.service.UserService;
import com.se.pumptesting.utils.AppException;
import com.se.pumptesting.utils.ApplicationConstants;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	static Logger log = Logger.getLogger(UserController.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) throws AppException {

		JSONObject finalJSON = new JSONObject();
		try {
			finalJSON = userService.userLogin(request, username, password);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.CONTROLLER_EXCEPTION_MSG);
		}
		return finalJSON.toString();
	}

	@RequestMapping(value = "/setUserSessionData", method = RequestMethod.GET)

	public String setUserSessionData(HttpServletRequest request) throws AppException {

		try {
			userService.setUserSessionData(request);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.CONTROLLER_EXCEPTION_MSG);
		}
		return "true";
	}

	@RequestMapping(value = "/getUserSessionData", method = RequestMethod.GET)
	public String getUserSessionData(HttpServletRequest request) throws AppException {

		JSONObject finalJSON = new JSONObject();
		try {
			finalJSON = userService.getUserSessionData(request);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.CONTROLLER_EXCEPTION_MSG);
		}
		return finalJSON.toString();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) throws AppException {

		try {
			httpSession.invalidate();
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.CONTROLLER_EXCEPTION_MSG);
		}
		return "true";
	}
}
