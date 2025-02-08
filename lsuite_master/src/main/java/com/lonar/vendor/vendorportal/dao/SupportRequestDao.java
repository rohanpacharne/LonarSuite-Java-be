package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;

import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.SupportRequest;
 
public interface SupportRequestDao {
	
 
 
		SupportRequest saveSupportEmail(SupportRequest request);
		
		List<SupportRequest> getLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
				throws ServiceException;
 
		Long getCountLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
				throws ServiceException;
 
		
 
//	    SupportRequest findById(Long ticketId);
//
//	    List<SupportRequest> findAll();
//
//	    SupportRequest update(SupportRequest supportRequest);
//
//	    void deleteById(Long ticketId);
	}