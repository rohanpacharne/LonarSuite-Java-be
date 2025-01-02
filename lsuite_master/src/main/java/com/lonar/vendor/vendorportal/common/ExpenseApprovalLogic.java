package com.lonar.vendor.vendorportal.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.sql.DataSource;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.lonar.vendor.vendorportal.dao.LtExpExpenseHeadersDao;
import com.lonar.vendor.vendorportal.model.BusinessException;
import com.lonar.vendor.vendorportal.model.CodeMaster;
import com.lonar.vendor.vendorportal.model.ExpenseApproval;
import com.lonar.vendor.vendorportal.model.LtExpExpenseHeaders;
import com.lonar.vendor.vendorportal.model.LtExpenseApprovalHistory;
import com.lonar.vendor.vendorportal.model.LtMastEmailtoken;
import com.lonar.vendor.vendorportal.model.Mail;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.repository.LtMastEmailtokenRepository;
import com.lonar.vendor.vendorportal.service.LtExpenseApprovalHistoryService;
import com.lonar.vendor.vendorportal.service.LtExpenseApprovalService;
import com.lonar.vendor.vendorportal.service.LtMastEmailtokenService;

@Component
@EnableScheduling
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class ExpenseApprovalLogic  implements CodeMaster{
	
	@Autowired
	private Environment env;
	
	private JdbcTemplate jdbcTemplate;
	
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
	LtExpExpenseHeadersDao ltExpExpenseHeadersDao;
    
    @Autowired
	LtExpenseApprovalService ltExpenseApprovalService;
    
    @Autowired
	LtExpenseApprovalHistoryService ltExpenseApprovalHistoryService;
    
    @Autowired
	LtMastEmailtokenService ltMastEmailtokenService;
    
    @Autowired
	Mailer mailer;
    
    @Autowired
    LtMastEmailtokenRepository ltMastEmailtokenRepository;

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() throws ServiceException {
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    	
//		vendorChronJob();
//		invoiceChronJob();
//		sendMail();
    	expenseChronJob();
    	sendMail();
    	//sendSimpleEmail();
    	//sendEmailConsumeEmailService();
    }
	
    private void expenseChronJob() {
//	System.out.println("Expense Chron Job called at "+LocalDateTime.now());
    	try {
			List<LtExpExpenseHeaders> expInprogressList = ltExpExpenseHeadersDao.getExpenseList(INPROGRESS);
			System.out.println("expInprogressList = "+expInprogressList);
			String currentApprovalLavel = null ;
			List<ExpenseApproval> expenseApprovals = null;
			List<ExpenseApproval> expenseApprovals1 = null;
			
			for ( LtExpExpenseHeaders ltExpExpenseHeader :  expInprogressList ) 
			{ 
				expenseApprovals1 = new ArrayList<>();
				ExpenseApproval approvalLavel =  ltExpExpenseHeadersDao.getApprovalLevel(ltExpExpenseHeader.getExpHeaderId());
				System.out.println("approvalLavel = "+approvalLavel);
				if(approvalLavel != null){
					if( approvalLavel.getCurrentApprovalLevel() != null &&  !approvalLavel.getCurrentApprovalLevel().trim().equals("")){ 
						System.out.println("in if");
						 currentApprovalLavel = approvalLavel.getCurrentApprovalLevel();
						 System.out.println("currentApprovalLavel = "+currentApprovalLavel);
						 expenseApprovals = ltExpExpenseHeadersDao.getApprovalList(ltExpExpenseHeader.getExpHeaderId(), approvalLavel.getCurrentApprovalLevel());
						 System.out.println("expenseApprovals = "+expenseApprovals);
					}
					else { 
						System.out.println("in else");
						currentApprovalLavel = approvalLavel.getApprovalLevel();
						 System.out.println("currentApprovalLavel = "+currentApprovalLavel);
						expenseApprovals = ltExpExpenseHeadersDao.getApprovalList(ltExpExpenseHeader.getExpHeaderId(), approvalLavel.getApprovalLevel());
						 System.out.println("expenseApprovals = "+expenseApprovals);

					}
					}
				
				boolean isApproved = false;
				boolean isNoAction = false;
				
				for (ExpenseApproval expenseApproval : expenseApprovals) {
					
					if(expenseApproval.getStatus().equals(NO_ACTION)) {	
						isNoAction = true;
						break;
					}else if(expenseApproval.getStatus().equals(APPROVED) && 
							( expenseApproval.getApprovedByAnyone() != null && expenseApproval.getApprovedByAnyone().equals("N"))){
						isApproved = true;						
					} else if( !expenseApproval.getStatus().equals(APPROVED) && 
							( expenseApproval.getApprovedByAnyone() != null && expenseApproval.getApprovedByAnyone().equals("N") )){
						isApproved = false;
						break;
					}
					else if (expenseApproval.getStatus().equals(APPROVED) &&
							( expenseApproval.getApprovedByAnyone() == null || expenseApproval.getApprovedByAnyone().equals("Y"))){
						isApproved = true;						
						break;
					}
				}
				
//				System.out.println("isNoAction = "+isNoAction);
//				System.out.println("isApproved = "+isApproved);
				
				if(isNoAction || isApproved ){
					
					if(isApproved) {
						currentApprovalLavel = ltExpExpenseHeadersDao.getNextApprovalLevel(ltExpExpenseHeader.getExpHeaderId() , currentApprovalLavel);
						
						if(currentApprovalLavel != null && !currentApprovalLavel.trim().equals("") ){		
							//ltExpExpenseHeadersService.updateCurrentApprovalLevel(ltExpExpenseHeader.getExpHeaderId(), currentApprovalLavel);
							expenseApprovals = ltExpExpenseHeadersDao.getApprovalList(ltExpExpenseHeader.getExpHeaderId(), currentApprovalLavel);
						}else {
					
							ltExpenseApprovalService.submitForApproval(ltExpExpenseHeader.getExpenseSubmissionDate(),
									   ltExpExpenseHeader.getExpHeaderId(), APPROVED, new Date());
						
							expenseApprovals.get(0).setApprovalId(ltExpExpenseHeader.getEmployeeId());
							expenseApprovals.get(0).setLastUpdateDate(new Date());
							
							if(ltExpExpenseHeader.getExpenseCategory().equals(ADVANCE)) {
//								System.out.println("in if of saveEmailTokan");
								saveEmailTokan(expenseApprovals,"eAdvanceApproval",ltExpExpenseHeader); 
							}else {
//								System.out.println("in else of saveEmailTokan");
								saveEmailTokan(expenseApprovals,"eExpenseApproval",ltExpExpenseHeader); 
							}
							
							//ltExpenseApprovalDao.callProceduere(ltExpExpenseHeader);
						}
					}
					
					if(currentApprovalLavel != null && !currentApprovalLavel.trim().equals("") ) {	
						ltExpExpenseHeadersDao.upDateStatus(ltExpExpenseHeader.getExpHeaderId(), PENDING, currentApprovalLavel);
						ltExpExpenseHeadersDao.updateCurrentApprovalLevel(ltExpExpenseHeader.getExpHeaderId(), currentApprovalLavel);
								
						//--------------------------chk for delegation here
						ExpenseApproval obj = new ExpenseApproval();
						for(ExpenseApproval expApproval : expenseApprovals)
						{
							System.out.println("delegation id = "+expApproval.getDelegationId());
							if(expApproval.getDelegationId()!=null)
							{
								obj.setApprovalId(expApproval.getDelegationId());
								obj.setApprovalLevel(expApproval.getApprovalLevel());
								obj.setApprovedByAnyone(expApproval.getApprovedByAnyone());
								obj.setCurrentApprovalLevel(expApproval.getCurrentApprovalLevel());
								obj.setExpHeaderId(expApproval.getExpHeaderId());
								obj.setModuleApprovalId(expApproval.getModuleApprovalId());
								obj.setStatus(expApproval.getStatus());
								obj.setExpApprovalId(expApproval.getExpApprovalId());
							}
							
						}
						if(obj!=null && obj.getExpHeaderId()!=null)
						{ 
							//expenseApprovals.add(obj);
							expenseApprovals1.add(obj);
						}
//						System.out.println("obj exp header id = "+obj.getExpHeaderId());
						System.out.println("expenseApprovals1 for debugging = "+expenseApprovals1);
						//---------------------------------------------------
						saveApprovalHistoryData(expenseApprovals1, PENDING);
						if(ltExpExpenseHeader.getExpenseCategory().equals(ADVANCE)) {
							saveEmailTokan(expenseApprovals1,"eAdvanceApprovalNotification",ltExpExpenseHeader); 
						} else {
							saveEmailTokan(expenseApprovals1,"eExpenseApprovalNotification",ltExpExpenseHeader); 
						}
					}
					
					
					//---------------------- for the self approval ---------------------------
//					System.out.println("outer "+expenseApprovals);
					for(ExpenseApproval expApproval:expenseApprovals)
					{
//						System.out.println("akshay "+ltExpExpenseHeader.getExpHeaderId()+" "+
//					ltExpExpenseHeader.getEmployeeId()+" "+expApproval.getApprovalId());
						if(ltExpExpenseHeader.getEmployeeId().equals(expApproval.getApprovalId())
								&& currentApprovalLavel != null)
						{
							LtExpenseApprovalHistory approvalHistory= new LtExpenseApprovalHistory();
							
							approvalHistory.setLastUpdateDate(new Date());
							approvalHistory.setExpenseHeaderId(ltExpExpenseHeader.getExpHeaderId());
							approvalHistory.setEmployeeId(ltExpExpenseHeader.getEmployeeId());
							approvalHistory.setStatus(APPROVED);
							ltExpenseApprovalService.updateStatusApproval(approvalHistory);	
						}
					}
					//---------------------end for the self approval--------------------------
					
					
				}
				
				

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
    private void saveApprovalHistoryData(List<ExpenseApproval> expenseApprovalsList,String status) throws Exception
	{
		for(ExpenseApproval expenseApproval:expenseApprovalsList)
		{
			LtExpenseApprovalHistory ltExpenseApprovalHistory=new LtExpenseApprovalHistory();
			
			ltExpenseApprovalHistory.setStatus(status);
			ltExpenseApprovalHistory.setExpenseHeaderId(expenseApproval.getExpHeaderId());
			if(expenseApproval.getExpApprovalId()!=null) {
				ltExpenseApprovalHistory.setExpenseApprovalId(expenseApproval.getExpApprovalId());

			}
			ltExpenseApprovalHistory.setEmployeeId(expenseApproval.getApprovalId());
			//ltExpenseApprovalHistory.setExpenseHeaderId(expenseApproval.getExpHeaderId());
			ltExpenseApprovalHistory.setLastUpdateDate(expenseApproval.getLastUpdateDate());
			
		try
		{
			System.out.println("sav in cron job "+ltExpenseApprovalHistory);
			ltExpenseApprovalHistoryService.saveApprovalHistory(ltExpenseApprovalHistory);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}
    
    private void saveEmailTokan(List<ExpenseApproval> expenseApprovals,String emailTemplate,LtExpExpenseHeaders ltExpExpenseHeader)
	{
		try
		{
			System.out.println("in saveEmailTokan");
			ltMastEmailtokenService.makeEntryInEmailToken(expenseApprovals,emailTemplate,ltExpExpenseHeader,"MAIL");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    
    
    private void sendMail() throws ServiceException
	{
//    	System.out.println("sendMail chron job called....");
		List<LtMastEmailtoken> ltMastEmailtokenList;
		try {
			ltMastEmailtokenList = ltMastEmailtokenService.findAllActive();
//			System.out.println("ltMastEmailtokenList = "+ltMastEmailtokenList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ltMastEmailtokenList = new ArrayList<>();
			e.printStackTrace();
		}
		for (LtMastEmailtoken ltMastEmailtoken2 : ltMastEmailtokenList)
		{
		try {
			Mail mail = new Mail();
			mail.setMailFrom(new InternetAddress("rohan.pacharne@lonartech.com"));
			mail.setMailTo(ltMastEmailtoken2.getSendTo());
			mail.setMailSubject(ltMastEmailtoken2.getEmailTitle());
			mail.setTemplateName(ltMastEmailtoken2.getEmailTemplate() + ".vm");
//			mail.setTemplateName(ltMastEmailtoken2.getEmailTemplate());
			mail.setMailCc(ltMastEmailtoken2.getSendCc());
			mail.setAttachment(ltMastEmailtoken2.getAttachmentPath());
			
			VelocityContext velocityContext = new VelocityContext();

			String emailObject[]=null;
			if(ltMastEmailtoken2.getEmailObject()!=null)
			{
				 emailObject = ltMastEmailtoken2.getEmailObject().split("\\#\\$\\#\\$");
			}
							
			if(emailObject!=null)
			{
				
				String tempKey = null;
				for (String stringEmail : emailObject) 
				{
					
					if(stringEmail != null && stringEmail.contains("="))
					{
						tempKey = stringEmail.split("=")[0].toString();
						if (stringEmail.split("=")[0].toString().equals("action_url")
							&& !ltMastEmailtoken2.getEmailTemplate().equals("vendorInvite")) {
						velocityContext.put(stringEmail.split("=")[0].toString(),
								stringEmail.split("=")[1].toString() + "=" + ltMastEmailtoken2.getTokenId());
					}

					else if (stringEmail.split("=")[0].toString().equals("message")){
						velocityContext.put(stringEmail.split("=")[0].toString(), ltMastEmailtoken2.getMessage());
					}
					else{
						velocityContext.put(stringEmail.split("=")[0].toString(), stringEmail.split("=")[1].toString());
					}
				}else if(tempKey !=null) {
					 Object value = velocityContext.get(tempKey);
					 if(value != null){
						 velocityContext.put(tempKey, value.toString() +", "+ stringEmail); 
					 }
				}
			}
			}	
			
//			int result = mailer.sendMail(mail, velocityContext);
			int result = mailer.sendEmail(mail, velocityContext);
			System.out.println("result = "+result);
			if(result==1){
				System.out.println("in result....");
				ltMastEmailtokenService.updateStatus( ltMastEmailtoken2.getEmailTokenId(), "Send", 0);
				}else{
					ltMastEmailtokenService.updateStatus( ltMastEmailtoken2.getEmailTokenId(), "Fail to Send", 1);
				}
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			if (ltMastEmailtokenRepository.exists(ltMastEmailtoken2.getEmailTokenId())) {
				ltMastEmailtoken2 = ltMastEmailtokenRepository.findOne(ltMastEmailtoken2.getEmailTokenId());
				ltMastEmailtoken2.setEmailStatus("Fail to Send");

				if (ltMastEmailtoken2.getFailureCount() != null)
					ltMastEmailtoken2.setFailureCount(ltMastEmailtoken2.getFailureCount() + 1L);
				else
					ltMastEmailtoken2.setFailureCount(1L);
				ltMastEmailtokenRepository.save(ltMastEmailtoken2);
			}
			//ex.printStackTrace();
			throw new BusinessException(0, null, ex);	
		}
	}
		
	}
    
    private void sendEmailConsumeEmailService(){
    	
    	String urlString = "http://174.138.187.142:8016/api/EmailService";
        String boundary = "Boundary-" + System.currentTimeMillis();
        
        try {
            // Create the connection
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("accept", "text/plain");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            // Write the multipart form data
            try (OutputStream outputStream = connection.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true)) {

                // Subject field
                addFormField(writer, boundary, "Subject", "Email for Testing");

                // Body field
                addFormField(writer, boundary, "Body", "This is Test mail");

                // To field
                addFormField(writer, boundary, "To", "keyur.kunte@lonartech.com");

                // Cc field
                addFormField(writer, boundary, "Cc", "rohan.pacharne@lonartech.com");

                // Attachment field
                //addFilePart(writer, outputStream, boundary, "Attachment", new File("Payslip.pdf"));

                // End of multipart form data
                writer.append("--").append(boundary).append("--").append("\r\n").flush();
            }

            // Get the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void addFormField(PrintWriter writer, String boundary, String name, String value) {
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"").append(name).append("\"\r\n");
        writer.append("Content-Type: text/plain; charset=UTF-8\r\n\r\n");
        writer.append(value).append("\r\n").flush();
    }
    
    private static void addFilePart(PrintWriter writer, OutputStream outputStream, String boundary, String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--").append(boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"").append(fieldName).append("\"; filename=\"").append(fileName).append("\"\r\n");
        writer.append("Content-Type: ").append("application/pdf").append("\r\n\r\n").flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append("\r\n").flush();
    }
    	
}
    
    

