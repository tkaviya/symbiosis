package net.blaklizt.symbiosis.sym_common.response;

/**
 * User: tkaviya
 * Date: 3/27/2015
 * Time: 7:47 PM
 */
public class ResponseObject
{

	ResponseCode responseCode;
	Object responseObject = null;

	public ResponseObject(ResponseCode responseCode, Object responseObject)
	{
		this.responseCode = responseCode;
		this.responseObject = responseObject;
	}

	public ResponseCode getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode)
	{
		this.responseCode = responseCode;
	}

	public Object getResponseObject()
	{
		return responseObject;
	}

	public void setResponseObject(Object responseObject)
	{
		this.responseObject = responseObject;
	}
}
