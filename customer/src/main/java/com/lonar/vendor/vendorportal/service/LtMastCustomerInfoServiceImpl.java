package com.lonar.vendor.vendorportal.service;

import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerAttachmentsDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerInfoDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteCommercialsDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteContactDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteGenInfoDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerSitesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastCustomerInfoServiceImpl implements LtMastCustomerInfoService, CodeMaster {

	@Autowired
	LtMastCustomerInfoDao ltMastCustomerInfoDao;

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtCustomerApprovalDao ltCustomerApprovalDao;
	
	@Autowired
	LtMastCustomerSiteGenInfoDao ltMastCustomerSiteGenInfoDao;
	
	@Autowired
	LtMastCustomerSiteCommercialsDao ltMastCustomerSiteCommercialsDao;
	
	@Autowired
	LtMastCustomerSiteContactDao ltMastCustomerSiteContactDao;
	
	@Autowired
	LtMastCustomerSitesDao ltMastCustomerSitesDao;
	
	@Autowired
	LtMastCustomerAttachmentsDao ltCustomerAttachmentDao;
	
	@Override
	public List<LtMastCustomer> getAllCustomerInfo() throws ServiceException {
		return ltMastCustomerInfoDao.getAllCustomerInfo();
	}
 

	public static String checkNull(LtMastCustomer customer) {
		if (customer.getCustomerNumber() == null||customer.getCustomerName() == null || customer.getStatus() ==null
				|| customer.getCustomerClassCode() ==null || customer.getLegalStatus()==null || customer.getPanNo()==null 
				|| customer.getCustomerType()==null || customer.getCustomerGlClass() ==null|| customer.getAccountType()	==null			 
				|| customer.getCreatedBy() == null || customer.getCreationDate() == null
				|| customer.getLastUpdateLogin() == null ) {
			return "null";
		} else
			return "notnull";

	}

	public String checkDuplicate(LtMastCustomer tMastCustomer) throws ServiceException {
		String stat = null;
		if (ltMastCustomerInfoDao.getByCustomerNumber(tMastCustomer)) {
			return stat = "Duplicate Customer Number.";
		} else if (ltMastCustomerInfoDao.getByCustomerName(tMastCustomer)) {
			return stat = "Duplicate Customer Name.";
		} else if (ltMastCustomerInfoDao.getByPanNo(tMastCustomer)) {
			return stat = "Duplicate Pan number.";
		}

		return stat;
	}

	@Transactional
	@Override
	public Status save(LtMastCustomer customer) throws ServiceException {

		Status status = new Status();
		String chknull = checkNull(customer);
		/*if (chknull.equals("null")) {
			status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			if (status.getMessage() == null) {
				status.setCode(EXCEPTION);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		} else*/ {
			String chkDuplicate = checkDuplicate(customer);
			if (chkDuplicate == null) {
				Long customerId = ltMastCustomerInfoDao.save(customer);
				if (customerId != null) {
					if(ltCustomerApprovalDao.chkForApprovers(customerId)) {
						if(ltCustomerApprovalDao.loadApprovers(customerId,customer.getCompanyId())){
//							status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							status.setData(customerId);
						}else {
							ltMastCustomerInfoDao.delete(customerId);
							System.out.println("1");
							status.setCode(0);
							status.setMessage("No Approvers found against a Customer module");
						}
					}else {
						ltMastCustomerInfoDao.delete(customerId);
						System.out.println("2");
						status.setCode(0);
						status.setMessage("No Approvers found against a Customer module");
					}
				} else {
//					status = ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (status.getMessage() == null) {
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
			} else {
				status.setCode(0);
				status.setMessage(chkDuplicate);
			}
		}
		return status;

	}

	@Override
	public Status update(LtMastCustomer customer) throws ServiceException {

		Status status = new Status();
		if (customer.getCustomerId() != null) {
			String chknull = checkNull(customer);
			if (chknull.equals("null")) {
//				status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (status.getMessage() == null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			} else {
				String chkDuplicate = checkDuplicate(customer);
				if (chkDuplicate == null) {
					if (ltMastCustomerInfoDao.update(customer)) {
//						status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (status.getMessage() == null) {
							status.setCode(1);
							status.setMessage("Error in finding message! The action is completed successfully.");
						}
					} else {
//						status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
						try {
							status.setCode(0);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (status.getMessage() == null) {
							status.setCode(0);
							status.setMessage("Error in finding message! The action was unsuccessful");
						}
					}
				} else {
					status.setCode(0);
					status.setMessage(chkDuplicate);
					return status;
				}
			}

		} else {
//			status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}

		return status;
	}

	
	@Override
	public Status delete(Long customerId) throws ServiceException {

		Status status = new Status();
		/*if(ltMastCustomerSiteGenInfoDao.delete(customerId)) {
			System.out.println("1");
			if(ltMastCustomerSiteCommercialsDao.deleteByCustomerId(customerId)) {
				System.out.println("2");
				if(ltMastCustomerSiteContactDao.deleteByCustomerId(customerId)) {
					System.out.println("3");
					if(ltMastCustomerSitesDao.deleteByCustomerId(customerId)) {
						System.out.println("4");
						if (ltMastCustomerInfoDao.delete(customerId)) {
							System.out.println("5");
							status = ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
						} else {
							status = ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
						}
					}
				}
			}
		}*/
		ltMastCustomerSiteGenInfoDao.deleteByCustomerId(customerId);
			ltMastCustomerSiteCommercialsDao.deleteByCustomerId(customerId);
				ltMastCustomerSiteContactDao.deleteByCustomerId(customerId);
					ltMastCustomerSitesDao.deleteByCustomerId(customerId);
					ltCustomerAttachmentDao.deleteByCustomerId(customerId);
						if (ltMastCustomerInfoDao.delete(customerId)) {
							ltCustomerApprovalDao.deleteByCustomerId(customerId);
//							status = ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
							try {
								status.setCode(1);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
//							status = ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
							try {
								status.setCode(0);
								status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
		System.out.println("status "+status);
		return status;
	}

	@Override
	public List<LtMastCustomer> getLtMastCustomerDataTable(Long customerId, LtMastCustomer input)
			throws ServiceException {
		if (input.getColumnNo() == 2 && input.getSort().equals("desc")) {
			input.setColumnNo(12);
		}
		if (input.getColumnNo() == 3 && input.getSort().equals("desc")) {
			input.setColumnNo(13);
		}
		if (input.getColumnNo() == 4 && input.getSort().equals("desc")) {
			input.setColumnNo(14);
		}
		if (input.getColumnNo() == 5 && input.getSort().equals("desc")) {
			input.setColumnNo(15);
		}
		if (input.getColumnNo() == 6 && input.getSort().equals("desc")) {
			input.setColumnNo(16);
		}
		if (input.getColumnNo() == 7 && input.getSort().equals("desc")) {
			input.setColumnNo(17);
		}
		if (input.getColumnNo() == 8 && input.getSort().equals("desc")) {
			input.setColumnNo(18);
		}
		if (input.getColumnNo() == 9 && input.getSort().equals("asc")) {
			input.setColumnNo(19);
		}
//		if (input.getColumnNo() == 0 && input.getSort().equals("asc")) {
//			input.setColumnNo(11);
//		}

		return ltMastCustomerInfoDao.getLtMastCustomerDataTable(customerId, input);
	}

	@Override
	public Long getLtMastCustomerCountByInitiatorId(LtMastCustomer input, Long initiatorId) throws ServiceException {
		return ltMastCustomerInfoDao.getLtMastCustomerCountByInitiatorId(input, initiatorId);
	}

	@Override
	public Long getLtMastCustomerInfoCount(Long companyId, LtMastCustomer input) throws ServiceException {
		return ltMastCustomerInfoDao.getLtMastCustomerInfoCount(companyId, input);
	}

	@Override
	public List<LtMastCustomer> getAllActiveCustomerInfo(Long companyId) throws ServiceException {
		return ltMastCustomerInfoDao.getAllActiveCustomerInfo(companyId);
	}


	@Override
	public LtMastCustomer getCustomerById(Long customerId) throws ServiceException {
		return ltMastCustomerInfoDao.getCustomerById(customerId);
	}

}
