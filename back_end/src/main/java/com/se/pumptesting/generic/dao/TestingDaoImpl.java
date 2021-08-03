
package com.se.pumptesting.generic.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.pumptesting.dtos.TestingMasterDto;
import com.se.pumptesting.models.Tbl04TestingMaster;
import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ExtPagination;
import com.se.pumptesting.utils.HibernateSession;
import com.se.pumptesting.utils.RequestWrapper;
import com.se.pumptesting.utils.VelotechUtil;

@Repository
public class TestingDaoImpl extends HibernateSession implements TestingDao {

	static Logger log = Logger.getLogger(TestingDaoImpl.class.getName());

	@Autowired
	private VelotechUtil velotechUtil;

	@Override
	public ApplicationResponse getTestingMaster(RequestWrapper requestWrapper) {

		ApplicationResponse response = new ApplicationResponse();
		Object data = null;
		try {
			ExtPagination extPagination = requestWrapper.getExtPagination();
			TestingMasterDto dto = requestWrapper.getTestingMasterDto();
			Criteria criteria = getSessionWFilter().createCriteria(Tbl04TestingMaster.class);
			criteria.createAlias("tbl00MotorMaster", "tbl00MotorMaster");
			criteria.createAlias("tbl03TestbedMaster", "tbl03TestbedMaster");
			criteria.createAlias("tbl00ModelMaster", "tbl00ModelMaster");

			Conjunction conjunction = Restrictions.conjunction();

			if (dto != null) {

				if (dto.getStartDate() != null)
					conjunction.add(Restrictions.between("date", dto.getStartDate(), dto.getEndDate()));

				if (dto.getTestbedNo() != null)
					conjunction.add(Restrictions.like("tbl03TestbedMaster.testbedNo", dto.getTestbedNo(), MatchMode.ANYWHERE));

				if (dto.getMotorNo() != null)
					conjunction.add(Restrictions.like("tbl00MotorMaster.motorNo", dto.getMotorNo(), MatchMode.ANYWHERE));

				if (dto.getPumpModelName() != null)
					conjunction.add(Restrictions.like("tbl00ModelMaster.pumpModel", dto.getPumpModelName(), MatchMode.ANYWHERE));

				if (dto.getPumpSerialNo() != null)
					conjunction.add(Restrictions.like("pumpSerialNo", dto.getPumpSerialNo(), MatchMode.ANYWHERE));

				if (dto.getCustomerName() != null)
					conjunction.add(Restrictions.like("customerName", dto.getCustomerName(), MatchMode.ANYWHERE));
				if (dto.getWorkorderNo() != null)
					conjunction.add(Restrictions.like("workorderNo", dto.getWorkorderNo(), MatchMode.ANYWHERE));

			}
			criteria.add(conjunction);

			ScrollableResults results = criteria.scroll();
			results.last();
			long total = results.getRowNumber() + 1;
			results.close();
			if (extPagination != null) {
				criteria.setFirstResult(extPagination.getStart()).setMaxResults(extPagination.getLimit());
				if (extPagination.getSortProperty() != null)
					if (extPagination.getSortDirection().equals("ASC"))
						criteria.addOrder(Order.asc(extPagination.getSortProperty()));
					else
						criteria.addOrder(Order.desc(extPagination.getSortProperty()));
			}
			data = criteria.list();
			response.setData(data);
			response.setTotal(total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;
	}

}
