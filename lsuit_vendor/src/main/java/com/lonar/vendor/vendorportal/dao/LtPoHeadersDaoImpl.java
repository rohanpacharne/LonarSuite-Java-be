package com.lonar.vendor.vendorportal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import com.lonar.vendor.vendorportal.controller.VendorBuyerDetails;
import com.lonar.vendor.vendorportal.model.Approval;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.DashboardDetails;
import com.lonar.vendor.vendorportal.model.LtMastEmployeeDelegation;
import com.lonar.vendor.vendorportal.model.LtMastModuleApprovals;
import com.lonar.vendor.vendorportal.model.LtPoHeaders;
import com.lonar.vendor.vendorportal.model.LtPoLineReport;
import com.lonar.vendor.vendorportal.model.LtPoReport;
import com.lonar.vendor.vendorportal.model.PoApproval;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.repository.LtPoHeadersRepository;

@Repository
@PropertySource(value = "classpath:queries/poHeaderQueries.properties", ignoreResourceNotFound = true)
public class LtPoHeadersDaoImpl implements LtPoHeadersDao,CodeMaster {

	@Autowired
	private Environment env;

	private JdbcTemplate jdbcTemplate;

	private JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@PersistenceContext(name = "em")
	private EntityManager em;
	
	@Autowired
	LtPoHeadersRepository ltPoHeadersRepository;
	
	@Autowired
	LtMastEmployeeDelegationDao ltMastEmployeeDelegationDao;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Long getLtPoHeaderCount(LtPoHeaders input,Long companyId) throws ServiceException {
		String query = env.getProperty("getLtPoHeaderCount");

		String typeLookupCode = null;
		if (input.getPoType() != null && !input.getPoType().equals("")) {
			typeLookupCode = "%" + input.getPoType().trim().toUpperCase() + "%";
		}

		String poNumber = null;
		if (input.getPoNumber() != null && !input.getPoNumber().equals("")) {
			poNumber = "%" + input.getPoNumber().trim().toUpperCase() + "%";
		}

		String revNumber = null;
		if (input.getRevisionNum() != null) {
			revNumber = "%" + input.getRevisionNum() + "%";
		}

		if (input.getpDate() == null || input.getpDate().trim().equals("")) {
			input.setpDate(null);
		}
		/*
		 * if(input.getRevDate() == null || input.getRevDate().trim().equals("")) {
		 * input.setRevDate(null); }
		 */

		String stat = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			stat = "%" + input.getStatus().trim().trim().toUpperCase() + "%";
		}

		String pAmount = null;
		if (input.getPoAmount() != null) {
			pAmount = "%" + Double.valueOf(input.getPoAmount()).intValue() + "%";
		}

		String code = null;
		if (input.getCurrencyCode() != null && !input.getCurrencyCode().equals("")) {
			code = "%" + input.getCurrencyCode().trim().toUpperCase() + "%";
		}

		String agnt = null;
		if (input.getAgent() != null && !input.getAgent().equals("")) {
			agnt = "%" + input.getAgent().trim().toUpperCase() + "%";
		}
		
		String aFlag = null;
		if (input.getAckFlag() != null && !input.getAckFlag().equals("")) {
			aFlag = "%" + input.getAckFlag().trim().toUpperCase() + "%";
		}

