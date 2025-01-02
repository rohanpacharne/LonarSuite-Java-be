package com.lonar.asn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lonar.asn.model.Approval;
import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.BusinessException;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.InvoiceApproval;
import com.lonar.asn.model.LtInvoiceHeaders;
import com.lonar.asn.model.LtMastAttachmentType;
import com.lonar.asn.model.LtMastEmployeeDelegation;
import com.lonar.asn.model.LtPoShipment;
import com.lonar.asn.model.LtShipmentApprovalHistory;
import com.lonar.asn.model.LtShipmentHeaders;
import com.lonar.asn.model.LtShipmentLines;
import com.lonar.asn.model.ProcedureCall;
import com.lonar.asn.model.Status;
import com.lonar.asn.model.SubmitAsn;
import com.lonar.asn.repository.LtInvoiceHeadersRepository;
import com.lonar.asn.repository.LtShipmentLinesRepository;

@Repository
@PropertySource(value = "classpath:queries/asnQueries.properties", ignoreResourceNotFound = true)
public class LtShipmentHeaderDaoImpl implements LtShipmentHeaderDao, CodeMaster{

	@Autowired
	private Environment env;
	
	@Autowired
	LtShipmentLinesRepository ltShipmentLinesRepository;
	
	@Autowired
	LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;
	
	@Autowired
	LtInvoiceHeadersRepository ltInvoiceHeadersRepository;
	
	@PersistenceContext(name = "em")
	private EntityManager em;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	
	
	@Override
	public List<String> getListOfStr() throws BusinessException {
		List<String> listStr = new ArrayList<>();
		listStr.add("ABC");
		listStr.add("PQR");
		listStr.add("XYZ");
		return listStr;
	}

	@Override
	public Long getAsnHeaderDataCount(LtShipmentHeaders input) throws BusinessException {
		String query = env.getProperty("asnDataTableDataCount");
		
		String shipmentNumber=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().trim().toUpperCase() + "%";}
	   
		/*if(input.getShippedDate() == null )
		{
			input.setShippedDate(null);
		}*/
		
		if (input.getShippedDateStr() == null || input.getShippedDateStr().trim().equals("")) {
			input.setShippedDateStr(null);
		}
		
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
			
		String vendorAddress=null;
		if(input.getVendorAdd()!=null &&  !input.getVendorAdd().equals("")) 
		{vendorAddress="%"+input.getVendorAdd().trim().trim().toUpperCase()+"%";}
		   
		String status=null;
		if(input.getApprovalStatus()!=null && !input.getApprovalStatus().equals(""))
		{status="%"+input.getApprovalStatus().trim().trim().toUpperCase()+"%";}
		  
		   
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {input.getVendorId(), shipmentNumber, input.getShippedDateStr(), 
						vendorName, vendorAddress, status}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtShipmentHeaders> getAsnHeaderDataTableDetails(LtShipmentHeaders input) throws BusinessException {
		String query = env.getProperty("asnDataTableQueries");
		
		String shipmentNumber=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().trim().toUpperCase() + "%";}
	   
		/*if(input.getShippedDate() == null )
		{
			input.setShippedDate(null);
		}*/
		
		if (input.getShippedDateStr() == null || input.getShippedDateStr().trim().equals("")) {
			input.setShippedDateStr(null);
		}
		   
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
			
		String vendorAddress=null;
		if(input.getVendorAdd()!=null &&  !input.getVendorAdd().equals("")) 
		{vendorAddress="%"+input.getVendorAdd().trim().trim().toUpperCase()+"%";}
		   
		String status=null;
		if(input.getApprovalStatus()!=null && !input.getApprovalStatus().equals(""))
		{status="%"+input.getApprovalStatus().trim().trim().toUpperCase()+"%";}
			
//		if(input.getColumnNo()==0) {
			input.setColumnNo(7);
//		}
		
