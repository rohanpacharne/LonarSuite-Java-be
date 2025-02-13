package com.lonar.vendor.vendorportal.reports;

import java.util.List;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lonar.vendor.vendorportal.model.LtMastReportRequest;
import com.lonar.vendor.vendorportal.model.LtMastSysRequests;
import com.lonar.vendor.vendorportal.model.ServiceException;


@Repository
@PropertySource(value = "classpath:queries/reportQueries.properties", ignoreResourceNotFound = true)
public class LtMastExcelReportsDaoImpl implements LtMastExcelReportsDao{
	
	
	private static final Logger logger = LoggerFactory.getLogger(LtMastExcelReportsDaoImpl.class);


	@Autowired
	private Environment env;
	
	 @Autowired
	  private EntityManager entityManager;
	  private final ObjectMapper objectMapper = new ObjectMapper();


	private JdbcTemplate jdbcTemplate;
	@Autowired
	LtMastReportRequestRepository ltMastReportRequestRepository;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public List<LtMastMasterReportMap> getHeader(String masterName) throws ServiceException 
	{
		String query = env.getProperty("getLtMastMasterReportMapHeader");
		
		List<LtMastMasterReportMap> list = (List<LtMastMasterReportMap>) 
				jdbcTemplate.query(query , new Object[]{ masterName.trim().toUpperCase()},
			 new  BeanPropertyRowMapper<LtMastMasterReportMap>(LtMastMasterReportMap.class));
		return list;

		
	}

	@Override
	public Long getCount(LtMastReportRequest input,Long companyId) throws ServiceException {
		String query = env.getProperty("getLtMastReportRequestCount");
		 
		System.out.println("input.getRequestId() "+input.getRequestId());
			String reqId=null;
			if(input.getRequestIdStr()!=null && !input.getRequestIdStr().equals(" ") )
			{reqId="%"+input.getRequestIdStr()+"%";}
		   
		   String requestName=null;
		   if(input.getRequestName()!=null && !input.getRequestName().equals(" ") && !input.getRequestName().isEmpty())
		   {requestName="%"+input.getRequestName().toUpperCase()+"%";}
		   
		   String fileName=null;
		   if(input.getFileName()!=null && !input.getFileName().equals(" ") && !input.getFileName().isEmpty())
		   {fileName="%"+input.getFileName().toUpperCase()+"%";}
		   
		   String filePath=null;
		   if(input.getFilePath()!=null && !input.getFilePath().equals(" ") && !input.getFilePath().isEmpty())
		   {filePath="%"+input.getFilePath().toUpperCase()+"%";}
		   
		   if(input.getReqDate() == null || input.getReqDate().trim().equals(""))
			{
				input.setReqDate(null);
			}
		   
		   if(input.getCompDate() == null || input.getCompDate().trim().equals(""))
			{
				input.setCompDate(null);
			}
		   
		   String status=null;
		   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
		   {status="%"+input.getStatus().toUpperCase()+"%";}
		   
		   String empName=null;
		   if(input.getRequestorName()!=null && !input.getRequestorName().equals(" ") && !input.getRequestorName().isEmpty()) 
		   {empName="%"+input.getRequestorName().toUpperCase()+"%";}
			
		   System.out.println("reqId "+reqId);
			
		String count  = (String)getJdbcTemplate().queryForObject(
			query, new Object[] {companyId,reqId,requestName,fileName,input.getReqDate(),input.getCompDate(),status,empName }, String.class);

		
		return Long.parseLong(count);
	}

