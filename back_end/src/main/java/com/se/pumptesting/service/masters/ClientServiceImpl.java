
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.ClientDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.models.Tbl00Client;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class ClientServiceImpl implements MasterService {

	static Logger log = Logger.getLogger(ClientServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl00Client model = new Tbl00Client();
			ClientDto dto = new ClientDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, ClientDto.class);
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
			ClientDto dto = new ClientDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, ClientDto.class);
			Tbl00Client model = (Tbl00Client) genericDao.getRecordById(Tbl00Client.class, dto.getId());
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
			genericDao.deleteAll(Tbl00Client.class, ids);
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

			applicationResponse = genericDao.getRecordsWithPagination(Tbl00Client.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl00Client.class, requestWrapper.getSearchCriterias()));
			List<Tbl00Client> models = (List<Tbl00Client>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<ClientDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<ClientDto> getData(List<Tbl00Client> models) {

		List<ClientDto> dtos = new ArrayList<>();
		try {
			for (Tbl00Client model : models) {
				ClientDto dto = new ClientDto();
				BeanUtils.copyProperties(model, dto);
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
