package com.lonar.vendor.vendorportal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtVendCompanyDao;
import com.lonar.vendor.vendorportal.dao.LtVendCompanyMgmtDdetailsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtCompanyVenMgmtInclude;
import com.lonar.vendor.vendorportal.model.LtCompanyVenMgmtIncludedto;
import com.lonar.vendor.vendorportal.model.LtVendCompanyMgmtDdetails;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtCompanyVenMgmtIncludeRepository;
import com.lonar.vendor.vendorportal.repository.LtVendCompanyMgmtDdetailsRepository;

@Service
public class LtVendCompanyMgmtDdetailsServiceImpl implements LtVendCompanyMgmtDdetailsService, CodeMaster {

	@Autowired
	LtVendCompanyDao ltVendCompanyDao;
	
	@Autowired
	LtVendCompanyMgmtDdetailsDao ltVendCompanyMgmtDdetailsDao;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Autowired
	LtVendCompanyMgmtDdetailsRepository ltVendCompanyMgmtDdetailsRepository;
	
	@Autowired
	LtCompanyVenMgmtIncludeRepository ltCompanyVenMgmtIncludeRepository;
	
	@Override
	public List<LtVendCompanyMgmtDdetails> getBycompanyId(Long companyId) {
		return ltVendCompanyMgmtDdetailsDao.getBycompanyId(companyId);
	}

	@Override
	public LtVendCompanyMgmtDdetails getById(Long id) {
		return ltVendCompanyMgmtDdetailsDao.getById(id);
	}

	@Override
	public List<LtVendCompanyMgmtDdetails> getAll() {
		return ltVendCompanyMgmtDdetailsDao.getAll();
	}

	@Override
	public List<LtVendCompanyMgmtDdetails> getAllActive() {
		return ltVendCompanyMgmtDdetailsDao.getAllActive();
	}

