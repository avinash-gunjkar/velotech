
package com.se.pumptesting.service;

import java.util.List;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.RequestWrapper;

public interface TestingDataService {

	public ApplicationResponse addTestingData(String requestPayload);

	public ApplicationResponse updateTestingData(String requestPayload);

	public ApplicationResponse deleteTestingData(List<Integer> ids);

	public ApplicationResponse getRecords();

	public ApplicationResponse getTestingData(Integer testingMasterId);

	public ApplicationResponse getReadings(List<String> parameters);

	

}