	@Override
	public List<LtMastReportRequest> getReportRequestDataTableRecords(LtMastReportRequest input,Long companyId)
			throws ServiceException {
		String query = env.getProperty("getLtMastReportRequestDatatableRecords");
		
		String reqId=null;
		if(input.getRequestIdStr()!=null && !input.getRequestIdStr().equals(" ") )
		{reqId="%"+input.getRequestIdStr()+"%";}
	   
	   String requestName=null;
	   if(input.getRequestName()!=null && !input.getRequestName().equals(" ") && !input.getRequestName().isEmpty())
	   {requestName="%"+input.getRequestName().toUpperCase()+"%";}
	   
	   String fileName=null;
	   if(input.getFileName()!=null && !input.getFileName().equals(" ") && !input.getFileName().isEmpty())
	   {fileName="%"+input.getFileName().toUpperCase()+"%";}
	   
	   String filePath=null;
	   if(input.getFilePath()!=null && !input.getFilePath().equals(" ") && !input.getFilePath().isEmpty())
	   {filePath="%"+input.getFilePath().toUpperCase()+"%";}
	   
	   if(input.getReqDate() == null || input.getReqDate().trim().equals(""))
		{
			input.setReqDate(null);
		}
	   
	   if(input.getCompDate() == null || input.getCompDate().trim().equals(""))
		{
			input.setCompDate(null);
		}
	   
	   String status=null;
	   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
	   {status="%"+input.getStatus().toUpperCase()+"%";}
	   
	   String empName=null;
	   if(input.getRequestorName()!=null && !input.getRequestorName().equals(" ") && !input.getRequestorName().isEmpty()) 
	   {empName="%"+input.getRequestorName().toUpperCase()+"%";}
		
	   if(input.getColumnNo()==0) {
		   input.setColumnNo(12);
	   }
	   
			List<LtMastReportRequest> list = (List<LtMastReportRequest>) 
					jdbcTemplate.query(query , new Object[]{companyId,reqId,requestName,fileName,input.getReqDate(),input.getCompDate(),
							status,empName,
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							
							input.getStart()+input.getLength(),input.getStart()+1},
				 new  BeanPropertyRowMapper<LtMastReportRequest>(LtMastReportRequest.class));
			
				return list;
	}

	@Override
	public List<LtMastSysRequests> getReportRequestDataTableRecords(LtMastSysRequests input, Long companyId)
			throws ServiceException {
		// TODO Auto-generated method stub
		String query = env.getProperty("getLtMastSysRequestsDatatableRecords");
		
		String reqId=null;
		if(input.getRequestId()!=null && !input.getRequestId().equals(" ") )
		{reqId="%"+input.getRequestId()+"%";}
		
		if(input.getRequestDate() == null || input.getRequestDate().equals(""))
		{
			input.setRequestDate(null);
		}
	   
	   if(input.getCompletedDate() == null || input.getCompletedDate().equals(""))
		{
			input.setCompletedDate(null);
		}
	   
	   String status=null;
	   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
	   {status="%"+input.getStatus().toUpperCase()+"%";}
	   
	 
	   
	   String empName=null;
	   if(input.getRequestorName()!=null && !input.getRequestorName().equals(" ") && !input.getRequestorName().isEmpty()) 
	   {empName="%"+input.getRequestorName().toUpperCase()+"%";}
	   
	   String requestName=null;
	   if(input.getRequestName()!=null && !input.getRequestName().equals(" ") && !input.getRequestName().isEmpty())
	   {requestName="%"+input.getRequestName().toUpperCase()+"%";}
	   
		
//	   if(input.getColumnNo()==0) {
//		   input.setColumnNo(12);
//	   }
	   
			List<LtMastSysRequests> list = (List<LtMastSysRequests>) 
					jdbcTemplate.query(query , new Object[]{companyId,reqId,input.getRequestDate(),
							input.getCompletedDate(),status,empName,requestName,
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							
							
							input.getStart()+1,input.getStart()+input.getLength()},
				 new  BeanPropertyRowMapper<LtMastSysRequests>(LtMastSysRequests.class));
			
				return list;
	}

