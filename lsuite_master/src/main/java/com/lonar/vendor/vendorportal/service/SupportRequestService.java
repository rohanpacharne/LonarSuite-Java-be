package com.lonar.vendor.vendorportal.service;
 
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.SupportRequest;
 
public interface SupportRequestService {

//		public  SupportRequest sendSupportEmail(SupportRequest request, MultipartFile file) throws Exception;
 
		public void sendSupportRequest(SupportRequest request, MultipartFile file) throws IOException;
		
		List<SupportRequest> getLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
				throws ServiceException;
 
		Long getCountLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
				throws ServiceException;


 
}