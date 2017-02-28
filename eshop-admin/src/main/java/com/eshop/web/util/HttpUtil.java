package com.eshop.web.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.eshop.web.model.ResultModel;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
	public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
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

	/**
	 * 
	 * @param 返回值类型
	 * @param Model对象
	 * @param 相对URL
	 */
	public static <T> T exectRequest(Class<T> tClass, Object object, String relativelyUrl) {
		T t = null;
		// build a request
		Request request = new Request.Builder().url(HttpUtil.BASE_URL + relativelyUrl)
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
}
