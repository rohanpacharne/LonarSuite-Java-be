package com.lonar.vendor.vendorportal.model;

import java.util.List;

import javax.validation.Valid;

public class CommonMasterPagination {
	private @Valid LtMastComnMaster ltMastComnMaster;
	private @Valid  List<CustomeDataTable> customDataTable;
	
	
	
	public  List<CustomeDataTable>  getCustomDataTable() {
		return customDataTable;
	}
	public void setCustomDataTable( List<CustomeDataTable>  customDataTable) {
		this.customDataTable = customDataTable;
	}
	
	public LtMastComnMaster getLtMastComnMaster() {
		return ltMastComnMaster;
	}
	public void setLtMastComnMaster(LtMastComnMaster ltMastComnMaster) {
		this.ltMastComnMaster = ltMastComnMaster;
	}
	
	
	@Override
	public String toString() {
		return "CommonMasterWithValue [ltMastComnMaster=" + ltMastComnMaster + ", customDataTable="
				+ customDataTable + "]";
	}
		}
	

	/*public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}*/
	



