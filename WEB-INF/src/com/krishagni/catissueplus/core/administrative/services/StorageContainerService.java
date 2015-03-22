package com.krishagni.catissueplus.core.administrative.services;

import java.util.List;
import java.util.Map;

import com.krishagni.catissueplus.core.administrative.events.ContainerQueryCriteria;
import com.krishagni.catissueplus.core.administrative.events.PositionTenantDetail;
import com.krishagni.catissueplus.core.administrative.events.StorageContainerDetail;
import com.krishagni.catissueplus.core.administrative.events.StorageContainerPositionDetail;
import com.krishagni.catissueplus.core.administrative.events.StorageContainerSummary;
import com.krishagni.catissueplus.core.administrative.repository.StorageContainerListCriteria;
import com.krishagni.catissueplus.core.common.events.RequestEvent;
import com.krishagni.catissueplus.core.common.events.ResponseEvent;

public interface StorageContainerService {
	public ResponseEvent<List<StorageContainerSummary>> getStorageContainers(RequestEvent<StorageContainerListCriteria> req);
	
	public ResponseEvent<StorageContainerDetail> getStorageContainer(RequestEvent<ContainerQueryCriteria> req);
	
	public ResponseEvent<List<StorageContainerPositionDetail>> getOccupiedPositions(RequestEvent<Long> req);
	
	public ResponseEvent<StorageContainerDetail> createStorageContainer(RequestEvent<StorageContainerDetail> req);
	
	public ResponseEvent<StorageContainerDetail> updateStorageContainer(RequestEvent<StorageContainerDetail> req);
	
	public ResponseEvent<Boolean> isAllowed(RequestEvent<PositionTenantDetail> req);
	
	public ResponseEvent<List<Map<String, Object>>> getStorageContainerDependencyStat(RequestEvent<Long> req);
	
	public ResponseEvent<StorageContainerDetail> deleteStorageContainer(RequestEvent<Long> req);
}
