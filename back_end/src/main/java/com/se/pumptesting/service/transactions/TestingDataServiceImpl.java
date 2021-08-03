
package com.se.pumptesting.service.transactions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.Reading;
import com.se.pumptesting.dtos.TestingDataDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.models.Tbl00MotorMaster;
import com.se.pumptesting.models.Tbl04TestingMaster;
import com.se.pumptesting.models.Tbl05TestingData;
import com.se.pumptesting.service.TestingDataService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.PumpTestingUtil;
import com.se.pumptesting.utils.TwinCatDM;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class TestingDataServiceImpl implements TestingDataService {

	static Logger log = Logger.getLogger(TestingDataServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Autowired
	private PumpTestingUtil pumpTestingUtil;

	@Override
	public ApplicationResponse addTestingData(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl05TestingData model = new Tbl05TestingData();
			TestingDataDto dto = new TestingDataDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestingDataDto.class);
			BeanUtils.copyProperties(dto, model);
			model.setTbl04TestingMaster(new Tbl04TestingMaster(dto.getTestingMasterId()));
			model.setClientId(velotechUtil.getClientId());
			Tbl04TestingMaster testingMaster = (Tbl04TestingMaster) genericDao.getRecordById(Tbl04TestingMaster.class, dto.getTestingMasterId());
			Tbl00MotorMaster motorMaster = (Tbl00MotorMaster) genericDao.getRecordById(Tbl00MotorMaster.class,
					testingMaster.getTbl00MotorMaster().getId());
			model = pumpTestingUtil.setTestingData(testingMaster, motorMaster, model);
			genericDao.save(model);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.INSERT_SUCCESS_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	@Override
	public ApplicationResponse updateTestingData(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			TestingDataDto dto = new TestingDataDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestingDataDto.class);
			Tbl05TestingData model = (Tbl05TestingData) genericDao.getRecordById(Tbl05TestingData.class, dto.getId());
			BeanUtils.copyProperties(dto, model);
			model.setClientId(velotechUtil.getClientId());
			Tbl04TestingMaster testingMaster = (Tbl04TestingMaster) genericDao.getRecordById(Tbl04TestingMaster.class, dto.getTestingMasterId());
			Tbl00MotorMaster motorMaster = (Tbl00MotorMaster) genericDao.getRecordById(Tbl00MotorMaster.class,
					testingMaster.getTbl00MotorMaster().getId());
			model = pumpTestingUtil.setTestingData(testingMaster, motorMaster, model);
			genericDao.update(model);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.INSERT_SUCCESS_MSG);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	@Override
	public ApplicationResponse deleteTestingData(List<Integer> ids) {

		ApplicationResponse applicationResponse = null;
		try {
			genericDao.deleteAll(Tbl05TestingData.class, ids);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.DELETE_SUCCESS_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	@Override
	public ApplicationResponse getRecords() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationResponse getTestingData(Integer testingMasterId) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			applicationResponse = genericDao.getRecordsByParentId(Tbl05TestingData.class, "tbl04TestingMaster.id", testingMasterId);
			List<Tbl05TestingData> models = (List<Tbl05TestingData>) applicationResponse.getData();
			long total = applicationResponse.getTotal();
			List<TestingDataDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<TestingDataDto> getData(List<Tbl05TestingData> models) {

		List<TestingDataDto> dtos = new ArrayList<>();
		try {
			for (Tbl05TestingData model : models) {
				TestingDataDto dto = new TestingDataDto();
				BeanUtils.copyProperties(model, dto);
				dto.setTestingMasterId(model.getTbl04TestingMaster().getId());
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

	@Override
	public ApplicationResponse getReadings(List<String> parameters) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			TwinCatDM twinCatDM = new TwinCatDM();
			String pre = "GVL.";
			List<Reading> readings = new ArrayList<>();
			for (String parameter : parameters) {
				Reading reading = new Reading();
				reading.setParameter(parameter);
				reading.setValue(twinCatDM.readValuefromTwincat(pre + parameter, 4, 2, "REAL"));
				readings.add(reading);
			}

			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, readings,
					readings.size());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

}