	@Override
	public Long getCount(LtMastSysRequests input, Long companyId) throws ServiceException {
		String query = env.getProperty("getCountOfSysRequest");
		
		String reqId=null;
		if(input.getRequestId()!=null && !input.getRequestId().equals(" ") )
		{reqId="%"+input.getRequestId()+"%";}
		
		if(input.getRequestDate() == null || input.getRequestDate().equals(""))
		{
			input.setRequestDate(null);
		}
	   
	   if(input.getCompletedDate() == null || input.getCompletedDate().equals(""))
		{
			input.setCompletedDate(null);
		}
	   
	   String status=null;
	   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
	   {status="%"+input.getStatus().toUpperCase()+"%";}
	   
	   
	   String empName=null;
	   if(input.getRequestorName()!=null && !input.getRequestorName().equals(" ") && !input.getRequestorName().isEmpty()) 
	   {empName="%"+input.getRequestorName().toUpperCase()+"%";}
	   
	   String requestName=null;
	   if(input.getRequestName()!=null && !input.getRequestName().equals(" ") && !input.getRequestName().isEmpty())
	   {requestName="%"+input.getRequestName().toUpperCase()+"%";}
	   
	   
	   String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {companyId,reqId,input.getRequestDate(),
						input.getCompletedDate(),status,empName,requestName }, String.class);

			
			return Long.parseLong(count);
	}

	@Override
	public List<ReportField> getTravelReportData(ReportParameter reportParameter) throws ServiceException {
		  String query = env.getProperty("travelReportQuery");
		    
		    List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[]{reportParameter.getCompanyId(),
		                    reportParameter.getStartDate(), reportParameter.getEndDate(), reportParameter.getDivisionId(),reportParameter.getEmployeeId()},
		            new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
		    }

	@Override
	public List<ReportField> getExpenseReportData(ReportParameter reportParameter) throws ServiceException {
		 String query = env.getProperty("expenseReportQuery");
		    
		    List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[]{reportParameter.getCompanyId(),
		                    reportParameter.getStartDate(), reportParameter.getEndDate(),reportParameter.getDivisionId(),reportParameter.getEmployeeId()},
		            new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;}

	@Override
	public List<ReportField> getpurchaseReportData(ReportParameter reportParameter)
			throws ServiceException {
		String query = env.getProperty("purchaseReportQuery");
		    
		    List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus(),
		            		reportParameter.getBuyerId(),
		            		reportParameter.getPoNumberFrom(),
		            		reportParameter.getPoNumberTo()},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;}

	@Override
	public List<ReportField> getinvoiceReportData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("invoicevspaymentReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus(),
		            		reportParameter.getBuyerId(),
		            		reportParameter.getInvoiceNumberFrom(),
		            		reportParameter.getInvoiceNumberTo()},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;}

	@Override
	public List<ReportField> getRentalAgreementReportData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("rentalAgreementReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus(),
		            		reportParameter.getRentalType(),
		            		reportParameter.getAgreementNumber()},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;}

	@Override
	public List<ReportField> getExpiryRentalAgreementReportData(ReportParameter reportParameter)
			throws ServiceException {
		String query = env.getProperty("expiryRentalAgreementReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getRentalType(),
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}

	@Override
	public List<ReportField> getVendorPaymentData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("vendorPaymentReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}
	
	@Override
	public List<ReportField> getVendorRegisterData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("vendorRegisterReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus()
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}
	
	@Override
	public List<ReportField> getVendorProgressData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("vendorProgressReportQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus()
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}

	@Override
	public List<ReportField> getRentalInvoiceSummeryData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("rentalInvoiceSummeryQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus()
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}
	

	@Override
	public List<ReportField> getRentalInvoiceDetailsData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("rentalInvoiceDetailsQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getStartDate(),
		            		reportParameter.getEndDate(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus()
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}

	@Override
	public List<ReportField> getRentalAgreementInvoiceData(ReportParameter reportParameter) throws ServiceException {
		String query = env.getProperty("rentalAgreementInvoiceQuery");
		  List<ReportField> list = (List<ReportField>) 
		            jdbcTemplate.query(query, new Object[] {
		            		reportParameter.getCompanyId(),
		            		reportParameter.getVendorId(),
		            		reportParameter.getAddress(),
		            		reportParameter.getStatus(),
		            		reportParameter.getAgreementNumber()
		            	},    
			new BeanPropertyRowMapper<ReportField>(ReportField.class));
		    return list;
	}


	@Override
	public LtMastReportRequest findById(Long requestId) throws ServiceException {
		  String query = env.getProperty("getParameterQuery");

		    List< LtMastReportRequest> list = jdbcTemplate.query(
		        query,
		        new Object[]{requestId},
		        new BeanPropertyRowMapper<>( LtMastReportRequest.class)
		    );
		    if (list.isEmpty()) {
		        return null; 
		    }
		    return list.get(0);
		}
	
	@Override
	public String findEmployeeNameById(String employeeId) {
	    try {
	    	String query = env.getProperty("getEmployeeIdQuery");
	        return jdbcTemplate.queryForObject(query, new Object[]{employeeId}, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        return null; // Return null if no employee is found
	    }
	}

	@Override
	public String findDivisionNameById(String divisionId) {
	    try {
	    	String query = env.getProperty("getDivisionIdQuery");
	        return jdbcTemplate.queryForObject(query, new Object[]{divisionId}, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}

	@Override
	public String findVendorNameById(String vendorId) {
	    try {
	    	String query = env.getProperty("getVendorIdQuery");
	        return jdbcTemplate.queryForObject(query, new Object[]{vendorId}, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}

	@Override
	public String findBuyerNameById(String buyerId) {
	    try {
	    	String query = env.getProperty("getBuyerIdQuery");
	        return jdbcTemplate.queryForObject(query, new Object[]{buyerId}, String.class);
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}



	@Override
	public List<ModuleDTO> getModulesByUserId(Long userId, Long companyId) {
		  String query = env.getProperty("findMenuQuery");
		    
		  List<ModuleDTO> list = jdbcTemplate.query(query, new Object[]{userId,companyId},
	                new BeanPropertyRowMapper<>(ModuleDTO.class));
	        return list;
	    }
}