package com.lonar.vendor.vendorportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtExpExpenseLinesService extends CodeMaster{
	
	public Status updateV1(LtExpExpenseLines ltExpExpenseLine, MultipartFile[] files, int cpmpanyId) throws Exception ;
	
	public Long getCount(Long headerId, LtExpExpenseLines input) throws Exception;
	
	public List<LtExpExpenseLines> getDatatableByExpenseHeaderId(Long headerId, Long userId, LtExpExpenseLines input) throws Exception;
	
	public Status saveV1(LtExpExpenseLines ltExpExpenseLine, MultipartFile[] files,int companyId) throws Exception;
	
	public List<LtExpExpenseLines> getByExpenseHeaderId(Long expenseHeaderId) throws Exception;
	
	public LtExpExpenseLines getExpenseLinesByLineIdV1(Long lineId) throws Exception;
	
	public Status delete(Long lineId) throws Exception;
	
	public Status deleteExpenseLineAttachments(Long expLineId);
	
	public Status updateHeader(Long expHeaderId, List<Long> expLineIds);

}
