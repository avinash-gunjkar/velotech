
package com.se.pumptesting.generic.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ComboBox;
import com.se.pumptesting.utils.ExtPagination;
import com.se.pumptesting.utils.HibernateSession;

@Repository
public class GenericDaoImpl extends HibernateSession implements GenericDao {

	private static Logger log = Logger.getLogger(GenericDaoImpl.class.getName());

	@Override
	public boolean save(Object object) {

		boolean ans = false;
		try {
			getSession().save(object);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public boolean update(Object object) {

		boolean ans = false;
		try {
			getSession().update(object);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public boolean saveOrUpdate(Object object) {

		boolean ans = false;
		try {
			getSession().saveOrUpdate(object);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public boolean delete(Object object) {

		boolean ans = false;
		try {
			getSession().delete(object);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public boolean deleteAll(Object objects) {

		boolean ans = false;
		try {
			Collection<?> collection = (Collection<?>) objects;
			getHibernateTemplate().deleteAll(collection);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public boolean deleteAll(final Class<?> o, final List<Integer> ids) {

		boolean ans = false;
		try {
			Criteria criteria = getSession().createCriteria(o).add(Restrictions.in("id", ids));
			List<?> list = criteria.list();
			getHibernateTemplate().deleteAll(list);
			ans = true;
		} catch (Exception e) {
			ans = false;
			log.error(e.getMessage(), e);
		}
		return ans;
	}

	@Override
	public Object getRecordById(final Class<?> o, final Integer id) {

		Object object = null;
		try {
			object = getSession().get(o, id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return object;
	}

	@Override
	public Object getRecordByIds(final Class<?> o, final List<Integer> ids) {

		Object data = null;
		try {
			Criteria criteria = getSession().createCriteria(o).add(Restrictions.in("id", ids));
			List<?> list = criteria.list();
			data = list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return data;
	}

	@Override
	public ApplicationResponse getRecords(final Class<?> o, final Object restrictions) {

		ApplicationResponse response = new ApplicationResponse();
		Object data = null;
		try {
			Criteria criteria = getSessionWFilter().createCriteria(o);
			if (restrictions != null)
				criteria.add((Conjunction) restrictions);
			List<?> list = criteria.list();
			long total = list.size();
			data = list;
			response.setData(data);
			response.setTotal(total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ApplicationResponse getRecordsWithPagination(final Class<?> o, final ExtPagination extPagination,
			final Object restrictions) {

		ApplicationResponse response = new ApplicationResponse();
		Object data = null;
		try {
			Criteria criteria = getSessionWFilter().createCriteria(o);
			if (restrictions != null)
				criteria.add((Conjunction) restrictions);
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

	@Override
	public ApplicationResponse getComboRecords(Class<?> o, String displayField, String valueField,
			Object restrictions) {

		ApplicationResponse response = new ApplicationResponse();
		Object data = null;
		try {
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.property(displayField), "label");
			projectionList.add(Projections.property(valueField), "value");
			Criteria criteria = getSessionWFilter().createCriteria(o);
			criteria.setProjection(projectionList);
			criteria.add((Conjunction) restrictions);
			List<?> list = criteria.setResultTransformer(Transformers.aliasToBean(ComboBox.class)).list();
			long total = list.size();
			data = list;
			response.setData(data);
			response.setTotal(total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;
	}

	@Override
	public ApplicationResponse getRecordsByParentId(Class<?> o, String parentProperty, Integer parentId) {

		ApplicationResponse response = new ApplicationResponse();
		Object data = null;
		try {
			Criteria criteria = getSessionWFilter().createCriteria(o);
			criteria.add(Restrictions.eq(parentProperty, parentId));
			List<?> list = criteria.list();
			long total = list.size();
			data = list;
			response.setData(data);
			response.setTotal(total);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return response;
	}

	@Override
	public Object findByParam(Class<?> o, String property, String value) {

		Object data = null;
		try {
			Criteria criteria = getSessionWFilter().createCriteria(o);
			criteria.add(Restrictions.eq(property, value));
			List<?> list = criteria.list();
			data = list;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return data;
	}
}
