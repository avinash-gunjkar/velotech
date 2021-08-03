
package com.se.pumptesting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.service.TestingMasterService;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

@RestController
@RequestMapping(value = "/testingmaster")
public class TestingMasterController {

	static Logger log = Logger.getLogger(TestingMasterController.class.getName());

	@Autowired
	private TestingMasterService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/addtestingmasterdata", method = RequestMethod.POST)
	public ApplicationResponse addTestingMasterData(@RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.addTestingData(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/updatetestingmasterdata", method = RequestMethod.POST)
	public ApplicationResponse updateTestingMasterData(@RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.updateTestingData(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/deletetestingmasterdata", method = RequestMethod.POST)
	public ApplicationResponse deleteTestingMasterData(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.deleteTestingData(requestWrapper.getIds());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getRecords", method = RequestMethod.GET)
	public ApplicationResponse getRecords(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.getRecords();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/gettestingmasterdata", method = RequestMethod.GET)
	public ApplicationResponse getTestingMasterData(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.getTestingData(requestWrapper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/gettestbedinstruments", method = RequestMethod.GET)
	public ApplicationResponse getTestbedInstruments(@RequestParam(value = "testbedMasterId") Integer testbedMasterId) {

		ApplicationResponse controllerResponse = null;
		try {
			controllerResponse = service.getTestbedInstruments(testbedMasterId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/copytestingmasters", method = RequestMethod.POST)
	public ApplicationResponse copyTestingMasters(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.copyTestingMasters(requestWrapper.getIds());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}
}
