
package com.se.pumptesting.utils;

import java.util.List;

import com.se.pumptesting.dtos.MotorNoLoadDetailsDto;
import com.se.pumptesting.dtos.TestbedInstrumentDto;
import com.se.pumptesting.dtos.TestingDataDto;
import com.se.pumptesting.dtos.TestingMasterDto;
import com.se.pumptesting.dtos.WorkOrderDetailsDto;
import com.se.pumptesting.generic.util.SearchCriteria;

public class RequestWrapper {

	private int start;

	private int limit;

	private String sort;

	private String modelName;

	private String serviceName;

	private String displayField;

	private String valueField;

	private List<Integer> ids;

	private String requestPayload;

	private List<SearchCriteria> searchCriterias;

	private String searchProperty;

	private MotorNoLoadDetailsDto motorNoLoadDetailsDto;

	private WorkOrderDetailsDto workOrderDetailsDto;

	private TestingDataDto testingDataDto;

	private TestingMasterDto testingMasterDto;

	private TestbedInstrumentDto testbedInstrumentDto;

	private Object searchValue;

	public int getStart() {

		return start;
	}

	public void setStart(int start) {

		this.start = start;
	}

	public int getLimit() {

		return limit;
	}

	public void setLimit(int limit) {

		this.limit = limit;
	}

	public String getSort() {

		return sort;
	}

	public void setSort(String sort) {

		this.sort = sort;
	}

	public String getModelName() {

		return modelName;
	}

	public void setModelName(String modelName) {

		this.modelName = modelName;
	}

	public String getServiceName() {

		return serviceName;
	}

	public void setServiceName(String serviceName) {

		this.serviceName = serviceName;
	}

	public String getDisplayField() {

		return displayField;
	}

	public void setDisplayField(String displayField) {

		this.displayField = displayField;
	}

	public String getValueField() {

		return valueField;
	}

	public void setValueField(String valueField) {

		this.valueField = valueField;
	}

	public List<Integer> getIds() {

		return ids;
	}

	public void setIds(List<Integer> ids) {

		this.ids = ids;
	}

	public String getRequestPayload() {

		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {

		this.requestPayload = requestPayload;
	}

	public List<SearchCriteria> getSearchCriterias() {

		return searchCriterias;
	}

	public void setSearchCriterias(List<SearchCriteria> searchCriterias) {

		this.searchCriterias = searchCriterias;
	}

	public String getSearchProperty() {

		return searchProperty;
	}

	public void setSearchProperty(String searchProperty) {

		this.searchProperty = searchProperty;
	}

	public Object getSearchValue() {

		return searchValue;
	}

	public void setSearchValue(Object searchValue) {

		this.searchValue = searchValue;
	}

	/**
	 * @return the extPagination
	 */
	public ExtPagination getExtPagination() {

		return new ExtPagination(this.start, this.limit, this.sort);
	}

	public TestbedInstrumentDto getTestbedInstrumentDto() {

		return testbedInstrumentDto;
	}

	public void setTestbedInstrumentDto(TestbedInstrumentDto testbedInstrumentDto) {

		this.testbedInstrumentDto = testbedInstrumentDto;
	}

	public TestingMasterDto getTestingMasterDto() {

		return testingMasterDto;
	}

	public void setTestingMasterDto(TestingMasterDto testingMasterDto) {

		this.testingMasterDto = testingMasterDto;
	}

	public TestingDataDto getTestingDataDto() {

		return testingDataDto;
	}

	public void setTestingDataDto(TestingDataDto testingDataDto) {

		this.testingDataDto = testingDataDto;
	}

	public MotorNoLoadDetailsDto getMotorNoLoadDetailsDto() {

		return motorNoLoadDetailsDto;
	}

	public void setMotorNoLoadDetailsDto(MotorNoLoadDetailsDto motorNoLoadDetailsDto) {

		this.motorNoLoadDetailsDto = motorNoLoadDetailsDto;
	}

	public WorkOrderDetailsDto getWorkOrderDetailsDto() {

		return workOrderDetailsDto;
	}

	public void setWorkOrderDetailsDto(WorkOrderDetailsDto workOrderDetailsDto) {

		this.workOrderDetailsDto = workOrderDetailsDto;
	}

}
