
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.TestbedMasterDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.models.Tbl03TestbedMaster;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class TestbedMasterServiceImpl implements MasterService {

	static Logger log = Logger.getLogger(TestbedMasterServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl03TestbedMaster model = new Tbl03TestbedMaster();
			TestbedMasterDto dto = new TestbedMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestbedMasterDto.class);
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
			TestbedMasterDto dto = new TestbedMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, TestbedMasterDto.class);
			Tbl03TestbedMaster model = (Tbl03TestbedMaster) genericDao.getRecordById(Tbl03TestbedMaster.class, dto.getId());
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
			genericDao.deleteAll(Tbl03TestbedMaster.class, ids);
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
		try {

			applicationResponse = genericDao.getRecordsWithPagination(Tbl03TestbedMaster.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl03TestbedMaster.class, requestWrapper.getSearchCriterias()));
			List<Tbl03TestbedMaster> models = (List<Tbl03TestbedMaster>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<TestbedMasterDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<TestbedMasterDto> getData(List<Tbl03TestbedMaster> models) {

		List<TestbedMasterDto> dtos = new ArrayList<>();
		try {
			for (Tbl03TestbedMaster model : models) {
				TestbedMasterDto dto = new TestbedMasterDto();
				BeanUtils.copyProperties(model, dto);
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