		String count = (String) getJdbcTemplate().queryForObject(query,
				new Object[] {companyId, typeLookupCode, poNumber, revNumber, input.getpDate(), stat, pAmount, agnt, code,aFlag },
				String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtPoHeaders> getLtPoHeaderDataTable(LtPoHeaders input,Long companyId) throws ServiceException {
		String query = env.getProperty("getLtPoHeaderDataTable");

		String typeLookupCode = null;
		if (input.getPoType() != null && !input.getPoType().equals("")) {
			typeLookupCode = "%" + input.getPoType().trim().toUpperCase() + "%";
		}
		System.out.println("typeLookupCode for po = "+typeLookupCode);
		String poNumber = null;
		if (input.getPoNumber() != null && !input.getPoNumber().equals("")) {
			poNumber = "%" + input.getPoNumber().trim().toUpperCase() + "%";
		}

		System.out.println("poNumber for po = "+poNumber);
		
		String revNumber = null;
		if (input.getRevisionNum() != null) {
			revNumber = "%" + input.getRevisionNum() + "%";
		}

		if (input.getpDate() == null || input.getpDate().trim().equals("")) {
			input.setpDate(null);
		}

		/*
		 * if(input.getRevDate() == null || input.getRevDate().trim().equals("")) {
		 * input.setRevDate(null); }
		 */

		String stat = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			stat = "%" + input.getStatus().trim().trim().toUpperCase() + "%";
		}

		String pAmount = null;
		if (input.getPoAmount() != null) {
			pAmount = "%" + Double.valueOf(input.getPoAmount()).intValue() + "%";
		}

		String code = null;
		if (input.getCurrencyCode() != null && !input.getCurrencyCode().equals("")) {
			code = "%" + input.getCurrencyCode().trim().toUpperCase() + "%";
		}

		String agnt = null;
		if (input.getAgent() != null && !input.getAgent().equals("")) {
			agnt = "%" + input.getAgent().trim().toUpperCase() + "%";
		}

		String aFlag = null;
		if (input.getAckFlag() != null && !input.getAckFlag().equals("")) {
			aFlag = "%" + input.getAckFlag().trim().toUpperCase() + "%";
		}

		if (input.getColumnNo() == 0) {
			input.setColumnNo(1);
		}

		List<LtPoHeaders> list = (List<LtPoHeaders>) jdbcTemplate.query(query,
				new Object[] {companyId, typeLookupCode, poNumber, revNumber, input.getpDate(), stat, pAmount, agnt, code, aFlag,

						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),

						input.getStart() + input.getLength(), input.getStart()+1 },
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
		return list;
	}

