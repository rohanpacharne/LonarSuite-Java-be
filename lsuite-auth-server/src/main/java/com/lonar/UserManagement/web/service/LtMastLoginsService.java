package com.lonar.UserManagement.web.service;

import com.lonar.UserManagement.web.model.LtMastLogins;
import com.lonar.UserManagement.web.model.Status;

public interface LtMastLoginsService {
	
	public Status saveLoginDetails(LtMastLogins ltMastLogins);
	
	public Status updateLoginDetails(LtMastLogins ltMastLogins);


}
