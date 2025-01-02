package com.lonar.vendor.vendorportal.service;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteGenInfoDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustSiteGenInfo;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastCustomerSiteGenInfoServiceImpl implements LtMastCustomerSiteGenInfoService, CodeMaster{
														  
	@Autowired
	private LtMastCustomerSiteGenInfoDao ltMastCustomerSiteGenInfoDao;
	
	@Autowired
	private LtMastCommonMessageService ltMastCommonMessageService;
	

	
	@Override
	public LtMastCustSiteGenInfo getBySiteGenInfoId(Long siteGenInfoId)throws ServiceException  {
		return  ltMastCustomerSiteGenInfoDao.getBySiteGenInfoId(siteGenInfoId);
	}

	@Override
	public List<LtMastCustSiteGenInfo> getBycustomerSiteId(Long customerSiteId)throws ServiceException {
		return ltMastCustomerSiteGenInfoDao.getBycustomerSiteId(customerSiteId);
	}

	@Override
	public List<LtMastCustSiteGenInfo> getBycustomerId(Long customerId) throws ServiceException {
		return  ltMastCustomerSiteGenInfoDao.getBycustomerId(customerId);
		
	}
	public static String checkNull(LtMastCustSiteGenInfo custSiteGenInfo) {
		if (custSiteGenInfo.getCustomerSiteId() == null || custSiteGenInfo.getCustomerId() ==null
				|| custSiteGenInfo.getCreatedBy() ==null || custSiteGenInfo.getCreationDate()==null || custSiteGenInfo.getLastUpdateLogin()==null ) {
			return "null";
		} else
			return "notnull";

	}
	@Override
	public Status save(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException {
		Status status = new Status();
		String chknull = checkNull(ltMastCustSiteGenInfo);
		if (chknull.equals("null")) {
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
		} else {
				Long customerId = ltMastCustomerSiteGenInfoDao.save(ltMastCustSiteGenInfo);
				if (customerId != null) {

//					status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					status.setData(customerId);
				} else {
//					status = ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}

		return status;
	}

	@Override
	public Status update(LtMastCustSiteGenInfo ltMastCustSiteGenInfo) throws ServiceException {
		Status status = new Status();
		if (ltMastCustSiteGenInfo.getSiteGenInfoId() != null) {
			String chknull = checkNull(ltMastCustSiteGenInfo);
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
					if (ltMastCustomerSiteGenInfoDao.update(ltMastCustSiteGenInfo)) {
//						status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
						try {
							status.setCode(1);
							status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
		}

		return status;
	}
	public Status returnStatus() throws ServiceException {
		Status status = new Status();
//		status = ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
		try {
			status.setCode(1);
			status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (status.getMessage() == null) {
			status.setCode(1);
			status.setMessage("Error in finding message! The action is completed successfully.");
		}
		return status;
	}
	@Override
	public Status delete(Long customerId) throws ServiceException {
		Status status = new Status();

		if (ltMastCustomerSiteGenInfoDao.delete(customerId)) {
			status = returnStatus();
		} else {
//			status = ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
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
	public Long getByCustomerSiteDataTableCount(Long customerSiteId, LtMastCustSiteGenInfo input)
			throws ServiceException {
		return ltMastCustomerSiteGenInfoDao.getByCustomerSiteDataTableCount(customerSiteId, input);
	}

	@Override
	public List<LtMastCustSiteGenInfo> getByCustomerSiteDataTable(Long customerSiteId, LtMastCustSiteGenInfo input) {
		if (input.getColumnNo() == 2 && input.getSort().equals("desc")) {
			input.setColumnNo(12);
		}
		if (input.getColumnNo() == 3 && input.getSort().equals("desc")) {
			input.setColumnNo(13);
		}
		if (input.getColumnNo() == 4 && input.getSort().equals("desc")) {
			input.setColumnNo(14);
		}
		if (input.getColumnNo() == 5 && input.getSort().equals("asc")) {
			input.setColumnNo(15);
		}
		return ltMastCustomerSiteGenInfoDao.getByCustomerSiteDataTable(customerSiteId, input);
	}

}
