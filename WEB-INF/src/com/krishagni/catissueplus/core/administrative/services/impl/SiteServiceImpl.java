
package com.krishagni.catissueplus.core.administrative.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.krishagni.catissueplus.core.administrative.domain.Site;
import com.krishagni.catissueplus.core.administrative.domain.User;
import com.krishagni.catissueplus.core.administrative.domain.factory.SiteErrorCode;
import com.krishagni.catissueplus.core.administrative.domain.factory.SiteFactory;
import com.krishagni.catissueplus.core.administrative.events.SiteDetail;
import com.krishagni.catissueplus.core.administrative.events.SiteQueryCriteria;
import com.krishagni.catissueplus.core.administrative.events.SiteSummary;
import com.krishagni.catissueplus.core.administrative.repository.SiteListCriteria;
import com.krishagni.catissueplus.core.administrative.services.SiteService;
import com.krishagni.catissueplus.core.biospecimen.repository.DaoFactory;
import com.krishagni.catissueplus.core.common.PlusTransactional;
import com.krishagni.catissueplus.core.common.access.AccessCtrlMgr;
import com.krishagni.catissueplus.core.common.errors.ErrorType;
import com.krishagni.catissueplus.core.common.errors.OpenSpecimenException;
import com.krishagni.catissueplus.core.common.events.BulkEntityDetail;
import com.krishagni.catissueplus.core.common.events.DeleteEntityOp;
import com.krishagni.catissueplus.core.common.events.DependentEntityDetail;
import com.krishagni.catissueplus.core.common.events.Operation;
import com.krishagni.catissueplus.core.common.events.RequestEvent;
import com.krishagni.catissueplus.core.common.events.Resource;
import com.krishagni.catissueplus.core.common.events.ResponseEvent;
import com.krishagni.catissueplus.core.common.service.ObjectStateParamsResolver;
import com.krishagni.catissueplus.core.common.util.AuthUtil;
import com.krishagni.catissueplus.core.common.util.Status;
import com.krishagni.catissueplus.core.common.util.Utility;
import com.krishagni.catissueplus.core.exporter.services.ExportService;
import com.krishagni.rbac.common.errors.RbacErrorCode;
import com.krishagni.rbac.service.RbacService;


public class SiteServiceImpl implements SiteService, ObjectStateParamsResolver, InitializingBean {
	private SiteFactory siteFactory;

	private DaoFactory daoFactory;
	
	private RbacService rbacSvc;

	private ExportService exportSvc;
	
	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void setSiteFactory(SiteFactory siteFactory) {
		this.siteFactory = siteFactory;
	}
	
	public void setRbacSvc(RbacService rbacSvc) {
		this.rbacSvc = rbacSvc;
	}

	public void setExportSvc(ExportService exportSvc) {
		this.exportSvc = exportSvc;
	}

	@Override
	@PlusTransactional	
	public ResponseEvent<List<SiteSummary>> getSites(RequestEvent<SiteListCriteria> req) {
		try {
			SiteListCriteria listCrit = req.getPayload();			
			List<Site> sites = null;
			
			if (AuthUtil.isAdmin()) {
				sites = daoFactory.getSiteDao().getSites(listCrit);
			} else if (listCrit.listAll() && isAllSitesAllowed()) {
				sites = daoFactory.getSiteDao().getSites(listCrit);
			} else {
				sites = getAccessibleSites(listCrit);
			}
			
			List<SiteSummary> result = SiteSummary.from(sites);
			if (listCrit.includeStat()) {
				addSiteStats(result);
			}
			
			return ResponseEvent.response(result);
		} catch(OpenSpecimenException ose) {
			return ResponseEvent.error(ose);
		} catch(Exception ex) {
			return ResponseEvent.serverError(ex);
		}
	}

	@Override
	@PlusTransactional
	public ResponseEvent<Long> getSitesCount(RequestEvent<SiteListCriteria> req) {
		return ResponseEvent.response(daoFactory.getSiteDao().getSitesCount(req.getPayload()));
	}

	@Override
	@PlusTransactional		
	public ResponseEvent<SiteDetail> getSite(RequestEvent<SiteQueryCriteria> req) {
		SiteQueryCriteria crit = req.getPayload();
		Site site = null;
		
		if (AuthUtil.isAdmin()) {
			site = getFromDb(crit);
		} else {
			site = getFromAccessibleSite(crit);
		}
		
		if (site == null) {
			return ResponseEvent.userError(SiteErrorCode.NOT_FOUND);
		}
		
		return ResponseEvent.response(SiteDetail.from(site));
	}

