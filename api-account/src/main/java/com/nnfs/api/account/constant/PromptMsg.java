package com.nnfs.api.account.constant;

public enum PromptMsg {
	Account_does_not_exist("用户不存在."), Account_is_locked("账号被锁定.");

	private String msg;

	PromptMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
