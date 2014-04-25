package com.xframework.http;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.xframework.utils.ConfigTools;

public class HttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient(); // 实例话对象
	public static String ServerAddress;
	public static AsyncHttpClient getClient() {
		return client;
	}

	static {
		client.setTimeout(10000); // 设置链接超时，如果不设置，默认为10s
		ServerAddress = ConfigTools.getInstance().getConfigModel().getServerAddress();
	}

	/**
	 * 传入url与json参数 获取一个json返回值
	 * 
	 * @param context
	 * @param url
	 * @param entity
	 * @param responseHandler
	 */
	public static void post(Context context, String url, JSONObject jsonObject,ResponseHandlerInterface responseHandler) {
		HttpEntity entity = null;
		Log.i("Action", ""+ServerAddress+url);
		try {
			if (jsonObject != null) {
				entity = new StringEntity(jsonObject.toString(), "UTF-8");
				client.post(context, ServerAddress+url, entity, "application/json",responseHandler);
			} else {
				client.get(ServerAddress+url, responseHandler);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 传入url与文件对象上传图片
	 * 
	 * @param url
	 * @param file
	 * @param asyncHttpResponseHandler
	 */
	public static void fileUpLoad(String url, File file,AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams requestParams = new RequestParams();
		try {
			requestParams.put("profile_picture", file,"application/octet-stream");
			client.post(ServerAddress+url, requestParams, new AsyncHttpResponseHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
