package com.se.pumptesting.generic.dao;

import java.util.List;

import com.se.pumptesting.utils.ApplicationResponse;
import com.se.pumptesting.utils.ExtPagination;

public interface GenericDao {

	boolean save(Object object);

	boolean update(Object object);

	boolean saveOrUpdate(Object object);

	boolean delete(Object object);

	boolean deleteAll(Object objects);

	boolean deleteAll(Class<?> o, List<Integer> ids);

	Object getRecordById(Class<?> o, Integer id);

	Object getRecordByIds(Class<?> o, List<Integer> ids);

	ApplicationResponse getRecordsByParentId(Class<?> o, String parentProperty, Integer parentId);

	ApplicationResponse getRecords(Class<?> o, Object restrictions);

	ApplicationResponse getRecordsWithPagination(Class<?> o, ExtPagination extPagination, Object restrictions)
			throws Exception;

	ApplicationResponse getComboRecords(Class<?> o, String displayField, String valueField, Object restrictions)
			throws Exception;

	Object findByParam(Class<?> o, String fieldName, String value);

}
