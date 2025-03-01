package com.lonar.vendor.vendorportal.dao;

import java.util.Date;
import java.util.List;

import com.lonar.vendor.vendorportal.controller.VendorBuyerDetails;
import com.lonar.vendor.vendorportal.model.DashboardDetails;
import com.lonar.vendor.vendorportal.model.LtPoHeaders;
import com.lonar.vendor.vendorportal.model.LtPoReport;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPoHeadersDao 
{

	Long getLtPoHeaderCount(LtPoHeaders input, Long companyId) throws ServiceException;

	List<LtPoHeaders> getLtPoHeaderDataTable(LtPoHeaders input, Long companyId) throws ServiceException;

	List<LtPoHeaders> getAllPoHeaders() throws ServiceException;

	List<LtPoHeaders> getAllActivePoHeaders() throws ServiceException;

	LtPoHeaders getPoHeaderById(Long poHeaderId) throws ServiceException;

	Long getLtPoHeaderCountByVendorId(LtPoHeaders input, Long venId) throws ServiceException;

	List<LtPoHeaders> getLtPoHeadersDataTableByVendorId(LtPoHeaders input, Long venId) throws ServiceException;

	DashboardDetails getAmountByVendorId(Long vendorId) throws ServiceException;

	List<DashboardDetails> getCountAndStatusByVendorId(Long vendorId) throws ServiceException;

	List<LtPoHeaders> getTopFivePoById(Long vendorId) throws ServiceException;
	
	List<DashboardDetails> getStatusCountByBuyerId(Long buyerId, Long companyId) throws ServiceException;
	
	List<VendorBuyerDetails> getVendorMsgByBuyerId(Long buyerId) throws ServiceException;
	
	List<DashboardDetails> getQtrStatusCountByBuyerId(Long buyerId, Long year) throws ServiceException;

	boolean acknowldge(LtPoHeaders ltPoHeaders) throws ServiceException;

	List<LtPoHeaders> getActivePoHeadersByPoNumber(Long companyId, Long userId, String poNumber) throws ServiceException;

	public List<LtPoReport> createPOPDFReport(Long poNumber,Long companyId);

	boolean upDateStatus(Long poHeaderId, String status, String currentApprovalLavel) throws ServiceException;

	boolean chkForApprovers(Long poHeaderId) throws ServiceException;

	boolean submitForApproval(Date date, Long poHeaderId, String status, Object object) throws ServiceException;

	Status callPoValidationProc(Long poHeaderId) throws ServiceException;

	String checkforApprovals(Long poHeaderId) throws ServiceException;

	boolean checkStatusIsPending(Long poHeaderId, Long approvalId) throws ServiceException;

	LtPoHeaders getPoStatusById(Long poHeaderId) throws ServiceException;

	Long save(LtPoHeaders ltPoHeaders) throws ServiceException;

	boolean loadApprovers(LtPoHeaders ltPoHeaders) throws ServiceException;

	List<PoApproval> getApprovalList(Long poHeaderId, String currentApprovalLevel) throws ServiceException;

	LtPoHeaders getByPoNumVendAndAddr(String poNumber, Long vendorId, Long vendorAddId) throws ServiceException;

	List<LtPoHeaders> getInprocessPoList(String inprogressStr) throws ServiceException;

	PoApproval getApprovalLevel(Long poHeaderId) throws ServiceException;

	String getNextApprovalLevel(Long poHeaderId, String currentApprovalLavel) throws ServiceException;

	void updateCurrentApprovalLevel(Long poHeaderId, String currentApprovalLavel) throws ServiceException;

}
