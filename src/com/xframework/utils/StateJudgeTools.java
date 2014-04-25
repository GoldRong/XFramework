package com.xframework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

public class StateJudgeTools {
	/**
	 * 检测sd卡是否可用
	 * 
	 * @return
	 */
	public static boolean isSDAvailable() {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	/**
	 * 获取本地版本号名称 versionName
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalVersionName(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionName;
	}

	/**
	 * 检测当的网络（WLAN、3G/2G）状态
	 * 
	 * @param context
	 *            Context
	 * @return true 表示网络可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前的网络状态 -1：没有网络 1：WIFI网络 2：wap网络 3：net网络
	 * 
	 * @author 荣
	 * @param context
	 * @return
	 */
	public static int getNetworkState(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			Log.e("networkInfo.getExtraInfo()",
					"networkInfo.getExtraInfo() is "
							+ networkInfo.getExtraInfo());
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				netType = 3;
			} else {
				netType = 2;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = 1;
		}
		return netType;
	}
}
