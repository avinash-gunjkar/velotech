
package com.se.pumptesting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.MasterSericeUtil;

@RestController
@RequestMapping(value = "/testbedinstrumentcontroller")
public class TestbedInstrumentController {

	static Logger log = Logger.getLogger(MasterController.class.getName());

	@Autowired
	private MasterSericeUtil serviceUtil;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApplicationResponse addRecord(@RequestParam(value = "serviceName") String serviceName, @RequestBody String requestPayload) {

		ApplicationResponse controllerResponse = null;
		try {
			MasterService service = serviceUtil.getService(serviceName);
			controllerResponse = service.addRecord(requestPayload);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (controllerResponse == null) {
			controllerResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.RECORD_EXIST_MSG);
		}
		return controllerResponse;
	}
}
