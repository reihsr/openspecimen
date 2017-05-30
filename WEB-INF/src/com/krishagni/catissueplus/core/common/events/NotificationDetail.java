package com.krishagni.catissueplus.core.common.events;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NotificationDetail {
	private Long id;
	
	private String operationType;

	private String entityType;

	private long entityId;

	private String message;

	private long createdBy;
	
	private Date creationTime;
	
	private Set<UserSummary> users = new HashSet<UserSummary>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Set<UserSummary> getUsers() {
		return users;
	}

	public void setUsers(Set<UserSummary> users) {
		this.users = users;
	}
}