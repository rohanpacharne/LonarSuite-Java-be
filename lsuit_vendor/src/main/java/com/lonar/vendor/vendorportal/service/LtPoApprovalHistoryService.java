package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lonar.vendor.vendorportal.model.LtPoApprovalHistory;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPoApprovalHistoryService {

	ResponseEntity<Status> save(LtPoApprovalHistory poApprovalHistory) throws ServiceException;

	List<LtPoApprovalHistory> getPoApprovalHistoryByPoId(Long poHeaderId) throws ServiceException;

}
