
package com.se.pumptesting.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.service.combobox.ComboboxService;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

@RestController
@RequestMapping(value = "/combobox")
public class ComboboxController {

	static Logger log = Logger.getLogger(ComboboxController.class.getName());

	@Autowired
	private ComboboxService service;

	@RequestMapping(value = "/getcomborecords", method = RequestMethod.GET)
	public ApplicationResponse getComboRecords(RequestWrapper requestWrapper) {

		ApplicationResponse controllerResponse = null;
		try {
			controllerResponse = service.getComboRecords(requestWrapper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

	@RequestMapping(value = "/getmodelmasters", method = RequestMethod.GET)
	public ApplicationResponse getModelMasters() {

		ApplicationResponse controllerResponse = null;
		try {
			controllerResponse = service.getModelMasters();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}

}