	@Override
	@PlusTransactional	
	public ResponseEvent<SiteDetail> createSite(RequestEvent<SiteDetail> req) {
		try {
			Site site = siteFactory.createSite(req.getPayload());
			AccessCtrlMgr.getInstance().ensureCreateUpdateDeleteSiteRights(site);
			
			OpenSpecimenException ose = new OpenSpecimenException(ErrorType.USER_ERROR);
			ensureUniqueConstraint(site, null, ose);
			ose.checkAndThrow();
			daoFactory.getSiteDao().saveOrUpdate(site, true);
			site.addOrUpdateExtension();
			addDefaultCoordinatorRoles(site, site.getCoordinators());
			return ResponseEvent.response(SiteDetail.from(site));
		} catch (OpenSpecimenException ose) {
			return ResponseEvent.error(ose);
		} catch (Exception e) {
			return ResponseEvent.serverError(e);
		}
	}

	@Override
	@PlusTransactional		
	public ResponseEvent<SiteDetail> updateSite(RequestEvent<SiteDetail> req) {
		return updateSite(req, false);
	}

	@Override
	@PlusTransactional		
	public ResponseEvent<SiteDetail> patchSite(RequestEvent<SiteDetail> req) {
		return updateSite(req, true);
	}

	@Override
	@PlusTransactional
	public ResponseEvent<List<SiteDetail>> bulkUpdateSites(RequestEvent<BulkEntityDetail<SiteDetail>> req) {
		try {
			BulkEntityDetail<SiteDetail> buDetail = req.getPayload();

			List<SiteDetail> updatedSites = new ArrayList<>();
			SiteDetail detail = curateBulkUpdateFields(buDetail.getDetail());
			for (Long siteId : Utility.nullSafe(buDetail.getIds())) {
				detail.setId(siteId);
				updatedSites.add(updateSite(detail, true));
			}

			detail.setId(null);
			for (String name : Utility.nullSafe(buDetail.getNames())) {
				detail.setName(name);
				updatedSites.add(updateSite(detail, true));
			}

			return ResponseEvent.response(updatedSites);
		} catch (OpenSpecimenException ose) {
			return ResponseEvent.error(ose);
		} catch (Exception e) {
			return ResponseEvent.serverError(e);
		}
	}

	@Override
	@PlusTransactional
	public ResponseEvent<List<DependentEntityDetail>> getDependentEntities(RequestEvent<Long> req) {
		try {
			Site existing = daoFactory.getSiteDao().getById(req.getPayload());
			if (existing == null) {
				return ResponseEvent.userError(SiteErrorCode.NOT_FOUND);
			}
			
			return ResponseEvent.response(existing.getDependentEntities());
		} catch (Exception e) {
			return ResponseEvent.serverError(e);
		}
	}
	
	@Override
	@PlusTransactional	
	public ResponseEvent<SiteDetail> deleteSite(RequestEvent<DeleteEntityOp> req) {
		try {
			DeleteEntityOp deleteOp = req.getPayload();
			Site existing = daoFactory.getSiteDao().getById(deleteOp.getId());
			if (existing == null) {
				return ResponseEvent.userError(SiteErrorCode.NOT_FOUND);
			}

			AccessCtrlMgr.getInstance().ensureCreateUpdateDeleteSiteRights(existing);
			
			removeDefaultCoordinatorRoles(existing, existing.getCoordinators());
			existing.delete(deleteOp.isClose());
			return ResponseEvent.response(SiteDetail.from(existing));
		} catch (OpenSpecimenException ose) {
			return ResponseEvent.error(ose);
		} catch (Exception e) {
			return ResponseEvent.serverError(e);
		}
	}

	@Override
	public String getObjectName() {
		return "site";
	}

	@Override
	@PlusTransactional
	public Map<String, Object> resolve(String key, Object value) {
		if (key.equals("id")) {
			value = Long.valueOf(value.toString());
		}

		return daoFactory.getSiteDao().getSiteIds(key, value);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		exportSvc.registerObjectsGenerator("site", this::getSitesGenerator);
	}

	private boolean isAllSitesAllowed() {
		return AccessCtrlMgr.getInstance().canCreateUpdateParticipant() ||
			AccessCtrlMgr.getInstance().canCreateUpdateDistributionOrder() ||
			AccessCtrlMgr.getInstance().canCreateUpdateShipment();
	}

