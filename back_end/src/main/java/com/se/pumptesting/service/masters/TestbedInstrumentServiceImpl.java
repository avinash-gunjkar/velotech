
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.TestbedInstrumentDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.generic.util.SearchCriteria;
import com.se.pumptesting.models.Tbl03TestbedMaster;
import com.se.pumptesting.models.Tbl07TestbedInstrument;
import com.se.pumptesting.models.Tbl08InstrumentMaster;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.HibernateSession;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class TestbedInstrumentServiceImpl extends HibernateSession implements MasterService {

	static Logger log = Logger.getLogger(TestbedInstrumentServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl07TestbedInstrument model = new Tbl07TestbedInstrument();
			TestbedInstrumentDto dto = new TestbedInstrumentDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestbedInstrumentDto.class);
			if (dto.getTestbedMasterId() != 0)
				model.setTbl03TestbedMaster(new Tbl03TestbedMaster(dto.getTestbedMasterId()));
			if (dto.getInstrumentMasterId() != 0)
				model.setTbl08InstrumentMaster(new Tbl08InstrumentMaster(dto.getInstrumentMasterId()));
			BeanUtils.copyProperties(dto, model);
			model.setClientId(velotechUtil.getClientId());
			genericDao.save(model);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.INSERT_SUCCESS_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		}
		return applicationResponse;

	}

	@Override
	public ApplicationResponse updateRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			TestbedInstrumentDto dto = new TestbedInstrumentDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestbedInstrumentDto.class);
			Tbl07TestbedInstrument model = (Tbl07TestbedInstrument) genericDao.getRecordById(Tbl07TestbedInstrument.class, dto.getId());
			if (dto.getTestbedMasterId() != 0)
				model.setTbl03TestbedMaster(new Tbl03TestbedMaster(dto.getTestbedMasterId()));
			if (dto.getInstrumentMasterId() != 0)
				model.setTbl08InstrumentMaster(new Tbl08InstrumentMaster(dto.getInstrumentMasterId()));
			BeanUtils.copyProperties(dto, model);
			model.setClientId(velotechUtil.getClientId());
			genericDao.update(model);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.INSERT_SUCCESS_MSG);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	@Override
	public ApplicationResponse deleteRecords(List<Integer> ids) {

		ApplicationResponse applicationResponse = null;
		try {
			genericDao.deleteAll(Tbl07TestbedInstrument.class, ids);
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
	public ApplicationResponse getRecordsWithPagination(RequestWrapper requestWrapper) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		Object data = null;
		try {
			TestbedInstrumentDto dto = requestWrapper.getTestbedInstrumentDto();

			List<SearchCriteria> searchCriterias = new ArrayList<>();
			SearchCriteria searchCriteria = new SearchCriteria();
			if (dto != null) {
				searchCriteria.setSearchProperty("tbl03TestbedMaster.id");
				searchCriteria.setSearchValue(dto.getTestbedMasterId());
			}
			searchCriterias.add(searchCriteria);
			applicationResponse = genericDao.getRecordsWithPagination(Tbl07TestbedInstrument.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl07TestbedInstrument.class, searchCriterias));
			List<Tbl07TestbedInstrument> models = (List<Tbl07TestbedInstrument>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<TestbedInstrumentDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<TestbedInstrumentDto> getData(List<Tbl07TestbedInstrument> models) {

		List<TestbedInstrumentDto> dtos = new ArrayList<>();
		try {
			for (Tbl07TestbedInstrument model : models) {
				TestbedInstrumentDto dto = new TestbedInstrumentDto();
				BeanUtils.copyProperties(model, dto);
				if (model.getTbl03TestbedMaster() != null) {
					dto.setTestbedMasterId(model.getTbl03TestbedMaster().getId());
					dto.setTestbedMasterNo(model.getTbl03TestbedMaster().getTestbedNo());
				}
				if (model.getTbl08InstrumentMaster() != null) {
					dto.setInstrumentMasterId(model.getTbl08InstrumentMaster().getId());
					dto.setInstrumentMasterName(model.getTbl08InstrumentMaster().getName());
				}
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
