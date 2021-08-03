
package com.se.pumptesting.service.masters;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.pumptesting.dtos.UserMasterDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.models.Tbl00RoleMaster;
import com.se.pumptesting.models.Tbl00UserMaster;
import com.se.pumptesting.service.MasterService;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.ResourceUtil;
import com.se.pumptesting.utils.StringEncryptor;
import com.se.pumptesting.utils.VelotechUtil;

@Service
@Transactional
public class UserMasterServiceImpl implements MasterService {

	static Logger log = Logger.getLogger(UserMasterServiceImpl.class.getName());

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse addRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			Tbl00UserMaster model = new Tbl00UserMaster();
			UserMasterDto dto = new UserMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, UserMasterDto.class);
			if (dto.getRoleId() != 0)
				model.setTbl00RoleMaster(new Tbl00RoleMaster(dto.getRoleId()));
			BeanUtils.copyProperties(dto, model);
			model.setClientId(velotechUtil.getClientId());
			model.setPassword(encryptedPassword(dto.getPassword()));
			genericDao.save(model);
			applicationResponse = ApplicationResponseUtil.getResponseToCRUD(true, ApplicationConstants.INSERT_SUCCESS_MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return applicationResponse;
	}

	private String encryptedPassword(String password) {

		String ans = "";
		try {
			StringEncryptor stringEncrypter = new StringEncryptor(new ResourceUtil().getPropertyValues("encryptionScheme"),
					new ResourceUtil().getPropertyValues("encryptionKey"));
			ans = stringEncrypter.encrypt(password);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return ans;
	}

	@Override
	public ApplicationResponse updateRecord(String requestPayload) {

		ApplicationResponse applicationResponse = null;
		try {
			UserMasterDto dto = new UserMasterDto();
			ObjectMapper mapper = new ObjectMapper();
			dto = mapper.readValue(requestPayload, UserMasterDto.class);
			Tbl00UserMaster model = (Tbl00UserMaster) genericDao.getRecordById(Tbl00UserMaster.class, dto.getId());
			String password = dto.getPassword();
			if (!model.getPassword().equals(dto.getPassword()))
				password = encryptedPassword(dto.getPassword());
			if (dto.getRoleId() != 0)
				model.setTbl00RoleMaster(new Tbl00RoleMaster(dto.getRoleId()));
			BeanUtils.copyProperties(dto, model);
			model.setClientId(velotechUtil.getClientId());
			model.setPassword(password);

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
			genericDao.deleteAll(Tbl00UserMaster.class, ids);
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

			applicationResponse = genericDao.getRecordsWithPagination(Tbl00UserMaster.class, requestWrapper.getExtPagination(),
					GenericUtil.getConjuction(Tbl00UserMaster.class, requestWrapper.getSearchCriterias()));
			List<Tbl00UserMaster> models = (List<Tbl00UserMaster>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<UserMasterDto> dtos = getData(models);
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<UserMasterDto> getData(List<Tbl00UserMaster> models) {

		List<UserMasterDto> dtos = new ArrayList<>();
		try {
			for (Tbl00UserMaster model : models) {
				UserMasterDto dto = new UserMasterDto();
				BeanUtils.copyProperties(model, dto);
				if (model.getTbl00RoleMaster() != null) {
					dto.setRoleId(model.getTbl00RoleMaster().getId());
					dto.setRoleName(model.getTbl00RoleMaster().getName());
				}
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
