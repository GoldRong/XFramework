package com.xframework.activity;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.xframework.R;
import com.xframework.http.HttpUtil;

import android.os.Bundle;
import android.widget.Toast;

public class HomeActivity extends SuperActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		init();
	}

	/**
	 * 初始化
	 */
	public void init() {
		try {
			JSONObject json = new JSONObject();
			json.put("username", "徐荣");
			json.put("sexy", "男");
			json.put("age", 23);
			HttpUtil.post(This(),"hello.do", json,new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(JSONObject response) {
					Toast.makeText(This(), ""+response, Toast.LENGTH_LONG).show();
				}

				@Override
				public void onFailure(Throwable e, JSONObject errorResponse) {
					Toast.makeText(This(), "数据访问失败！", Toast.LENGTH_LONG).show();
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
