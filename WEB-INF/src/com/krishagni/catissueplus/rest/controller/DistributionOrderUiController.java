package com.krishagni.catissueplus.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.krishagni.catissueplus.core.common.errors.OpenSpecimenException;
import com.krishagni.catissueplus.core.common.events.RequestEvent;
import com.krishagni.catissueplus.core.common.events.ResponseEvent;
import com.krishagni.catissueplus.core.common.util.MessageUtil;

//
// Simple UI order API wrapper to wrap error in HTTP 200 responses
//
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
		return response(distributionService.createOrder(request(order)));
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
		return response(distributionService.updateOrder(request(order)));
	}

	private <T> RequestEvent<T> request(T payload) {
		return new RequestEvent<T>(payload);
	}

	private Object response(ResponseEvent<?> resp) {
		if (resp.isSuccessful()) {
			return resp.getPayload();
		} else {
			OpenSpecimenException ose = resp.getError();
			Map<String, Object> errorResp = new HashMap<>();
			errorResp.put("errorType", ose.getErrorType().name());
			errorResp.put("errors", getErrors(ose));
			return errorResp;
		}
	}

	private List<Map<String, Object>> getErrors(OpenSpecimenException ose) {
		return ose.getErrors().stream().map(pe -> {
			Map<String, Object> error = new HashMap<>();
			error.put("code", pe.error().code());
			error.put("message", MessageUtil.getInstance().getMessage(pe.error().code().toLowerCase(), pe.params()));
			error.put("params", pe.params());
			return error;
		}).collect(Collectors.toList());
	}
}
