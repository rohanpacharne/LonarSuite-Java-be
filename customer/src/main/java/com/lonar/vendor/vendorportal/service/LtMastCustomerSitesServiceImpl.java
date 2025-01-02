package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastCustomerSitesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustomerSites;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastCustomerSitesServiceImpl implements LtMastCustomerSitesService, CodeMaster{

	@Autowired
	private LtMastCustomerSitesDao ltMastCustomerSitesDao;
	
	@Autowired
	private LtMastCommonMessageService ltMastCommonMessageService;

	@Override
	public List<LtMastCustomerSites> getAllCustomerSites() {
		return ltMastCustomerSitesDao.getAllCustomerSites();
	}

	@Override
	public Long getCustomerSitesCount(Long customerId,LtMastCustomerSites input) {
		 
		return ltMastCustomerSitesDao.getCustomerSitesCount(customerId,input);
	}

	@Override
	public List<LtMastCustomerSites> getCustomerSiteByCustomerId(Long customerSiteId) throws ServiceException {
		return ltMastCustomerSitesDao.getCustomerSiteByCustomerId(customerSiteId);
	}
	
	@Override
	public List<LtMastCustomerSites> getCustomerSitesDataTable(Long customerId,LtMastCustomerSites input)  {
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
		if (input.getColumnNo() == 8 && input.getSort().equals("asc")) {
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==0) {
			input.setColumnNo(8);
		}
		return ltMastCustomerSitesDao.getCustomerSitesDataTable(customerId,input);
	}

	@Override
	public LtMastCustomerSites getCustomerSiteById(Long customerSiteId) {
		 
		return  ltMastCustomerSitesDao.getCustomerSiteById(customerSiteId);
	}
	
	
	public static String checkNull(LtMastCustomerSites customerSite) {
		if (customerSite.getSiteNumber() == null ||customerSite.getCustomerId()==null|| customerSite.getCountry()==null||customerSite.getStateId()==null 
				|| customerSite.getCity() == null  || customerSite.getPincode()  == null || customerSite.getAddress1() == null 
				|| customerSite.getStatus()==null
				|| customerSite.getCreatedBy() == null || customerSite.getCreationDate() == null
				|| customerSite.getLastUpdateLogin() == null ) {
			return "null";
		} else
			return "notnull";

	}

	@Override
	public Status save(LtMastCustomerSites ltMastCustomerSites)throws ServiceException {
		Status status = new Status();
		String chknull = checkNull(ltMastCustomerSites);
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
			
				Long customerSiteId = ltMastCustomerSitesDao.save(ltMastCustomerSites);
				if (customerSiteId != null) {

//					status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					status.setData(customerSiteId);
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
	public Status update(LtMastCustomerSites ltMastCustomerSites) throws ServiceException {
		Status status = new Status();
		if (ltMastCustomerSites.getCustomerSiteId() != null) {
			String chknull = checkNull(ltMastCustomerSites);
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
					if (ltMastCustomerSitesDao.update(ltMastCustomerSites)) {
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
			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
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
	public Status delete(Long customerSiteId) throws ServiceException {
		Status status = new Status();

		if (ltMastCustomerSitesDao.delete(customerSiteId)) {
//			status = ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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


	 
}
