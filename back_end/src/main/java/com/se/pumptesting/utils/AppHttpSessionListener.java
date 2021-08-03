package com.se.pumptesting.utils;

import java.io.File;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class AppHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {

		System.out.println("Accounts sessionCreated ID : " + httpSessionEvent.getSession().getId());
		httpSessionEvent.getSession().setMaxInactiveInterval(2 * 60 * 60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

		System.out.println("sessionDestroyed ID: " + httpSessionEvent.getSession().getId());
		if (httpSessionEvent.getSession().getAttribute("userMasterId") != null) {
			try {
				String path = (String) httpSessionEvent.getSession().getAttribute("userRealPath");
				File f1 = new File(path);
				if (f1.exists()) {
					File[] files = f1.listFiles();
					for (Integer i = 0; i < files.length; i++) {
						if (files[i].isFile())
							files[i].delete();
					}
					f1.delete();
				}
				System.out.println("sessionDestroyed User: " + httpSessionEvent.getSession().getAttribute("userName"));
			} catch (Exception ex) {
				System.out.println("sessionDestroyed User: " + httpSessionEvent.getSession().getAttribute("userName")
						+ " Can't able to delete all files.");
			}

		}
	}
}
