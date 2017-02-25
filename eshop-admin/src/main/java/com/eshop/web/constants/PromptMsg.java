package com.eshop.web.constants;

public enum PromptMsg {
	SUCCESS(0, "请求成功"), ACCOUNT_NOT_EXIST(100, "用户不存在."), ACCOUNT_LOCKED(101, "账号被锁定."), LOGIN_ERROR(102,
			"登录错误."), ACCOUNT_INCORRECTCREDENTIALS(106, "密码不正确"), ACCOUNT_ADD_SUCCESS(107, "账户添加成功."),QUERY_FAILED(108,"查询失败");
	

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
