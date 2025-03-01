package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtPoInboxDao {

	Long getPoCount(String status1, String approvalId, PoApproval input)throws ServiceException;

	List<PoApproval> getPoByStatus(String status1, String approvalId, PoApproval input)throws ServiceException;

}
