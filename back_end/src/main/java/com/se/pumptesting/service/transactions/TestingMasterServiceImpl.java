
package com.se.pumptesting.service.transactions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.TestbedInstrumentDto;
import com.se.pumptesting.dtos.TestingMasterDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.dao.TestingDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.generic.util.SearchCriteria;
import com.se.pumptesting.models.Tbl00ModelMaster;
import com.se.pumptesting.models.Tbl00MotorMaster;
import com.se.pumptesting.models.Tbl03TestbedMaster;
import com.se.pumptesting.models.Tbl04TestingMaster;
import com.se.pumptesting.models.Tbl05TestingData;
import com.se.pumptesting.models.Tbl07TestbedInstrument;
import com.se.pumptesting.service.TestingMasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.PumpTestingUtil;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class TestingMasterServiceImpl implements TestingMasterService {

	static Logger log = Logger.getLogger(TestingMasterServiceImpl.class.getName());

	@Autowired
	private TestingDao testingDao;

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
			Tbl04TestingMaster model = new Tbl04TestingMaster();
			TestingMasterDto dto = new TestingMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestingMasterDto.class);
			BeanUtils.copyProperties(dto, model);
			model.setTbl00MotorMaster(new Tbl00MotorMaster(dto.getMotorMasterId()));
			model.setTbl03TestbedMaster(new Tbl03TestbedMaster(dto.getTestbedMasterId()));
			model.setTbl00ModelMaster(new Tbl00ModelMaster(dto.getModelMasterId()));
			model.setClientId(velotechUtil.getClientId());
			genericDao.save(model);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.INSERT_SUCCESS_MSG,
					dto.getTestbedMasterId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationResponse updateTestingData(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			TestingMasterDto dto = new TestingMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestingMasterDto.class);
			Tbl04TestingMaster model = (Tbl04TestingMaster) genericDao.getRecordById(Tbl04TestingMaster.class, dto.getId());
			Boolean dataChange = false;
			if (model.getSuctionPipeSize() != dto.getSuctionPipeSize() || model.getDischargePipeSize() != dto.getDischargePipeSize()
					|| model.getDpSpeed() != dto.getDpSpeed())
				dataChange = true;
			BeanUtils.copyProperties(dto, model);
			model.setTbl00MotorMaster(new Tbl00MotorMaster(dto.getMotorMasterId()));
			model.setTbl03TestbedMaster(new Tbl03TestbedMaster(dto.getTestbedMasterId()));
			model.setTbl00ModelMaster(new Tbl00ModelMaster(dto.getModelMasterId()));
			genericDao.update(model);

			if (dataChange) {
				List<Tbl05TestingData> tbl05TestingDatas = (List<Tbl05TestingData>) genericDao
						.getRecordsByParentId(Tbl05TestingData.class, "tbl04TestingMaster.id", model.getId()).getData();
				Tbl00MotorMaster motorMaster = (Tbl00MotorMaster) genericDao.getRecordById(Tbl00MotorMaster.class,
						model.getTbl00MotorMaster().getId());
				for (Tbl05TestingData tbl05TestingData : tbl05TestingDatas) {
					tbl05TestingData = pumpTestingUtil.setTestingData(model, motorMaster, tbl05TestingData);
					genericDao.update(tbl05TestingData);
				}
			}
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.INSERT_SUCCESS_MSG,
					dto.getTestbedMasterId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	@Override
	public ApplicationResponse deleteTestingData(List<Integer> ids) {

		ApplicationResponse applicationResponse = null;
		try {
			genericDao.deleteAll(Tbl04TestingMaster.class, ids);
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
	public ApplicationResponse getTestingData(RequestWrapper requestWrapper) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			applicationResponse = testingDao.getTestingMaster(requestWrapper);

			List<Tbl04TestingMaster> models = (List<Tbl04TestingMaster>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<TestingMasterDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<TestingMasterDto> getData(List<Tbl04TestingMaster> models) {

		List<TestingMasterDto> dtos = new ArrayList<>();
		try {
			for (Tbl04TestingMaster model : models) {
				TestingMasterDto dto = new TestingMasterDto();
				BeanUtils.copyProperties(model, dto);
				dto.setMotorMasterId(model.getTbl00MotorMaster().getId());
				dto.setMotorNo(model.getTbl00MotorMaster().getMotorNo());
				dto.setPowerHp(model.getTbl00MotorMaster().getPowerHp());
				dto.setTestbedMasterId(model.getTbl03TestbedMaster().getId());
				dto.setTestbedNo(model.getTbl03TestbedMaster().getTestbedNo());
				dto.setModelMasterId(model.getTbl00ModelMaster().getId());
				dto.setPumpModelName(model.getTbl00ModelMaster().getPumpModel());

				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

	@Override
	public ApplicationResponse getTestbedInstruments(Integer testbedMasterId) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			List<SearchCriteria> searchCriterias = new ArrayList<>();
			SearchCriteria searchCriteria = new SearchCriteria();
			searchCriteria.setSearchProperty("tbl03TestbedMaster.id");
			searchCriteria.setSearchValue(testbedMasterId);
			searchCriterias.add(searchCriteria);
			Conjunction conjuction = GenericUtil.getConjuction(Tbl07TestbedInstrument.class, searchCriterias);
			applicationResponse = genericDao.getRecords(Tbl07TestbedInstrument.class, conjuction);
			List<Tbl07TestbedInstrument> models = (List<Tbl07TestbedInstrument>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<TestbedInstrumentDto> dtos = getTestbedInstrumentsData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<TestbedInstrumentDto> getTestbedInstrumentsData(List<Tbl07TestbedInstrument> models) {

		List<TestbedInstrumentDto> dtos = new ArrayList<>();
		for (Tbl07TestbedInstrument model : models) {
			TestbedInstrumentDto dto = new TestbedInstrumentDto();
			dto.setId(model.getId());
			dto.setIsActive(model.getIsActive());
			dto.setInstrumentMasterId(model.getTbl08InstrumentMaster().getId());
			dto.setColumnName(model.getTbl08InstrumentMaster().getColumnName());
			dto.setInstrumentMasterName(model.getTbl08InstrumentMaster().getName());
			dto.setParameter(model.getTbl08InstrumentMaster().getParameter());
			dto.setTestbedMasterId(model.getTbl03TestbedMaster().getId());
			dtos.add(dto);
		}
		return dtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApplicationResponse copyTestingMasters(List<Integer> ids) {

		ApplicationResponse applicationResponse = null;
		try {
			List<Tbl04TestingMaster> tbl04TestingMasters = (List<Tbl04TestingMaster>) genericDao.getRecordByIds(Tbl04TestingMaster.class, ids);

			for (Tbl04TestingMaster tbl04TestingMasterOld : tbl04TestingMasters) {
				Tbl04TestingMaster tbl04TestingMasterNew = new Tbl04TestingMaster();
				BeanUtils.copyProperties(tbl04TestingMasterOld, tbl04TestingMasterNew);
				genericDao.save(tbl04TestingMasterNew);

				List<Tbl05TestingData> tbl05TestingDatas = (List<Tbl05TestingData>) genericDao
						.getRecordsByParentId(Tbl05TestingData.class, "tbl04TestingMaster.id", tbl04TestingMasterOld.getId()).getData();

				for (Tbl05TestingData tbl05TestingDataOld : tbl05TestingDatas) {
					Tbl05TestingData tbl05TestingDataNew = new Tbl05TestingData();
					BeanUtils.copyProperties(tbl05TestingDataOld, tbl05TestingDataNew);
					tbl05TestingDataNew.setTbl04TestingMaster(tbl04TestingMasterNew);
					genericDao.save(tbl05TestingDataNew);
				}
			}

			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.COPY_SUCCESS_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

}
