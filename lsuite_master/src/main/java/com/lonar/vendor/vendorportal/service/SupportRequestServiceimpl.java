package com.lonar.vendor.vendorportal.service;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
 
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
import com.lonar.vendor.vendorportal.common.Mailer;
import com.lonar.vendor.vendorportal.dao.SupportRequestDao;
import com.lonar.vendor.vendorportal.model.LtVendCompany;
import com.lonar.vendor.vendorportal.model.Mail;
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.SupportRequest;
import com.lonar.vendor.vendorportal.model.SysVariableWithValues;
 
@Service
public class SupportRequestServiceimpl implements SupportRequestService{
	@Autowired
	SupportRequestDao  supportRequestDao;
 
 
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    LtMastSysVariablesService ltMastSysVariablesService;


    @Autowired
    Mailer mailer;

    public void sendSupportRequest(SupportRequest request, MultipartFile file) throws IOException {
        // Handle file attachment
        String filePath = null;
        if (file != null && !file.isEmpty())
			try {
				{
				    filePath = saveFile(file, request);
				    request.setAttachmentFilePath(filePath);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
        // Save the support request to DB
        request.setRaisedDate(new Date());
//        request.setMailSentStatus("Send");
//        request.setStatus("Pending");
//        supportRequestDao.saveSupportEmail(request);
        
        SupportRequest savedRequest = supportRequestDao.saveSupportEmail(request);
        sendSupportEmail(savedRequest);
 
        // Send email
//        sendSupportEmail(request);
    }

    private String saveFile(MultipartFile file,SupportRequest request) throws IOException, ServiceException {
    	  String uploadDir=null;
    	SysVariableWithValues sysVariableWithValues = null;
		try {
			sysVariableWithValues = ltMastSysVariablesService.getBySysVariableName("SUPPORT_REQUEST_ATTACHMENT", request.getCompanyId());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	if (sysVariableWithValues != null) {
				if (sysVariableWithValues.getLtMastSysVariableValues().get(0) != null) {
					uploadDir= sysVariableWithValues.getLtMastSysVariableValues().get(0).getUserValue();
    	       } else {
    	    	   uploadDir = sysVariableWithValues.getLtMastSysVariables().getSystemValue();
       }
    	   }
 
    	
//        String uploadDir = "D:\\swapnil test\\emailDoc\\uploads\\";
//        String uploadDir = "D:\\Lexa\\emailattachment\\";

        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
 
        String filePath = uploadDir + file.getOriginalFilename();
        File savedFile = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }
        return filePath;
    }
 
 
    
    public SupportRequest sendSupportEmail(SupportRequest request) {
    	System.out.println("request = "+request);
 
    	Mail mail=new Mail();
    	SupportRequest supportRequestSaved = new SupportRequest();
//    	LtVendCompany ltvendcompany =new LtVendCompany();
    	String	SupportEMail=request.getSupportEmail();
    	mail.setMailTo(SupportEMail);
    	mail.setMailSubject(request.getIssueSubject());
    	mail.setTemplateName("supportemailTemplate.vm");
    	mail.setAttachment(request.getAttachmentFilePath());
    	System.out.println("mail::"+mail);
    	System.out.println("Test getIssueSubject: "+request.getIssueSubject());

    	VelocityContext vc=new VelocityContext() ;
    	vc.put("ticketId", request.getTicketId() != null ? request.getTicketId() : "N/A");
    	 vc.put("issueSubject", request.getIssueSubject());
    	 vc.put("issueDescription", request.getIssueDescription());
//    	 vc.put("attachmentFilePath", request.getAttachmentFilePath());
    	 vc.put("contactNumber", request.getContactNumber());
    	 vc.put("priority", request.getPriority());
     	vc.put("loginUserMail", request.getLoginUserMail());

//    	 System.out.println("Test VelocityContext: "+vc);
    	int result=mailer.sendEmail(mail, vc);
//    	System.out.println("Mail result : "+result);
    	Date currentDate =new Date(System.currentTimeMillis());
//    	
//    	request.setRaisedDate(currentDate);
//    	request.setDateResolved(currentDate);
    	request.setCreatedBy(request.getRaisedBy());
    	request.setLastUpdatedBy(request.getRaisedBy());
    	request.setLastUpdateLogin(request.getRaisedBy());
    	request.setCreationDate(currentDate);
    	request.setLastUpdateDate(currentDate);
    	
    	if(result==1) {
    		request.setMailSentStatus("Send");
    		request.setStatus("Pending");
 
    	}
    	else {
    		request.setMailSentStatus("Fail To Send");
    		request.setStatus("Pending");
    	}
    	  supportRequestSaved = supportRequestDao.saveSupportEmail(request);
    	  return supportRequestSaved;
    }
 
    private String buildEmailBody(SupportRequest request) {
        return String.format(
        	"Ticket ID: %s\n\n" +
            "issueSubject: %s\n\n" +
            "issueDescription: %s\n\n" +
            "attachmentFilePath: %s\n\n" +
            "contactNumber: %s\n\n" +
            "priority: %s\n\n" +
            "loginUserMail: %s",
            request.getTicketId() != null ? request.getTicketId() : "N/A",
            request.getIssueSubject(),
            request.getIssueDescription(),
            request.getAttachmentFilePath() != null ? request.getAttachmentFilePath() : "None",
            request.getContactNumber(),
            request.getPriority(),
            request.getLoginUserMail()
        );
    }

 
    
//    public List<LtVendCompany> getSupportRequestById(Long companyId) {
//        return supportRequestDao.findById(companyId);
//    }
//
//    public List<SupportRequest> getAllSupportRequests() {
//        return supportRequestDao.findAll();
//    }
//
//    public SupportRequest updateSupportRequest(SupportRequest request) {
//        return supportRequestDao.update(request);
//    }
//
//    public void deleteSupportRequest(Long ticketId) {
//        supportRequestDao.deleteById(ticketId);
//    }
    
    @Override
	public List<SupportRequest> getLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
			throws ServiceException {
    	System.out.println("in service getLtMastSupportRequestDataTable");
    	 if (input.getColumnNo() == 1 && input.getSort().equals("desc")) {
    	        input.setColumnNo(11);
    	    }
    	    if (input.getColumnNo() == 2 && input.getSort().equals("desc")) {
    	        input.setColumnNo(12);
    	    }
    	    if (input.getColumnNo() == 3 && input.getSort().equals("desc")) {
    	        input.setColumnNo(13);
    	    }
    	    if (input.getColumnNo() == 4 && input.getSort().equals("desc")) {
    	        input.setColumnNo(14);
    	    }
    	    if (input.getColumnNo() == 5 && input.getSort().equals("desc")) {
    	        input.setColumnNo(15);
    	    }
    	    if (input.getColumnNo() == 6 && input.getSort().equals("desc")) {
    	        input.setColumnNo(16);
    	    }
    	    if (input.getColumnNo() == 7 && input.getSort().equals("desc")) {
    	        input.setColumnNo(17);
    	    }
		return supportRequestDao.getLtMastSupportRequestDataTable(input,companyId);
	}
    
	@Override
	public Long getCountLtMastSupportRequestDataTable(SupportRequest input, Long companyId) throws ServiceException {
		return supportRequestDao.getCountLtMastSupportRequestDataTable(input,companyId);
	}
 
 
}