
package com.se.pumptesting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.se.pumptesting.service.TestingDataService;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

@RestController
@RequestMapping(value = "/testingdata")
public class TestingDataController {

	static Logger log = Logger.getLogger(TestingDataController.class.getName());

	@Autowired
	private TestingDataService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/addtestingdata", method = RequestMethod.POST)
	public ApplicationResponse addTestingData(@RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.addTestingData(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/updatetestingdata", method = RequestMethod.POST)
	public ApplicationResponse updateTestingData(@RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.updateTestingData(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/deletetestingdata", method = RequestMethod.POST)
	public ApplicationResponse deleteTestingData(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.deleteTestingData(requestWrapper.getIds());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getRecords", method = RequestMethod.GET)
	public ApplicationResponse testingDataGetRecords(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.getRecords();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}
	
	@RequestMapping(value = "/gettestingdata", method = RequestMethod.GET)
	public ApplicationResponse getTestingData(@RequestParam(value = "testingMasterId") Integer testingMasterId) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.getTestingData(testingMasterId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getreadings")
	public ApplicationResponse getReadings(@RequestParam(value = "parameters") List<String> parameters) {

		ApplicationResponse controllerResponse = null;
		try {

			controllerResponse = service.getReadings(parameters);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

}
