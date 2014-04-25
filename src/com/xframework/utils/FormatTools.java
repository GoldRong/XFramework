package com.xframework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;

import com.xframework.pojo.SuperArrayList;
import com.xframework.pojo.SuperHashMap;

/**
 * 提供了一个工具类
 * 
 * @author 荣
 * 
 */
public class FormatTools {

	/**
	 * 将byte[]转换成InputStream
	 * 
	 * @param b
	 * @return
	 */
	public static InputStream Byte2InputStream(byte[] b) {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		return bais;
	}

	/**
	 * 将InputStream转换成byte[]
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] InputStream2Bytes(InputStream is) {
		String str = "";
		byte[] readByte = new byte[1024];
		int readCount = -1;
		try {
			while ((readCount = is.read(readByte, 0, 1024)) != -1) {
				str += new String(readByte).trim();
			}
			return str.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Bitmap转换成InputStream
	 * 
	 * @param bm
	 * @return
	 */
	public static InputStream Bitmap2InputStream(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	/**
	 * 将Bitmap转换成InputStream
	 * 
	 * @param bm
	 * @param quality
	 * @return
	 */
	public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, quality, baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

	/**
	 * 将InputStream转换成Bitmap
	 * 
	 * @param is
	 * @return
	 */
	public static Bitmap InputStream2Bitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	/**
	 * Drawable转换成InputStream
	 * 
	 * @param d
	 * @return
	 */
	public static InputStream Drawable2InputStream(Drawable d) {
		Bitmap bitmap = drawable2Bitmap(d);
		return Bitmap2InputStream(bitmap);
	}

	/**
	 * InputStream转换成Drawable
	 * 
	 * @param is
	 * @return
	 */
	public static Drawable InputStream2Drawable(InputStream is) {
		Bitmap bitmap = InputStream2Bitmap(is);
		return bitmap2Drawable(bitmap);
	}

	/**
	 * Drawable转换成byte[]
	 * 
	 * @param d
	 * @return
	 */
	public static byte[] Drawable2Bytes(Drawable d) {
		Bitmap bitmap = drawable2Bitmap(d);
		return Bitmap2Bytes(bitmap);
	}

	/**
	 * byte[]转换成Drawable
	 * 
	 * @param b
	 * @return
	 */
	public static Drawable Bytes2Drawable(byte[] b) {
		Bitmap bitmap = Bytes2Bitmap(b);
		return bitmap2Drawable(bitmap);
	}

	/**
	 * Bitmap转换成byte[]
	 * 
	 * @param bm
	 * @return
	 */
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * byte[]转换成Bitmap
	 * 
	 * @param b
	 * @return
	 */
	public static Bitmap Bytes2Bitmap(byte[] b) {
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		}
		return null;
	}

	/**
	 * Drawable转换成Bitmap
	 * 
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * Bitmap转换成Drawable
	 * 
	 * @param bitmap
	 * @return
	 */
	public static Drawable bitmap2Drawable(Bitmap bitmap) {
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		Drawable d = (Drawable) bd;
		return d;
	}

