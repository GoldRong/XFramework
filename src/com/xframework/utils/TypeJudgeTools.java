package com.xframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类型判断工具
 * 
 * @author 荣
 * 
 */
public class TypeJudgeTools {
	/**
	 * 判断传入的字符串是否是一个邮箱地址
	 * 
	 * @param strEmail
	 * @return
	 */
	public static boolean isEmail(String strEmail) {
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(strEmail);
		return m.find();
	}

	/**
	 * 判断传入的字符串是否是一个手机号码
	 * 
	 * @param strPhone
	 * @return
	 */
	public static boolean isPhoneNumber(String strPhone) {
		Pattern p = Pattern.compile("^(13[0-9]|15[0-9]|18[0-9])\\d{8}$");
		Matcher m = p.matcher(strPhone);
		return m.find();
	}

	/**
	 * 判断传入的字符是否是一个网址
	 * 
	 * @param strWebSite
	 * @return
	 */
	public static boolean isWebSite(String strPhone) {
		Pattern p = Pattern
				.compile("(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		Matcher m = p.matcher(strPhone);
		return m.find();
	}
	
	public static boolean isNull(String str){
		if(str.equals("")||str==null)
			return true;
		else
			return false;
	}
}
