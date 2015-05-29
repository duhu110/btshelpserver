package com.duhu.servlet;

import java.io.Serializable;

public class ResultJSONBeanbtscheck implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result;
	private String errormsg;
	public ResultJSONBeanbtscheck() {
		
	}
	public ResultJSONBeanbtscheck(String result, String errormsg) {
		this.result = result;
		this.errormsg = errormsg;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

}
