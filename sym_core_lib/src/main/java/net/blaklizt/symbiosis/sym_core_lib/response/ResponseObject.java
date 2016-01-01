package net.blaklizt.symbiosis.sym_core_lib.response;

import net.blaklizt.symbiosis.sym_core_lib.enumeration.SYM_RESPONSE_CODE;

/**
 * User: tkaviya
 * Date: 3/27/2015
 * Time: 7:47 PM
 */
public class ResponseObject<O>
{

	SYM_RESPONSE_CODE responseCode;
	O responseObject = null;

	public ResponseObject(SYM_RESPONSE_CODE responseCode, O responseObject)
	{
		this.responseCode = responseCode;
		this.responseObject = responseObject;
	}

	public ResponseObject(SYM_RESPONSE_CODE responseCode)
	{
		this.responseCode = responseCode;
	}

    public int getCode() {  return responseCode.code; }

    public String getMessage() {   return responseCode.message;  }

	public SYM_RESPONSE_CODE getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(SYM_RESPONSE_CODE responseCode)
	{
		this.responseCode = responseCode;
	}

	public O getResponseObject()
	{
		return responseObject;
	}

	public void setResponseObject(O responseObject)
	{
		this.responseObject = responseObject;
	}
}
