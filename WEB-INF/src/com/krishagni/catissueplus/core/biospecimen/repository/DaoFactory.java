
package com.krishagni.catissueplus.core.biospecimen.repository;

import com.krishagni.catissueplus.core.administrative.repository.AutoFreezerProviderDao;
import com.krishagni.catissueplus.core.administrative.repository.ContainerStoreListDao;
import com.krishagni.catissueplus.core.administrative.repository.ContainerTypeDao;
import com.krishagni.catissueplus.core.administrative.repository.DistributionOrderDao;
import com.krishagni.catissueplus.core.administrative.repository.DistributionProtocolDao;
import com.krishagni.catissueplus.core.administrative.repository.DpRequirementDao;
import com.krishagni.catissueplus.core.administrative.repository.InstituteDao;
import com.krishagni.catissueplus.core.administrative.repository.PermissibleValueDao;
import com.krishagni.catissueplus.core.administrative.repository.ScheduledJobDao;
import com.krishagni.catissueplus.core.administrative.repository.ShipmentDao;
import com.krishagni.catissueplus.core.administrative.repository.SiteDao;
import com.krishagni.catissueplus.core.administrative.repository.SpecimenRequestDao;
import com.krishagni.catissueplus.core.administrative.repository.StorageContainerDao;
import com.krishagni.catissueplus.core.administrative.repository.StorageContainerPositionDao;
import com.krishagni.catissueplus.core.administrative.repository.UserDao;
import com.krishagni.catissueplus.core.audit.repository.AuditDao;
import com.krishagni.catissueplus.core.auth.repository.AuthDao;
import com.krishagni.catissueplus.core.common.repository.ConfigSettingDao;
import com.krishagni.catissueplus.core.common.repository.UnhandledExceptionDao;
import com.krishagni.catissueplus.core.common.repository.UniqueIdGenerator;
import com.krishagni.catissueplus.core.common.repository.UpgradeLogDao;

public interface DaoFactory {
	CollectionProtocolDao getCollectionProtocolDao();

	ParticipantDao getParticipantDao();

	StagedParticipantDao getStagedParticipantDao();

	CollectionProtocolRegistrationDao getCprDao();

	AnonymizeEventDao getAnonymizeEventDao();

	SiteDao getSiteDao();

	SpecimenDao getSpecimenDao();
	
	SpecimenRequirementDao getSpecimenRequirementDao();

	VisitsDao getVisitsDao();

	UserDao getUserDao();
	
	AuthDao getAuthDao();

	UniqueIdGenerator getUniqueIdGenerator();

	InstituteDao getInstituteDao();

	StorageContainerDao getStorageContainerDao();

	StorageContainerPositionDao getStorageContainerPositionDao();
	
	ContainerTypeDao getContainerTypeDao();

	DistributionProtocolDao getDistributionProtocolDao();

	SpecimenListDao getSpecimenListDao();

	SpecimenKitDao getSpecimenKitDao();

	PermissibleValueDao getPermissibleValueDao();
	
	ScheduledJobDao getScheduledJobDao();
	
	DistributionOrderDao getDistributionOrderDao();
	
	ConfigSettingDao getConfigSettingDao();
	
	LabelPrintJobDao getLabelPrintJobDao();
	
	AuditDao getAuditDao();

	DpRequirementDao getDistributionProtocolRequirementDao();
	
	ShipmentDao getShipmentDao();

	SpecimenRequestDao getSpecimenRequestDao();
	
	UpgradeLogDao getUpgradeLogDao();

	CpReportSettingsDao getCpReportSettingsDao();
	
	UnhandledExceptionDao getUnhandledExceptionDao();

	ConsentStatementDao getConsentStatementDao();

	ContainerStoreListDao getContainerStoreListDao();

	AutoFreezerProviderDao getAutoFreezerProviderDao();
} 
