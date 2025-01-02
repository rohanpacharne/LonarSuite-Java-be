package com.lonar.vendor.vendorportal.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class LtCompanyVenMgmtIncludedto {
	
	//1
	//@Column(name = "COMPANY_VEN_MGMT_INCLUDE_ID")
	private Long companyVenMgmtIncludeId;
	
	//2
	//@Column(name = "COMPANY_ID")
	private Long companyId;
	
	//3
	//@Column(name = "VENDOR_ID")
	private Long vendorId;
	
	//4
	//@Column(name = "COMP_MGMT_ID")
	private Long compMgmtId;
	
	//5
	//@Column(name = "MGMT_INCLUDE_VENDOR")
	private String mgmtIncludeVendor;
	
	//6
	//@Column(name = "MGMT_MANDATORY_TAB")
	private String mgmtMandatoryTab;
	
	//7
	//@Column(name = "COMP_CLIENT_ID")
	private Long compClientId;
	
	//8
	//@Column(name = "CLIENT_INCLUDE_VENDOR")
	private String clientIncludeVendor;
	
	//9
	//@Column(name = "CLIENT_MANDATORY_TAB")
	private String clientMandatoryTab;
	
	//10
	//@Column(name = "COMP_SIST_CONCERNS_ID")
	private Long compSistConcernsId;
	
	//11
	//@Column(name = "SIST_INCLUDE_VENDOR")
	private String sistIncludeVendor;
	
	//12
	//@Column(name = "SIST_MANDATORY_TAB")
	private String sistMandatoryTab;
	
	//from base class...
	
	//13
	//@Basic(optional = false)
	//@NotNull
	//@Column(name = "START_DATE")
	//@Temporal(TemporalType.TIMESTAMP)
	private String startDate;
	
	//14
	//@Column(name = "END_DATE")
	//@Temporal(TemporalType.TIMESTAMP)
	private String endDate;
	
	//15
	//@Basic(optional = false)
	//@Column(name = "CREATED_BY")
	private Long createdBy;
	
	//16
	//@Basic(optional = false)
	//@Column(name = "CREATION_DATE")
	//@Temporal(TemporalType.TIMESTAMP)
	private String creationDate;
	
	//17
	//@Basic(optional = false)
	//@NotNull
	//@Column(name = "LAST_UPDATE_LOGIN")
	private Long lastUpdateLogin;
	
	//18
	//@Column(name = "LAST_UPDATED_BY")
	private Long lastUpdatedBy;
	
	//19
	//@Column(name = "LAST_UPDATE_DATE")
	//@Temporal(TemporalType.TIMESTAMP)
	private String lastUpdateDate;

	//20
	//@Column(name = "ADDITIONAL_FIELD_1")
	private String additionalField1;

	//21
	//@Column(name = "ADDITIONAL_FIELD_2")
	private String additionalField2;
	
	//22
	//@Column(name = "ADDITIONAL_FIELD_3")
	private String additionalField3;
	
	//@Column(name = "ADDITIONAL_FIELD_4")
	private String additionalField4;
	
	//23
	//@Column(name = "ADDITIONAL_FIELD_5")
	private String additionalField5;
	
	//24
	//@Column(name = "ADDITIONAL_FIELD_6")
	private String additionalField6;
	
	//25
	//@Column(name = "ADDITIONAL_FIELD_7")
	private String additionalField7;
	
	//26
	//@Column(name = "ADDITIONAL_FIELD_8")
	private String additionalField8;
	
	//27
	//@Column(name = "ADDITIONAL_FIELD_9")
	private String additionalField9;
	
	//28
	//@Column(name = "ADDITIONAL_FIELD_10")
	private String additionalField10;
	
	//29
	//@Column(name = "ADDITIONAL_FIELD_11")
	private String additionalField11;
	
	//30
	//@Column(name = "ADDITIONAL_FIELD_12")
	private String additionalField12;
	
	//31
	//@Column(name = "ADDITIONAL_FIELD_13")
	private String additionalField13;
	
	//32
	//@Column(name = "ADDITIONAL_FIELD_14")
	private String additionalField14;
	
	//34
	//@Column(name = "ADDITIONAL_FIELD_15")
	private String additionalField15;
	
	
	public Long getCompanyVenMgmtIncludeId() {
		return companyVenMgmtIncludeId;
	}

	public void setCompanyVenMgmtIncludeId(Long companyVenMgmtIncludeId) {
		this.companyVenMgmtIncludeId = companyVenMgmtIncludeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompMgmtId() {
		return compMgmtId;
	}

	public void setCompMgmtId(Long compMgmtId) {
		this.compMgmtId = compMgmtId;
	}

	public String getMgmtIncludeVendor() {
		return mgmtIncludeVendor;
	}

	public void setMgmtIncludeVendor(String mgmtIncludeVendor) {
		this.mgmtIncludeVendor = mgmtIncludeVendor;
	}

	public String getMgmtMandatoryTab() {
		return mgmtMandatoryTab;
	}

	public void setMgmtMandatoryTab(String mgmtMandatoryTab) {
		this.mgmtMandatoryTab = mgmtMandatoryTab;
	}

	public Long getCompClientId() {
		return compClientId;
	}

	public void setCompClientId(Long compClientId) {
		this.compClientId = compClientId;
	}

	public String getClientIncludeVendor() {
		return clientIncludeVendor;
	}

	public void setClientIncludeVendor(String clientIncludeVendor) {
		this.clientIncludeVendor = clientIncludeVendor;
	}

	public String getClientMandatoryTab() {
		return clientMandatoryTab;
	}

	public void setClientMandatoryTab(String clientMandatoryTab) {
		this.clientMandatoryTab = clientMandatoryTab;
	}

	public Long getCompSistConcernsId() {
		return compSistConcernsId;
	}

	public void setCompSistConcernsId(Long compSistConcernsId) {
		this.compSistConcernsId = compSistConcernsId;
	}

	public String getSistIncludeVendor() {
		return sistIncludeVendor;
	}

	public void setSistIncludeVendor(String sistIncludeVendor) {
		this.sistIncludeVendor = sistIncludeVendor;
	}

	public String getSistMandatoryTab() {
		return sistMandatoryTab;
	}

	public void setSistMandatoryTab(String sistMandatoryTab) {
		this.sistMandatoryTab = sistMandatoryTab;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Long getLastUpdateLogin() {
		return lastUpdateLogin;
	}

	public void setLastUpdateLogin(Long lastUpdateLogin) {
		this.lastUpdateLogin = lastUpdateLogin;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public String getAdditionalField1() {
		return additionalField1;
	}

	public void setAdditionalField1(String additionalField1) {
		this.additionalField1 = additionalField1;
	}

	public String getAdditionalField2() {
		return additionalField2;
	}

	public void setAdditionalField2(String additionalField2) {
		this.additionalField2 = additionalField2;
	}

	public String getAdditionalField3() {
		return additionalField3;
	}

	public void setAdditionalField3(String additionalField3) {
		this.additionalField3 = additionalField3;
	}

	public String getAdditionalField4() {
		return additionalField4;
	}

	public void setAdditionalField4(String additionalField4) {
		this.additionalField4 = additionalField4;
	}

	public String getAdditionalField5() {
		return additionalField5;
	}

	public void setAdditionalField5(String additionalField5) {
		this.additionalField5 = additionalField5;
	}

	public String getAdditionalField6() {
		return additionalField6;
	}

	public void setAdditionalField6(String additionalField6) {
		this.additionalField6 = additionalField6;
	}

	public String getAdditionalField7() {
		return additionalField7;
	}

	public void setAdditionalField7(String additionalField7) {
		this.additionalField7 = additionalField7;
	}

	public String getAdditionalField8() {
		return additionalField8;
	}

	public void setAdditionalField8(String additionalField8) {
		this.additionalField8 = additionalField8;
	}

	public String getAdditionalField9() {
		return additionalField9;
	}

	public void setAdditionalField9(String additionalField9) {
		this.additionalField9 = additionalField9;
	}

	public String getAdditionalField10() {
		return additionalField10;
	}

	public void setAdditionalField10(String additionalField10) {
		this.additionalField10 = additionalField10;
	}

	public String getAdditionalField11() {
		return additionalField11;
	}

	public void setAdditionalField11(String additionalField11) {
		this.additionalField11 = additionalField11;
	}

	public String getAdditionalField12() {
		return additionalField12;
	}

	public void setAdditionalField12(String additionalField12) {
		this.additionalField12 = additionalField12;
	}

	public String getAdditionalField13() {
		return additionalField13;
	}

	public void setAdditionalField13(String additionalField13) {
		this.additionalField13 = additionalField13;
	}

	public String getAdditionalField14() {
		return additionalField14;
	}

	public void setAdditionalField14(String additionalField14) {
		this.additionalField14 = additionalField14;
	}

	public String getAdditionalField15() {
		return additionalField15;
	}

	public void setAdditionalField15(String additionalField15) {
		this.additionalField15 = additionalField15;
	}

	@Override
	public String toString() {
		return "LtCompanyVenMgmtIncludedto [companyVenMgmtIncludeId=" + companyVenMgmtIncludeId + ", companyId="
				+ companyId + ", vendorId=" + vendorId + ", compMgmtId=" + compMgmtId + ", mgmtIncludeVendor="
				+ mgmtIncludeVendor + ", mgmtMandatoryTab=" + mgmtMandatoryTab + ", compClientId=" + compClientId
				+ ", clientIncludeVendor=" + clientIncludeVendor + ", clientMandatoryTab=" + clientMandatoryTab
				+ ", compSistConcernsId=" + compSistConcernsId + ", sistIncludeVendor=" + sistIncludeVendor
				+ ", sistMandatoryTab=" + sistMandatoryTab + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate + ", lastUpdateLogin=" + lastUpdateLogin
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate=" + lastUpdateDate + ", additionalField1="
				+ additionalField1 + ", additionalField2=" + additionalField2 + ", additionalField3=" + additionalField3
				+ ", additionalField4=" + additionalField4 + ", additionalField5=" + additionalField5
				+ ", additionalField6=" + additionalField6 + ", additionalField7=" + additionalField7
				+ ", additionalField8=" + additionalField8 + ", additionalField9=" + additionalField9
				+ ", additionalField10=" + additionalField10 + ", additionalField11=" + additionalField11
				+ ", additionalField12=" + additionalField12 + ", additionalField13=" + additionalField13
				+ ", additionalField14=" + additionalField14 + ", additionalField15=" + additionalField15 + "]";
	}
	
	

}
