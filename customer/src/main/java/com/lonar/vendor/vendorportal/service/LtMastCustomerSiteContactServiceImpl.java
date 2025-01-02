package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteContactDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustSiteContacts;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastCustomerSiteContactServiceImpl implements LtMastCustomerSiteContactService,CodeMaster{

	@Autowired
	LtMastCustomerSiteContactDao ltMastCustomerSiteContactDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Override
	public List<LtMastCustSiteContacts> getAllCustSiteContacts() throws ServiceException {
		return ltMastCustomerSiteContactDao.getAllCustSiteContacts();
		
	}

	@Override
	public List<LtMastCustSiteContacts> getContactsByCustomerId(Long customerId) throws ServiceException {
		return ltMastCustomerSiteContactDao.getContactsByCustomerId(customerId);
		
	}

	@Override
	public List<LtMastCustSiteContacts> getContactsByCustomerSiteId(Long customerSiteId) throws ServiceException {
		return ltMastCustomerSiteContactDao.getContactsByCustomerSiteId(customerSiteId);
		
	}

	@Override
	public LtMastCustSiteContacts getCustSiteContactsById(Long siteContactId) throws ServiceException {
		return ltMastCustomerSiteContactDao.getCustSiteContactsById(siteContactId);
		
	}

	@Override
	public Status save(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException {
		Status status = new Status();

		String isChecknull = checkNull(ltMastCustSiteContacts);
		if (isChecknull.equals("null")) {
//			status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			if (ltMastCustomerSiteContactDao.save(ltMastCustSiteContacts)) {
//				status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
//				status = ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
	public Status update(LtMastCustSiteContacts ltMastCustSiteContacts) throws ServiceException {
		Status status = new Status();

		if (ltMastCustSiteContacts.getSiteContactId() != null) {
			String isCheckNull = checkNull(ltMastCustSiteContacts);
			if (isCheckNull.equals("null")) {
//				status = ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (ltMastCustomerSiteContactDao.update(ltMastCustSiteContacts)) {
//					status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
//					status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
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

	@Override
	public Status delete(Long siteContactId) throws ServiceException {
		Status status = new Status();
		if (ltMastCustomerSiteContactDao.delete(siteContactId)) {
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
		}
		return status;
	}
	
	public static String checkNull(LtMastCustSiteContacts ltMastCustSiteContacts)
	{
		if (ltMastCustSiteContacts.getCreatedBy() == null || ltMastCustSiteContacts.getCreationDate() == null
				|| ltMastCustSiteContacts.getLastUpdateLogin() == null||ltMastCustSiteContacts.getCustomerId()==null||
						ltMastCustSiteContacts.getCustomerSiteId()==null) {
			return "null";
		} else
			return "notnull";

	}

	@Override
	public Long getByCustomerSiteContactsDataTableCount(Long customerSiteId, LtMastCustSiteContacts input)
			throws ServiceException {
		return ltMastCustomerSiteContactDao.getByCustomerSiteContactsDataTableCount(customerSiteId, input);
	}

	@Override
	public List<LtMastCustSiteContacts> getByCustomerSiteContactsDataTable(Long customerSiteId,
			LtMastCustSiteContacts input) throws ServiceException {
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
		if (input.getColumnNo() == 6 && input.getSort().equals("asc")) {
			input.setColumnNo(16);
		}
		return ltMastCustomerSiteContactDao.getByCustomerSiteContactsDataTable(customerSiteId, input);
	}

}
