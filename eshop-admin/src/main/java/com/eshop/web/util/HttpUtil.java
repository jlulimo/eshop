package com.eshop.web.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class HttpUtil {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final String BASE_URL = "http://localhost:9000";
	private static OkHttpClient httpclient;

	private HttpUtil() {
		Proxy fiddlerProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
		httpclient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
				.proxy(fiddlerProxy).build();
	}

	public static synchronized OkHttpClient getHttpClient() {
		if (null == httpclient) {
			new HttpUtil();
		}
		return httpclient;
	}
}
