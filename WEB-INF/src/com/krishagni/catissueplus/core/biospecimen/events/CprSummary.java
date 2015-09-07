package com.krishagni.catissueplus.core.biospecimen.events;

import java.util.Date;

public class CprSummary {
	private ParticipantSummary participant;
	
	private Long cprId;
	
	private Date registrationDate;
	
	private String ppid;

	private String cpShortTitle;
	
	private Long scgCount;
	
	private Long specimenCount;

	public ParticipantSummary getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantSummary participant) {
		this.participant = participant;
	}

	public Long getCprId() {
		return cprId;
	}

	public void setCprId(Long cprId) {
		this.cprId = cprId;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public String getCpShortTitle() {
		return cpShortTitle;
	}

	public void setCpShortTitle(String cpShortTitle) {
		this.cpShortTitle = cpShortTitle;
	}

	public Long getScgCount() {
		return scgCount;
	}

	public void setScgCount(Long scgCount) {
		this.scgCount = scgCount;
	}

	public Long getSpecimenCount() {
		return specimenCount;
	}

	public void setSpecimenCount(Long specimenCount) {
		this.specimenCount = specimenCount;
	}
}
