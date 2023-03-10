package com.lonar.asn.dao;
import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtMastSysVariableValues;


public interface LtMastSysVariableValuesDao
{

	public List<LtMastSysVariableValues> getByVariableId(Long parseLong) throws ServiceException;

	public LtMastSysVariableValues getBySysVariableValuesId(Long id) throws ServiceException;
}
