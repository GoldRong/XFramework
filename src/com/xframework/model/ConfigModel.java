package com.xframework.model;

public class ConfigModel extends SuperModel{
	// 当前版本号
	private int versionCode = -1;
	// 新版本的下载地址
	private String newVersion = null;
	// 服务器的地址
	private String serverAddress = null;
	// 服务器图片的地址
	private String imageAddress = null;
	
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public String getNewVersion() {
		return newVersion;
	}
	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}


}
