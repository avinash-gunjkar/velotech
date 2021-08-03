
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.InstrumentMasterDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.models.Tbl08InstrumentMaster;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class InstrumentMasterServiceImpl implements MasterService {

	static Logger log = Logger.getLogger(ClientServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl08InstrumentMaster model = new Tbl08InstrumentMaster();
			InstrumentMasterDto dto = new InstrumentMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, InstrumentMasterDto.class);
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
			InstrumentMasterDto dto = new InstrumentMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, InstrumentMasterDto.class);
			Tbl08InstrumentMaster model = (Tbl08InstrumentMaster) genericDao.getRecordById(Tbl08InstrumentMaster.class, dto.getId());
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
			genericDao.deleteAll(Tbl08InstrumentMaster.class, ids);
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

			applicationResponse = genericDao.getRecordsWithPagination(Tbl08InstrumentMaster.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl08InstrumentMaster.class, requestWrapper.getSearchCriterias()));
			List<Tbl08InstrumentMaster> models = (List<Tbl08InstrumentMaster>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<InstrumentMasterDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<InstrumentMasterDto> getData(List<Tbl08InstrumentMaster> models) {

		List<InstrumentMasterDto> dtos = new ArrayList<>();
		try {
			for (Tbl08InstrumentMaster model : models) {
				InstrumentMasterDto dto = new InstrumentMasterDto();
				BeanUtils.copyProperties(model, dto);
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
