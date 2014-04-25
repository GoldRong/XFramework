package com.xframework.activity.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.xframework.cache.ACache;

public class SuperFragment extends Fragment {
	/**
	 * 缓存对象Acache
	 */
	public ACache getACache() {
		if (aCache == null) {
			aCache = ACache.get(this.getActivity());
		}
		return aCache;
	}

	/**
	 * 返回对象本身
	 * 
	 * @return
	 */
	public Context This() {
		return this.getActivity();
	}
	public static ACache aCache;

	/**
	 * 打印info级log
	 * 
	 * @param info
	 */
	public void LogInfo(String info) {
		Log.i("info--->", info);
	}
}
