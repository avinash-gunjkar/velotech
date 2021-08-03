
package com.se.pumptesting.utils;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public abstract class HibernateSession {

	@Autowired
	private SessionFactory sessionFactory;

	private VelotechUtil velotechUtil = new VelotechUtil();

	protected Session getSessionWFilter() {

		Filter filter = getSessionFactory().getCurrentSession().enableFilter("ClientIdCheck");
		filter.setParameter("clientIdParam", velotechUtil.getClientId());

		return getSessionFactory().getCurrentSession();
	}

	protected Session getSession() {

		return getSessionFactory().getCurrentSession();
	}

	protected HibernateTemplate getHibernateTemplate() {

		return new HibernateTemplate(getSessionFactory());
	}

	protected SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}
