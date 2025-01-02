package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtExpDashboardDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.DatatableData;
import com.lonar.vendor.vendorportal.model.LtNotification;
import com.lonar.vendor.vendorportal.model.LtNotificationsPojo;

@Service
public class LtExpDashboardServiceImpl implements LtExpDashboardService,CodeMaster
{
	@Autowired
	LtExpDashboardDao ltExpDashboardDao;
	
	@Override
	public List<DatatableData> getExpenseCountByHeaderId(Long empId) throws Exception 
	{
		// TODO Auto-generated method stub
		List<DatatableData> dataList= ltExpDashboardDao.getExpenseCountByHeaderId(empId);
		for(DatatableData datatableData : dataList)
		{
			if(datatableData.getStatus().equals("DRAFTCODE"))
			{
				datatableData.setStatus(DRAFT);
			}
		}
		return dataList;
	}
	
	@Override
	public List<DatatableData> getExpenseTypeByEmpId(Long empId) throws Exception {
		// TODO Auto-generated method stub
		return ltExpDashboardDao.getExpenseTypeByEmpId(empId);
		
	}
	
	@Override
	public LtNotificationsPojo getNotificatioByEmpId(Long empId) throws Exception 
	{
		LtNotificationsPojo ltNotifications=new LtNotificationsPojo();
		List<LtNotification> notificationsList = ltExpDashboardDao.getNotificatioByEmpId(empId);
		ltNotifications.setNotificationTotal(notificationsList.size());
		ltNotifications.setNotificationsList(notificationsList);
		
		return ltNotifications;
	}

	
	

}
