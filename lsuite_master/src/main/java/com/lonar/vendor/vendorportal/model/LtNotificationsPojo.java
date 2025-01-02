package com.lonar.vendor.vendorportal.model;

import java.util.List;

public class LtNotificationsPojo 
{
	private int notificationTotal;
	private List<LtNotification> notificationsList;
	public int getNotificationTotal() {
		return notificationTotal;
	}
	public void setNotificationTotal(int notificationTotal) {
		this.notificationTotal = notificationTotal;
	}
	
	public List<LtNotification> getNotificationsList() {
		return notificationsList;
	}
	public void setNotificationsList(List<LtNotification> notificationsList) {
		this.notificationsList = notificationsList;
	}
	@Override
	public String toString() {
		return "LtNotificationsPojo [notificationTotal=" + notificationTotal + ", notificationsList="
				+ notificationsList + "]";
	}
	
	
	

}

