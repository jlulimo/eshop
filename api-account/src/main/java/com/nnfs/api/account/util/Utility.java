package com.nnfs.api.account.util;

import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class Utility {

	public static String tokenGenerator() {
		String ori = UUID.randomUUID().toString();
		ori = ori.replaceAll("-", "");
		byte[] encodedBytes = Base64.encodeBase64(ori.getBytes());
		return String.valueOf(encodedBytes);
	}
	
}
