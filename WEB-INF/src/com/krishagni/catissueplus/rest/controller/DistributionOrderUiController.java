package com.krishagni.catissueplus.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.krishagni.catissueplus.core.administrative.events.DistributionOrderDetail;
import com.krishagni.catissueplus.core.administrative.services.DistributionOrderService;
import com.krishagni.catissueplus.core.common.events.RequestEvent;
import com.krishagni.catissueplus.core.common.events.ResponseEvent;

@Controller
@RequestMapping("/ui-orders")
public class DistributionOrderUiController {

	@Autowired
	private DistributionOrderService distributionService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object createDistribution(@RequestBody DistributionOrderDetail order) {
		order.setId(null);
		ResponseEvent<DistributionOrderDetail> resp = distributionService.createOrder(getRequest(order));
		if (resp.isSuccessful()) {
			return resp.getPayload();
		} else {
			return resp.getError();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object updateDistribution(
		@PathVariable("id")
			Long distributionId,

		@RequestBody
			DistributionOrderDetail order) {

		order.setId(distributionId);
		ResponseEvent<DistributionOrderDetail> resp = distributionService.updateOrder(getRequest(order));
		if (resp.isSuccessful()) {
			return resp.getPayload();
		} else {
			return resp.getError();
		}
	}

	private <T> RequestEvent<T> getRequest(T payload) {
		return new RequestEvent<T>(payload);
	}

}
