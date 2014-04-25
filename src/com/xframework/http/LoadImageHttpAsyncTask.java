package com.xframework.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.xframework.cache.ACache;

/**
 * 异步任务下载图片
 * 
 * @param图片的url
 * @param ImageView图片对象
 * @author Administrator
 * 
 */
public class LoadImageHttpAsyncTask extends AsyncTask<String, Void, Bitmap> {

	private Context context;
	private ImageView iv = null;
	private String url = null;
	private ACache aCache = null;

	public LoadImageHttpAsyncTask(Context c, String url, ImageView iv) {
		this.context = c;
		this.url = url;
		this.iv = iv;
	}

	@Override
	protected void onPreExecute() {
		aCache = ACache.get(context);
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		SuperHttp superHttp = SuperHttp.getInstance();
		Bitmap bitmap = superHttp.post4Bitmap(url);
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if (result != null) {
			iv.setImageBitmap(result);
			aCache.put(url, result);
		} else {
			Log.i("LoadImageHttpAsyncTask", "downLoadImageError!");
		}

	}

}
