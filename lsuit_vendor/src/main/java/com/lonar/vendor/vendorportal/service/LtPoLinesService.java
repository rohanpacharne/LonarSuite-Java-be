package com.lonar.vendor.vendorportal.service;

import java.util.List;

import com.lonar.vendor.vendorportal.model.LtPoLines;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;

public interface LtPoLinesService {

	Long getLtPoLinesCount(Long headerId, LtPoLines input) throws ServiceException;

	List<LtPoLines> getLtPoLinesDataTable(Long headerId, LtPoLines input) throws ServiceException;

	List<LtPoLines> getAllPoLinesByHeaderId(Long headerId) throws ServiceException;

	LtPoLines getPoLinesByPolineId(Long poLineId) throws ServiceException;

	Status save(LtPoLines ltPoLines) throws ServiceException;

	Status update(LtPoLines ltPoLines) throws ServiceException;

	Status delete(Long id);

}