	private void addSiteStats(List<SiteSummary> sites) {
		if (CollectionUtils.isEmpty(sites)) {
			return;
		}
		
		Map<Long, SiteSummary> sitesMap = new HashMap<Long, SiteSummary>();
		for (SiteSummary site : sites) {
			sitesMap.put(site.getId(), site);
		}
		
		Map<Long, Integer> cpCountMap = daoFactory.getSiteDao().getCpCountBySite(sitesMap.keySet());
		for (Map.Entry<Long, Integer> cpCount : cpCountMap.entrySet()) {
			sitesMap.get(cpCount.getKey()).setCpCount(cpCount.getValue());
		}		
	}

	private ResponseEvent<SiteDetail> updateSite(RequestEvent<SiteDetail> req, boolean partial) {
		try {
			return ResponseEvent.response(updateSite(req.getPayload(), partial));
		} catch (OpenSpecimenException ose) {
			return ResponseEvent.error(ose);
		} catch (Exception e) {
			return ResponseEvent.serverError(e);
		}
	}

	private SiteDetail updateSite(SiteDetail detail, boolean partial) {
		Site existing = getSite(detail.getId(), detail.getName());
		AccessCtrlMgr.getInstance().ensureCreateUpdateDeleteSiteRights(existing);

		Site site = partial ? siteFactory.createSite(existing, detail) : siteFactory.createSite(detail);
		AccessCtrlMgr.getInstance().ensureCreateUpdateDeleteSiteRights(site);

		OpenSpecimenException ose = new OpenSpecimenException(ErrorType.USER_ERROR);
		ensureUniqueConstraint(site, existing, ose);
		ose.checkAndThrow();

		Collection<User> addedCoordinators =
			CollectionUtils.subtract(site.getCoordinators(), existing.getCoordinators());
		Collection<User> removedCoordinators =
			CollectionUtils.subtract(existing.getCoordinators(), site.getCoordinators());

		existing.update(site);
		daoFactory.getSiteDao().saveOrUpdate(existing);
		existing.addOrUpdateExtension();

		if (Status.isClosedOrDisabledStatus(existing.getActivityStatus())) {
			removeDefaultCoordinatorRoles(existing, existing.getCoordinators());
		} else {
			removeDefaultCoordinatorRoles(existing, removedCoordinators);
			addDefaultCoordinatorRoles(existing, addedCoordinators);
		}

		return SiteDetail.from(existing);
	}

	private Site getSite(Long siteId, String name) {
		Site site = null;
		Object key = null;

		if (siteId != null) {
			site = daoFactory.getSiteDao().getById(siteId);
			key = siteId;
		} else if (StringUtils.isNotBlank(name)) {
			site = daoFactory.getSiteDao().getSiteByName(name);
			key = name;
		}

		if (key == null) {
			throw  OpenSpecimenException.userError(SiteErrorCode.NAME_REQUIRED);
		} else if (site == null) {
			throw  OpenSpecimenException.userError(SiteErrorCode.NOT_FOUND, key);
		}

		return site;
	}

	private void addDefaultCoordinatorRoles(Site site, Collection<User> users) {
		for (User user: users) {
			rbacSvc.addSubjectRole(site, null, user, getDefaultCoordinatorRoles());
		}
	}
	
	private void removeDefaultCoordinatorRoles(Site site, Collection<User> users) {
		for (User user: users) {
			rbacSvc.removeSubjectRole(site, null, user, getDefaultCoordinatorRoles());
		}
	}
	
	private String[] getDefaultCoordinatorRoles() {
		return new String[] {"Administrator"};
	}

	private void ensureUniqueConstraint(Site newSite, Site existingSite, OpenSpecimenException ose) {
		if (!isUniqueName(newSite, existingSite)) {
			ose.addError(SiteErrorCode.DUP_NAME);
		}
		
		if(!isUniqueCode(newSite, existingSite)) {
			ose.addError(SiteErrorCode.DUP_CODE);
		}
	}

	private boolean isUniqueName(Site newSite, Site existingSite) {
		if (existingSite != null && newSite.getName().equals(existingSite.getName())) {
			return true;
		}
		
		Site site = daoFactory.getSiteDao().getSiteByName(newSite.getName());
		if (site != null) {
			return false; 
		} 
		
		return true;
	}
	
