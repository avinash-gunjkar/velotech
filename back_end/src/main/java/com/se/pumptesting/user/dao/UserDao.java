
package com.se.pumptesting.user.dao;

import com.se.pumptesting.models.Tbl00UserMaster;
import com.se.pumptesting.utils.AppException;

public interface UserDao {

	Tbl00UserMaster userLogin(String userName, String password) throws AppException;

	Tbl00UserMaster getUserMaster(Object userMasterId) throws AppException;

}
