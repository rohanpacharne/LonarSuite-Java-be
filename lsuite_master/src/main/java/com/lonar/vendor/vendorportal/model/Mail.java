package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.mail.internet.InternetAddress;

public class Mail {

	private InternetAddress mailFrom;

	private String mailTo;

	private String mailCc;

	private String mailBcc;

	private String mailSubject;

	private String mailContent;

	private String templateName;

	private String contentType;
	
	private String attachment;
	
	
	

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Mail() {
		contentType = "text/html";
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public InternetAddress getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(InternetAddress internetAddress) {
		this.mailFrom = internetAddress;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public Date getMailSendDate() {
		return new Date();
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	@Override
	public String toString() {
		StringBuilder lBuilder = new StringBuilder();
		lBuilder.append("Mail From:- ").append(getMailFrom());
		lBuilder.append("Mail To:- ").append(getMailTo());
		lBuilder.append("Mail Cc:- ").append(getMailCc());
		lBuilder.append("Mail Bcc:- ").append(getMailBcc());
		lBuilder.append("Mail Subject:- ").append(getMailSubject());
		lBuilder.append("Mail Send Date:- ").append(getMailSendDate());
		lBuilder.append("Mail Content:- ").append(getMailContent());
		return lBuilder.toString();
	}

}
