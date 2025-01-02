package com.lonar.vendor.vendorportal.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lonar.vendor.vendorportal.dao.LtCustomerApprovalDao;
import com.lonar.vendor.vendorportal.dao.LtMastCustomerInfoDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmailtokenDao;
import com.lonar.vendor.vendorportal.dao.LtMastEmployeesDao;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.LtCustomerApproval;
import com.lonar.vendor.vendorportal.model.LtMastCustomer;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.LtMastEmployees;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.Status;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
import com.lonar.vendor.vendorportal.repository.LtMastEmailtokenRepository;

@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class LtMastEmailtokenServiceImpl implements LtMastEmailtokenService, CodeMaster {

	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;

	@Autowired
	LtCustomerApprovalDao ltCustomerApprovalDao;

	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;

	@Autowired
	LtMastCustomerInfoDao ltMastCustomerInfoDao;

	@Autowired
	private Environment env;

	@Autowired
	LtMastEmailtokenRepository ltMastEmailtokenRepository;

	@Autowired
	LtMastSysVariablesService ltMastSysVariablesService;

	@Autowired
	LtMastCommonMessageService ltMastCommonMessageService;

	@Override
	public void makeEntryInEmailToken(List<LtCustomerApproval> customerApprovals, String emailTemplate,
			LtMastCustomer ltMastCustomer, String string) throws ServiceException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		DecimalFormat twoDForm = new DecimalFormat("0.00");

		for (Iterator iterator = customerApprovals.iterator(); iterator.hasNext();) {
			LtCustomerApproval customerApproval = (LtCustomerApproval) iterator.next();
			if (customerApproval.getApprovalId() != null) {
				if (emailTemplate.equals("customerApprovalNotification")) {
					List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao
							.getByEmpIdForEmail(customerApproval.getApprovalId().longValue());
					if (!ltMastEmployees.isEmpty() && ltMastEmployees.size() > 0) {
						for (Iterator iterator2 = ltMastEmployees.iterator(); iterator2.hasNext();) {
							// ltExpExpenseHeader=ltExpExpenseHeadersService.findOne(ltExpExpenseHeader.getExpHeaderId());
						//	ltMastCustomer = ltMastCustomerInfoDao.getVendorById(ltMastCustomer.getCustomerId());
							LtMastEmployees ltMastEmployees2 = (LtMastEmployees) iterator2.next();

							LtMastEmailtoken ltMastEmailtoken = new LtMastEmailtoken();
							ltMastEmailtoken.setSendTo(ltMastEmployees2.getOfficialEmail());
							ltMastEmailtoken.setEmailStatus("SENDING");
							ltMastEmailtoken
									.setEmailTitle("Pending for your approval " + ltMastCustomer.getCustomerName());
							ltMastEmailtoken.setEmailTemplate(emailTemplate);
							ltMastEmailtoken.setExpiredWithin(1296000L);
							ltMastEmailtoken.setSendDate(new Date());
							ltMastEmailtoken.setFailureCount(0L);
							/*String startDate = formatter.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ss")
									.parse(ltMastCustomer.getStartDate().toString()));*/
							String endDate = null;
							/*if (ltMastCustomer.getEndDate() != null) {
								endDate = formatter.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ss")
										.parse(ltMastCustomer.getEndDate().toString()));
							} else {
								endDate = "-";
							}*/
							ltMastEmailtoken.setEmailObject("customerName=" + ltMastCustomer.getCustomerName()
									 + "#$#$status="
									+ "pending" + "#$#$name=" + ltMastEmployees2.getEmpName() + "#$#$LoginURL="
									+ env.getProperty("LOGIN_URL"));

							Long emailtokenId = ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
						}
					}
				} else if (emailTemplate.equals("customerApproval")) {
					StringBuffer sendCc = new StringBuffer();
					String companyName = null;
					List<LtCustomerApproval> customerApproversList = ltCustomerApprovalDao
							.getApprovalList(ltMastCustomer.getCustomerId(), null);
					if (customerApproversList != null) {

						for (LtCustomerApproval customerApprovalEmp : customerApproversList) {
							List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao
									.getByEmpIdForEmail(customerApprovalEmp.getApprovalId().longValue());
							if (ltMastEmployees != null) {
								sendCc.append(ltMastEmployees.get(0).getOfficialEmail() + ",");
								companyName = ltMastEmployees.get(0).getCompanyName();
							}
						}
					}
					LtMastEmailtoken ltMastEmailtoken = new LtMastEmailtoken();
					List<LtMastEmployees> ltMastEmployees = ltMastEmployeesDao
							.getByEmpIdForEmail(ltMastCustomer.getInitiatorId());
					ltMastEmailtoken.setSendTo(ltMastEmployees.get(0).getOfficialEmail());
					ltMastEmailtoken.setEmailStatus("SENDING");
					ltMastEmailtoken.setEmailTitle(ltMastCustomer.getCustomerName() + " Approved");
					ltMastEmailtoken.setEmailTemplate(emailTemplate);
					ltMastEmailtoken.setExpiredWithin(1296000L);
					ltMastEmailtoken.setSendDate(new Date());
					ltMastEmailtoken.setFailureCount(0L);
					ltMastEmailtoken.setSendCc(sendCc + "");
					
					ltMastEmailtoken.setEmailObject("customerName=" + ltMastCustomer.getCustomerName() + "#$#$StartDate="
							+ "pending" + "#$#$name="
							+ ltMastCustomer.getCustomerName() + "#$#$companyName=" + companyName + "#$#$LoginURL="
							+ env.getProperty("LOGIN_URL"));

					Long emailtokenId = ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);
				}
			}

		}

	}

	@Override
	public List<LtMastEmailtoken> findAllActive() throws ServiceException {
		return ltMastEmailtokenDao.findAllActive();
	}

	@Override
	public void updateStatus(Long emailTokenId, String status, Integer count) throws ServiceException {
		ltMastEmailtokenDao.updateStatus(emailTokenId, status, count);

	}

	
	

}
