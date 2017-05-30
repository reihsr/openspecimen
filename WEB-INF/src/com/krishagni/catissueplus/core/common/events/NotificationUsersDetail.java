package com.krishagni.catissueplus.core.common.events;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NotificationUsersDetail {
	private UserSummary user;
	
	private long notificationId;
	
	private String status;

	public UserSummary getUser() {
		return user;
	}

	public void setUser(UserSummary user) {
		this.user = user;
	}

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}