	@Override
	public List<LtPoHeaders> getAllPoHeaders() throws ServiceException {
		String query = env.getProperty("getAllPoHeaders");
		List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] {},
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));

		return list;
	}

	@Override
	public List<LtPoHeaders> getAllActivePoHeaders() throws ServiceException {
		String query = env.getProperty("getAllActivePoHeaders");
		List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] {},
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
		return list;
	}

	@Override
	public LtPoHeaders getPoHeaderById(Long poHeaderId) throws ServiceException {
		String query = env.getProperty("getPoHeaderById");
		List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] { poHeaderId },
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));

		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public Long getLtPoHeaderCountByVendorId(LtPoHeaders input, Long venId) throws ServiceException {
		String query = env.getProperty("getLtPoHeaderCountByVendorId");

		String typeLookupCode = null;
		if (input.getPoType() != null && !input.getPoType().equals("")) {
			typeLookupCode = "%" + input.getPoType().trim().toUpperCase() + "%";
		}

		String poNumber = null;
		if (input.getPoNumber() != null && !input.getPoNumber().equals("")) {
			poNumber = "%" + input.getPoNumber().trim().toUpperCase() + "%";
		}

		String revNumber = null;
		if (input.getRevisionNum() != null) {
			revNumber = "%" + input.getRevisionNum() + "%";
		}

		if (input.getpDate() == null || input.getpDate().trim().equals("")) {
			input.setpDate(null);
		}
		/*
		 * if(input.getRevDate() == null || input.getRevDate().trim().equals("")) {
		 * input.setRevDate(null); }
		 */

		String stat = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			stat = "%" + input.getStatus().trim().trim().toUpperCase() + "%";
		}

		String pAmount = null;
		if (input.getPoAmount() != null) {
			pAmount = "%" + Double.valueOf(input.getPoAmount()).intValue() + "%";
		}

		String code = null;
		if (input.getCurrencyCode() != null && !input.getCurrencyCode().equals("")) {
			code = "%" + input.getCurrencyCode().trim().toUpperCase() + "%";
		}

		String agnt = null;
		if (input.getAgent() != null && !input.getAgent().equals("")) {
			agnt = "%" + input.getAgent().trim().toUpperCase() + "%";
		}

		String count = (String) getJdbcTemplate().queryForObject(query, new Object[] { venId, typeLookupCode, poNumber,
				revNumber, input.getpDate(), stat, pAmount, agnt, code }, String.class);

		return Long.parseLong(count);
	}

	@Override
	public List<LtPoHeaders> getLtPoHeadersDataTableByVendorId(LtPoHeaders input, Long venId) throws ServiceException {
		String query = env.getProperty("getLtPoHeadersDataTableByVendorId");

		String typeLookupCode = null;
		if (input.getPoType() != null && !input.getPoType().equals("")) {
			typeLookupCode = "%" + input.getPoType().trim().toUpperCase() + "%";
		}

		String poNumber = null;
		if (input.getPoNumber() != null && !input.getPoNumber().equals("")) {
			poNumber = "%" + input.getPoNumber().trim().toUpperCase() + "%";
		}

		String revNumber = null;
		if (input.getRevisionNum() != null) {
			revNumber = "%" + input.getRevisionNum() + "%";
		}

		if (input.getpDate() == null || input.getpDate().trim().equals("")) {
			input.setpDate(null);
		}
		/*
		 * if(input.getRevDate() == null || input.getRevDate().trim().equals("")) {
		 * input.setRevDate(null); }
		 */

		String stat = null;
		if (input.getStatus() != null && !input.getStatus().equals("")) {
			stat = "%" + input.getStatus().trim().trim().toUpperCase() + "%";
		}

		String pAmount = null;
		if (input.getPoAmount() != null) {
			pAmount = "%" + Double.valueOf(input.getPoAmount()).intValue() + "%";
		}

		String code = null;
		if (input.getCurrencyCode() != null && !input.getCurrencyCode().equals("")) {
			code = "%" + input.getCurrencyCode().trim().toUpperCase() + "%";
		}

		String agnt = null;
		if (input.getAgent() != null && !input.getAgent().equals("")) {
			agnt = "%" + input.getAgent().trim().toUpperCase() + "%";
		}

		if (input.getColumnNo() == 0) {
			input.setColumnNo(1);
		}

		List<LtPoHeaders> list = (List<LtPoHeaders>) jdbcTemplate.query(query,
				new Object[] { venId, typeLookupCode, poNumber, revNumber, input.getpDate(), stat, pAmount, agnt, code,

						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(), input.getColumnNo(), input.getColumnNo(),
						input.getColumnNo(), input.getColumnNo(),input.getColumnNo(), input.getColumnNo(),

						input.getStart() + input.getLength(), input.getStart()+1 },
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
		return list;
	}

	@Override
	public DashboardDetails getAmountByVendorId(Long vendorId) throws ServiceException {
		String query = env.getProperty("getAmountByVendorId");
		List<DashboardDetails> list = jdbcTemplate.query(query, new Object[] { vendorId },
				new BeanPropertyRowMapper<DashboardDetails>(DashboardDetails.class));
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<DashboardDetails> getCountAndStatusByVendorId(Long vendorId) throws ServiceException {
		String query = env.getProperty("getCountAndStatusByVendorId");
		List<DashboardDetails> list = jdbcTemplate.query(query, new Object[] { vendorId },
				new BeanPropertyRowMapper<DashboardDetails>(DashboardDetails.class));

		return list;

	}

	@Override
	public List<LtPoHeaders> getTopFivePoById(Long vendorId) throws ServiceException {
		String query = env.getProperty("getTopFivePoById");
		List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] { vendorId },
				new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));

		return list;
	}

	@Override
	public List<DashboardDetails> getStatusCountByBuyerId(Long buyerId, Long companyId) throws ServiceException {
		String query = env.getProperty("getCountAndStatusByBuyerId");
		if (buyerId == 0) {
			buyerId = null;
		}
//		List<DashboardDetails> list = jdbcTemplate.query(query, new Object[] { buyerId, companyId },
//				new BeanPropertyRowMapper<DashboardDetails>(DashboardDetails.class));
		
		List<DashboardDetails> list = jdbcTemplate.query(query, new Object[] {companyId },
				new BeanPropertyRowMapper<DashboardDetails>(DashboardDetails.class));

		return list;
	}

	@Override
	public List<VendorBuyerDetails> getVendorMsgByBuyerId(Long buyerId) throws ServiceException {
		String query = env.getProperty("getVendorMesaageByBuyerId");

		List<VendorBuyerDetails> list = jdbcTemplate.query(query, new Object[] { buyerId },
				new BeanPropertyRowMapper<VendorBuyerDetails>(VendorBuyerDetails.class));
		return list;

	}

	@Override
	public List<DashboardDetails> getQtrStatusCountByBuyerId(Long buyerId, Long year) throws ServiceException {
		String query = env.getProperty("getQtrCountAndStatusByBuyerIdANdYearWise");
		List<DashboardDetails> list = jdbcTemplate.query(query, new Object[] { buyerId, year },
				new BeanPropertyRowMapper<DashboardDetails>(DashboardDetails.class));
		return list;
	}

	@Override
	public boolean acknowldge(LtPoHeaders ltPoHeaders) throws ServiceException {
		String query = env.getProperty("acknowldgeLtPoHeaders");
		int res = jdbcTemplate.update(query,
				new Object[] { ltPoHeaders.getAckFlag(), ltPoHeaders.getAckMsg(), ltPoHeaders.getPoHeaderId() });
		if (res != 0)
			return true;
		else
			return false;
	}

	@Override
	public List<LtPoHeaders> getActivePoHeadersByPoNumber(Long companyId, Long userId, String poNumber)
			throws ServiceException {
		
		if(userId==-1) {
			String query = env.getProperty("getActivePoHeadersByPoNumberBuyer");
			List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] { "%" + poNumber + "%", companyId },
					new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
			return list;
		}else {
			String query = env.getProperty("getActivePoHeadersByPoNumber");
			List<LtPoHeaders> list = jdbcTemplate.query(query, new Object[] { "%" + poNumber + "%", userId, companyId },
					new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
			return list;
		}
		

		
	}

	@Override
	public List<LtPoReport> createPOPDFReport(Long poHeaderId,Long companyId) {
		String query = env.getProperty("ltPOReportQueryByPONumberAndCompanyId");
		 
		//List<LtPoReport> poReportList = jdbcTemplate.query(query, new Object[] { poHeaderId}, 
		//		new BeanPropertyRowMapper<LtPoReport>(LtPoReport.class));
		//========================================================================
		List<LtPoReport> poReportList = (ArrayList<LtPoReport>)jdbcTemplate.query(
				query, new Object[] { poHeaderId }, new LtPoReportRowMapper());
		
		//========================================================================
		return poReportList;
	}
	
	@Override
	public boolean upDateStatus(Long poHeaderId, String status, String currentApprovalLavel)
			throws ServiceException {
		int res=0;
		if(currentApprovalLavel!=null)
		{
			//String query = env.getProperty("upDateStatus1");
			String query = " UPDATE LT_PO_APPROVAL SET STATUS=?,LAST_UPDATE_DATE=? " +
					" WHERE PO_HEADER_ID=? AND APPROVAL_LEVEL =? AND STATUS <> ? ";
			 res=jdbcTemplate.update(query,
				        status,new Date(),poHeaderId,currentApprovalLavel,"APPROVED");
		}
		else
		{
			//String query = env.getProperty("upDateStatus2");
			String query =" UPDATE LT_PO_APPROVAL SET " +
					"STATUS=? ,LAST_UPDATE_DATE= ?, CURRENT_APPROVAL_LEVEL=? WHERE PO_HEADER_ID=?";
			
			res=jdbcTemplate.update(query,
			        status,new Date(),currentApprovalLavel,poHeaderId);
		}
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean chkForApprovers(Long poHeaderId) throws ServiceException {
		
		String query = "SELECT ea.*, \n" +
	               "       COALESCE(CONCAT( \n" +
	               "           COALESCE(CONCAT(em.FIRST_NAME, ' ', em.LAST_NAME, ' (', em.EMPLOYEE_NUMBER, ')'), ''), \n" +
	               "           COALESCE(ea.DELEGATION_ID, CONCAT(' (', CONCAT(emm.FIRST_NAME, ' ', emm.LAST_NAME, ' (', emm.EMPLOYEE_NUMBER, ')')), '') \n" +
	               "       )) AS approval_Name, \n" +
	               "       CASE \n" +
	               "           WHEN ea.MODULE_APPROVAL_ID = 0 THEN 'Invitor' \n" +
	               "           ELSE ema.approval_role_name \n" +
	               "       END AS approval_level_name \n" +
	               "FROM lt_Po_Approval ea \n" +
	               "LEFT JOIN lt_mast_employees em ON ea.APPROVAL_ID = em.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_employees emm ON ea.DELEGATION_ID = emm.EMPLOYEE_ID \n" +
	               "LEFT JOIN lt_mast_module_approvals ema ON ea.APPROVAL_LEVEL = ema.APPROVAL_LEVEL \n" +
	               "    AND ea.MODULE_APPROVAL_ID = ema.MODULE_APPROVAL_ID \n" +
	               "WHERE ea.PO_HEADER_ID = ? \n" +
	               "ORDER BY ea.APPROVAL_LEVEL";
 
 
		List<PoApproval> poApprovalList = jdbcTemplate.query(query, new Object[] {poHeaderId},
				new RowMapper<PoApproval>() {
					public PoApproval mapRow(ResultSet rs, int arg1) throws SQLException {
 
						PoApproval poApproval = new PoApproval();
						
						poApproval.setPoApprovalId(rs.getLong("PO_APPROVAL_ID"));
						poApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						poApproval.setApprovalId(rs.getLong("APPROVAL_ID"));
						poApproval.setApprovalLevel(rs.getString("APPROVAL_LEVEL"));
						poApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						
						poApproval.setPoHeaderId(rs.getLong("PO_HEADER_ID"));
						
						return poApproval;
					}
				});
		
		
		if(poApprovalList.size() > 0){
			return true;
		}else
			return false;
	}
	
	@Override
	public boolean submitForApproval(Date date, Long poHeaderId, String status, Object object)
			throws ServiceException {
		String query ="UPDATE LT_PO_HEADERS SET Status = ? , Last_update_date =? WHERE PO_HEADER_ID = ? ";
		
		int res=jdbcTemplate.update(query,status ,new Date(), poHeaderId);
		
		if(res!=0)
			return true;
		else
			return false;
	}
	
	@Override
	public Status callPoValidationProc(Long poHeaderId) throws ServiceException {
		Status status = new Status();
		StoredProcedureQuery query = em
			    .createStoredProcedureQuery("validate_po")
			    .registerStoredProcedureParameter(1, Long.class,
				         ParameterMode.IN)
			    .registerStoredProcedureParameter(2, String.class,
			         ParameterMode.OUT)
		        .registerStoredProcedureParameter(3, String.class,
		         ParameterMode.OUT)
			    .setParameter(1, poHeaderId);
			query.execute();
 
			if(query.getOutputParameterValue(2).toString().trim().equals("ERROR")){
				status.setCode(0);
				status.setMessage(query.getOutputParameterValue(3).toString().trim());
			}
			else if(query.getOutputParameterValue(2).toString().trim().equals("SUCCESS")){
				status.setCode(1);
				status.setMessage(query.getOutputParameterValue(3).toString().trim());
				
			}
			return status;
	}
	
	@Override
	public String checkforApprovals(Long poHeaderId) throws ServiceException {
		String query = " SELECT ma.*  "
				+" FROM LT_MAST_MODULE_APPROVALS ma "
				+" WHERE ma.DIVISION_ID = "
				+"  (SELECT e.DIVISION_ID FROM LT_MAST_EMPLOYEES e,LT_PO_HEADERS inv WHERE e.EMPLOYEE_ID = inv.buyer_id and inv.PO_HEADER_ID = ? ) ";
				
		List<LtMastModuleApprovals> list=   jdbcTemplate.query(query, new Object[]{poHeaderId},
					 new BeanPropertyRowMapper<LtMastModuleApprovals>(LtMastModuleApprovals.class));
		
		if(list.isEmpty())
			return "null";
		else
		return "present";
	}
	
	@Override
	public boolean checkStatusIsPending(Long poHeaderId, Long approvalId) throws ServiceException {
		String query = " select * from LT_PO_APPROVAL "
				 		+" where PO_HEADER_ID = ? "
						 +" AND (APPROVAL_ID = ? OR DELEGATION_ID = ? ) "
						 +" AND APPROVAL_LEVEL = CURRENT_APPROVAL_LEVEL "
						 +" AND STATUS = 'PENDING' ";
				
		List<PoApproval> list=   jdbcTemplate.query(query, new Object[]{poHeaderId, approvalId,approvalId},
					 new BeanPropertyRowMapper<PoApproval>(PoApproval.class));
		
		if(list.size() > 0)
			return true;
		else
		return false;
	}
	
	@Override
	public LtPoHeaders getPoStatusById(Long poHeaderId) throws ServiceException {
		
		Status status = new Status();
		String query = " select STATUS, CREATED_BY from LT_PO_HEADERS where PO_HEADER_ID = ? ";
		
		List<LtPoHeaders> ltPoHeadersList = jdbcTemplate.query(query, new Object[] {poHeaderId},
				new RowMapper<LtPoHeaders>() {
					public LtPoHeaders mapRow(ResultSet rs, int arg1) throws SQLException {
 
						LtPoHeaders poHeaders = new LtPoHeaders();
						
						poHeaders.setStatus(rs.getString("STATUS"));
						poHeaders.setCreatedBy(rs.getLong("CREATED_BY"));
						
						return poHeaders;
					}
				});
		
		
		if(ltPoHeadersList.size() > 0){
			return ltPoHeadersList.get(0);
		}else {
			return null;
		}
		
		
	}
	
	@Override
	public Long save(LtPoHeaders ltPoHeaders) throws ServiceException {
//		if(ltPoHeaders.getPoHeaderId()!=null) {
//		String internalPoNum =  (String) em.createNativeQuery(
//			        "SELECT get_internal_invoice_number(:p_invoice_header_id) FROM DUAL"
//			    )
//			    .setParameter("p_invoice_header_id", ltPoHeaders.getPoHeaderId())
//			    .getSingleResult();
//	    		  
//		ltPoHeaders.setInternalPoNumber(internalPoNum);
//		}
		System.out.println("company Id = "+ltPoHeaders.getCompanyId());
		ltPoHeaders = ltPoHeadersRepository.save(ltPoHeaders);
		if(ltPoHeaders.getPoHeaderId()!=null) {
			
			return ltPoHeaders.getPoHeaderId();
		}
			
		else
		return null;
	}
	
	@Transactional
	@Override
	public boolean loadApprovers(LtPoHeaders ltPoHeaders) throws ServiceException {
		
		List<PoApproval> poApprovalsList = getApprovalList(ltPoHeaders.getPoHeaderId(),null);
		if(poApprovalsList.isEmpty()) {
		 String query = " SELECT a.module_app_employees_id,a.employees_id,b.approval_level,b.module, "
					+ " a.MODULE_APPROVAL_ID ,a.START_DATE,a.END_DATE  "
					+ " FROM lt_mast_module_app_emp a,lt_mast_module_approvals b "
					+ " WHERE a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID "
					+ " AND DIVISION_ID= ? "
					+ " AND MODULE= 'PURCHASE'  "
					+ " AND STATUS= 'ACTIVE' "
					+ " AND ( a.START_DATE <= SYSDATE() AND (a.END_DATE is null or a.END_DATE > SYSDATE()) ) ";
			
			List<Approval> approvalList=   jdbcTemplate.query(query, new Object[]{ ltPoHeaders.getDivisionId()},
				 new BeanPropertyRowMapper<Approval>(Approval.class));
		
			//List<LtMastEmployees>  empList=ltMastEmployeesDao.getByEmpId(ltInvoiceHeaders.getBuyerId());
			
				Approval superviserApproval = new Approval();
				superviserApproval.setEmployeesId(ltPoHeaders.getBuyerId());
				superviserApproval.setApprovalLevel("00");
				superviserApproval.setModuleApprovalId(00L);
			
				approvalList.add(superviserApproval);
			
			boolean flag=false;
		if(approvalList.size()>0)
		{
			
			for(Approval approvalObj:approvalList)
			{
				System.out.println("approvalObj = "+approvalObj);
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
					res=jdbcTemplate.update(" INSERT INTO lt_po_approval "
							+ " (MODULE_APPROVAL_ID,APPROVAL_ID,APPROVAL_LEVEL,CURRENT_APPROVAL_LEVEL,DELEGATION_ID, "
							+ " PO_HEADER_ID, STATUS,START_DATE,END_DATE, CREATED_BY,CREATION_DATE,LAST_UPDATE_LOGIN,"
							+ " LAST_UPDATED_BY,LAST_UPDATE_DATE ,MODULE_APP_EMPLOYEES_ID)  "
			 		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
			 		approval.getModuleApprovalId(),approval.getEmployeesId(),approval.getApprovalLevel(),
			 		null,approval.getDelegationId(),ltPoHeaders.getPoHeaderId(),NO_ACTION,new Date(),
			 		null,ltPoHeaders.getCreatedBy(),new Date(),
			 		ltPoHeaders.getLastUpdateLogin(),ltPoHeaders.getLastUpdatedBy(),
			 		new Date(),approval.getModuleAppEmployeesId());
					if(res!=0)
						flag=true;
				}
				
			}
		}
		
		return flag;
		}else {
			boolean flag=false;
			poApprovalsList.get(0).setApprovalId(ltPoHeaders.getBuyerId());
			//for(InvoiceApproval invoiceApproval : invoiceApprovalsList ) {
				int res=0;
				res=jdbcTemplate.update(" UPDATE lt_po_approval SET APPROVAL_ID = ?,LAST_UPDATE_DATE = ? "
						+ "  WHERE APPROVAL_ID = ? AND APPROVAL_LEVEL = ? ",
						poApprovalsList.get(0).getApprovalId(),new Date(),poApprovalsList.get(0).getPoApprovalId(),"00");
				if(res!=0) {
					return flag=true;
				}
			//}
			return flag;
		}
	}
	
	@Override
	public List<PoApproval> getApprovalList(Long poHeaderId, String currentApprovalLevel)
			throws ServiceException {
		
		String query = " SELECT a.*,'N' as APPROVED_BY_ANYONE " +
				" FROM LT_PO_APPROVAL a left outer join lt_mast_module_approvals b " +
				" on a.MODULE_APPROVAL_ID=b.MODULE_APPROVAL_ID  " +
				" WHERE a.PO_HEADER_ID = ? AND a.APPROVAL_LEVEL = ifnull(?,a.APPROVAL_LEVEL) ORDER BY a.APPROVAL_LEVEL ASC ";
		List<PoApproval> list=   jdbcTemplate.query(query, new Object[]{ poHeaderId,currentApprovalLevel},
				 new BeanPropertyRowMapper<PoApproval>(PoApproval.class));
			return list;
	}
	
	@Override
	public LtPoHeaders getByPoNumVendAndAddr(String poNumber, Long vendorId, Long vendorAddId)
			throws ServiceException {
		String query = env.getProperty("getByPoNumVendAndAddr");
		List<LtPoHeaders> list=   jdbcTemplate.query(query, new Object[]{poNumber.toUpperCase(),vendorId, vendorAddId },
				 new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
		if(list.isEmpty())
			return null;
		else
		 return list.get(0);
	}
	
	@Override
	public List<LtPoHeaders> getInprocessPoList(String inprogressStr) throws ServiceException {
		String query = " SELECT  po.*, apr.APPROVAL_LEVEL  " +
				" FROM LT_PO_HEADERS po, LT_PO_APPROVAL apr " +
				" WHERE apr.PO_HEADER_ID = po.PO_HEADER_ID" +
				" AND po.Status= 'INPROCESS' " +
				" AND ((apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'PO_APPROVED') " +
				" OR (apr.CURRENT_APPROVAL_LEVEL IS NULL AND apr.STATUS = 'NO_ACTION') " +
				" OR  (apr.APPROVAL_LEVEL = apr.CURRENT_APPROVAL_LEVEL AND apr.STATUS = 'NO_ACTION'))";
		List<LtPoHeaders> list=   jdbcTemplate.query(query, new Object[]{  },
			 new BeanPropertyRowMapper<LtPoHeaders>(LtPoHeaders.class));
		return list;
	}
	
	@Override
	public PoApproval getApprovalLevel(Long poHeaderId) throws ServiceException {
		//String query = env.getProperty("getApprovalLevel");
		
		String query = "select   MIN( APPROVAL_LEVEL) as MIN_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID " +
				" from LT_PO_APPROVAL where PO_HEADER_ID = ? " +
				" group by APPROVAL_LEVEL,  CURRENT_APPROVAL_LEVEL ,MODULE_APPROVAL_ID order by MIN_LEVEL";
		
		List<PoApproval> poApprovalList = jdbcTemplate.query(query, new Object[] {poHeaderId},
				
				new RowMapper<PoApproval>() {
					public PoApproval mapRow(ResultSet rs, int arg1) throws SQLException {
 
						PoApproval poApproval = new PoApproval();
 
						poApproval.setApprovalLevel(rs.getString("MIN_LEVEL"));
						poApproval.setCurrentApprovalLevel(rs.getString("CURRENT_APPROVAL_LEVEL"));
						poApproval.setModuleApprovalId(rs.getLong("MODULE_APPROVAL_ID"));
						
						
						return poApproval;
					}
				});
		if(poApprovalList.size()>0)
			return poApprovalList.get(0);
		else
			return null;
	}
	
 
	@Override
	public String getNextApprovalLevel(Long poHeaderId, String currentApprovalLavel) throws ServiceException {
		String query = "select MIN(APPROVAL_LEVEL) AS  CURRENT_APPROVAL_LEVEL " +
				" from LT_PO_APPROVAL where PO_HEADER_ID = ? AND APPROVAL_LEVEL > ? AND STATUS <> ? ";
		
		String nextlavel  = (String)getJdbcTemplate().queryForObject(
				query, new Object[] { poHeaderId, currentApprovalLavel,APPROVED}, String.class);
 
		return nextlavel;
	}
	
	
	@Override
	public void updateCurrentApprovalLevel(Long poHeaderId, String currentApprovalLavel) throws ServiceException {
		//String query = env.getProperty("updateCurrentApprovalLevel");
		String query = "UPDATE LT_PO_APPROVAL SET " +
				"CURRENT_APPROVAL_LEVEL = ?  WHERE PO_HEADER_ID=? ";
		int res=jdbcTemplate.update(query,
				currentApprovalLavel, poHeaderId );
	}

	 
	 
}
class LtPoReportRowMapper implements RowMapper<LtPoReport>
{
	List<LtPoLineReport> lineReportList=new ArrayList<>();
	LtPoReport poReport = new LtPoReport();
	public LtPoReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		poReport.setPoNumber(rs.getString("order_number"));
		poReport.setRevisionNum(rs.getString("revision"));
		poReport.setPoDate(rs.getTimestamp("po_date"));
		poReport.setRevisionDate(rs.getTimestamp("rev_date")); 
		poReport.setVendorName(rs.getString("v_name"));
		poReport.setVendorAddress(rs.getString("ven_add"));
		poReport.setPurchasingContact(rs.getString("buyer"));
		
