package net.blaklizt.symbiosis.sym_persistence.structure;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_response_code;

/**
 * User: tkaviya
 * Date: 3/27/2015
 * Time: 7:47 PM
 */
public class ResponseObject<T>
{
	symbiosis_response_code responseCode;

	String responseMessage;

	T responseObject = null;

	public ResponseObject(symbiosis_response_code responseCode, T responseObject) {
		this.responseCode = responseCode;
		this.responseMessage = responseCode.getResponse_message();
		this.responseObject = responseObject;
	}

	public ResponseObject(symbiosis_response_code responseCode) {
		this.responseCode = responseCode;
		this.responseMessage = responseCode.getResponse_message();
	}

    public Long getCode() {  return responseCode.getId(); }

	//Override default message with custom message. Return this to allow method chaining
    public ResponseObject<T> setMessage(String responseMessage) { this.responseMessage = responseMessage; return this; }

	public String getMessage() {   return responseMessage;  }

	public symbiosis_response_code getResponseCode() { return responseCode; }

	//Change response code for this ResponseObject. Return this to allow method chaining
	public ResponseObject<T> setResponseCode(symbiosis_response_code responseCode) {
		this.responseCode = responseCode;
		this.responseMessage = responseCode.getResponse_message();
		return this;
	}

	public T getResponseObject() {
		return responseObject;
	}

	//Change object for this ResponseObject. Return this to allow method chaining
	public ResponseObject<T> setResponseObject(T responseObject) { this.responseObject = responseObject; return this; }
}
