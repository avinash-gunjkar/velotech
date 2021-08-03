
package com.se.pumptesting.service.combobox;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.se.pumptesting.generic.dao.ComboboxDao;
import com.se.pumptesting.generic.dao.GenericDao;
import com.se.pumptesting.generic.util.GenericUtil;
import com.se.pumptesting.generic.util.SearchCriteria;
import com.se.pumptesting.models.Tbl00ModelMaster;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ApplicationResponseUtil;
import com.se.pumptesting.utils.ComboBox;
import com.se.pumptesting.utils.RequestWrapper;

@Service
@Transactional
public class ComboboxServiceImpl implements ComboboxService {

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private ComboboxDao dao;

	static Logger log = Logger.getLogger(ComboboxServiceImpl.class.getName());

	@Override
	public ApplicationResponse getComboRecords(RequestWrapper requestWrapper) {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			String model = requestWrapper.getModelName();
			String displayField = requestWrapper.getDisplayField();
			String valueField = requestWrapper.getValueField();
			String searchProperty = requestWrapper.getSearchProperty();
			Object searchValue = requestWrapper.getSearchValue();

			SearchCriteria searchCriteria = null;
			if (searchProperty != null) {
				searchCriteria = new SearchCriteria();
				searchCriteria.setSearchProperty(searchProperty);
				searchCriteria.setSearchValue(searchValue);
			}

			Class<?> modelClass = Class.forName(model);
			List<?> models = new ArrayList<>();

			applicationResponse = genericDao.getComboRecords(modelClass, displayField, valueField,
					GenericUtil.getConjuction(modelClass, searchCriteria));
			models = (List<?>) applicationResponse.getData();
			long total = applicationResponse.getTotal();
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, models, total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

	@Override
	public ApplicationResponse getModelMasters() {

		ApplicationResponse applicationResponse = new ApplicationResponse();
		try {
			List<Tbl00ModelMaster> tbl00ModelMasters = dao.getModelMasters();
			List<ComboBox> models = new ArrayList<>();
			for (Tbl00ModelMaster tbl00ModelMaster : tbl00ModelMasters) {
				ComboBox model = new ComboBox();
				model.setLabel(tbl00ModelMaster.getPumpModel());
				model.setValue(tbl00ModelMaster.getId());
				models.add(model);
			}
			applicationResponse = ApplicationResponseUtil.getResponseToGetData(true, ApplicationConstants.DATA_LOAD_SUCCESS_MSG, models,
					models.size());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return applicationResponse;
	}

}
