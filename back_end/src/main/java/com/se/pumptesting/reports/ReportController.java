package com.se.pumptesting.reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.se.pumptesting.reports.dto.ReportDto;
import com.se.pumptesting.reports.service.ReportService;
import com.se.pumptesting.utils.ApplicationResponse;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

	static Logger log = Logger.getLogger(ReportController.class.getName());

	@Autowired
	private ReportService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty
		// String to null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/getreport", method = RequestMethod.GET)
	public ApplicationResponse getReport(ReportDto dto) {

		ApplicationResponse controllerResponse = null;
		try {
			controllerResponse = service.getReport(dto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return controllerResponse;
	}
}
