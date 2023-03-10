package com.lonar.asn.dao;

import java.util.List;

import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;

public interface LtShipmentInboxDao {

	Long getShipmentCount(String status, String approvalId, AsnApproval input) throws BusinessException;

	List<AsnApproval> getShipmentByStatus(String status, String approvalId, AsnApproval input) throws BusinessException;

}
