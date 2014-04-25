package com.xframework.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xframework.utils.FormatTools;

public class SuperHttp {
	/**
	 * 单例模式
	 */
	private static SuperHttp superHttp = new SuperHttp();

	private SuperHttp() {
	}

	public static SuperHttp getInstance() {
		if (superHttp == null) {
			superHttp = new SuperHttp();
		}
		return superHttp;
	}

	/**
	 * 请求获取后台jsonobject数据
	 * 
	 * @param uri
	 * @param jsonobject
	 * @return jsonObject
	 */
	public JSONObject HttpPost4Json(String uri, JSONObject jsonObject) {
		JSONObject result = null;
		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
			HttpClient httpclient = new DefaultHttpClient(httpParams);
			HttpPost httppost = new HttpPost(uri);

			// 添加http请求信息头
			httppost.addHeader("Content-Type", "application/json");
			if (jsonObject != null) {
				httppost.setEntity(new StringEntity(jsonObject.toString(),
						"UTF-8"));
			}

			HttpResponse response = null;
			response = httpclient.execute(httppost);

			// 获取状态码，如果成功接收数据
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				result = new JSONObject(FormatTools.inputStream2String(response
						.getEntity().getContent()));
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			Log.i("SuperHttp", "ClientProtocolException");
		} catch (IOException e) {
			e.printStackTrace();
			Log.i("SuperHttp", "IOException");
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("SuperHttp", "Exception");
		}
		return null;
	}

	/**
	 * 异步http请求下载图片返回Drawable对象
	 */
	public Drawable post4Drawable(String url) {
		HttpPost httpPost = null;
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = new DefaultHttpClient();
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {

				InputStream is = httpResponse.getEntity().getContent();
				return FormatTools.InputStream2Drawable(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 异步http请求下载图片返回Bitmap对象
	 */
	public Bitmap post4Bitmap(String url) {
		HttpPost httpPost = null;
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = new DefaultHttpClient();
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				InputStream is = httpResponse.getEntity().getContent();
				return FormatTools.InputStream2Bitmap(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载更新的安装文件apk
	 */
	public File post4APK(String url) {
		HttpPost httpPost = null;
		HttpClient httpClient = null;
		HttpResponse httpResponse = null;
		try {
			httpPost = new HttpPost(url);
			httpClient = new DefaultHttpClient();
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				InputStream is = httpResponse.getEntity().getContent();
				File apkFile = new File("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
