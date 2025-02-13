package com.lonar.vendor.vendorportal.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.lonar.vendor.vendorportal.controller.POReportService;
import com.lonar.vendor.vendorportal.controller.VendorBuyerDetails;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.dao.LtPoHeadersDao;
import com.lonar.vendor.vendorportal.dao.LtVendCompanyDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.DashboardDetails;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.LtPoHeaders;
import com.lonar.vendor.vendorportal.model.LtPoReport;
import com.lonar.vendor.vendorportal.model.LtVendCompany;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.repository.LtMastEmailtokenRepository;
import com.lonar.vendor.vendorportal.repository.LtPoHeadersRepository;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class LtPoHeadersServiceImpl implements LtPoHeadersService, CodeMaster {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	LtPoHeadersDao ltPoHeadersDao;

	@Autowired
	LtPoHeadersRepository ltPoHeadersRepository;

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Autowired
	LtMastEmailtokenRepository ltMastEmailtokenRepository;

	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;

	@Autowired
	LtVendCompanyDao ltVendCompanyDao;

	
	@Override
	public Long getLtPoHeaderCount(LtPoHeaders input,Long companyId) throws ServiceException {
		return ltPoHeadersDao.getLtPoHeaderCount(input,companyId);
	}

	@Override
	public List<LtPoHeaders> getLtPoHeaderDataTable(LtPoHeaders input,Long companyId) throws ServiceException {
		if (input.getColumnNo() == 2 && input.getSort().equals("desc")) {
			input.setColumnNo(22);
		}
		if (input.getColumnNo() == 3 && input.getSort().equals("desc")) {
			input.setColumnNo(23);
		}
		if (input.getColumnNo() == 4 && input.getSort().equals("desc")) {
			input.setColumnNo(24);
		}
		if (input.getColumnNo() == 5 && input.getSort().equals("desc")) {
			input.setColumnNo(25);
		}
		if (input.getColumnNo() == 6 && input.getSort().equals("desc")) {
			input.setColumnNo(26);
		}
		if (input.getColumnNo() == 7 && input.getSort().equals("desc")) {
			input.setColumnNo(27);
		}
		if (input.getColumnNo() == 8 && input.getSort().equals("desc")) {
			input.setColumnNo(28);
		}
		if (input.getColumnNo() == 9 && input.getSort().equals("desc")) {
			input.setColumnNo(29);
		}
		if (input.getColumnNo() == 11 && input.getSort().equals("desc")) {
			input.setColumnNo(31);
		}
		if (input.getColumnNo() == 10 && input.getSort().equals("asc")) {
			input.setColumnNo(30);
		}
		return ltPoHeadersDao.getLtPoHeaderDataTable(input,companyId);
	}

	@Override
	public List<LtPoHeaders> getAllPoHeaders() throws ServiceException {
		return ltPoHeadersDao.getAllPoHeaders();
	}

	@Override
	public List<LtPoHeaders> getAllActivePoHeaders() throws ServiceException {
		return ltPoHeadersDao.getAllActivePoHeaders();
	}

	@Override
	public LtPoHeaders getPoHeaderById(Long poHeaderId) throws ServiceException {
		return ltPoHeadersDao.getPoHeaderById(poHeaderId);
	}

	@Override
	public Status save(LtPoHeaders ltPoHeaders) throws ServiceException {
		Status status = new Status();
		ltPoHeaders = ltPoHeadersRepository.save(ltPoHeaders);
		if (ltPoHeaders.getPoHeaderId() != null) {
//			status = ltMastCommonMessageService.getCodeAndMessage(INSERT_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		} else {
//			status = ltMastCommonMessageService.getCodeAndMessage(INSERT_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("INSERT_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		return status;
	}

	@Override
	public Status update(LtPoHeaders ltPoHeaders) throws ServiceException {
		Status status = new Status();
		ltPoHeaders = ltPoHeadersRepository.save(ltPoHeaders);
		if (ltPoHeaders.getPoHeaderId() != null) {
//			status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		} else {
//			status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		return status;
	}

	@Override
	public Long getLtPoHeaderCountByVendorId(LtPoHeaders input, Long venId) throws ServiceException {
		return ltPoHeadersDao.getLtPoHeaderCountByVendorId(input, venId);
	}

	@Override
	public List<LtPoHeaders> getLtPoHeadersDataTableByVendorId(LtPoHeaders input, Long venId) throws ServiceException {

		if (input.getColumnNo() == 1 && input.getSort().equals("asc")) {
			input.setColumnNo(21);
		}
		if (input.getColumnNo() == 2 && input.getSort().equals("desc")) {
			input.setColumnNo(22);
		}
		if (input.getColumnNo() == 3 && input.getSort().equals("desc")) {
			input.setColumnNo(23);
		}
		if (input.getColumnNo() == 4 && input.getSort().equals("desc")) {
			input.setColumnNo(24);
		}
		if (input.getColumnNo() == 5 && input.getSort().equals("desc")) {
			input.setColumnNo(25);
		}
		if (input.getColumnNo() == 6 && input.getSort().equals("desc")) {
			input.setColumnNo(26);
		}
		if (input.getColumnNo() == 7 && input.getSort().equals("desc")) {
			input.setColumnNo(27);
		}
		if (input.getColumnNo() == 8 && input.getSort().equals("desc")) {
			input.setColumnNo(28);
		}
		if (input.getColumnNo() == 9 && input.getSort().equals("asc")) {
			input.setColumnNo(29);
		}
		if (input.getColumnNo() == 10 && input.getSort().equals("asc")) {
			input.setColumnNo(30);
		}
		return ltPoHeadersDao.getLtPoHeadersDataTableByVendorId(input, venId);
	}

	@Override
	public DashboardDetails getAmountByVendorId(Long vendorId) throws ServiceException {
		return ltPoHeadersDao.getAmountByVendorId(vendorId);
	}

	@Override
	public List<DashboardDetails> getCountAndStatusByVendorId(Long vendorId) throws ServiceException {
		return ltPoHeadersDao.getCountAndStatusByVendorId(vendorId);
	}

	@Override
	public List<LtPoHeaders> getTopFivePoById(Long vendorId) throws ServiceException {
		return ltPoHeadersDao.getTopFivePoById(vendorId);
	}

	@Override
	public Status viewFile(String name) throws ServiceException {
		String filepath = "/LexaApplication/Upload/";
		Status status = new Status();
		String str = "C:/LexaApplication/Upload/";
		str = str.concat("/" + name + ".pdf");
		File f = new File(str);

		if (f.exists()) {
			status.setCode(1);
			status.setData(filepath + name + ".pdf");
			status.setMessage("File existed.");
		} else {
			status.setCode(1);
			status.setMessage("File not found!");
		}
		return status;
	}

	@Override
	public List<DashboardDetails> getStatusCountByBuyerId(Long buyerId, Long companyId) throws ServiceException {
		return ltPoHeadersDao.getStatusCountByBuyerId(buyerId, companyId);
	}

	@Override
	public List<VendorBuyerDetails> getVendorMsgByBuyerId(Long buyerId) throws ServiceException {
		return ltPoHeadersDao.getVendorMsgByBuyerId(buyerId);
	}

	@Override
	public List<Object> getQtrStatusCountByBuyerId(Long buyerId, Long year) throws ServiceException {
		List<DashboardDetails> list = ltPoHeadersDao.getQtrStatusCountByBuyerId(buyerId, year);

		List<Object> listOfQtr = new ArrayList<>();

		List<DashboardDetails> listQtr1 = new ArrayList<DashboardDetails>();
		List<DashboardDetails> listQtr2 = new ArrayList<DashboardDetails>();
		List<DashboardDetails> listQtr3 = new ArrayList<DashboardDetails>();
		List<DashboardDetails> listQtr4 = new ArrayList<DashboardDetails>();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			DashboardDetails dashboardDetails = (DashboardDetails) iterator.next();

			if (dashboardDetails.getQtr() != null && dashboardDetails.getQtr() == 1) {
				listQtr1.add(dashboardDetails);
			}

			if (dashboardDetails.getQtr() != null && dashboardDetails.getQtr() == 2) {
				listQtr2.add(dashboardDetails);
			}

			if (dashboardDetails.getQtr() != null && dashboardDetails.getQtr() == 3) {
				listQtr3.add(dashboardDetails);
			}

			if (dashboardDetails.getQtr() != null && dashboardDetails.getQtr() == 4) {
				listQtr4.add(dashboardDetails);
			}
		}
		listOfQtr.add(listQtr1);
		listOfQtr.add(listQtr2);
		listOfQtr.add(listQtr3);
		listOfQtr.add(listQtr4);

		return listOfQtr;
	}

	@Override
	public Status acknowldge(LtPoHeaders ltPoHeaders) throws ServiceException {
		Status status = new Status();
		if (ltPoHeadersDao.acknowldge(ltPoHeaders)) {

			sendAcknowledgeMail(ltPoHeaders);

//			status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_SUCCESSFULLY);
			try {
				status.setCode(1);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_SUCCESSFULLY").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (status.getMessage() == null) {
				status.setCode(1);
				status.setMessage("Error in finding message! The action is completed successfully.");
			}
		} else {
//			status = ltMastCommonMessageService.getCodeAndMessage(UPDATE_FAIL);
			try {
				status.setCode(0);
				status.setMessage(ltMastCommonMessageService.getMessageNameByCode("UPDATE_FAIL").getMessageName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (status.getMessage() == null) {
				status.setCode(0);
				status.setMessage("Error in finding message! The action was unsuccessful");
			}
		}
		return status;
	}

	private void sendAcknowledgeMail(LtPoHeaders ltPoHeaders) throws ServiceException {

		LtPoHeaders ltPoHeader = ltPoHeadersDao.getPoHeaderById(ltPoHeaders.getPoHeaderId());
		LtMastEmailtoken ltP2pEmailtoken = new LtMastEmailtoken();
		ltP2pEmailtoken.setEmailTitle(" Acknolegment of PO Number-" + ltPoHeader.getPoNumber()+" by "+ltPoHeader.getVendorName());
		ltP2pEmailtoken.setEmailTemplate("ackMail");

		List<LtMastEmployees> employee = ltMastEmployeesDao.getByEmpIdForEmail(ltPoHeader.getBuyerId());
		if (!employee.isEmpty()) {
			ltP2pEmailtoken.setSendTo(employee.get(0).getOfficialEmail());
		}

		ltP2pEmailtoken.setEmailObject("poNumber=" + ltPoHeader.getAckMsg());
		ltP2pEmailtoken.setExpiredWithin(1296000L);
		ltP2pEmailtoken.setSendDate(new Date());

		ltP2pEmailtoken.setEmailUserId(ltPoHeader.getLastUpdateLogin());

		ltP2pEmailtoken.setEmailStatus("SENDING");
		ltP2pEmailtoken.setFailureCount(0L);
		ltP2pEmailtoken = ltMastEmailtokenRepository.save(ltP2pEmailtoken);

	}

	@Override
	public List<LtPoHeaders> getActivePoHeadersByPoNumber(Long companyId, Long userId, String poNumber)
			throws ServiceException {
		return ltPoHeadersDao.getActivePoHeadersByPoNumber(companyId, userId, poNumber);
	}
	@Override
	public List<LtPoHeaders> getAllActivePo(Long companyId)
			throws ServiceException {
		return ltPoHeadersDao.getAllActivePo(companyId);
	}

	@Override
	public Status createPOPDFReport(Long poHeaderId,long companyId) {
		Status status = new Status();
		String saveDirectory = null;
		String companyLogPath = null;
		try {
			SysVariableWithValues sysVariableWithValues = ltMastSysVariablesService
					.getBySysVariableName("PO_SUMMARY_PDF",companyId);
			System.out.println("sysVariableWithValues is "+sysVariableWithValues);
			if (sysVariableWithValues != null) {
				if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
					saveDirectory = sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();

				} else {
					saveDirectory = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
				}
			}
			/*
			 * SysVariableWithValues reportCompanyLogoSysVariable =
			 * ltMastSysVariablesService
			 * .getBySysVariableName("REPORT_COMPANY_LOGO",companyId); if
			 * (reportCompanyLogoSysVariable != null) { if
			 * (reportCompanyLogoSysVariable.getLtMastSysVariableValues().get(0) != null) {
			 * companyLogPath =
			 * reportCompanyLogoSysVariable.getLtMastSysVariableValues().get(0).getUserValue
			 * ();
			 * 
			 * } else { companyLogPath =
			 * reportCompanyLogoSysVariable.getLtMastSysVariables().getSystemValue(); }
			 * }else { ClassLoader classLoader = this.getClass().getClassLoader();
			 * companyLogPath = classLoader.getResource("Logo.jpg").getFile();
			 * //companyLogPath="D:\\akshay\\svn-vpal-grn\\lvendormaster\\Logo.jpg"; }
			 */
			
			LtVendCompany ltVendCompany = ltVendCompanyDao.getLtVendCompanyBycompanyId(companyId);
			
			SysVariableWithValues sysvar=
					ltMastSysVariablesService.getBySysVariableName("COMPANY_LOGO_PATH",companyId);
			System.out.println("sysvar is "+sysvar);
			if(sysvar!=null)
			{
				System.out.println("in if sysvar!=null check");
				if(sysvar.getLtMastSysVariableValues().get(0)!=null)
				{
					System.out.println("inside if sysvar.getLtMastSysVariableValues().get(0)!=null");
					System.out.println(sysvar.getLtMastSysVariableValues().get(0));
					companyLogPath=sysvar.getLtMastSysVariableValues().get(0).getUserValue();
				}
				else
				{
					companyLogPath=sysvar.getLtMastSysVariables().getSystemValue();
				}
			}	
			System.out.println("ltVendCompany is "+ltVendCompany);
			if(ltVendCompany!=null) {
				if(ltVendCompany.getLogoPath()!=null) {
					System.out.println("in if of ltVendCompany.getLogoPath()!=null");
					File file = new File(ltVendCompany.getLogoPath());
					//if(file.createNewFile())
					//{
					//}
					//companyLogPath =ltVendCompany.getLogoPath();
					//companyLogPath = companyLogPath+file.getName();
					companyLogPath = companyLogPath+file.getName();
					System.out.println("Logo path is "+companyLogPath);
					System.out.println("file name is "+file.getName());
					
				}else {
					System.out.println("in else of ltVendCompany.getLogoPath()!=null");
					 ClassLoader classLoader = this.getClass().getClassLoader();
					 companyLogPath = classLoader.getResource("Logo.jpg").getFile();
					 System.out.println("companyLogPath is "+companyLogPath);
				}
			}
			POReportService poReportService = new POReportService();
			LtPoReport poReport = null ;
			
			List<LtPoReport> ltPoReportList=ltPoHeadersDao.createPOPDFReport(poHeaderId,companyId);
			if(ltPoReportList!=null &&!ltPoReportList.isEmpty()) {
				poReport=ltPoReportList.get(0);
				
				poReport.setReportGeneratedPath(saveDirectory);
				poReport.setReportCompanyLogoPath(companyLogPath);
				
				
				LtPoReport poReportRes = poReportService.createPOPDFReport(poReport);
				String path= poReportRes.getPdfPath();
				path=path.replaceAll("^[a-zA-Z]:", "");
				
				
				 
			}
			
//			for(LtPoReport poReportTemp:ltPoReportList) {
//			}
			
			status.setCode(1);
			status.setMessage("Report creted successfully");
			
			String path = null;
			SysVariableWithValues sysVariableWithValues1 = ltMastSysVariablesService
					.getBySysVariableName("PO_SUMMARY_PDF",companyId);//("FILE_OPEN_PATH",companyId);
			if (sysVariableWithValues1 != null) {
				if (sysVariableWithValues1.getLtMastSysVariableValues().get(0) != null) {
					path = sysVariableWithValues1.getLtMastSysVariableValues().get(0).getUserValue();

				} else {
					path = sysVariableWithValues1.getLtMastSysVariables().getSystemValue();
				}
			}
			try {
				path = path+poReport.getFileName();
				System.out.println("File open path is ="+path);
				status.setData(path);
			}catch(Exception e) {
				status.setCode(0);
				status.setMessage("Record not found.");
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setCode(0);
			status.setMessage("Exception while creating PO Report");
		}
		return status;
	}
	
	public Status createPOPDFReportWithTemplate(Long poHeaderId, long companyId) {
	    Status status = new Status();
	    String htmlTemplate;
	    try {
	        // Load the HTML template from a file
	        htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/POTemplate.html")), "UTF-8");

	        // Fetch PO header data
	        String headerQuery = "SELECT poh.po_number, poh.revision_num, poh.po_date, poh.revision_date, vcm.company_name, vcm.logo_path, " +
	                             "mb.branch_name, mb.gst_reg_no as company_gst, mv.vendor_name, " +
	                             "concat(mva.address1, ', ', mva.address2, ', ', mva.address3, ', ', mva.city, '-', mva.pin_code) as vendor_address, " +
	                             "mva.gst_reg_no as vendor_gst, mvc.contact_person, mvc.contact_mobile, mvc.contact_email, mpt.term_name as payment_term, " +
	                             "poh.currency_code, concat(me.first_name, ' ', me.last_name) as buyer, me.official_email as buyer_email " +
	                             "FROM lt_po_headers poh, lt_mast_vendors mv, lt_mast_vendor_addresses mva, lt_mast_vendor_contacts mvc, " +
	                             "lt_mast_branches mb, lt_vend_company_master vcm, lt_mast_payment_terms mpt, lt_mast_employees me " +
	                             "WHERE poh.vendor_id = mv.vendor_id AND poh.vendor_add_id = mva.vendor_add_id AND poh.vendor_contact_id = mvc.vendor_contact_id " +
	                             "AND poh.billing_add_id = mb.branch_id AND poh.company_id = vcm.company_id AND poh.terms_id = mpt.payterm_id " +
	                             "AND poh.buyer_id = me.employee_id AND poh.po_header_id = ?";
	        Map<String, Object> headerData = jdbcTemplate.queryForMap(headerQuery, poHeaderId);
	        
	        if (headerData.containsKey("logo_path") && headerData.get("logo_path") != null) {
	            String logoPath = headerData.get("logo_path").toString().replace("\\", "/");
	            logoPath = "file:///" + logoPath;
	            headerData.put("logo_path", logoPath); // Update the logo_path in the map with forward slashes
	        }

	        // Replace header placeholders
	        for (Map.Entry<String, Object> entry : headerData.entrySet()) {
	            htmlTemplate = htmlTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue() != null ? entry.getValue().toString() : "");
	        }

	        // Fetch PO lines data
	        String linesQuery = "SELECT pol.line_num, pol.line_type, pol.product_code, mp.product_name, pol.quantity, pol.unit_price, " +
	                            "mcmv.value_name as UOM, pol.line_amount, pol.tax_amount, pol.total_amount, mp.product_desc, pol.note_to_vendor " +
	                            "FROM lt_po_lines pol, lt_mast_products mp, lt_mast_comn_master mcm, lt_mast_comn_master_values mcmv " +
	                            "WHERE pol.product_id = mp.product_id AND mcmv.value_code = mp.uom AND mcm.master_id = mcmv.master_id " +
	                            "AND mcm.master_name = 'UOM_MASTER' AND pol.po_header_id = ?";
	        List<Map<String, Object>> lineItems = jdbcTemplate.queryForList(linesQuery, poHeaderId);
	        StringBuilder linesTable = new StringBuilder();
	        for (Map<String, Object> lineItem : lineItems) {
	            linesTable.append("<tr>")
	                      .append("<td>").append(lineItem.get("line_num")).append("</td>")
	                      .append("<td>").append(lineItem.get("line_type")).append("</td>")
	                      .append("<td>").append(lineItem.get("product_code")).append("</td>")
	                      .append("<td>").append(lineItem.get("product_name")).append("</td>")
	                      .append("<td>").append(lineItem.get("quantity")).append("</td>")
	                      .append("<td>").append(lineItem.get("unit_price")).append("</td>")
	                      .append("<td>").append(lineItem.get("UOM")).append("</td>")
	                      .append("<td class='text-right'>").append(lineItem.get("line_amount")).append("</td>")
	                      .append("<td class='text-right'>").append(lineItem.get("tax_amount")).append("</td>")
	                      .append("<td class='text-right'>").append(lineItem.get("total_amount")).append("</td>")
	                      .append("</tr>");
	        }
	        htmlTemplate = htmlTemplate.replace("{{lines_table}}", linesTable.toString());

	        // Fetch PO tax details data
	        String taxQuery = "SELECT pol.line_num, mp.hsn_sac_code, mtm.tax_name, pol.line_amount, mtm.tax_rate, plt.tax_amount " +
	                          "FROM lt_po_line_taxes plt, lt_mast_tax_master mtm, lt_po_lines pol, lt_mast_products mp " +
	                          "WHERE plt.tax_id = mtm.tax_id AND pol.po_line_id = plt.po_line_id AND pol.product_id = mp.product_id " +
	                          "AND plt.po_header_id = ?";
	        List<Map<String, Object>> taxItems = jdbcTemplate.queryForList(taxQuery, poHeaderId);
	        StringBuilder taxTable = new StringBuilder();
	        for (Map<String, Object> taxItem : taxItems) {
	            taxTable.append("<tr>")
	                    .append("<td>").append(taxItem.get("line_num")).append("</td>")
	                    .append("<td>").append(taxItem.get("hsn_sac_code")).append("</td>")
	                    .append("<td>").append(taxItem.get("tax_name")).append("</td>")
	                    .append("<td class='text-right'>").append(taxItem.get("line_amount")).append("</td>")
	                    .append("<td>").append(taxItem.get("tax_rate")).append("</td>")
	                    .append("<td class='text-right'>").append(taxItem.get("tax_amount")).append("</td>")
	                    .append("</tr>");
	        }
	        htmlTemplate = htmlTemplate.replace("{{tax_table}}", taxTable.toString());
	        
	     // Generate the PDF file from the populated HTML
	        File outputDir = new File("D:/Lexa/PO_PDF_TEMP");
	        if (!outputDir.exists()) {
	            outputDir.mkdirs(); // Creates the directory if it doesn't exist
	        }

	        // Define the PDF output path with the full directory
	        String path = null;
			SysVariableWithValues sysVariableWithValues1 = ltMastSysVariablesService
					.getBySysVariableName("PO_SUMMARY_PDF",companyId);//("FILE_OPEN_PATH",companyId);
			if (sysVariableWithValues1 != null) {
				if (sysVariableWithValues1.getLtMastSysVariableValues().get(0) != null) {
					path = sysVariableWithValues1.getLtMastSysVariableValues().get(0).getUserValue();

				} else {
					path = sysVariableWithValues1.getLtMastSysVariables().getSystemValue();
				}
			}
			try {
				path = path+"POReport_"+System.currentTimeMillis()+".pdf";
				System.out.println("File open path is ="+path);
				status.setData(path);
			}catch(Exception e) {
				status.setCode(0);
				status.setMessage("Record not found.");
				e.printStackTrace();
			}
//	        String pdfOutputPath = "D:/Lexa/PO_PDF_TEMP/POReport_" + poHeaderId + ".pdf";
			String pdfOutputPath = path; 
            try (OutputStream os = new FileOutputStream(pdfOutputPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(htmlTemplate, null);
                builder.toStream(os);
                builder.run();
            }

	        status.setCode(1);
	        status.setMessage("PO PDF report created successfully.");


	    } catch (Exception e) {
	        e.printStackTrace();
	        status.setCode(0);
	        status.setMessage("Error generating the PO PDF report.");
	    }
	    return status;
	}
	
//	public Status createPOPDFReportWithTemplate_Testing(Long poHeaderId, long companyId) {
//	    Status status = new Status();
//	    String htmlTemplate;
//	    try {
//	        // Load the HTML template from a file
//	        htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/POTemplate_Testing.html")), "UTF-8");
//
//	        // Fetch PO header data
//	        String headerQuery = "SELECT poh.po_number, poh.revision_num, poh.po_date, poh.revision_date, vcm.company_name, vcm.logo_path, " +
//	                             "mb.branch_name, mb.gst_reg_no as company_gst, mv.vendor_name, " +
//	                             "concat(mva.address1, ', ', mva.address2, ', ', mva.address3, ', ', mva.city, '-', mva.pin_code) as vendor_address, " +
//	                             "mva.gst_reg_no as vendor_gst, mvc.contact_person, mvc.contact_mobile, mvc.contact_email, mpt.term_name as payment_term, " +
//	                             "poh.currency_code, concat(me.first_name, ' ', me.last_name) as buyer, me.official_email as buyer_email " +
//	                             "FROM lt_po_headers poh, lt_mast_vendors mv, lt_mast_vendor_addresses mva, lt_mast_vendor_contacts mvc, " +
//	                             "lt_mast_branches mb, lt_vend_company_master vcm, lt_mast_payment_terms mpt, lt_mast_employees me " +
//	                             "WHERE poh.vendor_id = mv.vendor_id AND poh.vendor_add_id = mva.vendor_add_id AND poh.vendor_contact_id = mvc.vendor_contact_id " +
//	                             "AND poh.billing_add_id = mb.branch_id AND poh.company_id = vcm.company_id AND poh.terms_id = mpt.payterm_id " +
//	                             "AND poh.buyer_id = me.employee_id AND poh.po_header_id = ?";
//	        Map<String, Object> headerData = jdbcTemplate.queryForMap(headerQuery, poHeaderId);
//	        
//	        if (headerData.containsKey("logo_path") && headerData.get("logo_path") != null) {
//	            String logoPath = headerData.get("logo_path").toString().replace("\\", "/");
//	            logoPath = "file:///" + logoPath;
//	            headerData.put("logo_path", logoPath); // Update the logo_path in the map with forward slashes
//	        }
//
//	        // Replace header placeholders
//	        for (Map.Entry<String, Object> entry : headerData.entrySet()) {
//	            htmlTemplate = htmlTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue() != null ? entry.getValue().toString() : "");
//	        }
//
//	        // Fetch PO lines data
//	        String linesQuery = "SELECT pol.line_num, pol.line_type, pol.product_code, mp.product_name, pol.quantity, pol.unit_price, " +
//	                            "mcmv.value_name as UOM, pol.line_amount, pol.tax_amount, pol.total_amount, mp.product_desc, pol.note_to_vendor " +
//	                            "FROM lt_po_lines pol, lt_mast_products mp, lt_mast_comn_master mcm, lt_mast_comn_master_values mcmv " +
//	                            "WHERE pol.product_id = mp.product_id AND mcmv.value_code = mp.uom AND mcm.master_id = mcmv.master_id " +
//	                            "AND mcm.master_name = 'UOM_MASTER' AND pol.po_header_id = ?";
//	        List<Map<String, Object>> lineItems = jdbcTemplate.queryForList(linesQuery, poHeaderId);
//	        StringBuilder linesTable = new StringBuilder();
//	        for (Map<String, Object> lineItem : lineItems) {
//	            linesTable.append("<tr>")
//	                      .append("<td>").append(lineItem.get("line_num")).append("</td>")
//	                      .append("<td>").append(lineItem.get("line_type")).append("</td>")
//	                      .append("<td>").append(lineItem.get("product_code")).append("</td>")
//	                      .append("<td>").append(lineItem.get("product_name")).append("</td>")
//	                      .append("<td>").append(lineItem.get("quantity")).append("</td>")
//	                      .append("<td>").append(lineItem.get("unit_price")).append("</td>")
//	                      .append("<td>").append(lineItem.get("UOM")).append("</td>")
//	                      .append("<td class='text-right'>").append(lineItem.get("line_amount")).append("</td>")
//	                      .append("<td class='text-right'>").append(lineItem.get("tax_amount")).append("</td>")
//	                      .append("<td class='text-right'>").append(lineItem.get("total_amount")).append("</td>")
//	                      .append("</tr>");
//	        }
//	        htmlTemplate = htmlTemplate.replace("{{lines_table}}", linesTable.toString());
//
//	        // Fetch PO tax details data
//	        String taxQuery = "SELECT pol.line_num, mp.hsn_sac_code, mtm.tax_name, pol.line_amount, mtm.tax_rate, plt.tax_amount " +
//	                          "FROM lt_po_line_taxes plt, lt_mast_tax_master mtm, lt_po_lines pol, lt_mast_products mp " +
//	                          "WHERE plt.tax_id = mtm.tax_id AND pol.po_line_id = plt.po_line_id AND pol.product_id = mp.product_id " +
//	                          "AND plt.po_header_id = ?";
//	        List<Map<String, Object>> taxItems = jdbcTemplate.queryForList(taxQuery, poHeaderId);
//	        StringBuilder taxTable = new StringBuilder();
//	        for (Map<String, Object> taxItem : taxItems) {
//	            taxTable.append("<tr>")
//	                    .append("<td>").append(taxItem.get("line_num")).append("</td>")
//	                    .append("<td>").append(taxItem.get("hsn_sac_code")).append("</td>")
//	                    .append("<td>").append(taxItem.get("tax_name")).append("</td>")
//	                    .append("<td class='text-right'>").append(taxItem.get("line_amount")).append("</td>")
//	                    .append("<td>").append(taxItem.get("tax_rate")).append("</td>")
//	                    .append("<td class='text-right'>").append(taxItem.get("tax_amount")).append("</td>")
//	                    .append("</tr>");
//	        }
//	        htmlTemplate = htmlTemplate.replace("{{tax_table}}", taxTable.toString());
//	        
//	     // Generate the PDF file from the populated HTML
//	        File outputDir = new File("D:/Lexa/PO_PDF_TEMP");
//	        if (!outputDir.exists()) {
//	            outputDir.mkdirs(); // Creates the directory if it doesn't exist
//	        }
//
//	       
//	        String pdfOutputPath = "D:/Lexa/PO_PDF_TEMP/POReport_" + poHeaderId + ".pdf";
////			String pdfOutputPath = path; 
//            try (OutputStream os = new FileOutputStream(pdfOutputPath)) {
//                PdfRendererBuilder builder = new PdfRendererBuilder();
//                builder.useFastMode();
//                builder.withHtmlContent(htmlTemplate, null);
//                builder.toStream(os);
//                builder.run();
//            }
//
//	        status.setCode(1);
//	        status.setMessage("PO PDF report created successfully.");
//
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        status.setCode(0);
//	        status.setMessage("Error generating the PO PDF report.");
//	    }
//	    return status;
//	}

	public Status createPOPDFReportWithTemplate_Testing(Long poHeaderId, long companyId) {
	    Status status = new Status();
	    String htmlTemplate;
	    try {
	        // Load the HTML template from a file
	        htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/POTemplate_Testing.html")), "UTF-8");

	        // Fetch PO header data
	        String headerQuery = "SELECT poh.po_number, poh.revision_num, poh.po_date, poh.revision_date, vcm.company_name, vcm.logo_path, " +
	                             "mb.branch_name, mb.gst_reg_no as company_gst, mv.vendor_name, " +
	                             "concat(mva.address1, ', ', mva.address2, ', ', mva.address3, ', ', mva.city, '-', mva.pin_code) as vendor_address, " +
	                             "mva.gst_reg_no as vendor_gst, mvc.contact_person, mvc.contact_mobile, mvc.contact_email, mpt.term_name as payment_term, " +
	                             "poh.currency_code, concat(me.first_name, ' ', me.last_name) as buyer, me.official_email as buyer_email " +
	                             "FROM lt_po_headers poh, lt_mast_vendors mv, lt_mast_vendor_addresses mva, lt_mast_vendor_contacts mvc, " +
	                             "lt_mast_branches mb, lt_vend_company_master vcm, lt_mast_payment_terms mpt, lt_mast_employees me " +
	                             "WHERE poh.vendor_id = mv.vendor_id AND poh.vendor_add_id = mva.vendor_add_id AND poh.vendor_contact_id = mvc.vendor_contact_id " +
	                             "AND poh.billing_add_id = mb.branch_id AND poh.company_id = vcm.company_id AND poh.terms_id = mpt.payterm_id " +
	                             "AND poh.buyer_id = me.employee_id AND poh.po_header_id = ?";
	        Map<String, Object> headerData = jdbcTemplate.queryForMap(headerQuery, poHeaderId);

	        if (headerData.containsKey("logo_path") && headerData.get("logo_path") != null) {
	            String logoPath = headerData.get("logo_path").toString().replace("\\", "/");
	            logoPath = "file:///" + logoPath;
	            headerData.put("logo_path", logoPath); // Update the logo_path in the map with forward slashes
	        }

	        // Replace header placeholders
	        for (Map.Entry<String, Object> entry : headerData.entrySet()) {
	            htmlTemplate = htmlTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue() != null ? entry.getValue().toString() : "");
	        }

	        // Fetch PO lines data
	        String linesQuery = "SELECT pol.line_num, pol.line_type, pol.product_code, mp.product_name, pol.quantity, pol.unit_price, " +
	                            "mcmv.value_name as UOM, pol.line_amount, pol.tax_amount, pol.total_amount, mp.product_desc, pol.note_to_vendor " +
	                            "FROM lt_po_lines pol, lt_mast_products mp, lt_mast_comn_master mcm, lt_mast_comn_master_values mcmv " +
	                            "WHERE pol.product_id = mp.product_id AND mcmv.value_code = mp.uom AND mcm.master_id = mcmv.master_id " +
	                            "AND mcm.master_name = 'UOM_MASTER' AND pol.po_header_id = ?";
	        List<Map<String, Object>> lineItems = jdbcTemplate.queryForList(linesQuery, poHeaderId);
	        BigDecimal totalLineAmount = BigDecimal.ZERO;
	        BigDecimal totalTaxAmount = BigDecimal.ZERO;
	        BigDecimal totalAmount = BigDecimal.ZERO;

	        StringBuilder linesTable = new StringBuilder();
	        for (Map<String, Object> lineItem : lineItems) {
	        	Object lineAmountObj = lineItem.get("line_amount");
	            Object taxAmountObj = lineItem.get("tax_amount");
	            Object totalAmountObj = lineItem.get("total_amount");

	            // Convert Float or BigDecimal to BigDecimal
	            BigDecimal lineAmount = convertToBigDecimal(lineAmountObj);
	            BigDecimal taxAmount = convertToBigDecimal(taxAmountObj);
	            BigDecimal totalLine = convertToBigDecimal(totalAmountObj);

	            totalLineAmount = totalLineAmount.add(lineAmount);
	            totalTaxAmount = totalTaxAmount.add(taxAmount);
	            totalAmount = totalAmount.add(totalLine);

	            linesTable.append("<tr>")
	                      .append("<td>").append(lineItem.get("line_num")).append("</td>")
	                      .append("<td>").append(lineItem.get("line_type")).append("</td>")
	                      .append("<td>").append(lineItem.get("product_code")).append("</td>")
	                      .append("<td>").append(lineItem.get("product_name")).append("</td>")
	                      .append("<td>").append(lineItem.get("quantity")).append("</td>")
	                      .append("<td>").append(lineItem.get("unit_price")).append("</td>")
	                      .append("<td>").append(lineItem.get("UOM")).append("</td>")
	                      .append("<td class='text-right'>").append(lineAmount.doubleValue()).append("</td>")  // Convert to double
	                      .append("<td class='text-right'>").append(taxAmount.doubleValue()).append("</td>")    // Convert to double
	                      .append("<td class='text-right'>").append(totalLine.doubleValue()).append("</td>")    // Convert to double
	                      .append("</tr>");
	        }
	        htmlTemplate = htmlTemplate.replace("{{lines_table}}", linesTable.toString());

	        // Fetch PO tax details data
	        String taxQuery = "SELECT pol.line_num, mp.hsn_sac_code, mtm.tax_name, pol.line_amount, mtm.tax_rate, plt.tax_amount " +
	                          "FROM lt_po_line_taxes plt, lt_mast_tax_master mtm, lt_po_lines pol, lt_mast_products mp " +
	                          "WHERE plt.tax_id = mtm.tax_id AND pol.po_line_id = plt.po_line_id AND pol.product_id = mp.product_id " +
	                          "AND plt.po_header_id = ?";
	        List<Map<String, Object>> taxItems = jdbcTemplate.queryForList(taxQuery, poHeaderId);
	        StringBuilder taxTable = new StringBuilder();
	        for (Map<String, Object> taxItem : taxItems) {
	        	Object lineAmountObj = taxItem.get("line_amount");
	            Object taxAmountObj = taxItem.get("tax_amount");

	            // Convert Float or BigDecimal to BigDecimal
	            BigDecimal lineAmount = convertToBigDecimal(lineAmountObj);
	            BigDecimal taxAmount = convertToBigDecimal(taxAmountObj);

	            taxTable.append("<tr>")
	                    .append("<td>").append(taxItem.get("line_num")).append("</td>")
	                    .append("<td>").append(taxItem.get("hsn_sac_code")).append("</td>")
	                    .append("<td>").append(taxItem.get("tax_name")).append("</td>")
	                    .append("<td class='text-right'>").append(lineAmount.doubleValue()).append("</td>")
	                    .append("<td>").append(taxItem.get("tax_rate")).append("</td>")
	                    .append("<td class='text-right'>").append(taxAmount.doubleValue()).append("</td>")
	                    .append("</tr>");
	        }
	        htmlTemplate = htmlTemplate.replace("{{tax_table}}", taxTable.toString());

	        // Now set total values in template
	        htmlTemplate = htmlTemplate.replace("{{total_line_amount}}", totalLineAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	        htmlTemplate = htmlTemplate.replace("{{total_tax_amount}}", totalTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	        htmlTemplate = htmlTemplate.replace("{{total_amount}}", totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

	        // Generate the PDF file from the populated HTML
	        File outputDir = new File("D:/Lexa/PO_PDF_TEMP");
	        if (!outputDir.exists()) {
	            outputDir.mkdirs(); // Creates the directory if it doesn't exist
	        }

	        String pdfOutputPath = "D:/Lexa/PO_PDF_TEMP/POReport_" + poHeaderId + ".pdf";
	        try (OutputStream os = new FileOutputStream(pdfOutputPath)) {
	            PdfRendererBuilder builder = new PdfRendererBuilder();
	            builder.useFastMode();
	            builder.withHtmlContent(htmlTemplate, null);
	            builder.toStream(os);
	            builder.run();
	        }

	        status.setCode(1);
	        status.setMessage("PO PDF report created successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        status.setCode(0);
	        status.setMessage("Error generating the PO PDF report.");
	    }
	    return status;
	}


	

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Override
    public Status createPoPdfReport(Long poHeaderId) throws ServiceException {
        Status status = new Status();
     

        String htmlTemplate;
        try {
            // Load the HTML template from a file
            htmlTemplate = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/POReport.html")), "UTF-8");

            // Replace &nbsp; with &#160; to avoid XML parsing issues
            htmlTemplate = htmlTemplate.replace("&nbsp;", "&#160;");

            // Fetch PO header data
            String headerQuery = "SELECT poh.po_number, poh.revision_num, poh.po_date, poh.revision_date, vcm.company_name, vcm.logo_path, " +
                                 "mb.branch_name, mb.gst_reg_no as company_gst,  mv.vendor_code, mv.vendor_name, " +
                                 "concat(mva.address1, ', ', mva.address2, ', ', mva.address3, ', ', mva.city, '-', mva.pin_code) as vendor_address, " +
                                 "mva.gst_reg_no as vendor_gst, mvc.contact_person, mvc.contact_mobile, mvc.contact_email, mpt.term_name as payment_term, " +
                                 "poh.currency_code, concat(me.first_name, ' ', me.last_name) as buyer, me.official_email as buyer_email " +
                                 "FROM lt_po_headers poh, lt_mast_vendors mv, lt_mast_vendor_addresses mva, lt_mast_vendor_contacts mvc, " +
                                 "lt_mast_branches mb, lt_vend_company_master vcm, lt_mast_payment_terms mpt, lt_mast_employees me " +
                                 "WHERE poh.vendor_id = mv.vendor_id AND poh.vendor_add_id = mva.vendor_add_id AND poh.vendor_contact_id = mvc.vendor_contact_id " +
                                 "AND poh.billing_add_id = mb.branch_id AND poh.company_id = vcm.company_id AND poh.terms_id = mpt.payterm_id " +
                                 "AND poh.buyer_id = me.employee_id AND poh.po_header_id = ?";
            Map<String, Object> headerData = jdbcTemplate.queryForMap(headerQuery, poHeaderId);

            if (headerData.containsKey("logo_path") && headerData.get("logo_path") != null) {
            	  String logoPath = "C:\\Users\\Admin\\Downloads\\landscaping-logo-template-design\\10432759.jpg".toString().replace("\\", "/");
 //               String logoPath = headerData.get("logo_path").toString().replace("\\", "/");
                logoPath = "file:///" + logoPath;
                headerData.put("logo_path", logoPath); // Update the logo_path in the map with forward slashes
            }

            // Replace header placeholders in the HTML template
            htmlTemplate = replacePlaceholders(htmlTemplate, headerData);

            // Fetch PO lines data
            String linesQuery = "SELECT pol.line_num, pol.line_type, pol.product_code, mp.product_name, pol.quantity, pol.unit_price, " +
                                "mcmv.value_name as UOM, pol.line_amount, pol.tax_amount, pol.total_amount, mp.product_desc, pol.note_to_vendor " +
                                "FROM lt_po_lines pol, lt_mast_products mp, lt_mast_comn_master mcm, lt_mast_comn_master_values mcmv " +
                                "WHERE pol.product_id = mp.product_id AND mcmv.value_code = mp.uom AND mcm.master_id = mcmv.master_id " +
                                "AND mcm.master_name = 'UOM_MASTER' AND pol.po_header_id = ?";
            List<Map<String, Object>> lineItems = jdbcTemplate.queryForList(linesQuery, poHeaderId);
            BigDecimal totalLineAmount = BigDecimal.ZERO;
            BigDecimal totalTaxAmount = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            StringBuilder linesTable = new StringBuilder();
            for (Map<String, Object> lineItem : lineItems) {
                Object lineAmountObj = lineItem.get("line_amount");
                Object taxAmountObj = lineItem.get("tax_amount");
                Object totalAmountObj = lineItem.get("total_amount");

                // Convert Float or BigDecimal to BigDecimal
                BigDecimal lineAmount = convertToBigDecimal(lineAmountObj);
                BigDecimal taxAmount = convertToBigDecimal(taxAmountObj);
                BigDecimal totalLine = convertToBigDecimal(totalAmountObj);

                totalLineAmount = totalLineAmount.add(lineAmount);
                totalTaxAmount = totalTaxAmount.add(taxAmount);
                totalAmount = totalAmount.add(totalLine);
                
               

                linesTable.append("<tr>")
                          .append("<td>").append(lineItem.get("line_num")).append("</td>")
                          .append("<td>").append(lineItem.get("line_type")).append("</td>")
                          .append("<td>")
                          .append(lineItem.get("product_code")).append(" <br />") // Add extra space
                          .append(lineItem.get("product_name")).append("  <br />") // Add extra space
                          .append(lineItem.get("product_desc"))
                          .append("</td>")
                          .append("<td>").append(lineItem.get("quantity")).append("</td>")
                          .append("<td>").append(lineItem.get("unit_price")).append("</td>")
                          .append("<td>").append(lineItem.get("UOM")).append("</td>")
                      
                          .append("<td>").append(lineItem.get("note_to_vendor")).append("</td>")
                          .append("<td class='text-right'>").append(totalLine.doubleValue()).append("</td>")    // Convert to double
                          .append("</tr>");
            }
            htmlTemplate = htmlTemplate.replace("{{lines_table}}", linesTable.toString());

            // Fetch PO tax details data
            String taxQuery = "SELECT pol.line_num, mp.hsn_sac_code, mtm.tax_name, pol.line_amount, mtm.tax_rate, plt.tax_amount " +
                              "FROM lt_po_line_taxes plt, lt_mast_tax_master mtm, lt_po_lines pol, lt_mast_products mp " +
                              "WHERE plt.tax_id = mtm.tax_id AND pol.po_line_id = plt.po_line_id AND pol.product_id = mp.product_id " +
                              "AND plt.po_header_id = ?";
            List<Map<String, Object>> taxItems = jdbcTemplate.queryForList(taxQuery, poHeaderId);
            StringBuilder taxTable = new StringBuilder();
            for (Map<String, Object> taxItem : taxItems) {
                Object lineAmountObj = taxItem.get("line_amount");
                Object taxAmountObj = taxItem.get("tax_amount");

                // Convert Float or BigDecimal to BigDecimal
                BigDecimal lineAmount = convertToBigDecimal(lineAmountObj);
                BigDecimal taxAmount = convertToBigDecimal(taxAmountObj);

                taxTable.append("<tr>")
                        .append("<td>").append(taxItem.get("line_num")).append("</td>")
                        .append("<td>").append(taxItem.get("hsn_sac_code")).append("</td>")
                        .append("<td>").append(taxItem.get("tax_name")).append("</td>")
                        .append("<td class='text-right'>").append(lineAmount.doubleValue()).append("</td>")
                        .append("<td>").append(taxItem.get("tax_rate")).append("</td>")
                        .append("<td class='text-right'>").append(taxAmount.doubleValue()).append("</td>")
                        .append("</tr>");
            }
            htmlTemplate = htmlTemplate.replace("{{tax_table}}", taxTable.toString());

            // Now set total values in template
            htmlTemplate = htmlTemplate.replace("{{total_line_amount}}", totalLineAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            htmlTemplate = htmlTemplate.replace("{{total_tax_amount}}", totalTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            htmlTemplate = htmlTemplate.replace("{{total_amount}}", totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
           
            BigDecimal levies = totalAmount.multiply(new BigDecimal("0.09")); // ISD 9% levies
            BigDecimal grandTotal = totalAmount.add(levies);

            // Convert totals to words
            String totalAmountInWords = convertNumberToWords(totalAmount);
            String taxAmountInWords = convertNumberToWords(totalTaxAmount);
            htmlTemplate = htmlTemplate.replace("{{levies}}", levies.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			htmlTemplate = htmlTemplate.replace("{{grand_total}}", grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

         // Replace the tax details table
         htmlTemplate = htmlTemplate.replace("{{tax_table}}", taxTable.toString());

         // Replace remarks placeholders
         htmlTemplate = htmlTemplate.replace("{{amount_in_words}}", totalAmountInWords);
         htmlTemplate = htmlTemplate.replace("{{tax_amount_in_words}}", taxAmountInWords);
         htmlTemplate = htmlTemplate.replace("{{remarks}}", "Any additional remarks can go here.");
            // Generate the PDF file from the populated HTML
            File outputDir = new File("C:/po");
            if (!outputDir.exists()) {
                outputDir.mkdirs(); // Creates the directory if it doesn't exist
            }

            String pdfOutputPath = "C:/po/POReport_" + poHeaderId + ".pdf";
            try (OutputStream os = new FileOutputStream(pdfOutputPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(htmlTemplate, null);
                builder.toStream(os);
                builder.run();
            }

            status.setCode(1);
            status.setMessage("PO PDF report created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            status.setCode(0);
            status.setMessage("Error generating the PO PDF report.");
        }
        return status;
    }
    
    private static final BigDecimal LEVY_RATE = new BigDecimal("0.12");
    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] SCALES = {"", "Thousand", "Million", "Billion", "Trillion"};



    private String convertNumberToWords(BigDecimal totalAmount) {
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            return "Zero";
        }

        long number = totalAmount.longValue(); // Convert BigDecimal to long
        StringBuilder words = new StringBuilder();
        int scaleIndex = 0;

        while (number > 0) {
            int chunk = (int) (number % 1000); // Extract last three digits
            if (chunk > 0) {
                String chunkInWords = convertChunkToWords(chunk);
                if (scaleIndex > 0) chunkInWords += " " + SCALES[scaleIndex];
                words.insert(0, chunkInWords + " ");
            }
            number /= 1000; // Remove last three digits
            scaleIndex++;
        }

        return words.toString().trim();
    }

    	private String convertChunkToWords(int number) {
    	    StringBuilder chunkWords = new StringBuilder();

    	    if (number >= 100) {
    	        chunkWords.append(UNITS[number / 100]).append(" Hundred ");
    	        number %= 100;
    	    }

    	    if (number >= 20) {
    	        chunkWords.append(TENS[number / 10]).append(" ");
    	        number %= 10;
    	    }

    	    if (number > 0) {
    	        chunkWords.append(UNITS[number]).append(" ");
    	    }

    	    return chunkWords.toString().trim();
    	}

	// Utility method to replace placeholders in the HTML template
    private String replacePlaceholders(String htmlTemplate, Map<String, Object> data) {
        // Replace &nbsp; with &#160; to avoid XML parsing issues
        htmlTemplate = htmlTemplate.replace("&nbsp;", "&#160;");

        // Replace other placeholders in the template with actual data
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            htmlTemplate = htmlTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue() != null ? entry.getValue().toString() : "");
        }

        return htmlTemplate;
    }

    // Method to safely convert objects to BigDecimal
    private BigDecimal convertToBigDecimal(Object obj) {
        if (obj == null) {
            return BigDecimal.ZERO;
        }
        try {
            if (obj instanceof Number) {
                return new BigDecimal(((Number) obj).toString());
            } else {
                return new BigDecimal(obj.toString());
            }
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

	@Override
	public String generateHtml(Long po_Header_Id) {
		// TODO Auto-generated method stub
		return null;
	}
 }
