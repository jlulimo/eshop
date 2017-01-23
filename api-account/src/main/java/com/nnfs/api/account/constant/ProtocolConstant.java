package com.nnfs.api.account.constant;

public enum ProtocolConstant {
	digest("digest"),accountName("accountName");
	
	private String name;
	
	public String getName() {
		return name;
	}

	ProtocolConstant(String name){
		this.name = name;
	}
	
	
}
