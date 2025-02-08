package com.lonar.vendor.vendorportal.dao;
 
import java.util.List;
 
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
 
import com.lonar.vendor.vendorportal.model.ServiceException;
import com.lonar.vendor.vendorportal.model.SupportRequest;
import com.lonar.vendor.vendorportal.repository.SupportRequestRepository;
 
@Component
@PropertySource(value = "classpath:queries/supportRequestQueries.properties", ignoreResourceNotFound = true)
public class SupportRequestDaoImpl implements SupportRequestDao {
    @Autowired
    SupportRequestRepository supportRequestRepository;
    
    private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	} 
	private JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
 
	@Autowired
	private Environment env;
    @Override
    public SupportRequest saveSupportEmail(SupportRequest request) {
    	SupportRequest requestSaved = supportRequestRepository.save(request);
        return requestSaved;
    }
    @Override
	public List<SupportRequest> getLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
			throws ServiceException {
    	System.out.println("in dao getLtMastSupportRequestDataTable");
		String query = env.getProperty("getLtSupportRequestDataTable");
		System.out.println("query = "+query);
		System.out.println("below query");
		String ticketId=null;		
		if(input.getTicketId()!=null && !input.getTicketId().equals(" "))
		{ticketId="%"+input.getTicketId()+"%";}
 
	   if(input.getRaisedDate() == null || input.getRaisedDate().equals(""))
		{
			input.setRaisedDate(null);
		}
	   String issueSubject=null;
	   if(input.getIssueSubject()!=null && !input.getIssueSubject().equals(" ") && !input.getIssueSubject().isEmpty()) 
	   {issueSubject="%"+input.getIssueSubject().toUpperCase()+"%";}
	   String raisedBystr=null;
	   if (input.getRaisedBystr() != null && !input.getRaisedBystr().trim().isEmpty()) {
		    raisedBystr = "%" + input.getRaisedBystr().trim().toUpperCase() + "%";
		} else {
		    raisedBystr = null; // Ensure correct handling of null values
		}
//	   
//	   String raisedBystr=null;
//	   if(input.getRaisedBystr()!=null && !input.getRaisedBystr().equals(" ") && !input.getRaisedBystr().isEmpty()) 
//	   {raisedBystr="%"+input.getRaisedBystr().toUpperCase()+"%";}
	   String priority=null;
	   if(input.getPriority()!=null && !input.getPriority().equals(" ") && !input.getPriority().isEmpty()) 
	   {priority="%"+input.getPriority().toUpperCase()+"%";}
	   String status=null;
	   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
	   {status="%"+input.getStatus().toUpperCase()+"%";}
//	   if(input.getColumnNo()==0) {
//		   input.setColumnNo(12);
//	   }

			List<SupportRequest> list = 
					jdbcTemplate.query(query , new Object[]{companyId, ticketId,  raisedBystr,
				           input.getRaisedDate(),  issueSubject,  priority,  status,
				            input.getColumnNo(), input.getColumnNo(),
				            input.getColumnNo(), input.getColumnNo(),
				            input.getColumnNo(), input.getColumnNo(), 
				            input.getColumnNo(), input.getColumnNo(),
				            input.getColumnNo(), input.getColumnNo(), 
				            input.getStart() + input.getLength(), input.getStart() + 1},
				 new  BeanPropertyRowMapper<SupportRequest>(SupportRequest.class));
	    	System.out.println("list = "+list);
 
			if(!list.isEmpty()) {
				return list;
			}else {
				return null;
			}

	}
    @Override
   	public Long getCountLtMastSupportRequestDataTable(SupportRequest input, Long companyId)
   			throws ServiceException {
   		String query = env.getProperty("getCountForLtMastSupportRequestDataTable");
   		String ticketId=null;	
   		if(input.getTicketId()!=null && !input.getTicketId().equals(" "))
		{ticketId="%"+input.getTicketId()+"%";}
 
	   if(input.getRaisedDate() == null || input.getRaisedDate().equals(""))
		{
			input.setRaisedDate(null);
		}
	   String issueSubject=null;
	   if(input.getIssueSubject()!=null && !input.getIssueSubject().equals(" ") && !input.getIssueSubject().isEmpty()) 
	   {issueSubject="%"+input.getIssueSubject().toUpperCase()+"%";}
	   String raisedBystr=null;
	   if (input.getRaisedBystr() != null && !input.getRaisedBystr().trim().isEmpty()) {
		    raisedBystr = "%" + input.getRaisedBystr().trim().toUpperCase() + "%";
		} else {
		    raisedBystr = null; // Ensure correct handling of null values
		}
//	   String raisedBystr=null;
//	   if(input.getRaisedBystr()!=null && !input.getRaisedBystr().equals(" ") && !input.getRaisedBystr().isEmpty()) 
//	   {raisedBystr="%"+input.getRaisedBystr().toUpperCase()+"%";}
	   String priority=null;
	   if(input.getPriority()!=null && !input.getPriority().equals(" ") && !input.getPriority().isEmpty()) 
	   {priority="%"+input.getPriority().toUpperCase()+"%";}
	   String status=null;
	   if(input.getStatus()!=null && !input.getStatus().equals(" ") && !input.getStatus().isEmpty()) 
	   {status="%"+input.getStatus().toUpperCase()+"%";}

//	   if(input.getColumnNo()==0) {
//		   input.setColumnNo(12);
//	   }

   	 String count  = (String)getJdbcTemplate().queryForObject(
				query, new Object[]{companyId}, String.class);
 
			
			return Long.parseLong(count);
   	}
 
 
    
}