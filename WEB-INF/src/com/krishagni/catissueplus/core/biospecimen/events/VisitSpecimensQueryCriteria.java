package com.krishagni.catissueplus.core.biospecimen.events;

import java.util.List;


public class VisitSpecimensQueryCriteria {
	private Long cprId;
	
	private Long eventId;
	
	private Long visitId;
	
	private Long dpId;
	
	private List<String> labels;
	
	private String recSiteName;

	public Long getCprId() {
		return cprId;
	}

	public void setCprId(Long cprId) {
		this.cprId = cprId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}
	
	public Long getDpId() {
		return dpId;
	}
	
	public void setDpId(Long dpId) {
		this.dpId = dpId;
	}
	
	public List<String> getLabels() {
		return labels;
	}
	
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	public String getRecSiteName() {
		return recSiteName;
	}
	
	public void setRecSiteName(String recSiteName) {
		this.recSiteName = recSiteName;
	}
}