		poReport.setEmail(rs.getString("email"));
		poReport.setTelephone(rs.getString("telephone"));
		poReport.setFax(rs.getString("fax"));
		poReport.setAuthorizedBy(rs.getString("auth_by"));
		poReport.setShipTo(rs.getString("ship_to"));
		poReport.setBillTo(rs.getString("bill_to"));
		poReport.setGstinShip(rs.getString("gstinst"));
		poReport.setGstinBill(rs.getString("gstinbt"));
		poReport.setPaymentTerms(rs.getString("payment_terms"));
		poReport.setPaymentMethod(rs.getString("payment_method"));
		poReport.setCurrencyCode(rs.getString("currency"));
		poReport.setFreightTerms(rs.getString("freight_terms"));
		poReport.setIncotermsFOB(rs.getString("fob"));
		poReport.setCarrier(rs.getString("carriers"));
		
		
	 
		poReport.setPdfPath("");
		poReport.setReportCompanyLogoPath("");
		poReport.setReportGeneratedPath("");
		//poReport.setTotalTaxAmount(rs.getString("total_taxes"));
		//poReport.setTotalLineTax(rs.getString("amount"));
		poReport.setTotalAmount(rs.getString("total_amount"));
		//poReport.setTotal(rs.getInt("count"));
		poReport.setCompanyName(rs.getString("company_name"));
		
