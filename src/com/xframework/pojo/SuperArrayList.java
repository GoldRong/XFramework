package com.xframework.pojo;

import java.util.ArrayList;

/**
 * Super ArrayList的实现类
 * @author 荣
 *
 */
public class SuperArrayList extends ArrayList<Object> {

	public String getString(int index){
		return (String) get(index);
	}
	public boolean getBoolean(int index){
		return (Boolean) get(index);
	}
	public int getInt(int index){
		return (Integer) get(index);
	}
	public float getFloat(int index){
		return (Float) get(index);
	}
	public double getDouble(int index){
		return (Double) get(index);
	}
	public SuperHashMap getSAFHashMap(int index){
		return (SuperHashMap) get(index);
	}
	public SuperArrayList getSAFArrayList(int index){
		return (SuperArrayList) get(index);
	}
	
}
