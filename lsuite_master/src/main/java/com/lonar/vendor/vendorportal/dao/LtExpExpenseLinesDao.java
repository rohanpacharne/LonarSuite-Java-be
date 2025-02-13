package com.lonar.vendor.vendorportal.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lonar.vendor.vendorportal.model.LtExpExpenseLines;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtExpExpenseLinesDao {
	
	public LtExpExpenseLines updateV1(LtExpExpenseLines ltExpExpenseLines) throws Exception;
	
	public LtExpExpenseLines saveV1(LtExpExpenseLines ltExpExpenseLines) throws Exception;
	
	public Long getCount(Long headerId, LtExpExpenseLines input) throws Exception;
	
	public List<LtExpExpenseLines> getDatatableByExpenseHeaderId(Long headerId, Long userId,LtExpExpenseLines input) throws Exception;
	
	public List<LtExpExpenseLines> getByExpenseHeaderId(Long expenseHeaderId) throws Exception;
	
	public LtExpExpenseLines getExpenseLinesByLineIdV1(Long lineId) throws Exception;

	public boolean delete(Long expLineId, String category) throws Exception;
	
	public LtExpExpenseLines getExpenseLinesByLineId(Long lineId) throws Exception;
	
	public boolean deleteExpenseLineAttachments(Long expLineId) throws Exception;
	
	public boolean updateHeader(Long expHeaderId, List<Long> expLineIds);

	


}
