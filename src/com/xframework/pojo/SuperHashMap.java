package com.xframework.pojo;

import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import com.xframework.utils.FormatTools;

/**
 * Super map的实现类
 * @author 荣
 *
 */
public class SuperHashMap extends HashMap<String, Object> {

	public SuperHashMap(){}
		
	
	public static SuperHashMap fromJsonString(String jsonString){
		try {
			return FormatTools.convertJSONToMap(new JSONObject(jsonString));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getString(String key){
		return (String) get(key);
	}
	public boolean getBoolean(String key){
		return (Boolean) get(key);
	}
	public int getInt(String key){
		Object o = get(key);
		if(o instanceof Integer){
			return (Integer) o;
		}else{
			return Integer.parseInt(o.toString());
		}
	}
	public float getFloat(String key){
		return (Float) get(key);
	}
	public double getDouble(String key){
		Object o = get(key);
		if(o instanceof Double){
			return (Double) o;
		}else{
			return Double.parseDouble(o.toString());
		}
	}
	public SuperHashMap getSAFHashMap(String key){
		return (SuperHashMap) get(key);
	}
	public SuperArrayList getSAFArrayList(String key){
		return (SuperArrayList) get(key);
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
	public JSONObject toJSONObject(){
		
		try {
			return new JSONObject(FormatTools.toJSON(this));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
