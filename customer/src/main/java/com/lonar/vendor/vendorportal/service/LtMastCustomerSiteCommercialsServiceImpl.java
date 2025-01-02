package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastCustomerSiteCommercialsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastCustSiteCommercials;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

@Service
public class LtMastCustomerSiteCommercialsServiceImpl implements LtMastCustomerSiteCommercialsService,CodeMaster{

	@Autowired
	LtMastCustomerSiteCommercialsDao ltMastCustomerSiteCommercialsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Override
	public List<LtMastCustSiteCommercials> getAllCustSiteCommercials() throws ServiceException {
		return ltMastCustomerSiteCommercialsDao.getAllCustSiteCommercials();
	}

	@Override
	public List<LtMastCustSiteCommercials> getByCustomerId(Long customerId) throws ServiceException {
		return ltMastCustomerSiteCommercialsDao.getByCustomerId(customerId);
	} 

	@Override
	public List<LtMastCustSiteCommercials> getByCustomerSiteId(Long customerSiteId) throws ServiceException {
		return ltMastCustomerSiteCommercialsDao.getByCustomerSiteId(customerSiteId);
	}

	@Override
	public LtMastCustSiteCommercials getBySiteCommercialId(Long siteCommercialId) throws ServiceException {
		return ltMastCustomerSiteCommercialsDao.getBySiteCommercialId(siteCommercialId);
	}

	@Override
	public Status save(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException {
		Status status = new Status();

		String isChecknull = checkNull(ltMastCustSiteCommercials);
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
			if (ltMastCustomerSiteCommercialsDao.save(ltMastCustSiteCommercials)) {
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
	public Status update(LtMastCustSiteCommercials ltMastCustSiteCommercials) throws ServiceException {
		Status status = new Status();

		if (ltMastCustSiteCommercials.getSiteCommercialId() != null) {
			String isCheckNull = checkNull(ltMastCustSiteCommercials);
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
				if (ltMastCustomerSiteCommercialsDao.update(ltMastCustSiteCommercials)) {
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
	public Status delete(Long siteCommercialId) throws ServiceException {
		Status status = new Status();
		if (ltMastCustomerSiteCommercialsDao.delete(siteCommercialId)) {
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

	public static String checkNull(LtMastCustSiteCommercials ltMastCustSiteCommercials)
	{
		if (ltMastCustSiteCommercials.getCreatedBy() == null || ltMastCustSiteCommercials.getCreationDate() == null
				|| ltMastCustSiteCommercials.getLastUpdateLogin() == null||ltMastCustSiteCommercials.getCustomerId()==null||
				ltMastCustSiteCommercials.getCustomerSiteId()==null) {
			return "null";
		} else
			return "notnull";

	}

	@Override
	public Long getCustomerCommercialsDataTableCount(Long customerSiteId, LtMastCustSiteCommercials input)
			throws ServiceException {
		return ltMastCustomerSiteCommercialsDao.getCustomerCommercialsDataTableCount(customerSiteId, input);
	}

	@Override
	public List<LtMastCustSiteCommercials> getCustomerCommercialsDataTable(Long customerSiteId,
			LtMastCustSiteCommercials input) throws ServiceException {
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
		/*if (input.getColumnNo() == 0) {
			input.setColumnNo(6);
		}*/
		return ltMastCustomerSiteCommercialsDao.getCustomerCommercialsDataTable(customerSiteId, input);
	}
}
