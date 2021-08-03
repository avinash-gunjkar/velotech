
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

import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.MasterSericeUtil;
import com.se.pumptesting.utils.RequestWrapper;

@RestController
@RequestMapping(value = "/master")
public class MasterController {

	static Logger log = Logger.getLogger(MasterController.class.getName());

	@Autowired
	private MasterSericeUtil serviceUtil;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApplicationResponse addRecord(@RequestParam(value = "serviceName") String serviceName, @RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.addRecord(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ApplicationResponse updateRecord(@RequestParam(value = "serviceName") String serviceName, @RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.updateRecord(requestPayload);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApplicationResponse deleteRecords(@RequestParam(value = "serviceName") String serviceName, RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.deleteRecords(requestWrapper.getIds());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getRecords", method = RequestMethod.GET)
	public ApplicationResponse getRecords(@RequestParam(value = "serviceName") String serviceName, RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.getRecords();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getRecordsWithPagination", method = RequestMethod.GET)
	public ApplicationResponse getRecordsWithPagination(@RequestParam(value = "serviceName") String serviceName, RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.getRecordsWithPagination(requestWrapper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

}
