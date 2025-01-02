package com.lonar.UserManagement.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Date;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.lonar.UserManagement.web.dao.LtMastAuditRecordsDao;
import com.lonar.UserManagement.web.model.LtMastAudit;
import com.lonar.UserManagement.web.model.LtMastAuditRecords;
import com.lonar.UserManagement.web.repository.LtMastAuditRecordsRepository;
import com.lonar.UserManagement.web.repository.LtMastAuditRepository;
import com.lonar.UserManagement.web.service.LtMastEmailtokenService;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import de.danielbechler.diff.node.Visit;




@Service
public class CommonMethod {
	@Autowired
	LtMastAuditRepository ltMastAuditRepository;
	
	@Autowired
	LtMastAuditRecordsRepository ltMastAuditRecordsRepository;
	
	@Autowired
	LtMastAuditRecordsDao ltMastAuditRecordsDao;
	// class for creating common method which are requied for project
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	private SecureRandom random = new SecureRandom();
	static final Logger logger = Logger.getLogger(CommonMethod.class);
	@Autowired
	LtMastEmailtokenService ltP2pEmailtokenService;
	String shaVesion = "SHA-256";

	public CommonMethod() {
		// TODO Auto-generated constructor stub
	}

	public void deleteFiles(File file) {
		if (file.isDirectory())
			for (File f : file.listFiles())
				deleteFiles(f);
		else
			file.delete();
	}

	public String getMetaData(byte[] bytes) throws IOException, SAXException, TikaException {

		// Parser method parameters
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		InputStream inputstream = new ByteArrayInputStream(bytes);
		ParseContext context = new ParseContext();

		parser.parse(inputstream, handler, metadata, context);
		return metadata.get(metadata.CONTENT_TYPE);
	}

	public boolean dateLessThanToday(Date date) {
	
		return date.getTime() < new Date().getTime() ? true : false;
	}

	public String getToken() {
		String token = new BigInteger(130, random).toString(32);
		/*
		 * if(!ltP2pEmailtokenService.findByTokenid(token).isEmpty())
		 * token=getToken();
		 */
		return token;
	}

	public boolean linkExpiredChecker(Date date, Long expiredSecound) {
		return ((new Date().getTime() - date.getTime()) / 1000) >= expiredSecound ? false : true;
	}

	public java.sql.Date convertStringtoSqlDate(String date) {
		return new java.sql.Date(Long.parseLong(date));
	}

	public java.sql.Timestamp convertStringtoSqlDateTime(String date) {
		return new java.sql.Timestamp(Long.parseLong(date));
	}

	public String getTodayDate() {
		return String.valueOf(new java.util.Date().getTime());
	}

	public void writeImage(String name, byte[] file) throws IOException, Exception {
		InputStream in = new ByteArrayInputStream(file);
		BufferedImage bImageFromConvert = ImageIO.read(in);
		ImageIO.write(bImageFromConvert, "jpg", new File(
				resourceLoader.getResource("D:/P2PAPPResources//ProductImages/").getURI().getPath() + name + ".jpg"));

	}

	public String codeGenertor(String startWith, String endWith, String intemidteChar, int codeLength) {
		String code = startWith;
		endWith = String.valueOf(Integer.parseInt(endWith) + 1);
		int len = startWith.length() + endWith.length();
		String remainIntemidteChar = "";
		for (int i = 0; i < codeLength - len; i++)
			remainIntemidteChar += intemidteChar;
		return code + remainIntemidteChar + endWith;
	}

	public void writeCabImage(String name, byte[] file) throws IOException {
		InputStream in = new ByteArrayInputStream(file);
		BufferedImage bImageFromConvert = ImageIO.read(in);

		ImageIO.write(bImageFromConvert, "jpg", new File(
				resourceLoader.getResource("D:/P2PAPPResources//CabImages/").getURI().getPath() + name + ".jpg"));

	}

	public String stripXSS(String value) {
		String cleanValue = null;
		if (value != null) {
			cleanValue = Normalizer.normalize(value, Normalizer.Form.NFD);

			// Avoid null characters
			cleanValue = cleanValue.replaceAll("\0", "");

			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid anything in a src='...' type of expression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid eval(...) expressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid expression(...) expressions
			scriptPattern = Pattern.compile("expression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid javascript:... expressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid vbscript:... expressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");

			// Avoid onload= expressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			cleanValue = scriptPattern.matcher(cleanValue).replaceAll("");
		}
		return cleanValue;
	}

	public String passwordGenater(String input) {
		MessageDigest digest = null;
		StringBuffer hexString = new StringBuffer();
		try {
			digest = MessageDigest.getInstance(shaVesion);

			byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			for (int i = 0; i < encodedhash.length; i++) {
				String hex = Integer.toHexString(0xff & encodedhash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hexString.toString();
	}
	
	
	public  float saveAudit( Object base,Object work, Long auditId, Long createdBy) throws IOException {
		return downcast(base,work,auditId,createdBy);
	}
	
	public  <newClass> float downcast(Object obj1,Object obj2, Long auditId, Long createdBy) {
		
		newClass base = (newClass) obj1;
			
			newClass work = (newClass) obj2;
			
			LtMastAudit ltMastAudit = new LtMastAudit();
			ltMastAudit.setCreationDate(new Date());
			ltMastAudit.setCreatedBy(1L);
			ltMastAudit.setMasterName(work.getClass().getName());
			ltMastAudit.setDifference("Audit");
			if(base!=null) {
			ltMastAudit.setOldEntity(base+"");
			}
			ltMastAudit.setNewEntity(work+"");
			if(auditId!=null) {
				ltMastAudit.setAuditId(auditId);
			}
			
			ltMastAudit = ltMastAuditRepository.save(ltMastAudit);
			final float auditId1 = ltMastAudit.getAuditId();
			
			
		DiffNode diff = ObjectDifferBuilder.buildDefault().compare(work, base);
		diff.visit(new DiffNode.Visitor()
		{
		    public void node(DiffNode node, Visit visit)
		    {
		    	
		        final Object baseValue = node.canonicalGet(base);
		        final Object workingValue = node.canonicalGet(work);
		        final String message = node.getPath() + " changed from " + 
		                               baseValue + " to " + workingValue;
		        LtMastAuditRecords ltMastAuditRecords = new LtMastAuditRecords();
		        ltMastAuditRecords.setCreationDate(new Date());
		        ltMastAuditRecords.setCreatedBy(createdBy);
		        ltMastAuditRecords.setValueName(node.getPath().toString().replace("/", ""));
		        ltMastAuditRecords.setOldValue(baseValue+"");
		        ltMastAuditRecords.setNewValue(workingValue+"");
		        ltMastAuditRecords.setAuditId(auditId1);
				
				//ltMastAuditRecordsRepository.save(ltMastAuditRecords);
		        try {
		        ltMastAuditRecordsDao.save(ltMastAuditRecords);
		        }catch(Exception e) {
		        	e.printStackTrace();
		        }
		    }
		});
		return auditId1;
		
		//}
		
		
	}
}
