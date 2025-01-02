package com.lonar.UserManagement.web.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "LT_MAST_SYS_VARIABLE_VALUES")

public class LtMastSysVariableValues extends WhoColumns implements Serializable 
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	//@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variableValues_seq")
//	@SequenceGenerator(name = "variableValues_seq", sequenceName = "LT_MAST_SYS_VARIABLE_VALUES_S", allocationSize = 1)
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "VARIABLEVALUESID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long variableValuesId;
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "VARIABLE_ID")
	private Long variableId;
		
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "User_id")
	private String userId;
	
	@Size(max = 240)
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "User_Value")
	private String userValue;
	
	

	public LtMastSysVariableValues() {
		super();
		
	}
	public Long getVariableValuesId() {
		return variableValuesId;
	}

	public void setVariableValuesId(Long variableValuesId) {
		this.variableValuesId = variableValuesId;
	}

	public Long getVariableId() {
		return variableId;
	}

	public void setVariableId(Long variableId) {
		this.variableId = variableId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserValue() {
		return userValue;
	}

	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}

	@Override
	public String toString() {
		return "LtMastSysVariableValues [variableValuesId=" + variableValuesId + ", variableId=" + variableId
				+ ", userId=" + userId + ", userValue=" + userValue + "]";
	}

	
}
