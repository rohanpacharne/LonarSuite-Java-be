package com.lonar.asn.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.lonar.asn.dao.LtMastEmailtokenDao;
import com.lonar.asn.dao.LtMastEmployeesDao;
import com.lonar.asn.dao.LtMastUsersDao;
import com.lonar.asn.model.AsnApproval;
import com.lonar.asn.model.CodeMaster;
import com.lonar.asn.model.LtMastEmailtoken;
import com.lonar.asn.model.LtMastEmployees;
import com.lonar.asn.model.LtMastUsers;
import com.lonar.asn.model.LtShipmentHeaders;
import com.lonar.asn.repository.LtMastEmailtokenRepository;


@Service
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class LtMastEmailtokenServiceImpl implements LtMastEmailtokenService, CodeMaster{

	@Autowired
	LtMastEmailtokenDao ltMastEmailtokenDao;
	
	@Autowired
	LtMastUsersDao ltMastUsersDao;
	
	@Autowired
	LtMastEmployeesDao ltMastEmployeesDao;
	
	@Autowired
	private Environment env;
	
	@Autowired
	LtMastEmailtokenRepository ltMastEmailtokenRepository;
	
	@Override
	public List<LtMastEmailtoken> findAllActive() throws ServiceException 
	{
		return ltMastEmailtokenDao.findAllActive();
	}

	@Override
	public void updateStatus(Long emailTokenId, String status, Integer count) throws ServiceException
	{
		ltMastEmailtokenDao.updateStatus(emailTokenId, status, count);
		
	}

	

	@Override
	public void makeAsnEntryInEmailToken(List<AsnApproval> asnApprovalsList, String emailTemplate,
			LtShipmentHeaders ltShipmentHeader, String string) throws ServiceException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		for (Iterator iterator = asnApprovalsList.iterator(); iterator.hasNext();) 
		{
			AsnApproval asnApproval = (AsnApproval) iterator.next();
			if(asnApproval.getApprovalId()!=null)
			{
				LtMastUsers ltMastUsers = null;
				 LtMastEmailtoken ltMastEmailtoken=new LtMastEmailtoken();
				if(!emailTemplate.equals("shipmentApprovalNotification")) {
					 ltMastUsers = ltMastUsersDao.getLtMastUsersByID(asnApproval.getLastUpdateLogin());
					
					// List<LtMastEmployees> ltMastEmployees=ltMastEmployeesDao.getByEmpIdForEmail(ltInvoiceHeaders.getBuyerId());
					// ltMastEmailtoken.setSendCc(ltMastEmployees.get(0).getOfficialEmail());
					 
					 ltMastEmailtoken.setSendTo(ltMastUsers.getEmail());
					 ltMastEmailtoken.setEmailTitle(ltShipmentHeader.getShipmentNum()+
								"  has been approved.");
					 List<LtMastUsers> ltP2pUsers1 = ltMastUsersDao.findVendorByUserName(ltMastUsers.getUserName().toUpperCase());
					 ltMastEmailtoken.setEmailObject("asnNumber="+ltShipmentHeader.getShipmentNum()+
								"#$#$status="+"pending"+
								"#$#$name="+ltP2pUsers1.get(0).getEmployeeName()+
								"#$#$LoginURL="+env.getProperty("LOGIN_URL"));
					
					 
				}else {
					List<LtMastEmployees> ltMastEmployees=ltMastEmployeesDao.getByEmpIdForEmail(asnApproval.getApprovalId()
							.longValue());
						if(!ltMastEmployees.isEmpty() && ltMastEmployees.size()>0)
						{
							for (LtMastEmployees empObj :ltMastEmployees )
							{
								//ltMastUsers = ltMastUsersDao.getLtMastUsersByID(expenseApproval2.getApprovalId());
								ltMastEmailtoken.setSendTo(empObj.getOfficialEmail());
								
								ltMastEmailtoken.setEmailTitle(ltShipmentHeader.getShipmentNum()+
											"  Pending for your approval ");
								
								ltMastEmailtoken.setEmailObject("asnNumber="+ltShipmentHeader.getShipmentNum()+
										"#$#$status="+"pending"+
										"#$#$name="+empObj.getEmpName()+
										"#$#$LoginURL="+env.getProperty("LOGIN_URL"));
							}
						}	
				}
				
				//ltInvoiceHeaders = ltInvoiceHeadersDao.getInvoiceById(ltInvoiceHeaders.getInvoiceHeaderId());
				ltMastEmailtoken.setEmailStatus("SENDING");
				ltMastEmailtoken.setEmailTemplate(emailTemplate);
				ltMastEmailtoken.setExpiredWithin(1296000L);
				ltMastEmailtoken.setFailureCount(0L);
				ltMastEmailtoken.setSendDate(new Date());
				Long emailtokenId=ltMastEmailtokenDao.makeEntryInEmailToken(ltMastEmailtoken);			
			
				}
			}
		}

	
	
}
