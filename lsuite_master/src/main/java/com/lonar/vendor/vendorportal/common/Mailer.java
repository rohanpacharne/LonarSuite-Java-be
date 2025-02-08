package com.lonar.vendor.vendorportal.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.lonar.vendor.vendorportal.model.Mail;

@Component
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class Mailer {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private Environment env;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void sendMail(Mail mail) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom(mail.getMailFrom().toString());
		smm.setTo(mail.getMailTo());
		smm.setSubject(mail.getMailSubject());
		smm.setText("dsds");
		mailSender.send(smm);

	}

/* this is original working code
	public int sendMail(Mail mail, VelocityContext velocityContext)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setFrom(new InternetAddress(env.getProperty("email.username").trim()));
				// message.setFrom(new
				// InternetAddress(ResourceServerWebConfig.sysVariableMap.get("email.username")));

				if (mail.getMailCc() != null && !mail.getMailCc().isEmpty()) {
					// message.setCc(InternetAddress.parse(mail.getMailCc()));
					message.setCc(StringUtils.split(mail.getMailCc(), ","));

				}
				if (mail.getAttachment() != null && !mail.getAttachment().isEmpty()) {

					String[] values = mail.getAttachment().split(",");
					for (String mailAttachPath : values) {
						File file = new File(mailAttachPath);
						FileSystemResource file1 = new FileSystemResource(file);
						message.addAttachment(file1.getFilename(), file);
					}
				}

				if (mail.getMailTo() != null && !mail.getMailTo().isEmpty())
					message.setTo(StringUtils.split(mail.getMailTo(), ","));
				message.setFrom(mail.getMailFrom().toString());
				message.setSubject(mail.getMailSubject());
				//Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
				//StringWriter stringWriter = new StringWriter();

				//template.merge(velocityContext, stringWriter);
				//message.setText(stringWriter.toString(), true);

			}

		};
		
		try {
			mailSender.send(preparator);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
*/
	
	public int sendMail(Mail mail, VelocityContext velocityContext)
			throws ResourceNotFoundException, ParseErrorException, Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setFrom(new InternetAddress(env.getProperty("email.username").trim()));
				// message.setFrom(new
				// InternetAddress(ResourceServerWebConfig.sysVariableMap.get("email.username")));

				if (mail.getMailCc() != null && !mail.getMailCc().isEmpty()) {
					// message.setCc(InternetAddress.parse(mail.getMailCc()));
					message.setCc(StringUtils.split(mail.getMailCc(), ","));

				}
				if (mail.getAttachment() != null && !mail.getAttachment().isEmpty()) {

					String[] values = mail.getAttachment().split(",");
					for (String mailAttachPath : values) {
						File file = new File(mailAttachPath);
						FileSystemResource file1 = new FileSystemResource(file);
						message.addAttachment(file1.getFilename(), file);
					}
				}

				if (mail.getMailTo() != null && !mail.getMailTo().isEmpty())
					message.setTo(StringUtils.split(mail.getMailTo(), ","));
				message.setFrom(mail.getMailFrom().toString());
				message.setSubject(mail.getMailSubject());
				//Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
				//StringWriter stringWriter = new StringWriter();

				//template.merge(velocityContext, stringWriter);
				//message.setText(stringWriter.toString(), true);

			}

		};
		
		try {
			mailSender.send(preparator);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
public int sendEmail(Mail mail, VelocityContext velocityContext){
    	
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

            	
            	if (mail.getMailCc() != null && !mail.getMailCc().isEmpty()) {
					addFormField(writer, boundary, "Cc", mail.getMailCc()); 
				}
				if (mail.getAttachment() != null && !mail.getAttachment().isEmpty()) {

					String[] values = mail.getAttachment().split(",");
					for (String mailAttachPath : values) {
						File file = new File(mailAttachPath);
						FileSystemResource file1 = new FileSystemResource(file);
						//message.addAttachment(file1.getFilename(), file);
		                addFilePart(writer, outputStream, boundary, "Attachment", file);

					}
				}
            	
				if (mail.getMailTo() != null && !mail.getMailTo().isEmpty())
				//	message.setTo(StringUtils.split(mail.getMailTo(), ","));
				//message.setFrom(mail.getMailFrom().toString());
				///message.setSubject(mail.getMailSubject());
				
                // Subject field
                addFormField(writer, boundary, "Subject", mail.getMailSubject());

//				Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
//				StringWriter stringWriter = new StringWriter();
//
//				template.merge(velocityContext, stringWriter);
				// Try to load the template, but catch the exception if it's not found
                StringWriter stringWriter = new StringWriter();
                try {
                    Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());
                    template.merge(velocityContext, stringWriter);
                } catch (Exception e) {
                    // Log the error and use a fallback message if the template is not found
                    System.err.println("Template not found: " + mail.getTemplateName());
                    stringWriter.write("Error: Email template not found.");
                }
				//message.setText(stringWriter.toString(), true);
                addFormField(writer, boundary, "Body", stringWriter.toString());
                addFormField(writer, boundary, "To", mail.getMailTo());
                
                // End of multipart form data
                writer.append("--").append(boundary).append("--").append("\r\n").flush();
            }

            // Get the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder(); // To accumulate the entire response
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    response.append(line);
                }
                String responseBody = response.toString().trim(); // Convert StringBuilder to String
                System.out.println("Response Body: " + responseBody);
                if(responseBody.equalsIgnoreCase("Success")) {
                	return 1;
                }else {
                return 0;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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

	
	
	public int sendHuhMail(Mail mail, VelocityContext velocityContext, Object object) {
		try {
			String smtpHostServer = env.getProperty("email.server").trim();
			System.out.println("smtpHostServer = "+smtpHostServer);
			Properties props = System.getProperties();

			props.put("mail.smtp.host", smtpHostServer);

			Session session = Session.getInstance(props, null);
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(env.getProperty("email.username").trim(), "NoReply-JD"));

			msg.setReplyTo(InternetAddress.parse(env.getProperty("email.username").trim(), false));

			msg.setSubject(mail.getMailSubject(), "UTF-8");

			// -------------------------------------------------------------
			Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName());

			StringWriter stringWriter = new StringWriter();

			template.merge(velocityContext, stringWriter);
			msg.setText(stringWriter.toString(), "UTF-8");
			msg.setContent(stringWriter.toString(), "text/html; charset=utf-8");
			// -------------------------------------------------------------
			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getMailTo(), false));
			Transport.send(msg);

			return 1;
		} catch (Exception e) {
			// e.printStackTrace();
			return 0;
		}
	}
	
	public void sendEmail(String host, String port, final String userName, final String password, String toAddress,
            String subject, String messageBody) throws AddressException, MessagingException {

		// Set up mail server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host); // SMTP server
		properties.put("mail.smtp.port", port); // SMTP port
		properties.put("mail.smtp.auth", "true"); // Enable authentication
		properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
		//properties.put("mail.smtp.ssl.enable", "true"); // Enable SSL
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Set SSL protocols
        properties.put("mail.smtp.ssl.ciphers", "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256"); // Set SSL ciphers
        properties.put("mail.debug", "true");

		// Create a mail session with authentication
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// Create the email message
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		message.setSubject(subject);
		message.setText(messageBody);

		// Send the email
		Transport.send(message);
}

}