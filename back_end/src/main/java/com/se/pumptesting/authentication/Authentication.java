
package com.se.pumptesting.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebFilter("/hello")
public class Authentication implements Filter {

	@Override
	public void destroy() {

		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean success = false;

		HttpSession session = httpRequest.getSession(false);
		if (session == null || !httpRequest.isRequestedSessionIdValid()) {
			sendResponse(httpResponse, "NO ACTIVE SESSION.PLEASE RE-LOGIN.", success);
		} else if ((boolean) httpRequest.getSession().getAttribute("isSuperAdmin")) {
			chain.doFilter(request, response);
		} else {
			String requestType = httpRequest.getParameter("requestType");
			Integer treeMenuMasterId = Integer.parseInt(httpRequest.getParameter("treeMenuMasterId"));

			HashMap<Integer, UserMenuModel> userMenuHashMap = (HashMap<Integer, UserMenuModel>) httpRequest.getSession()
					.getAttribute("userMenuHashMap");
			UserMenuModel model = userMenuHashMap.get(treeMenuMasterId);
			if (model != null)
				switch (requestType.toLowerCase()) {
				case "data":
					if (model.isGetData())
						chain.doFilter(request, response);
					else
						sendResponse(httpResponse, "YOU ARE NOT AUTHORIZED TO SEE DATA.", success);
					break;
				case "add":
					if (model.isAddData())
						chain.doFilter(request, response);
					else
						sendResponse(httpResponse, "YOU ARE NOT AUTHORIZED TO ADD DATA.", success);
					break;
				case "edit":
					if (model.isEditData())
						chain.doFilter(request, response);
					else
						sendResponse(httpResponse, "YOU ARE NOT AUTHORIZED TO EDIT DATA.", success);
					break;
				case "delete":
					if (model.isDeleteData())
						chain.doFilter(request, response);
					else
						sendResponse(httpResponse, "YOU ARE NOT AUTHORIZED TO DELETE DATA.", success);
					break;
				default:
					chain.doFilter(request, response);
					break;
				}
			else
				sendResponse(httpResponse, "YOU HAVE NO ACCESS.", success);

		}

		return;
	}

	private void sendResponse(HttpServletResponse httpResponse, String msg, boolean success) {

		PrintWriter out;
		JSONObject result = new JSONObject();
		try {
			out = httpResponse.getWriter();
			httpResponse.setContentType("application/json");
			httpResponse.setHeader("Cache-Control", "no-store");
			result.put("message", "AUTHENTICATION FAILED");
			result.put("data", msg);
			result.put("success", success);
			out.print(result);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		// TODO Auto-generated method stub

	}

}