	private boolean isUniqueCode(Site newSite, Site existingSite) {
		if (StringUtils.isBlank(newSite.getCode()) || 
			existingSite != null && newSite.getCode().equals(existingSite.getCode())) {
			return true;
		}
		
		Site site = daoFactory.getSiteDao().getSiteByCode(newSite.getCode());
		if (site != null) {
			return false; 
		}
		
		return true;
	}
	
	private List<Site> getAccessibleSites(SiteListCriteria criteria) {
		Set<Site> accessibleSites = null;
		if (StringUtils.isNotBlank(criteria.resource()) && StringUtils.isNotBlank(criteria.operation())) {
			Resource resource = Resource.fromName(criteria.resource());
			if (resource == null) {
				throw OpenSpecimenException.userError(RbacErrorCode.RESOURCE_NOT_FOUND);
			}
			
			Operation operation = Operation.fromName(criteria.operation());
			if (operation == null) {
				throw OpenSpecimenException.userError(RbacErrorCode.OPERATION_NOT_FOUND);
			}
			
			accessibleSites = AccessCtrlMgr.getInstance().getSites(resource, operation);
		} else {
			accessibleSites = AccessCtrlMgr.getInstance().getRoleAssignedSites();
		}

		boolean noIncTypes = CollectionUtils.isEmpty(criteria.includeTypes());
		boolean noExlTypes = CollectionUtils.isEmpty(criteria.excludeTypes());
		boolean noSearchTerm = StringUtils.isBlank(criteria.query());
		return accessibleSites.stream()
			.filter(site -> noIncTypes || criteria.includeTypes().contains(site.getType()))
			.filter(site -> noExlTypes || !criteria.excludeTypes().contains(site.getType()))
			.filter(site -> noSearchTerm || StringUtils.containsIgnoreCase(site.getName(), criteria.query()))
			.sorted((site1, site2) -> site1.getName().compareTo(site2.getName()))
			.collect(Collectors.toList());
	}
	
	private Site getFromAccessibleSite(SiteQueryCriteria crit) {
		Set<Site> accessibleSites = AccessCtrlMgr.getInstance().getRoleAssignedSites();
		
		Long siteId = crit.getId();
		String siteName = crit.getName();
		Site result = null;		
		for (Site site : accessibleSites) {
			if (siteId != null && siteId.equals(site.getId())) {
				result = site;
			} else if (StringUtils.isNotBlank(siteName) && siteName.equals(site.getName())) {
				result = site;
			}
			
			if (result != null) {
				break;
			}
		}

		if (result == null) {
			try {
				AccessCtrlMgr.getInstance().ensureCreateShipmentRights();
				result = getFromDb(crit);
			} catch (OpenSpecimenException ose) {
				
			}
		}
		
		return result;
	}
	
	private Site getFromDb(SiteQueryCriteria crit) {
		Site result = null;
		
		if (crit.getId() != null) {
			result = daoFactory.getSiteDao().getById(crit.getId());
		} else if (crit.getName() != null) {
			result = daoFactory.getSiteDao().getSiteByName(crit.getName());
		}		
		
		return result;
	}

	private SiteDetail curateBulkUpdateFields(SiteDetail input) {
		SiteDetail detail = new SiteDetail();

		if (input.isAttrModified("instituteName")) {
			detail.setInstituteName(input.getInstituteName());
		}

		if (input.isAttrModified("coordinators")) {
			detail.setCoordinators(input.getCoordinators());
		}

		if (input.isAttrModified("type")) {
			detail.setType(input.getType());
		}

		if (input.isAttrModified("activityStatus")) {
			detail.setActivityStatus(input.getActivityStatus());
		}

		//
		// TODO: need to handle custom site fields
		//

		return detail;
	}

	private Supplier<List<? extends Object>> getSitesGenerator() {
		return new Supplier<List<? extends Object>>() {
			private boolean endOfSites;

			private int startAt;

			@Override
			public List<? extends Object> get() {
				if (endOfSites) {
					return Collections.emptyList();
				}

				Collection<Site> sites;
				if (AuthUtil.isAdmin()) {
					sites = daoFactory.getSiteDao().getSites(new SiteListCriteria().startAt(startAt));
					startAt += sites.size();

					if (sites.isEmpty()) {
						endOfSites = true;
					}
				} else {
					sites = getAccessibleSites(new SiteListCriteria());
					endOfSites = true;
				}

				return SiteDetail.from(sites);
			}
		};
	}
}
