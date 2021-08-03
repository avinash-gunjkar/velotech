package com.se.pumptesting.service;

import java.util.List;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

public interface MasterService {

	public ApplicationResponse addRecord(String requestPayload);

	public ApplicationResponse updateRecord(String requestPayload);

	public ApplicationResponse deleteRecords(List<Integer> ids);

	public ApplicationResponse getRecords();

	public ApplicationResponse getRecordsWithPagination(RequestWrapper requestWrapper);
}
