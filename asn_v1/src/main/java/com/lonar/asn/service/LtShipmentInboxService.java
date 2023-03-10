package com.lonar.asn.service;

import java.util.List;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;

public interface LtShipmentInboxService {

	Long getShipmentCount(String status, String approvalId, AsnApproval input) throws BusinessException;

	List<AsnApproval> getShipmentByStatus(String status, String approvalId, AsnApproval input) throws BusinessException;

}
