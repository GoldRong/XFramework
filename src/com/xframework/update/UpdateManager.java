package com.xframework.update;

import com.xframework.http.SuperHttp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * 升级管理器
 * @author 荣
 *
 */
public class UpdateManager {

	private Context c;
	private boolean isUpdate=false;
	private String apkUrl="http://dl.wandoujia.com/files/phoenix/latest/wandoujia-wandoujia_web.apk?timestamp=1396254750996";

	/**
	 * 检查是否有新的安装包
	 */
	public void checkUpdate(){
		SuperHttp superHttp=SuperHttp.getInstance();
		//发送请求获取版本号
		if(isUpdate){
			Toast.makeText(c, "", 1000).show();
		}else{
			Toast.makeText(c, "开始下载", 1000).show();
			setupAPK(apkUrl);
		}
	}
	/**
	 * 启动安装程序
	 */
	public void setupAPK(String apkUrl){
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		Uri content_url = Uri.parse(apkUrl);
		intent.setData(content_url);
		c.startActivity(intent);
	}
}
