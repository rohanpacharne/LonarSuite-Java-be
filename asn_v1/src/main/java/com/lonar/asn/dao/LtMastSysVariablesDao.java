package com.lonar.asn.dao;


import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.lonar.asn.model.LtMastSysVariables;
import com.lonar.asn.model.SysVariableWithValues;



public interface LtMastSysVariablesDao
{
	public List<SysVariableWithValues> getBySysVariableName(String name,Long companyId) throws ServiceException;

	public List<SysVariableWithValues> getBySysVariableName1(String name) throws ServiceException;

	LtMastSysVariables getByName(LtMastSysVariables ltMastSysVariables) throws ServiceException;

	LtMastSysVariables getByCode(LtMastSysVariables ltMastSysVariables) throws ServiceException;

	public Long getCount(LtMastSysVariables input)  throws Exception;

	public List<LtMastSysVariables> getDatatableRecords(LtMastSysVariables input)  throws Exception;
}
