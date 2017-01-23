package com.nnfs.api.account.constant;

public enum PromptMsg {
	Account_does_not_exist(100, "用户不存在."), Account_is_locked(101, "账号被锁定."), login_error(102, "登录错误."), INVALID_CLIENT(
			103, "客户端验证失败,如错误的client_id/client_secret."), OAuth_callback_url_needs_to_be_provided_by_client(104,
					"OAuth callback url needs to be provided by client.");

	private String msg;

	private int code;

	PromptMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
