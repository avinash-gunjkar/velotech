
package com.se.pumptesting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.service.combogrid.CombogridService;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

@RestController
@RequestMapping(value = "/combogrid")
public class CombogridController {

	static Logger log = Logger.getLogger(CombogridController.class.getName());

	@Autowired
	private CombogridService service;

	@RequestMapping(value = "/getmotormasters", method = RequestMethod.GET)
	public ApplicationResponse getMotorMasters(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {
			controllerResponse = service.getMotorMasters(requestWrapper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

}
