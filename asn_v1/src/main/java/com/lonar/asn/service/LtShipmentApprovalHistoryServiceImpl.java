package com.lonar.asn.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.asn.dao.LtShipmentApprovalHistoryDao;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.LtShipmentApprovalHistory;
import com.lonar.asn.model.Status;


@Service
public class LtShipmentApprovalHistoryServiceImpl implements LtShipmentApprovalHistoryService,CodeMaster{

	@Autowired
	LtShipmentApprovalHistoryDao ltShipmentApprovalHistoryDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Override
	public void saveShipmentApprovalHistory(LtShipmentApprovalHistory ltShipmentApprovalHistory) {
		Status status=new Status();
		ltShipmentApprovalHistory.setLastUpdateDate(new Date());
		/*List<AsnApproval> invoiceApprovalList =invoiceApprovalDao.chkInvoiceEmpApproval(ltShipmentApprovalHistory.getEmployeeId()
				,ltShipmentApprovalHistory.getShipmentHeaderId());
		
		if(invoiceApprovalList.size()>0)
		{
			ltShipmentApprovalHistory.setStatus(APPROVED);
		}*/
		
		if (ltShipmentApprovalHistoryDao.save(ltShipmentApprovalHistory))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) 
			{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}
		else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
		
	}

}
