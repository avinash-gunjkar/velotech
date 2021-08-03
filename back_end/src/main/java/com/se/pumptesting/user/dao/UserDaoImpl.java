
package com.se.pumptesting.user.dao;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.se.pumptesting.models.Tbl00UserMaster;
import com.se.pumptesting.utils.AppException;
import com.se.pumptesting.utils.ApplicationConstants;
import com.se.pumptesting.utils.HibernateSession;
import com.se.pumptesting.utils.ResourceUtil;
import com.se.pumptesting.utils.StringEncryptor;

@Repository
public class UserDaoImpl extends HibernateSession implements UserDao {

	static Logger log = Logger.getLogger(UserDaoImpl.class.getName());

	@Override
	public Tbl00UserMaster userLogin(String userName, String password) throws AppException {

		Tbl00UserMaster tbl00Usermaster = null;
		try {
			StringEncryptor stringEncrypter = new StringEncryptor(new ResourceUtil().getPropertyValues("encryptionScheme"),
					new ResourceUtil().getPropertyValues("encryptionKey"));
			String encryptedPassword = stringEncrypter.encrypt(password);
			tbl00Usermaster = (Tbl00UserMaster) getSession().createCriteria(Tbl00UserMaster.class).add(Restrictions.eq("userName", userName))
					.add(Restrictions.eq("isActive", true)).add(Restrictions.eq("password", encryptedPassword))
					.createAlias("tbl00RoleMaster", "tbl00RoleMaster").uniqueResult();

		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.DAO_EXCEPTION_MSG);
		}
		return tbl00Usermaster;
	}

	@Override
	public Tbl00UserMaster getUserMaster(Object userMasterId) throws AppException {

		Tbl00UserMaster tbl00Usermaster = null;
		try {
			tbl00Usermaster = getSession().get(Tbl00UserMaster.class, Integer.parseInt(userMasterId.toString()));
		} catch (NullPointerException e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.NULL_POINTER_EXCEPTION__MSG);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppException(ApplicationConstants.DAO_EXCEPTION_MSG);
		}
		return tbl00Usermaster;
	}

}
