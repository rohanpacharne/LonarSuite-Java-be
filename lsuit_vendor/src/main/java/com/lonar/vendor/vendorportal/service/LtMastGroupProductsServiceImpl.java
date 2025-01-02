package com.lonar.vendor.vendorportal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lonar.vendor.vendorportal.dao.LtMastGroupProductsDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtMastGroupProducts;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtMastGroupProductsRepository;

@Service
public class LtMastGroupProductsServiceImpl implements LtMastGroupProductsService, CodeMaster {
	@Autowired
	LtMastGroupProductsDao ltP2pGroupProductsDao;

	@Autowired
	LtMastGroupProductsRepository ltP2pGroupProductsRepository;
	
	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;
	
	@Override
	public List<LtMastGroupProducts> findByParentProductId(Long parentProductId) throws ServiceException{
		// TODO Auto-generated method stub
		return ltP2pGroupProductsDao.findByParentProductId(parentProductId);
	}

	@Override
	public List<LtMastGroupProducts> findByChildProductId(Long childProductId) throws ServiceException{
		// TODO Auto-generated method stub
		return ltP2pGroupProductsDao.findByChildProductId(childProductId);
	}

	@Override
	public List<LtMastGroupProducts> findAllActive() throws ServiceException{
		// TODO Auto-generated method stub
		return ltP2pGroupProductsDao.findAllActive();
	}

	@Override
	public List<LtMastGroupProducts> getAll() throws ServiceException {
		// TODO Auto-generated method stub
		return ltP2pGroupProductsDao.getAll();
	}

	@Override
	public LtMastGroupProducts getById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return ltP2pGroupProductsDao.getById(id);
	}

	@Override
	public ResponseEntity<Status> saveGroupProducts(LtMastGroupProducts ltP2pGroupProducts) throws ServiceException {
		Status status = new Status();
		List<LtMastGroupProducts> ltP2pGroupProductsList=ltP2pGroupProductsDao.findByParentProductId(ltP2pGroupProducts.getParentProductId());
		
		if ( !ltP2pGroupProductsList.isEmpty() && (ltP2pGroupProducts.getGroupProductsId()==null?true: !ltP2pGroupProducts.getGroupProductsId().equals(ltP2pGroupProductsList.get(0).getGroupProductsId()))) {
			status.setCode(0);
			status.setMessage("parentproductidpresent");

			return new ResponseEntity<Status>(status, HttpStatus.OK);

		}
		ltP2pGroupProducts.setCreationDate(new Date());
		ltP2pGroupProducts.setLastUpdateDate(new Date());
		ltP2pGroupProducts.setCreatedBy(ltP2pGroupProducts.getLastUpdateLogin());
		ltP2pGroupProducts.setLastUpdatedBy(ltP2pGroupProducts.getLastUpdateLogin());
		ltP2pGroupProducts.setLastUpdateLogin(ltP2pGroupProducts.getLastUpdateLogin());
		ltP2pGroupProducts = ltP2pGroupProductsRepository.save(ltP2pGroupProducts);
		if(ltP2pGroupProducts.getGroupProductsId()!=null) {
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
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
		}else
		{
//			status=ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
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
	public ResponseEntity<Status> deleteGroupProducts(Long id) throws ServiceException
	{
		Status status = new Status();
		if (ltP2pGroupProductsRepository.exists(id)) 
		{
			ltP2pGroupProductsRepository.delete(id);
			if(ltP2pGroupProductsRepository.exists(id)) {
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_FAIL);
				try {
					status.setCode(0);
					status.setMessage(ltMastCommonMessageService.getMessageNameByCode("DELETE_FAIL").getMessageName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if( status.getMessage()==null) {
					status.setCode(0);
					status.setMessage("Error in finding message! The action is completed unsuccessfully.");
				}
			}else {
//				status=ltMastCommonMessageService.getCodeAndMessage(DELETE_SUCCESSFULLY);
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
		}
		return new ResponseEntity<Status>(status, HttpStatus.OK);
	}

}
