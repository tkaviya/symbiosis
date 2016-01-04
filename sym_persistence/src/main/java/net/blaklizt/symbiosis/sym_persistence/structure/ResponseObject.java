package net.blaklizt.symbiosis.sym_persistence.structure;

import net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE;

/**
 * User: tkaviya
 * Date: 3/27/2015
 * Time: 7:47 PM
 */
public class ResponseObject<T>
{
	SYM_RESPONSE_CODE responseCode;
	T responseObject = null;

	public ResponseObject(SYM_RESPONSE_CODE responseCode, T responseObject) {
		this.responseCode = responseCode;
		this.responseObject = responseObject;
	}

	public ResponseObject(SYM_RESPONSE_CODE responseCode)
	{
		this.responseCode = responseCode;
	}

    public int getCode() {  return responseCode.code; }

	//Override default message with custom message. Return this to allow method chaining
    public ResponseObject<T> setMessage(String responseMessage) { responseCode.message = responseMessage; return this; }

	public String getMessage() {   return responseCode.message;  }

	public SYM_RESPONSE_CODE getResponseCode() { return responseCode; }

	//Change response code for this ResponseObject. Return this to allow method chaining
	public ResponseObject<T> setResponseCode(SYM_RESPONSE_CODE responseCode) { this.responseCode = responseCode; return this; }

	public T getResponseObject()
	{
		return responseObject;
	}

	//Change object for this ResponseObject. Return this to allow method chaining
	public ResponseObject<T> setResponseObject(T responseObject) { this.responseObject = responseObject; return this; }
}
