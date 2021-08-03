
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.MotorNoLoadDetailsDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.generic.util.SearchCriteria;
import com.se.pumptesting.models.Tbl00MotorMaster;
import com.se.pumptesting.models.Tbl00MotorNoLoadDetails;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.HibernateSession;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class MotorNoLoadDetailsServiceImpl extends HibernateSession implements MasterService {

	static Logger log = Logger.getLogger(MotorNoLoadDetailsServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl00MotorNoLoadDetails model = new Tbl00MotorNoLoadDetails();
			MotorNoLoadDetailsDto dto = new MotorNoLoadDetailsDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, MotorNoLoadDetailsDto.class);
			if (dto.getMotorMasterId() != 0)
				model.setTbl00MotorMaster(new Tbl00MotorMaster(dto.getMotorMasterId()));
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
			MotorNoLoadDetailsDto dto = new MotorNoLoadDetailsDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, MotorNoLoadDetailsDto.class);
			Tbl00MotorNoLoadDetails model = (Tbl00MotorNoLoadDetails) genericDao.getRecordById(Tbl00MotorNoLoadDetails.class, dto.getId());
			if (dto.getMotorMasterId() != 0)
				model.setTbl00MotorMaster(new Tbl00MotorMaster(dto.getMotorMasterId()));
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
			genericDao.deleteAll(Tbl00MotorNoLoadDetails.class, ids);
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
			MotorNoLoadDetailsDto dto = requestWrapper.getMotorNoLoadDetailsDto();

			List<SearchCriteria> searchCriterias = new ArrayList<>();
			SearchCriteria searchCriteria = new SearchCriteria();
			if (dto != null) {
				searchCriteria.setSearchProperty("tbl00MotorMaster.id");
				searchCriteria.setSearchValue(dto.getMotorMasterId());
			}
			searchCriterias.add(searchCriteria);
			applicationResponse = genericDao.getRecordsWithPagination(Tbl00MotorNoLoadDetails.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl00MotorNoLoadDetails.class, searchCriterias));
			List<Tbl00MotorNoLoadDetails> models = (List<Tbl00MotorNoLoadDetails>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<MotorNoLoadDetailsDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<MotorNoLoadDetailsDto> getData(List<Tbl00MotorNoLoadDetails> models) {

		List<MotorNoLoadDetailsDto> dtos = new ArrayList<>();
		try {
			for (Tbl00MotorNoLoadDetails model : models) {
				MotorNoLoadDetailsDto dto = new MotorNoLoadDetailsDto();
				BeanUtils.copyProperties(model, dto);
				if (model.getTbl00MotorMaster() != null) {
					dto.setMotorMasterId(model.getTbl00MotorMaster().getId());
				}

				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
