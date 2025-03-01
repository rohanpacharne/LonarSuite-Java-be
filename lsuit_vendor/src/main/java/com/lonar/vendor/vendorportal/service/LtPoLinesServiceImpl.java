package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtPoLinesDao;
import com.lonar.vendor.vendorportal.model.LtPoLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtPoLinesRepository;

@Service
public class LtPoLinesServiceImpl implements LtPoLinesService {

	@Autowired
	LtPoLinesDao ltPoLinesDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtPoLinesRepository ltPoLinesRepository;
	
	@Override
	public Long getLtPoLinesCount(Long headerId, LtPoLines input) throws ServiceException {
		return ltPoLinesDao.getLtPoLinesCount(headerId,input);
	}

	@Override
	public List<LtPoLines> getLtPoLinesDataTable(Long headerId, LtPoLines input) throws ServiceException {
		if(input.getSort()==null) {
			input.setSort("desc");
		}
		if(input.getColumnNo()==1 && input.getSort().equals("desc"))
		{
			input.setColumnNo(11);
		}
		if(input.getColumnNo()==2 && input.getSort().equals("desc"))
		{
			input.setColumnNo(12);
		}
		if(input.getColumnNo()==3 && input.getSort().equals("desc"))
		{
			input.setColumnNo(13);
		}
		if(input.getColumnNo()==4 && input.getSort().equals("desc"))
		{
			input.setColumnNo(14);
		}
		if(input.getColumnNo()==5 && input.getSort().equals("desc"))
		{
			input.setColumnNo(15);
		}
		if(input.getColumnNo()==6 && input.getSort().equals("desc"))
		{
			input.setColumnNo(16);
		}
		if(input.getColumnNo()==7 && input.getSort().equals("desc"))
		{
			input.setColumnNo(17);
		}
		if(input.getColumnNo()==8 && input.getSort().equals("desc"))
		{
			input.setColumnNo(18);
		}
		if(input.getColumnNo()==9 && input.getSort().equals("asc"))
		{
			input.setColumnNo(19);
		}
		return ltPoLinesDao.getLtPoLinesDataTable(headerId,input);
	}

	@Override
	public List<LtPoLines> getAllPoLinesByHeaderId(Long headerId) throws ServiceException {
		return ltPoLinesDao.getAllPoLinesByHeaderId(headerId);
	}

	@Override
	public LtPoLines getPoLinesByPolineId(Long poLineId) throws ServiceException {
		return ltPoLinesDao.getPoLinesByPolineId(poLineId);
	}
	
	@Override
	public Status save(LtPoLines ltPoLines) throws ServiceException {
			System.out.println("PO line save called");
			Status status=new Status();
			String chknull=checkNull(ltPoLines);
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
			}
			else
			{
					Long poLineId=ltPoLinesDao.save(ltPoLines);
					if(poLineId!=null)
					{
						if(ltPoLinesDao.updateAmount(ltPoLines)) {
//							ltInvoiceLinesDao.callCreateInvoiceLineTaxes(ltInvoiceLines);
//							status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
							status.setData(poLineId);
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
 
	private String checkNull(LtPoLines ltPoLines) {
			if(ltPoLines.getCreatedBy()==null || ltPoLines.getCreationDate()==null ||
					ltPoLines.getLastUpdateLogin()==null)
			{
				return "null";
			}
			else
				return "notnull";
	}
	
	@Override
	public Status update(LtPoLines ltPoLines) throws ServiceException{
			Status status=new Status();
			String chknull=checkNull(ltPoLines);
			if(chknull.equals("null") || ltPoLines.getPoHeaderId()==null || ltPoLines.getPoLineId() == null)
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
			}
			else
			{
				ltPoLines = ltPoLinesRepository.save(ltPoLines);
					
					if(ltPoLines.getPoLineId()!=null)
					{
						if(ltPoLinesDao.updateAmount(ltPoLines)) {
//							status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
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
							status.setData(ltPoLines.getPoLineId());
						}
					}
					else
					{
//						status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
						
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
			return status;
		}
	
	@Override
	public Status delete(Long id) {
		Status status = new Status();
		ltPoLinesRepository.delete(id);
		if(ltPoLinesRepository.exists(id)) {
//		status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
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
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(status.getMessage()==null)
			{
				status.setCode(1);
				status.setMessage("Error in finding message! The action was successful");
			}
		}
		 return status;	
	}

}
