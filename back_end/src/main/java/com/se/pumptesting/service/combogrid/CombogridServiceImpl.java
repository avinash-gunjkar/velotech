
package com.se.pumptesting.service.combogrid;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.pumptesting.dtos.MotorMasterDto;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.models.Tbl00MotorMaster;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.ExtPagination;
import com.se.pumptesting.utils.RequestWrapper;

@Service
@Transactional
public class CombogridServiceImpl implements CombogridService {

	static Logger log = Logger.getLogger(CombogridServiceImpl.class.getName());

	@Autowired
	GenericDao genericDao;

	@Override
	public ApplicationResponse getMotorMasters(RequestWrapper requestWrapper) {

		ApplicationResponse applicationResponse = new ApplicationResponse();

		try {
			ExtPagination extPagination = requestWrapper.getExtPagination();
			extPagination.setSortProperty("id");
			extPagination.setSortDirection("DESC");

			applicationResponse = genericDao.getRecordsWithPagination(Tbl00MotorMaster.class, extPagination,
					GenericUtil.getConjuction(Tbl00MotorMaster.class, requestWrapper.getSearchCriterias()));

			List<Tbl00MotorMaster> models = (List<Tbl00MotorMaster>) applicationResponse.getData();
			long total = applicationResponse.getTotal();

			List<MotorMasterDto> dtos = getMotorMastersData(models);

			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, dtos, total);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	private List<MotorMasterDto> getMotorMastersData(List<Tbl00MotorMaster> models) {

		List<MotorMasterDto> dtos = new ArrayList<>();
		try {
			for (Tbl00MotorMaster model : models) {
				MotorMasterDto dto = new MotorMasterDto();
				BeanUtils.copyProperties(model, dto);
				dtos.add(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return dtos;
	}

}