		LtPoLineReport poLineReport=new LtPoLineReport();
		poLineReport.setLineNum(rs.getString("line_no"));
//		poLineReport.setShipmentNum(rs.getString("ship_num"));
		poLineReport.setItem(rs.getString("item"));
		poLineReport.setItemDescription(rs.getString("item_desc"));
		poLineReport.setDeliveryDate(rs.getTimestamp("delivery"));
		poLineReport.setQuantity(rs.getString("quantity"));
		poLineReport.setUOM(rs.getString("uom"));
		poLineReport.setUnitPrice(rs.getString("unit_price"));
		poLineReport.setSubAmount(rs.getString("sub_amount"));
		poLineReport.setLineNoOne(rs.getString("linec"));
		poLineReport.setTaxNameAndDescriptionCGST(rs.getString("tax_name_and_descc"));
		poLineReport.setTaxAmountCGST(rs.getString("ratec"));
		poLineReport.setLineNoTwo(rs.getString("LINESGST"));
		poLineReport.setTaxNameAndDescriptionSGST(rs.getString("tax_name_and_descs"));
		poLineReport.setTaxAmountSGST(rs.getString("rates"));
		poLineReport.setRateCGST(rs.getString("taxc"));
		poLineReport.setRateSGST(rs.getString("taxs"));
		poLineReport.setTotalTaxAmount(rs.getString("total_taxes"));
		poLineReport.setTotalLineTax(rs.getString("amount"));
		
		lineReportList.add(poLineReport);
		
		
		
		poReport.setLineReportList(lineReportList);
		
		return poReport;
	}
	
	
}
