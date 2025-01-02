package com.lonar.vendor.vendorportal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "lt_exp_emailtoken_exp_line")
public class LtExpEmailTokenlLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expEmailTokenLine_seq")
//	@SequenceGenerator(name = "expEmailTokenLine_seq", sequenceName = "lt_exp_emailtoken_exp_line_s", allocationSize = 1)
	@NotNull
	@Column(name = "Exp_EmailToken_Line_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expEmailTokenLineId;
	
	@Column(name = "EMAIL_TOKEN_ID")
	private Long emailTokenId;
	
	
	@NotNull
	@Column(name = "Emai_Line_Object")
	private String emailLineObject;


	public Long getExpEmailTokenLineId() {
		return expEmailTokenLineId;
	}


	public void setExpEmailTokenLineId(Long expEmailTokenLineId) {
		this.expEmailTokenLineId = expEmailTokenLineId;
	}


	public Long getEmailTokenId() {
		return emailTokenId;
	}


	public void setEmailTokenId(Long emailTokenId) {
		this.emailTokenId = emailTokenId;
	}


	public String getEmailLineObject() {
		return emailLineObject;
	}


	public void setEmailLineObject(String emailLineObject) {
		this.emailLineObject = emailLineObject;
	}
	
	
	

}

