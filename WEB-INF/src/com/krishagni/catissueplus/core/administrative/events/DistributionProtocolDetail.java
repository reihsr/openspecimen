
package com.krishagni.catissueplus.core.administrative.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.krishagni.catissueplus.core.administrative.domain.DistributionProtocol;
import com.krishagni.catissueplus.core.administrative.domain.User;
import com.krishagni.catissueplus.core.common.events.UserSummary;
import com.krishagni.catissueplus.core.de.events.SavedQuerySummary;

public class DistributionProtocolDetail {
	private Long id;

	private String instituteName;

	private String defReceivingSiteName;
	
	private UserSummary principalInvestigator;

	private String title;

	private String shortTitle;

	private String irbId;

	private Date startDate;
	
	private Date endDate;

	private String activityStatus;

	private int distributedSpecimensCount;
	
	private SavedQuerySummary report;
	
	private List<SiteDetail> distributingSites = new ArrayList<SiteDetail>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	public String getDefReceivingSiteName() {
		return defReceivingSiteName;
	}
	
	public void setDefReceivingSiteName(String defReceivingSiteName) {
		this.defReceivingSiteName = defReceivingSiteName;
	}

	public UserSummary getPrincipalInvestigator() {
		return principalInvestigator;
	}

	public void setPrincipalInvestigator(UserSummary principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getIrbId() {
		return irbId;
	}

	public void setIrbId(String irbId) {
		this.irbId = irbId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public int getDistributedSpecimensCount() {
		return distributedSpecimensCount;
	}

	public void setDistributedSpecimensCount(int distributedSpecimensCount) {
		this.distributedSpecimensCount = distributedSpecimensCount;
	}

	public SavedQuerySummary getReport() {
		return report;
	}

	public void setReport(SavedQuerySummary report) {
		this.report = report;
	}
	
	public List<SiteDetail> getDistributingSites() {
		return distributingSites;
	}
	
	public void setDistributingSites(List<SiteDetail> distributingSites) {
		this.distributingSites = distributingSites;
	}

	public static DistributionProtocolDetail from(DistributionProtocol distributionProtocol) {
		DistributionProtocolDetail detail = new DistributionProtocolDetail();
		detail.setShortTitle(distributionProtocol.getShortTitle());
		detail.setId(distributionProtocol.getId());
		detail.setTitle(distributionProtocol.getTitle());
		detail.setIrbId(distributionProtocol.getIrbId());
		detail.setStartDate(distributionProtocol.getStartDate());
		detail.setEndDate(distributionProtocol.getEndDate());
		detail.setInstituteName(distributionProtocol.getInstitute().getName());
		if (distributionProtocol.getDefReceivingSite() != null) {
			detail.setDefReceivingSiteName(distributionProtocol.getDefReceivingSite().getName());
		}
		detail.setPrincipalInvestigator(getPrincipleInvestigatorInfo(distributionProtocol.getPrincipalInvestigator()));
		detail.setActivityStatus(distributionProtocol.getActivityStatus());

		if (distributionProtocol.getReport() != null) {
			detail.setReport(SavedQuerySummary.fromSavedQuery(distributionProtocol.getReport()));
		}
		
		detail.setDistributingSites(SiteDetail.from(distributionProtocol.getDistributingSites()));
		
		return detail;
	}

	private static UserSummary getPrincipleInvestigatorInfo(User principleInvestigator) {
		return UserSummary.from(principleInvestigator);
	}
	
	public static List<DistributionProtocolDetail> from(List<DistributionProtocol> distributionProtocols) {
		List<DistributionProtocolDetail> list = new ArrayList<DistributionProtocolDetail>();
		
		for (DistributionProtocol dp : distributionProtocols) {
			list.add(from(dp));
		}
		
		return list;
	}
}
