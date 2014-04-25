package com.xframework.activity;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import com.xframework.cache.ACache;

public class SuperActivity extends Activity {
	public static ACache aCache;

	/**
	 * 缓存对象Acache
	 */
	public ACache getACache() {
		if (aCache == null) {
			aCache = ACache.get(this);
		}
		return aCache;
	}

	/**
	 * 返回对象本身
	 * 
	 * @return
	 */
	public Context This() {
		return this;
	}

	/**
	 * 打印info级log
	 * 
	 * @param info
	 */
	public void LogInfo(String info) {
		Log.i("info--->", info);
	}
	/**
	 * 获取LayoutInflater对象
	 */
	public LayoutInflater getLayoutInflater(){
		return LayoutInflater.from(this);
	}
	

	
	
}
