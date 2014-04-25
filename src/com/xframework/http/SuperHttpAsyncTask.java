package com.xframework.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.xframework.utils.FormatTools;

public class SuperHttpAsyncTask extends AsyncTask<Void, Void,JSONObject> {
	
	private String url=null;
	private JSONObject jsonObject=null;
	
	/**
	 * 接受一个jsonobject对象返回一个jsonobject对象
	 * @param url
	 * @param jsonObject
	 */
	public SuperHttpAsyncTask(String url,JSONObject jsonObject){
		this.url=url;
		this.jsonObject=jsonObject;
	}
	
	@Override
	protected JSONObject doInBackground(Void... params) {
		JSONObject result = null;
		try {
			BasicHttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
			HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
			HttpClient httpclient = new DefaultHttpClient(httpParams);
			HttpPost httppost = new HttpPost(url);
			
			// 添加http请求信息头
			httppost.addHeader("Content-Type", "application/json");
			if (jsonObject != null) {
				httppost.setEntity(new StringEntity(jsonObject.toString(),"UTF-8"));
			}

			HttpResponse response = null;
			response = httpclient.execute(httppost);

			// 获取状态码，如果成功接收数据
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				result = new JSONObject(FormatTools.inputStream2String(response.getEntity().getContent()));
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

}