			List<LtShipmentHeaders> list = (List<LtShipmentHeaders>) 
					jdbcTemplate.query(query , new Object[]{input.getVendorId(), shipmentNumber, input.getShippedDateStr(), 
							vendorName, vendorAddress, status,
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getStart()+input.getLength(),input.getStart()+1},
				 new  BeanPropertyRowMapper<LtShipmentHeaders>(LtShipmentHeaders.class));
			System.out.println("asn list = "+list);
				return list;
	}

	@Override
	public LtShipmentHeaders getByAsnHeaderId(Long id) throws BusinessException {
		String query = env.getProperty("getByAsnHeaderId");
		List<LtShipmentHeaders> list=   jdbcTemplate.query(query, new Object[]{ id}, 
				 new BeanPropertyRowMapper<LtShipmentHeaders>(LtShipmentHeaders.class));
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public List<LtShipmentLines> getAsnLinesByAsnHeaderId(Long id) throws BusinessException {
		String query = env.getProperty("getAsnLinesByAsnHeaderId");
		List<LtShipmentLines> list=   jdbcTemplate.query(query, new Object[]{ id,id}, 
				 new BeanPropertyRowMapper<LtShipmentLines>(LtShipmentLines.class));
		return list;
	}

	@Override
	public boolean updateLines(LtShipmentLines ltShipmentLines) throws BusinessException {
		String query = "UPDATE LT_SHIPMENT_LINES SET QUANTITY_SHIPPED = ? WHERE SHIPMENT_LINE_ID = ? "
				+ " AND SHIPMENT_HEADER_ID = ? AND LAST_UPDATE_DATE = ? ";
		int res = jdbcTemplate
				.update(query,ltShipmentLines.getQuantityShipped(),ltShipmentLines.getShipmentLineId(),
						ltShipmentLines.getShipmentHeaderId(),new Date());
				
				if (res != 0)
					return true;
				else
					return false;
	}

	@Override
	public Long getAsnHeaderDataCountByLocation(LtShipmentHeaders input, Long locationId)
			throws BusinessException {
		String query = env.getProperty("asnDataTableDataCountByLocation");
		
		String shipmentNumber=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().trim().toUpperCase() + "%";}
	   
		/*if(input.getShippedDate() == null )
		{
			input.setShippedDate(null);
		}*/
		
		if (input.getShippedDateStr() == null || input.getShippedDateStr().trim().equals("")) {
			input.setShippedDateStr(null);
		}
		   
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
			
		String vendorAddress=null;
		if(input.getVendorAdd()!=null &&  !input.getVendorAdd().equals("")) 
		{vendorAddress="%"+input.getVendorAdd().trim().trim().toUpperCase()+"%";}
		   
		String status=null;
		if(input.getApprovalStatus()!=null && !input.getApprovalStatus().equals(""))
		{status="%"+input.getApprovalStatus().trim().trim().toUpperCase()+"%";}
		  
		   
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {locationId, shipmentNumber, input.getShippedDateStr(), 
						vendorName, vendorAddress, status}, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtShipmentHeaders> getAsnHeaderDataTableDetailsByLocation(LtShipmentHeaders input, Long locationId)
			throws BusinessException {
		String query = env.getProperty("asnDataTableQueriesByLocation");
		
		String shipmentNumber=null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().trim().toUpperCase() + "%";}
	   
		if (input.getShippedDateStr() == null || input.getShippedDateStr().trim().equals("")) {
			input.setShippedDateStr(null);
		}
		   
		String vendorName=null;
		if(input.getVendorName()!=null && !input.getVendorName().equals(""))
		{vendorName="%"+input.getVendorName().trim().toUpperCase()+"%";}
			
		String vendorAddress=null;
		if(input.getVendorAdd()!=null &&  !input.getVendorAdd().equals("")) 
		{vendorAddress="%"+input.getVendorAdd().trim().trim().toUpperCase()+"%";}
		   
		String status=null;
		if(input.getApprovalStatus()!=null && !input.getApprovalStatus().equals(""))
		{status="%"+input.getApprovalStatus().trim().trim().toUpperCase()+"%";}
		
		System.out.println("input.getColumnNo() = "+input.getColumnNo());
			
		if(input.getColumnNo()==0) {
			input.setColumnNo(15);
		}
	
//			List<LtShipmentHeaders> list = (List<LtShipmentHeaders>) 
//					jdbcTemplate.query(query , new Object[]{locationId, shipmentNumber, input.getShippedDateStr(), 
//							vendorName, vendorAddress, status,
//							input.getStart()+input.getLength(),input.getStart(),
//							input.getColumnNo(),input.getColumnNo(),
//							input.getColumnNo(),input.getColumnNo(),
//							input.getColumnNo(),input.getColumnNo(),
//							input.getColumnNo(),input.getColumnNo()
//							},
//				 new  BeanPropertyRowMapper<LtShipmentHeaders>(LtShipmentHeaders.class));
			
			List<LtShipmentHeaders> list = (List<LtShipmentHeaders>) 
					jdbcTemplate.query(query , new Object[]{locationId,shipmentNumber, input.getShippedDateStr(), 
							vendorName, vendorAddress, status,
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getColumnNo(),input.getColumnNo(),
							input.getStart()+input.getLength(),input.getStart()+1
							},
				 new  BeanPropertyRowMapper<LtShipmentHeaders>(LtShipmentHeaders.class));
			
				return list;
	}

	@Override
	public boolean submitAsn(SubmitAsn submitAsn) throws BusinessException {
		System.out.println("in submit asn = "+submitAsn.getStatus());
		int res;
		if(submitAsn.getAcceptedBy() !=null) {
			String query2 = " UPDATE LT_SHIPMENT_HEADERS SET APPROVAL_STATUS = ? , LAST_UPDATED_BY = ? ,"
					+ "  LAST_UPDATE_LOGIN = ? , LAST_UPDATE_DATE = ?, ACCEPTED_BY = ? WHERE SHIPMENT_HEADER_ID = ? "
					+ "    ";
			
			 res = jdbcTemplate
						.update(query2,submitAsn.getStatus(),submitAsn.getLastUpdateLogin(),
								submitAsn.getLastUpdateLogin(),new Date(), submitAsn.getAcceptedBy(), submitAsn.getShipmentHeaderId());
		}else {
			 String query = " UPDATE LT_SHIPMENT_HEADERS SET APPROVAL_STATUS = ? , LAST_UPDATED_BY = ? ,"
						+ "  LAST_UPDATE_LOGIN = ? , LAST_UPDATE_DATE = ? WHERE SHIPMENT_HEADER_ID = ? "
						+ "    ";
				
				 res = jdbcTemplate
						.update(query,submitAsn.getStatus(),submitAsn.getLastUpdateLogin(),
								submitAsn.getLastUpdateLogin(),new Date(),submitAsn.getShipmentHeaderId());
		}
				if (res != 0)
					return true;
				else
					return false;
	}

	@Override
	public SubmitAsn getAsnStatusByAsnHeaderId(Long id) throws BusinessException {
		String query = env.getProperty("getAsnStatusByAsnHeaderId");
		List<SubmitAsn> list=   jdbcTemplate.query(query, new Object[]{ id}, 
				 new BeanPropertyRowMapper<SubmitAsn>(SubmitAsn.class));
		if(list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public Long getCountpoShipmentDataTableByVendorId(LtPoShipment input, Long vendorId) throws BusinessException {

		String query = env.getProperty("poShipmentDataTableCount");
		
		String poNumber=null;
		if(input.getPoNumber()!=null && !input.getPoNumber().equals(""))
		{poNumber="%"+input.getPoNumber().trim().toUpperCase() + "%";}
		   
		String poLineId =null;
		if(input.getPoLineId()!=null && !input.getPoLineId().equals(""))
		{poLineId="%"+input.getPoLineId().toString()+"%";}
		
		String shipmentNumber =null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().toString()+"%";}
		
		String itemDescription =null;
		if(input.getProductDescription()!=null &&  !input.getProductDescription().equals("")) 
		{itemDescription="%"+input.getProductDescription().trim().trim().toUpperCase()+"%";}
		
		if(input.getDueDate() == null )
		{
			input.setDueDate(null);
		}
		
		String orderQuantity =null;
		if(input.getQuantityOrdered()!=null && !input.getQuantityOrdered().equals(""))
		{orderQuantity="%"+input.getQuantityOrdered().toString()+"%";}
		
		String quantityRecived =null;
		if(input.getQuantityReceived()!=null && !input.getQuantityReceived().equals(""))
		{quantityRecived="%"+input.getQuantityReceived().toString()+"%";}
		   
		String shipToLocation =null;
		if(input.getShipToLocation()!=null && !input.getShipToLocation().equals(""))
		{shipToLocation="%"+input.getShipToLocation().trim().trim().toUpperCase()+"%";}
		  
		   
		String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] {vendorId,
						poNumber, poLineId,itemDescription, shipmentNumber
						, input.getDueDate(), orderQuantity,
						quantityRecived, shipToLocation}, String.class);
		
		return Long.parseLong(count);
		
	}

	@Override
	public List<LtPoShipment> getPoShipmentDataTableByVendorId(LtPoShipment input, Long vendorId)
			throws BusinessException {
		String query = env.getProperty("poShipmentDataTableList");
		
		String poNumber=null;
		if(input.getPoNumber()!=null && !input.getPoNumber().equals(""))
		{poNumber="%"+input.getPoNumber().trim().toUpperCase() + "%";}
		   
		String poLineId =null;
		if(input.getLineNum()!=null && !input.getLineNum().equals(""))
		{poLineId="%"+input.getLineNum().toString()+"%";}
		
		String shipmentNumber =null;
		if(input.getShipmentNum()!=null && !input.getShipmentNum().equals(""))
		{shipmentNumber="%"+input.getShipmentNum().toString()+"%";}
		
		String itemDescription =null;
		if(input.getProductDescription()!=null &&  !input.getProductDescription().equals("")) 
		{itemDescription="%"+input.getProductDescription().trim().trim().toUpperCase()+"%";}
		
		if(input.getDueDate() == null )
		{
			input.setDueDate(null);
		}
		
		String orderQuantity =null;
		if(input.getQuantityOrdered()!=null && !input.getQuantityOrdered().equals(""))
		{orderQuantity="%"+input.getQuantityOrdered().toString()+"%";}
		
		String quantityRecived =null;
		if(input.getQuantityReceived()!=null && !input.getQuantityReceived().equals(""))
		{quantityRecived="%"+input.getQuantityReceived().toString()+"%";}
		   
		String shipToLocation =null;
		if(input.getShipToLocation()!=null && !input.getShipToLocation().equals(""))
		{shipToLocation="%"+input.getShipToLocation().trim().trim().toUpperCase()+"%";}
		
		if(input.getColumnNo()==0) {
			input.setColumnNo(6);
		}
		List<LtPoShipment> list = (List<LtPoShipment>) 
				jdbcTemplate.query(query , new Object[]{
						vendorId,
						poNumber, poLineId, itemDescription,shipmentNumber
						, input.getDueDate(), orderQuantity,
						quantityRecived, shipToLocation,
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
						input.getColumnNo(),input.getColumnNo(),
					
						input.getStart()+input.getLength(),input.getStart()},
			 new  BeanPropertyRowMapper<LtPoShipment>(LtPoShipment.class));
		
			return list;
	}
	
	@Override
	@Transactional
	public Long getShipmentSourceIds() throws BusinessException {
		String sql = env.getProperty("shipmentSourceId");
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
	
	// x_status OUT VARCHAR2,x_message OUT VARCHAR2,x_shipment_header_id OUT NUMBER,p_shipment_source_id IN NUMBER

	@Override
	@Transactional
	public ProcedureCall saveAsnHeaderAndLineData(Long sourceId) throws BusinessException {
		ProcedureCall procedureCall= new ProcedureCall();
			StoredProcedureQuery query = em
				    .createStoredProcedureQuery("create_asn")
				    .registerStoredProcedureParameter(1, String.class, 
					         ParameterMode.OUT)
				    .registerStoredProcedureParameter(2, String.class, 
				         ParameterMode.OUT)
			        .registerStoredProcedureParameter(3, Long.class, 
			         ParameterMode.OUT)
				    .registerStoredProcedureParameter(4, Long.class, 
					         ParameterMode.IN)
				    .setParameter(4, sourceId);
				query.execute();

				if(query.getOutputParameterValue(1).toString().trim().equals("ERROR")){
					procedureCall.setStatusCode(query.getOutputParameterValue(1).toString().trim());
					procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString().trim());
				}
				else if(query.getOutputParameterValue(1).toString().trim().equals("SUCCESS")){
					procedureCall.setStatusCode("SUCCESS");
					if(query.getOutputParameterValue(2)!=null) {
					procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString());
					}
					procedureCall.setShipmentHeaderId((Long) query.getOutputParameterValue(3));
				}
				return procedureCall;
	}

	@Override
	public boolean deleteAsnLineAttachment(Long id) throws BusinessException {
		int res=jdbcTemplate.update(" DELETE FROM LT_SHIPMENT_ATTACHMENT WHERE SHIPMENT_ATTACHMENT_ID = "
		+ "( SELECT ATTACHMENT FROM LT_SHIPMENT_LINES WHERE SHIPMENT_LINE_ID = ? )",id );	
	
		if(res!=0) {
		int res1=jdbcTemplate.update("UPDATE LT_SHIPMENT_LINES SET ATTACHMENT = ?, LAST_UPDATE_DATE = ?  WHERE SHIPMENT_LINE_ID = ?  "
		, null,new Date(), id);	
		if(res1!=0) {
		return true;
	
		}else {
		return false;
		}
		}
		return false;
	}

	@Override
	public ProcedureCall createInvoiceFromAsn(Long shipmentHeaderId, Long userId) throws BusinessException {
		System.out.println("userId = "+userId);
		System.out.println("shipmentHeaderId = "+shipmentHeaderId);
		ProcedureCall procedureCall= new ProcedureCall();
			StoredProcedureQuery query = em
				    .createStoredProcedureQuery("create_invoice_from_asn")
				    .registerStoredProcedureParameter(1, Long.class, 
					         ParameterMode.IN)
				    .registerStoredProcedureParameter(2, Long.class, 
				         ParameterMode.IN)
			        .registerStoredProcedureParameter(3, String.class, 
			         ParameterMode.OUT)
				    .registerStoredProcedureParameter(4, String.class, 
					         ParameterMode.OUT)
				    .registerStoredProcedureParameter(5, Long.class, 
					         ParameterMode.OUT)
				    .setParameter(1, shipmentHeaderId)
				    .setParameter(2, userId);
				query.execute();

				if(query.getOutputParameterValue(3).toString().trim().equals("ERROR")){
					procedureCall.setStatusCode(query.getOutputParameterValue(3).toString().trim());
					procedureCall.setStatusMessage(query.getOutputParameterValue(4).toString().trim());
				}
				else if(query.getOutputParameterValue(3).toString().trim().equals("SUCCESS")){
					procedureCall.setStatusCode("SUCCESS");
					if(query.getOutputParameterValue(4)!=null) {
					procedureCall.setStatusMessage(query.getOutputParameterValue(4).toString());
					}
					if(query.getOutputParameterValue(5)!=null) {
						procedureCall.setShipmentHeaderId((Long)query.getOutputParameterValue(5));
						}
					//procedureCall.setShipmentHeaderId((Long) query.getOutputParameterValue(3));
				}
				return procedureCall;
	}

	@Override
	@Transactional
	public boolean updatePoShipmentLines(LtShipmentLines ltShipmentLinesObj) throws BusinessException {
		String query1  = " SELECT SUM(sl.QUANTITY_RECEIVED) as QUANTITY_RECEIVED  FROM LT_SHIPMENT_LINES sl,LT_PO_SHIPMENTS ps " + 
				"				   WHERE ps.PO_SHIPMENT_LINE_ID = sl.PO_SHIPMENT_ID " + 
				"				   AND sl.PO_SHIPMENT_ID = ? AND sl.PO_HEADER_ID = ? AND sl.PO_LINE_ID = ?  ";
		Double count  = getJdbcTemplate().queryForObject(
				query1, new Object[] { ltShipmentLinesObj.getPoShipmentId(), ltShipmentLinesObj.getPoHeaderId(), 
						ltShipmentLinesObj.getPoLineId() }, Double.class);
		
		if(count!=null) {
				String query = " UPDATE LT_PO_SHIPMENTS " + 
				"    SET QUANTITY_RECEIVED =  ? ," + 
				"    QUANTITY_SHIPPED = ?, LAST_UPDATE_DATE = ? WHERE PO_SHIPMENT_LINE_ID = ? AND PO_HEADER_ID = ? " + 
				"				 AND PO_LINE_ID = ? ";
		
				int res = 0;
				res = jdbcTemplate
						.update(query,count,ltShipmentLinesObj.getQuantityShipped(),new Date(),
							ltShipmentLinesObj.getShipmentLineId(),ltShipmentLinesObj.getPoHeaderId(),
						ltShipmentLinesObj.getPoLineId());
		
				if(res!=0) {
					return true;
				}else {
					return false;
				}
		}
		return true;
	}

	@Override
	@Transactional
	public boolean updateRecivedQuantity(LtShipmentLines ltShipmentLinesObj) throws BusinessException {
		String query = " UPDATE LT_SHIPMENT_LINES " + 
				"    SET QUANTITY_RECEIVED =  ? ," + 
				"    QUANTITY_SHIPPED = ? , LAST_UPDATE_DATE = ? WHERE SHIPMENT_LINE_ID = ? AND PO_SHIPMENT_ID = ? AND PO_HEADER_ID = ? " + 
				"				 AND PO_LINE_ID = ? ";
		
				int res = 0;
				res = jdbcTemplate
						.update(query,ltShipmentLinesObj.getQuantityReceived(),ltShipmentLinesObj.getQuantityShipped(),
								new Date(),ltShipmentLinesObj.getShipmentLineId(),ltShipmentLinesObj.getPoShipmentId(),
						ltShipmentLinesObj.getPoHeaderId(),ltShipmentLinesObj.getPoLineId());
				if(res!=0) {
					
					String query1  = " SELECT SUM(sl.QUANTITY_RECEIVED) as QUANTITY_RECEIVED  FROM LT_SHIPMENT_LINES sl,LT_PO_SHIPMENTS ps " + 
							"				   WHERE ps.PO_SHIPMENT_LINE_ID = sl.PO_SHIPMENT_ID " + 
							"				   AND sl.PO_SHIPMENT_ID = ? AND sl.PO_HEADER_ID = ? AND sl.PO_LINE_ID = ?  ";
					Double count  = getJdbcTemplate().queryForObject(
							query1, new Object[] { ltShipmentLinesObj.getPoShipmentId(), ltShipmentLinesObj.getPoHeaderId(), 
									ltShipmentLinesObj.getPoLineId() }, Double.class);
					
					
					
					if(count!=null) {
						String updateQuery = " UPDATE LT_PO_SHIPMENTS " + 
						"    SET QUANTITY_RECEIVED =  ? ," + 
						"    QUANTITY_SHIPPED = ?, LAST_UPDATE_DATE = ? WHERE PO_SHIPMENT_LINE_ID = ? AND PO_HEADER_ID = ? " + 
						"				 AND PO_LINE_ID = ? ";
				
						int res1 = 0;
						res1 = jdbcTemplate
								.update(updateQuery,count,ltShipmentLinesObj.getQuantityShipped(),
										new Date(),ltShipmentLinesObj.getPoShipmentId(),
								ltShipmentLinesObj.getPoHeaderId(),ltShipmentLinesObj.getPoLineId());
				
						if(res1!=0) {
							return true;
						}else {
							return false;
						}
				}
					
					return true;
				}else {
					return false;
				}
		
	}

	@Override
	public List<LtShipmentHeaders> getInprocessAsnList(String asnInprogress) throws BusinessException {
		String query = env.getProperty("getInprocessAsnList");
		List<LtShipmentHeaders> list=   jdbcTemplate.query(query, new Object[]{ asnInprogress.toUpperCase()}, 
				 new BeanPropertyRowMapper<LtShipmentHeaders>(LtShipmentHeaders.class));
		return list;
	}

	@Override
	public AsnApproval getApprovalLevel(Long shipmentHeaderId) throws BusinessException {
		String query = "select   MIN( APPROVAL_LEVEL) as MIN_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID " + 
				" from LT_SHIPMENT_APPROVAL where SHIPMENT_HEADER_ID = ? " + 
				" group by APPROVAL_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID order by MIN_LEVEL";
		
		List<AsnApproval> invoiceApprovalList = jdbcTemplate.query(query, new Object[] {shipmentHeaderId},
				
				new RowMapper<AsnApproval>() {
					public AsnApproval mapRow(ResultSet rs, int arg1) throws SQLException {

						AsnApproval asnApproval = new AsnApproval();

						asnApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
						asnApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						asnApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						
						
						return asnApproval;
					}
				});
		if(invoiceApprovalList.size()>0)
			return invoiceApprovalList.get(0); 
		else 
			return null;
	}

	@Override
	public List<AsnApproval> getApprovalList(Long shipmentHeaderId, String currentApprovalLevel)
			throws BusinessException {
		String query = " SELECT a.*,'N' as APPROVED_BY_ANYONE " + 
				" FROM LT_SHIPMENT_APPROVAL a  " + 
				" WHERE a.SHIPMENT_HEADER_ID = ? AND a.APPROVAL_LEVEL = nvl(?,a.APPROVAL_LEVEL) ORDER BY a.APPROVAL_LEVEL ASC ";
		List<AsnApproval> list=   jdbcTemplate.query(query, new Object[]{ shipmentHeaderId,currentApprovalLevel}, 
				 new BeanPropertyRowMapper<AsnApproval>(AsnApproval.class));
			return list;
	}

	@Override
	public String getNextApprovalLevel(Long shipmentHeaderId, String currentApprovalLavel) throws BusinessException {
		String query = "select MIN (APPROVAL_LEVEL) AS  CURRENT_APPROVAL_LEVEL " + 
				" from LT_SHIPMENT_APPROVAL where SHIPMENT_HEADER_ID = ? AND APPROVAL_LEVEL > ? AND STATUS <> ? ";
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { shipmentHeaderId, currentApprovalLavel,ASN_APPROVED}, String.class);

		return nextlavel;
	}

	@Override
	public boolean upDateStatus(Long shipmentHeaderId, String status, String currentApprovalLevel)
			throws BusinessException {
		int res=0;
		if(currentApprovalLevel!=null)
		{
			String query = env.getProperty("upDateVStatus1");
			 res=jdbcTemplate.update(query,
				        status,new Date(),shipmentHeaderId,currentApprovalLevel,"APPROVED");
		}
		else
		{
			String query = env.getProperty("upDateVStatus2");
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLevel,shipmentHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
		
	}

	@Override
	public void updateCurrentApprovalLevel(Long shipmentHeaderId, String currentApprovalLavel)
			throws BusinessException {
		String query = env.getProperty("updateCurrentApprovalLevelV");
		int res=jdbcTemplate.update(query,
				currentApprovalLavel, shipmentHeaderId );
		
	}

	@Override
	public List<AsnApproval> getShipmentApprovalByShipmentId(Long shipmentHeaderId) throws BusinessException {
		String query = env.getProperty("getShipmentApprovalByShipmentId");
		List<AsnApproval> list=   jdbcTemplate.query(query, new Object[]{ shipmentHeaderId}, 
				 new BeanPropertyRowMapper<AsnApproval>(AsnApproval.class));
		return list;
	}

	@Override
	public boolean updateStatusApproval(LtShipmentApprovalHistory approvalHistory) throws BusinessException {
		int res=0;	
		if(approvalHistory.getEmployeeId()!=null ) 
		{
			String query = " UPDATE LT_SHIPMENT_APPROVAL "
			+" SET  Status= ? , LAST_UPDATE_DATE = ?,LAST_UPDATED_BY=? " 
			+" WHERE SHIPMENT_HEADER_ID = ?  AND (APPROVAL_ID = ? OR DELEGATION_ID = ?) ";
			
			res = jdbcTemplate.update(query,
					approvalHistory.getStatus(),new Date(),approvalHistory.getLastUpdateLogin(),
					approvalHistory.getShipmentHeaderId(), approvalHistory.getEmployeeId(),approvalHistory.getEmployeeId());
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public String getCurrLevelByShipmentApprovalId(Long shipmentApprovalId) throws BusinessException {
		String query = env.getProperty("getCurrLevelByShipmentApprovalId");
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { shipmentApprovalId}, String.class);

		return nextlavel;
	}

	@Override
	public boolean submitForApproval(Date submissionDate, Long shipmentHeaderId, String status, Date approvedDate,
			Long lastUpdateLogin) throws BusinessException {
		int res=0;
		if(submissionDate!=null)
		{
			String query = env.getProperty("submitShipmentForApproval1");
			res=jdbcTemplate.update(query,status,new Date(),lastUpdateLogin,shipmentHeaderId);
			
		}
		else
		{
			String query = env.getProperty("submitShipmentForApproval2");
			 res=jdbcTemplate.update(query,status,new Date(),lastUpdateLogin,shipmentHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}

	@Override
	public boolean checkStatusIsPending(Long shipmentId, Long approvalId) throws BusinessException {
		String query = env.getProperty("checkShipmentStatusIsPending"); 
		List<AsnApproval> list=   jdbcTemplate.query(query, new Object[]{shipmentId, approvalId,approvalId}, 
			 new BeanPropertyRowMapper<AsnApproval>(AsnApproval.class)); 

		if(list.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public AsnApproval getShipmentApproval(Long shipmentId, Long apprId) throws BusinessException {
		String query = env.getProperty("getShipmentApprovalByShipmentIdAndApproverId");
		List<AsnApproval> list=   jdbcTemplate.query(query, new Object[]{ shipmentId,apprId }, 
				 new BeanPropertyRowMapper<AsnApproval>(AsnApproval.class)); 
		if(list.isEmpty())
			return null;
		else 
		 return list.get(0);
	}

	@Override
	public List<LtShipmentApprovalHistory> getApprovalHistoryByShipmentId(Long shipmentHeaderId)
			throws BusinessException {
		String query = env.getProperty("getShipmentApprovalHistoryByShipmentId");
		List<LtShipmentApprovalHistory> list=   jdbcTemplate.query(query, new Object[]{ shipmentHeaderId }, 
				 new BeanPropertyRowMapper<LtShipmentApprovalHistory>(LtShipmentApprovalHistory.class));
		return list; 
	}

	@Override
	public boolean saveRemark(LtShipmentApprovalHistory ltShipmentApprovalHistory) throws BusinessException {
		int res = jdbcTemplate
				.update("INSERT INTO LT_SHIPMENT_APPROVAL_HISTORY "
						+ " (SHIPMENT_APPROVAL_HISTORY_ID,SHIPMENT_APPROVAL_ID,EMPLOYEE_ID, "
						+ " STATUS,NOTE,LAST_UPDATE_DATE,SHIPMENT_HEADER_ID,REMARK,USER_TYPE,VENDOR_ID ) "
						+ " VALUES(LT_SHIPMENT_APPROVAL_HISTORY_S.NEXTVAL,?,?,?,?,?,?,?,?,?) ",

				//ltExpenseApprovalHistory.getVendorApprovalHistoryId(),
						ltShipmentApprovalHistory.getShipmentApprovalId(),
						ltShipmentApprovalHistory.getEmployeeId(),
						ltShipmentApprovalHistory.getStatus(),
						ltShipmentApprovalHistory.getNote(),
						new Date(),
						ltShipmentApprovalHistory.getShipmentHeaderId(),
						ltShipmentApprovalHistory.getRemark(),
						ltShipmentApprovalHistory.getUserType(),
						ltShipmentApprovalHistory.getVendorId());
				
				if (res != 0)
					return true;
				else
					return false;
	}

	@Override
	public Status getAsnApprovalByAsnHeaderId(Long venId) throws BusinessException {
		Status status = new Status();
		String query = env.getProperty("getAsnApprovalByAsnHeaderId");
		String count  = (String)getJdbcTemplate().queryForObject(query, new Object[] {venId}, String.class);
		if(count!=null) {
			status.setData(count);
			status.setCode(1);
		}else {
			status.setCode(0);
		}
		return status;
	}

	@Override
	public List<LtMastAttachmentType> getAsnAttachmentList(LtShipmentHeaders ltShipmentHeaders)
			throws BusinessException {
		String query = env.getProperty("getAsnAttachmentList");
		List<LtMastAttachmentType> list=   jdbcTemplate.query(query, new Object[]{ "ASN_ATTACHMENT_TYPE",ltShipmentHeaders.getVendorId()}, 
				 new BeanPropertyRowMapper<LtMastAttachmentType>(LtMastAttachmentType.class));
		return list;
	}

	@Override
	public ProcedureCall asnValidationPkgCall(Long shipmentHeaderId) throws BusinessException {
		System.out.println("in procedure");
		ProcedureCall procedureCall= new ProcedureCall();
		StoredProcedureQuery query = em
			    .createStoredProcedureQuery("validate_asn")
			    .registerStoredProcedureParameter(1, String.class, 
				         ParameterMode.OUT)
			    .registerStoredProcedureParameter(2, String.class, 
			         ParameterMode.OUT)
			    .registerStoredProcedureParameter(3, Long.class, 
				         ParameterMode.IN)
			    .setParameter(3, shipmentHeaderId);
			query.execute();

			if(query.getOutputParameterValue(1).toString().trim().equals("ERROR")){
				procedureCall.setStatusCode(query.getOutputParameterValue(1).toString().trim());
				procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString().trim());
			}
			else if(query.getOutputParameterValue(1).toString().trim().equals("SUCCESS")){
				procedureCall.setStatusCode("SUCCESS");
				if(query.getOutputParameterValue(2)!=null) {
				procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString());
				}
				//procedureCall.setShipmentHeaderId((Long) query.getOutputParameterValue(3));
			}
			System.out.println("procedureCall "+procedureCall);
			return procedureCall;
	}

	@Override
	public ProcedureCall asnDeletePkgCall(Long id) throws BusinessException {
		ProcedureCall procedureCall= new ProcedureCall();
		StoredProcedureQuery query = em
			    .createStoredProcedureQuery("delete_asn")
			    .registerStoredProcedureParameter(1, String.class, 
				         ParameterMode.OUT)
			    .registerStoredProcedureParameter(2, String.class, 
			         ParameterMode.OUT)
			    .registerStoredProcedureParameter(3, Long.class, 
				         ParameterMode.IN)
			    .setParameter(3, id);
			query.execute();

			if(query.getOutputParameterValue(1).toString().trim().equals("ERROR")){
				procedureCall.setStatusCode(query.getOutputParameterValue(1).toString().trim());
				procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString().trim());
			}
			else if(query.getOutputParameterValue(1).toString().trim().equals("SUCCESS")){
				procedureCall.setStatusCode("SUCCESS");
				if(query.getOutputParameterValue(2)!=null) {
				procedureCall.setStatusMessage(query.getOutputParameterValue(2).toString());
				}
			}
			return procedureCall;
	}

	@Override
	public LtShipmentLines getQuantityByPoHeaderId(Long poHeaderId) throws BusinessException {
		String query = env.getProperty("getQuantityByPoHeaderId");
		List<LtShipmentLines> list=   jdbcTemplate.query(query, new Object[]{ poHeaderId}, 
				 new BeanPropertyRowMapper<LtShipmentLines>(LtShipmentLines.class));
		System.out.println("poHeaderId "+poHeaderId);
		System.out.println(list);
		return list.get(0);
	}

	@Override
	public boolean loadInvoiceApprovers(Long invoiceHeaderId) throws BusinessException {
		List<InvoiceApproval> invoiceApprovalsList = getInvoiceApprovalList(invoiceHeaderId,null);
		LtInvoiceHeaders ltInvoiceHeaders = ltInvoiceHeadersRepository.findOne(invoiceHeaderId);
		if(invoiceApprovalsList.isEmpty()) {
//		 String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
//					+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
//					+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
//					+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
//					+ " AND DIVISION_ID= (SELECT DIVISION_ID FROM LT_INVOICE_HEADERS WHERE INVOICE_HEADER_ID = ? ) "
//					+ " AND MODULE= 'INVOICE'  "
//					+ " AND STATUS= 'ACTIVE' "
//					+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
		 
		 String query = "SELECT mae.module_app_employees_id,mae.employees_id,ma.approval_level,ma.module," +
		 	" ma.MODULE_APPROVAL_ID ,ma.START_DATE,ma.END_DATE  " +
	        " FROM lt_mast_module_approvals ma " +
	        " JOIN lt_mast_module_app_emp mae ON ma.module_approval_id = mae.module_approval_id " +
	        " JOIN lt_mast_employees emp ON mae.employees_id = emp.employee_id " +
	        " WHERE ma.module = 'INVOICE' " +
	        " AND ma.status = 'ACTIVE' " +
	        " AND NOW() BETWEEN ma.start_date AND IFNULL(ma.end_date, NOW()) " +
	        " AND ma.required_level = 'Y' " +
	        " AND ma.isdelete = 'N' " +
	        " AND ma.division_id = (SELECT DIVISION_ID FROM LT_INVOICE_HEADERS WHERE INVOICE_HEADER_ID = ? ) "+
	        " AND NOW() BETWEEN mae.start_date AND IFNULL(mae.end_date, NOW()) " +
	        " AND NOW() BETWEEN emp.start_date AND IFNULL(emp.end_date, NOW()) " +
	        " AND emp.status = 'ACTIVE' " +
	        " ORDER BY ma.approval_level ";
			
			List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ invoiceHeaderId}, 
				 new BeanPropertyRowMapper<Approval>(Approval.class)); 
		 System.out.println("approvalList = "+approvalList);
			
			//List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltInvoiceHeaders.getBuyerId());
			
				Approval superviserApproval = new Approval();
				superviserApproval.setEmployeesId(ltInvoiceHeaders.getBuyerId());
				superviserApproval.setApprovalLevel("00");
				superviserApproval.setModuleApprovalId(00L);
			
				approvalList.add(superviserApproval);
			
			boolean flag=false;
		if(approvalList.size()>0)
		{
			for(Approval approvalObj:approvalList)
			{
				Approval approval=approvalObj;
				List<LtMastEmployeeDelegation> ltMastEmployeeDelegation = ltMastEmployeeDelegationDao
						.findForDelegation(approvalObj.getEmployeesId());
				if(ltMastEmployeeDelegation!= null && ltMastEmployeeDelegation.size()>0)
				{
					approval.setDelegationId(ltMastEmployeeDelegation.get(0).getDelegationId());
				}
				
				int res=0;
				if(approval.getEmployeesId()!=null && approval.getModuleApprovalId()!=null && approval.getApprovalLevel()!=null)
				{
					res=jdbcTemplate.update(" INSERT INTO lt_invoice_approval "
							+ " ( MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
							+ " INVOICE_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
							+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
			 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
			 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
			 		null,approval.getDelegationId(),ltInvoiceHeaders.getInvoiceHeaderId(),NO_ACTION,new Date(),
			 		null,ltInvoiceHeaders.getCreatedBy(),new Date(),
			 		ltInvoiceHeaders.getLastUpdateLogin(),ltInvoiceHeaders.getLastUpdatedBy(),
			 		new Date(),approval.getModuleAppEmployeesId());
					if(res!=0)
						flag=true;
				}
				
			}
		}
		
		return flag;
		}else {
			boolean flag=false;
			invoiceApprovalsList.get(0).setApprovalId(ltInvoiceHeaders.getBuyerId());
			//for(InvoiceApproval invoiceApproval : invoiceApprovalsList ) {
				int res=0;
				res=jdbcTemplate.update(" UPDATE lt_invoice_approval SET APPROVAL_ID = ?,LAST_UPDATE_DATE = ? "
						+ "  WHERE INVOICE_APPROVAL_ID = ? AND APPROVAL_LEVEL = ? ",
						invoiceApprovalsList.get(0).getApprovalId(),new Date(),invoiceApprovalsList.get(0).getInvoiceApprovalId(),"00");
				if(res!=0) {
					return flag=true;
				}
			//}
			return flag;
		}
	}
	
	public List<InvoiceApproval> getInvoiceApprovalList(Long invoiceHeaderId, String currentApprovalLevel)
			throws BusinessException {
		
		String query = " SELECT a.*,'N' as APPROVED_BY_ANYONE " + 
				" FROM LT_INVOICE_APPROVAL a left outer join lt_mast_module_approvals b " + 
				" on a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID  " + 
				" WHERE a.INVOICE_HEADER_ID = ? AND a.APPROVAL_LEVEL = IFNULL(?,a.APPROVAL_LEVEL) ORDER BY a.APPROVAL_LEVEL ASC ";
		List<InvoiceApproval> list=   jdbcTemplate.query(query, new Object[]{ invoiceHeaderId,currentApprovalLevel}, 
				 new BeanPropertyRowMapper<InvoiceApproval>(InvoiceApproval.class));
			return list;
	}
}
