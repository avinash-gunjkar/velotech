package com.se.pumptesting.reports.service;

import com.se.pumptesting.reports.dto.ReportDto;
import com.se.pumptesting.utils.ApplicationResponse;

public interface ReportService {

	ApplicationResponse getReport(ReportDto dto);

}
