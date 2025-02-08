package com.lonar.UserManagement.web.dao;

import com.lonar.UserManagement.web.model.LtMastLogins;

public interface LtMastLoginsDao {
	
	public LtMastLogins saveLoginDetails(LtMastLogins ltMastLogins);
	
	public boolean updateLoginDetails(LtMastLogins ltMastLogins);

}
