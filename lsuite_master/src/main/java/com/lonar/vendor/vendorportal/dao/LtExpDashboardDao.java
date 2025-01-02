package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.DatatableData;
import com.lonar.vendor.vendorportal.model.LtNotification;

public interface LtExpDashboardDao {
	
	List<DatatableData> getExpenseCountByHeaderId(Long empId) throws Exception;

	List<DatatableData> getExpenseTypeByEmpId(Long empId) throws Exception;
//
	List<LtNotification> getNotificatioByEmpId(Long empId) throws Exception;

}