	/**
	 * 将一个inputstream，以字符串的形式返回
	 * 
	 * @param in
	 * @return
	 */
	public static String inputStream2String(InputStream in) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] readByte = new byte[1024];
		int readCount = -1;

		try {
			while ((readCount = in.read(readByte, 0, 1024)) != -1) {
				baos.write(readByte, 0, readCount);
			}
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new String(baos.toByteArray());
	}

	/**
	 * 将输入的dp转换为对应的px值，并返回px值
	 * 
	 * @param dp
	 * @param c
	 * @return
	 */
	public static int dp2px(int dp, Context c) {

		return (int) (dp * c.getResources().getDisplayMetrics().density + 0.5f);
	}

	/**
	 * 获取DP数量
	 * 
	 * @param c
	 * @return
	 */
	public static int getDensity(Context c) {
		return (int) c.getResources().getDisplayMetrics().density;
	}

	/**
	 * 半角转换为全角
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
	 * 
	 * @param tms
	 * @return
	 */
	public static String convertTimeToFormat(long tms) {

		long curTime = System.currentTimeMillis();

		long time = (curTime - tms) / (long) 1000;

		if (time < 60 && time >= 0) {
			return "刚刚";
		} else if (time >= 60 && time < 3600) {
			return time / 60 + "分钟前";
		} else if (time >= 3600 && time < 3600 * 24) {
			return time / 3600 + "小时前";
		} else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
			return time / 3600 / 24 + "天前";
		} else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
			return time / 3600 / 24 / 30 + "个月前";
		} else if (time >= 3600 * 24 * 30 * 12) {
			return time / 3600 / 24 / 30 / 12 + "年前";
		} else {
			return "刚刚";
		}

	}

	/**
	 * 将一个时间戳转化成时间字符串，如2010-12-12 23:24:33
	 * 
	 * @param time
	 * @return
	 */
	public static String convertTime(long time) {
		if (time == 0) {
			return "";
		}
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 将一个时间戳转化成时间字符串，自定义格式
	 * 
	 * @param time
	 * @param format
	 *            如 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String convertTime(long time, String format) {
		if (time == 0) {
			return "";
		}

		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * MD5加密工具
	 */
	public static String md5Util(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取本地版本号 versionCode
	 * 
	 * @param context
	 * @return
	 */
	public static int getLocalVersionCode(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return info.versionCode;
	}

	/**
	 * 将View转换为bitmap
	 * 
	 * @param view
	 * @return
	 */
	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();

		return bitmap;
	}

	/**
	 * 获取屏幕的分辨率key width,height
	 */
	public static HashMap<String, Integer> getResolution(Context c) {
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		DisplayMetrics dm = c.getResources().getDisplayMetrics();
		hashmap.put("width", dm.widthPixels);
		hashmap.put("height", dm.heightPixels);
		return hashmap;
	}

	/**
	 * 将json转换为map
	 * 
	 * @param json
	 * @return
	 */
	public static SuperHashMap convertJSONToMap(JSONObject json) {
		if (json == null) {
			return null;
		}
		SuperHashMap map = new SuperHashMap();

		Iterator<String> i = json.keys();
		while (i.hasNext()) {
			String name = i.next();
			Object o = null;
			try {
				o = json.get(name);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			if (o == null) {
				continue;
			}
			if (o instanceof JSONObject) {
				map.put(name, convertJSONToMap((JSONObject) o));
			} else if (o instanceof JSONArray) {
				map.put(name, convertJSONArrayToList((JSONArray) o));
			} else {
				map.put(name, o);
			}

		}
		return map;
	}

	/**
	 * 将jsonarray 转换为List
	 * 
	 * @param array
	 * @return
	 */
	public static SuperArrayList convertJSONArrayToList(JSONArray array) {
		if (array == null) {
			return null;
		}
		SuperArrayList list = new SuperArrayList();

		for (int i = 0; i < array.length(); i++) {
			Object o = null;
			try {
				o = array.get(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (o == null) {
				continue;
			}
			if (o instanceof JSONObject) {
				list.add(convertJSONToMap((JSONObject) o));
			} else if (o instanceof JSONArray) {
				list.add(convertJSONArrayToList((JSONArray) o));
			} else {
				list.add(o);
			}

		}

		return list;
	}

	/**
	 * 将Super Arraylist 转换为字符串
	 * 
	 * @param list
	 * @return
	 */
	public static String toJSON(SuperArrayList list) {
		if (list == null) {
			return "[]";
		}

		StringBuffer StrBuffer = new StringBuffer();

		// 起始
		StrBuffer.append("[");
		boolean first = true;

		for (Object o : list) {
			if (first) {
				first = false;
			} else {
				StrBuffer.append(",");
			}

			if (o instanceof SuperHashMap) {
				StrBuffer.append(toJSON((SuperHashMap) o));
			} else if (o instanceof SuperArrayList) {
				StrBuffer.append(toJSON((SuperArrayList) o));
			} else if (o instanceof String) {
				StrBuffer.append("\"" + o + "\"");
			} else {
				StrBuffer.append(o);
			}
		}

		StrBuffer.append("]");

		return StrBuffer.toString();
	}

	/**
	 * 将superhashmap 转换为字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String toJSON(SuperHashMap map) {
		if (map == null) {
			return "{}";
		}

		StringBuffer StrBuffer = new StringBuffer();

		// 起始
		StrBuffer.append("{");

		Iterator<String> keys = map.keySet().iterator();

		boolean first = true;

		while (keys.hasNext()) {
			if (first) {
				first = false;
			} else {
				StrBuffer.append(",");
			}

			String name = keys.next();

			StrBuffer.append("\"" + name + "\" : ");

			Object o = map.get(name);

			if (o instanceof SuperHashMap) {
				StrBuffer.append(toJSON((SuperHashMap) o));
			} else if (o instanceof SuperArrayList) {
				StrBuffer.append(toJSON((SuperArrayList) o));
			} else if (o instanceof String) {
				StrBuffer.append("\"" + o + "\"");
			} else {
				StrBuffer.append(o);
			}

		}

		StrBuffer.append("}");

		return StrBuffer.toString();
	}
}
