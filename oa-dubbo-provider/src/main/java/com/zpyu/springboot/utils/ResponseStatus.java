package com.zpyu.springboot.utils;

public class ResponseStatus {
	private Integer code;
	private String msg;
	private static ResponseStatus responseStatus = new ResponseStatus();
	
	public static ResponseStatus buildStatus() {
		return responseStatus;
	}
	
	private ResponseStatus() {
		this.code = 200;
		this.msg = "success";
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
