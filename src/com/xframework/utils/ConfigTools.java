package com.xframework.utils;

import org.json.JSONObject;

import com.xframework.model.ConfigModel;

public class ConfigTools {
	/**
	 * 单例模式
	 */
	private static ConfigTools configTools = new ConfigTools();

	private ConfigTools() {
	}

	public static ConfigTools getInstance() {
		if (configTools == null) {
			configTools = new ConfigTools();
		}
		return configTools;
	}

	/**
	 * 读取assets目录下的configure.json配置文件
	 */
	public ConfigModel getConfigModel() {
		try {
			JSONObject json = new JSONObject(
					FormatTools.inputStream2String(getClass()
							.getResourceAsStream("/assets/configure.json")));
			ConfigModel configModel = new ConfigModel();
			configModel.setVersionCode(json.getInt("versionCode"));
			configModel.setNewVersion(json.getString("versionCode"));
			configModel.setServerAddress(json.getString("serverAddress"));
			configModel.setImageAddress(json.getString("imageAddress"));
			return configModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
