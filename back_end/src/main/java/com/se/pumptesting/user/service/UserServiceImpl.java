
package com.se.pumptesting.user.service;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.models.Tbl00UserMaster;
import com.se.pumptesting.user.dao.UserDao;
import com.se.pumptesting.utils.AppException;
import com.se.pumptesting.utils.ApplicationConstants;

@Service
@Transactional(rollbackFor = AppException.class)
public class UserServiceImpl implements UserService {

	static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDao userDao;

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private ServletContext servletContext;

	@Override
	public JSONObject userLogin(final HttpServletRequest request, final String userName, final String password) throws AppException {

		JSONObject finalJSON = new JSONObject();

		Tbl00UserMaster tbl00UserMaster = new Tbl00UserMaster();
		try {
			tbl00UserMaster = userDao.userLogin(userName, password);

			if (tbl00UserMaster != null && tbl00UserMaster.getUserName().equals(userName)) {

				finalJSON.put("message", "do Login");
				finalJSON.put("success", true);

				HttpSession httpSession = request.getSession(true);
				setUserSessionData(httpSession, tbl00UserMaster);
			} else {
				finalJSON.put("data", "");
				finalJSON.put("message", "Wrong Combination of username and password");
				finalJSON.put("success", false);
			}

		}

		catch (AppException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage());
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.SERVICE_EXCEPTION_MSG);
		}
		return finalJSON;
	}

	private void setUserSessionData(HttpSession httpSession, Tbl00UserMaster tbl00UserMaster) {

		String path = servletContext.getRealPath(System.getProperty("file.separator") + "userTemp" + System.getProperty("file.separator"));

		String userRealPath = servletContext.getRealPath(System.getProperty("file.separator") + "userTemp" + System.getProperty("file.separator")
				+ httpSession.getId() + "" + tbl00UserMaster.getUserName());

		String userContextPath = servletContext.getContextPath() + System.getProperty("file.separator") + "userTemp"
				+ System.getProperty("file.separator") + httpSession.getId() + "" + tbl00UserMaster.getUserName();

		String projectRealPath = servletContext.getRealPath("");

		File f0 = new File(path);
		if (!f0.exists()) {
			f0.mkdir();
		}

		File f = new File(userRealPath);
		if (!f.exists()) {
			f.mkdir();
		}

		httpSession.setAttribute("clientId", tbl00UserMaster.getClientId());
		httpSession.setAttribute("userMasterId", tbl00UserMaster.getId());
		httpSession.setAttribute("userName", tbl00UserMaster.getUserName());
		httpSession.setAttribute("userRealPath", userRealPath);
		httpSession.setAttribute("userContextPath", userContextPath);
		httpSession.setAttribute("projectRealPath", projectRealPath);
	}

	@Override
	public void setUserSessionData(final HttpServletRequest request) throws AppException {

		try {
			HttpSession httpSession = request.getSession();

			Integer userMasterId = (Integer) httpSession.getAttribute("userMasterId");
			Tbl00UserMaster tbl00UserMaster = (Tbl00UserMaster) genericDao.getRecordById(Tbl00UserMaster.class, userMasterId);

			String path = servletContext.getRealPath(System.getProperty("file.separator") + "userTemp" + System.getProperty("file.separator"));

			String userRealPath = servletContext.getRealPath(System.getProperty("file.separator") + "userTemp" + System.getProperty("file.separator")
					+ httpSession.getId() + "" + httpSession.getAttribute("userName"));

			String userContextPath = servletContext.getContextPath() + System.getProperty("file.separator") + "userTemp"
					+ System.getProperty("file.separator") + httpSession.getId() + "" + httpSession.getAttribute("userName");

			String projectRealPath = servletContext.getRealPath("");

			File f0 = new File(path);
			if (!f0.exists()) {
				f0.mkdir();
			}

			File f = new File(userRealPath);
			if (!f.exists()) {
				f.mkdir();
			}

			httpSession.setAttribute("clientId", tbl00UserMaster.getClientId());
			httpSession.setAttribute("userMasterId", tbl00UserMaster.getId());
			httpSession.setAttribute("userName", tbl00UserMaster.getUserName());
			httpSession.setAttribute("userRealPath", userRealPath);
			httpSession.setAttribute("userContextPath", userContextPath);
			httpSession.setAttribute("projectRealPath", projectRealPath);
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.SERVICE_EXCEPTION_MSG);
		}
	}

	@Override
	public JSONObject getUserSessionData(final HttpServletRequest request) throws AppException {

		JSONObject finalJSON = new JSONObject();
		JSONObject dataJSON = new JSONObject();
		try {
			HttpSession httpSession = request.getSession();
			Tbl00UserMaster tbl00UserMaster = userDao.getUserMaster(httpSession.getAttribute("userMasterId"));

			dataJSON.put("username", tbl00UserMaster.getUserName());
			dataJSON.put("userrole", tbl00UserMaster.getTbl00RoleMaster().getName());
			finalJSON.put("data", dataJSON);
			finalJSON.put("success", true);
		} catch (AppException e) {
			log.error(e.getMessage(), e);
			throw new AppException(e.getMessage());
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.SERVICE_EXCEPTION_MSG);
		}
		return finalJSON;
	}

}
