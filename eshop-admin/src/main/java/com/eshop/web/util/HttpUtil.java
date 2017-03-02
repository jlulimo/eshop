package com.eshop.web.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
	public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
	public static final String ACCOUNT_BASE_URL = "http://localhost:9000";
	public static final String PRODUCT_BASE_URL = "http://localhost:9001";
	private static OkHttpClient httpclient;

	private HttpUtil() {
		Proxy fiddlerProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8888));
		httpclient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS).proxy(fiddlerProxy).build();
	}

	public static synchronized OkHttpClient getHttpClient() {
		if (null == httpclient) {
			new HttpUtil();
		}
		return httpclient;
	}

	/**
	 * 
	 * @param 返回值类型
	 * @param Model对象
	 * @param 相对URL
	 */
	public static <T> T exectPost(Class<T> tClass, Object object, String relativelyUrl) {
		T t = null;
		// build a request
		Request request = new Request.Builder().url(HttpUtil.PRODUCT_BASE_URL + relativelyUrl)
				.post(RequestBody.create(HttpUtil.JSON_TYPE, JSON.toJSONString(object))).build();
		try {
			Response response = HttpUtil.getHttpClient().newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				t = JSON.parseObject(resp, tClass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> T exectGet(Class<T> tClass, String relativelyUrl, Map<String, String> param) {
		T t = null;
		HttpUrl.Builder urlBuilder = HttpUrl.parse(HttpUtil.PRODUCT_BASE_URL + relativelyUrl).newBuilder();
		for (Map.Entry<String, String> kvp : param.entrySet()) {
			urlBuilder.addQueryParameter(kvp.getKey(), kvp.getValue());
		}
		String url = urlBuilder.build().toString();
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = HttpUtil.getHttpClient().newCall(request).execute();
			if (response.isSuccessful()) {
				String resp = response.body().string();
				t = JSON.parseObject(resp, tClass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}
}
