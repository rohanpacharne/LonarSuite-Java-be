package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.ExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;

public interface LtMastEmailtokenService {

	public List<LtMastEmailtoken> findByEmailtokenid(Long emailtokenid) throws Exception;

	public List<LtMastEmailtoken> findByTokenid(String tokenid) throws Exception;

	public List<LtMastEmailtoken> findAllActive() throws Exception;

	public List<LtMastEmailtoken> getDataTable(LtMastEmailtoken input) throws Exception;

	public Long getCount(LtMastEmailtoken input) throws Exception;

	public LtMastEmailtoken getLtMastEmailtokenById(String emailtokenid);
	
	public void makeEntryInEmailToken(List<ExpenseApproval> expenseApproval,String emailTemplate,LtExpExpenseHeaders ltExpExpenseHeaders,String type) throws Exception;
	
	public void updateStatus(Long tokenId, String status, Integer count) throws Exception;


	
}
