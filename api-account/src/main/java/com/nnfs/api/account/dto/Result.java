package com.nnfs.api.account.dto;

public class Result {
	/**
	 * 结果码
	 */
	private int code;

	/**
	 * 结果信息
	 */
	private String msg;

	/**
	 * 扩展对象(放置分页信息、其他信息等)
	 */
	private Object extendData;

	/**
	 * 数据对象
	 */
	private Object data;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getExtendData() {
		return extendData;
	}

	public void setExtendData(Object extendData) {
		this.extendData = extendData;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
