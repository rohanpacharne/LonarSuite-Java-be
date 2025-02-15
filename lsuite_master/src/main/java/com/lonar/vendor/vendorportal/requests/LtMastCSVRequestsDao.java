package com.lonar.vendor.vendorportal.requests;

import java.util.List;

import com.lonar.vendor.vendorportal.model.ServiceException;

public interface LtMastCSVRequestsDao {

	List<RequestField> getVendorData(RequestParameters requestParameters) throws ServiceException;



	

}
