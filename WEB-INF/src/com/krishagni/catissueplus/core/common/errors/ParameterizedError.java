package com.krishagni.catissueplus.core.common.errors;

import org.codehaus.jackson.annotate.JsonProperty;

public class ParameterizedError {
	private ErrorCode error;
	
	private Object[] params;

	public ParameterizedError(ErrorCode error, Object param) {
		this(error, new Object[] { param });
	}
	
	public ParameterizedError(ErrorCode error, Object[] params) {
		this.error = error;
		this.params = params;
	}
	
	@JsonProperty
	public ErrorCode error() {
		return error;
	}
	
	@JsonProperty
	public Object[] params() {
		return params;
	}
}
