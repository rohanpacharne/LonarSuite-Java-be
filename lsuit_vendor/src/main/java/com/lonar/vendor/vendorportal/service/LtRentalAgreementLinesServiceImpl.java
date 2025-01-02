package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtRentalAgreementLinesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtInvoiceLines;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementHeaders;
import com.lonar.vendor.vendorportal.model.LtRentalAgreementLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtRentalAgreementLinesRepository;

@Service
public class LtRentalAgreementLinesServiceImpl implements LtRentalAgreementLinesService,CodeMaster {
	
	@Autowired
	LtRentalAgreementLinesDao ltRentalAgreementLinesDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	

	@Override
	public Long getLtRentalAgreementLinesCount(LtRentalAgreementLines input, Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return ltRentalAgreementLinesDao.getLtRentalAgreementLinesCount(input, id);
	}

	@Override
	public List<LtRentalAgreementLines> getLtRentalAgreementLinesDataTable(LtRentalAgreementLines input, Long id)
			throws ServiceException {
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{ input.setColumnNo(11); }
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{ input.setColumnNo(12); }
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{ input.setColumnNo(13); }
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{ input.setColumnNo(14); }
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{ input.setColumnNo(15); }
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{ input.setColumnNo(16); }
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{ input.setColumnNo(17); }
		if(input.getColumnNo()==8 && input.getSort().equals("asc"))
		{ input.setColumnNo(18); }
		
		return ltRentalAgreementLinesDao.getLtRentalAgreementLinesDataTable(input,id);
	}
	
	
	private String checkNull(LtRentalAgreementLines ltRentalAgreementLines) {
		if(ltRentalAgreementLines.getCreatedBy()==null || ltRentalAgreementLines.getCreationDate()==null || 
				ltRentalAgreementLines.getLastUpdateLogin()==null)
		{
			return "null";
		}
		else
			return "notnull";
	}

	@Override
	public Status save(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException {
		Status status=new Status();
		String chknull=checkNull(ltRentalAgreementLines);
		if(chknull.equals("null"))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}else {
			Long agreementLineId=ltRentalAgreementLinesDao.save(ltRentalAgreementLines);
			
			if(agreementLineId!=null){
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if( status.getMessage()==null)
				{
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(agreementLineId);
		}else {
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(status.getMessage()==null)
			{
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		}
		return status;
	}

	@Override
	public LtRentalAgreementLines getRentalAgreementLineById(Long id) throws ServiceException {
		return ltRentalAgreementLinesDao.getRentalAgreementLineById(id);
	}

	@Override
	public Status deleteByAgreementLineId(Long agreementLineId) {
		Status status = new Status();
		if(ltRentalAgreementLinesDao.deleteByAgreementLineId(agreementLineId)) {
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		}else {
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		return status;
	}

	@Override
	public Status update(LtRentalAgreementLines ltRentalAgreementLines) throws ServiceException {
		Status status=new Status();
		if(ltRentalAgreementLines.getAgreementLineId()!=null)
		{
			String chknull=checkNull(ltRentalAgreementLines);
			if(chknull.equals("null"))
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(status.getMessage()==null)
				{
					status.setCode(0);
					status.setMessage("Error in finding message! The action was unsuccessful");
				}
			}else {
				ltRentalAgreementLines.setLastUpdatedBy(ltRentalAgreementLines.getLastUpdateLogin());
				ltRentalAgreementLines.setCreationDate(new Date());
				ltRentalAgreementLines.setLastUpdateDate(new Date());
				
				if(ltRentalAgreementLinesDao.update(ltRentalAgreementLines)) {

					try {
						status.setCode(1);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if( status.getMessage()==null)
					{
						status.setCode(1);
						status.setMessage("Error in finding message! The action is completed successfully.");
					}
					status.setData(ltRentalAgreementLines.getAgreementLineId());
				}else {
					try {
						status.setCode(0);
						status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(status.getMessage()==null)
					{
						status.setCode(0);
						status.setMessage("Error in finding message! The action was unsuccessful");
					}
				}
			
		}
			
		}
		return status;
	}
	
	

}
