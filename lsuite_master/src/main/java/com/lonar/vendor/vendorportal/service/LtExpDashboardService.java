package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.DatatableData;
import com.lonar.vendor.vendorportal.model.LtNotificationsPojo;

public interface LtExpDashboardService {
	
	
	List<DatatableData> getExpenseCountByHeaderId(Long empId) throws Exception;

	List<DatatableData> getExpenseTypeByEmpId(Long empId) throws Exception;
//
	LtNotificationsPojo getNotificatioByEmpId(Long empId) throws Exception;

}