	@Override
	public ResponseEntity<Status> save(LtVendCompanyMgmtDdetails ltVendCompanyMgmtDdetails) {
		Status status = new Status();
		if(ltVendCompanyMgmtDdetails.getLastUpdateLogin() == null )
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		ltVendCompanyMgmtDdetails.setStartDate(new Date());
		ltVendCompanyMgmtDdetails.setCreationDate(new Date());
		ltVendCompanyMgmtDdetails.setLastUpdateDate(new Date());
		ltVendCompanyMgmtDdetails.setCreatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
		ltVendCompanyMgmtDdetails.setLastUpdateLogin(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
		ltVendCompanyMgmtDdetails.setLastUpdatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin()); 
		ltVendCompanyMgmtDdetails = ltVendCompanyMgmtDdetailsRepository.save(ltVendCompanyMgmtDdetails);
			if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null)
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
				status.setData(ltVendCompanyMgmtDdetails.getCompMgmtId());
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}
		    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Status> update(LtVendCompanyMgmtDdetails ltVendCompanyMgmtDdetails) throws ServiceException {
		Status status = new Status();
		System.out.println("Inside update method");
		System.out.println("company mng details is "+ltVendCompanyMgmtDdetails);
		if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null) {
		System.out.println("Inside company mng details id");
			if(ltVendCompanyMgmtDdetails.getIncludeVendor().equals("N") 
					&& ltVendCompanyMgmtDdetails.getMandatoryTab().equals("Y")) {
				status.setCode(0);
				status.setMessage("Please include the Tab to make it mandatory!");
				 return new ResponseEntity<Status>(status, HttpStatus.OK);
			}
			System.out.println("Below if....");
			ltVendCompanyMgmtDdetails.setLastUpdateDate(new Date());
			System.out.println("Date is "+ltVendCompanyMgmtDdetails.getLastUpdateDate());
			ltVendCompanyMgmtDdetails.setStartDate(new Date());
			System.out.println("Below start date...");
			ltVendCompanyMgmtDdetails.setLastUpdateLogin(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
			ltVendCompanyMgmtDdetails.setLastUpdatedBy(ltVendCompanyMgmtDdetails.getLastUpdateLogin());
			System.out.println("Above ltVendCompanyMgmtDdetailsRepository....");
			ltVendCompanyMgmtDdetails = ltVendCompanyMgmtDdetailsRepository.save(ltVendCompanyMgmtDdetails);
			if(ltVendCompanyMgmtDdetails.getCompMgmtId()!=null)
			{
				System.out.println("Inside ltVendCompanyMgmtDdetails....");
				System.out.println("Company id = "+ltVendCompanyMgmtDdetails.getCompanyId());
				try {
					updateVendorCompanyMgmt(ltVendCompanyMgmtDdetails.getCompanyId());
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Below "+ltVendCompanyMgmtDdetails.getCompanyId());
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
				try {
					status.setCode(1);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Status is "+status);
				if( status.getMessage()==null) {
					status.setCode(1);
					status.setMessage("Error in finding message! The action is completed successfully.");
				}
			}
			else
			{
//				status=ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}
		}else {
//			status=ltMastCommonMessageService.getCodeAndMessage(INPUT_IS_EMPTY);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INPUT_IS_EMPTY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		}
		    return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

	private void updateVendorCompanyMgmt(Long companyId) throws ServiceException, ParseException {
		System.out.println("Inside update method...");
		List<LtVendCompanyMgmtDdetails> ltVendCompanyMgmtDdetails = ltVendCompanyMgmtDdetailsDao.getBycompanyId(companyId);
		System.out.println("In between list....");
		List<LtCompanyVenMgmtIncludedto> listofltCompanyVenMgmtIncludedto = ltVendCompanyMgmtDdetailsDao.getManagementBycompanyId(companyId);
		List<LtCompanyVenMgmtInclude> list = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(LtCompanyVenMgmtIncludedto ltCompanyVenMgmtIncludedto:listofltCompanyVenMgmtIncludedto) {
			LtCompanyVenMgmtInclude ltCompanyVenMgmtInclude = new LtCompanyVenMgmtInclude();
			ltCompanyVenMgmtInclude.setCompanyVenMgmtIncludeId(ltCompanyVenMgmtIncludedto.getCompanyVenMgmtIncludeId());
			ltCompanyVenMgmtInclude.setCompanyId(ltCompanyVenMgmtIncludedto.getCompanyId());
			ltCompanyVenMgmtInclude.setVendorId(ltCompanyVenMgmtIncludedto.getVendorId());
			ltCompanyVenMgmtInclude.setCompMgmtId(ltCompanyVenMgmtIncludedto.getCompMgmtId());
			ltCompanyVenMgmtInclude.setMgmtIncludeVendor(ltCompanyVenMgmtIncludedto.getMgmtIncludeVendor());
			ltCompanyVenMgmtInclude.setMgmtMandatoryTab(ltCompanyVenMgmtIncludedto.getMgmtMandatoryTab());
			ltCompanyVenMgmtInclude.setCompClientId(ltCompanyVenMgmtIncludedto.getCompClientId());
			ltCompanyVenMgmtInclude.setClientIncludeVendor(ltCompanyVenMgmtIncludedto.getClientIncludeVendor());
			ltCompanyVenMgmtInclude.setClientMandatoryTab(ltCompanyVenMgmtIncludedto.getClientMandatoryTab());
			ltCompanyVenMgmtInclude.setCompSistConcernsId(ltCompanyVenMgmtIncludedto.getCompSistConcernsId());
			ltCompanyVenMgmtInclude.setSistIncludeVendor(ltCompanyVenMgmtIncludedto.getSistIncludeVendor());
			ltCompanyVenMgmtInclude.setSistMandatoryTab(ltCompanyVenMgmtIncludedto.getSistMandatoryTab());
			
			ltCompanyVenMgmtInclude.setStartDate(dateFormat.parse(ltCompanyVenMgmtIncludedto.getStartDate()));
			if(ltCompanyVenMgmtIncludedto.getEndDate()==null) {
				ltCompanyVenMgmtInclude.setEndDate(new Date());
			}else {
				ltCompanyVenMgmtInclude.setEndDate(dateFormat.parse(ltCompanyVenMgmtIncludedto.getEndDate()));
			}
			ltCompanyVenMgmtInclude.setCreatedBy(ltCompanyVenMgmtIncludedto.getCreatedBy());
			ltCompanyVenMgmtInclude.setCreationDate(dateFormat.parse(ltCompanyVenMgmtIncludedto.getCreationDate()));
			ltCompanyVenMgmtInclude.setLastUpdateLogin(ltCompanyVenMgmtIncludedto.getLastUpdateLogin());
			ltCompanyVenMgmtInclude.setLastUpdatedBy(ltCompanyVenMgmtIncludedto.getLastUpdatedBy());
			ltCompanyVenMgmtInclude.setLastUpdateDate(dateFormat.parse(ltCompanyVenMgmtIncludedto.getLastUpdateDate()));
//			
//			ltCompanyVenMgmtInclude.setAdditionalField1(null);
//			ltCompanyVenMgmtInclude.setAdditionalField2(null);
//			ltCompanyVenMgmtInclude.setAdditionalField3(null);
//			ltCompanyVenMgmtInclude.setAdditionalField4(null);
//			ltCompanyVenMgmtInclude.setAdditionalField5(null);
//			ltCompanyVenMgmtInclude.setAdditionalField6(null);
//			ltCompanyVenMgmtInclude.setAdditionalField7(null);
//			ltCompanyVenMgmtInclude.setAdditionalField8(null);
//			ltCompanyVenMgmtInclude.setAdditionalField9(null);
//			ltCompanyVenMgmtInclude.setAdditionalField10(null);
//			ltCompanyVenMgmtInclude.setAdditionalField11(null);
//			ltCompanyVenMgmtInclude.setAdditionalField12(null);
//			ltCompanyVenMgmtInclude.setAdditionalField13(null);
//			ltCompanyVenMgmtInclude.setAdditionalField14(null);
//			ltCompanyVenMgmtInclude.setAdditionalField15(null);
			
			list.add(ltCompanyVenMgmtInclude);
		}
		System.out.println("Below list call....");
		System.out.println("ltVendCompanyMgmtDdetails "+ltVendCompanyMgmtDdetails);
		System.out.println("list "+list);
		if(!list.isEmpty()) {
			for(LtCompanyVenMgmtInclude ltCompanyVenMgmtInclude : list ) {
				String vendorStatus = ltVendCompanyDao.getVendorStatus(ltCompanyVenMgmtInclude.getVendorId());
				if(vendorStatus.equals("VENDOR_ACTIVE")) {
					if(ltVendCompanyMgmtDdetails.get(0).getIncludeVendor().equals("Y")) {
						ltCompanyVenMgmtInclude.setMgmtIncludeVendor(ltVendCompanyMgmtDdetails.get(0).getIncludeVendor());
						ltCompanyVenMgmtInclude.setMgmtMandatoryTab(ltVendCompanyMgmtDdetails.get(0).getMandatoryTab());
						ltCompanyVenMgmtInclude.setLastUpdateDate(new Date());
						ltCompanyVenMgmtIncludeRepository.save(ltCompanyVenMgmtInclude);
					}
				}else {
					ltCompanyVenMgmtInclude.setMgmtIncludeVendor(ltVendCompanyMgmtDdetails.get(0).getIncludeVendor());
					ltCompanyVenMgmtInclude.setMgmtMandatoryTab(ltVendCompanyMgmtDdetails.get(0).getMandatoryTab());
					ltCompanyVenMgmtInclude.setLastUpdateDate(new Date());
					ltCompanyVenMgmtIncludeRepository.save(ltCompanyVenMgmtInclude);
				}
				
				/*ltCompanyVenMgmtInclude.setMgmtIncludeVendor(ltVendCompanyMgmtDdetails.get(0).getIncludeVendor());
				ltCompanyVenMgmtInclude.setMgmtMandatoryTab(ltVendCompanyMgmtDdetails.get(0).getMandatoryTab());
				ltCompanyVenMgmtInclude.setLastUpdateDate(new Date());
				ltCompanyVenMgmtIncludeRepository.save(ltCompanyVenMgmtInclude);*/
			}
		}
		
		
		
	}

	@Override
	public ResponseEntity<Status> delete(Long id) {
		Status status = new Status();
		ltVendCompanyMgmtDdetailsRepository.delete(id);
		if (!ltVendCompanyMgmtDdetailsRepository.exists(id))
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if( status.getMessage()==null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		} 
		else 
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(ENTITY_CANNOT_DELETE);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("ENTITY_CANNOT_DELETE").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( status.getMessage()==null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action is completed unsuccessfully.");
			}
		} 
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
