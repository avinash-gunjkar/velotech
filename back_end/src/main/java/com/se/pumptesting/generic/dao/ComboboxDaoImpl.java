
package com.se.pumptesting.generic.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.se.pumptesting.models.Tbl00ModelMaster;
import com.se.pumptesting.utils.HibernateSession;

@Repository
public class ComboboxDaoImpl extends HibernateSession implements ComboboxDao {

	static Logger log = Logger.getLogger(ComboboxDaoImpl.class.getName());

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Tbl00ModelMaster> getModelMasters() {

		List<Tbl00ModelMaster> tbl00ModelMasters = null;
		try {

			Criteria criteria = getSessionWFilter().createCriteria(Tbl00ModelMaster.class);
			criteria.addOrder(Order.asc("pumpModel"));
			tbl00ModelMasters = criteria.list();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return tbl00ModelMasters;
	}

}
