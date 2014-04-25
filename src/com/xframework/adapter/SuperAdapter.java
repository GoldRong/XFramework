package com.xframework.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * adapter基类
 * @author panshihao
 *
 */
public abstract class SuperAdapter<Item> extends BaseAdapter {

	private Map<Integer, View> viewCache = new HashMap<Integer, View>();
	private List<Item> data;
	private Context context;
	private LayoutInflater inflater;
	
	public SuperAdapter(Context context, List<Item> data){
		this.context = context;
		this.data = data;
		if(getContext() != null){
			this.inflater = LayoutInflater.from(getContext());
		}
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public abstract View getView(int arg0, View arg1, ViewGroup arg2);


	public Map<Integer, View> getViewCache() {
		return viewCache;
	}

	public List<Item> getData() {
		return data;
	}

	public Context getContext() {
		return context;
	}
	/**
	 * 设置data,覆盖原有数据
	 * @param data
	 */
	public void setData(List<Item> data){
		this.data = data;
		this.viewCache.clear();
	}
	/**
	 * 添加 data,添加data
	 * @param data
	 */
	public void addData(List<Item> data){
		this.data.addAll(data);
	}
	/**
	 * 添加data
	 * @param item
	 */
	public void addData(Item item){
		this.data.add(item);
	}


	public LayoutInflater getInflater() {
		return inflater;
	}
	/**
	 * 缓存
	 */
	public void clearCache(){
		this.viewCache.clear();
		this.notifyDataSetChanged();
	}
	/**
	 * 清除date数据
	 */
	public void clearData(){
		this.data.clear();
		clearCache();
		
	}
	
}
