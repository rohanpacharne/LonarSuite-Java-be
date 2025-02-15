package com.lonar.vendor.vendorportal.requests;

import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtMastCSVRequestsService {

	Status exportVendorToErp(RequestParameters requestParameters) throws ServiceException;

}
