package com.xframework.http;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.xframework.http.CustomMultiPartEntity.ProgressListener;
import com.xframework.utils.ConfigTools;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class FileUpLoadAsyncTask extends
		AsyncTask<HttpResponse, Integer, Boolean> {
	private ProgressDialog pd;
	private long totalSize;
	private Context context;
	private ArrayList<File> files;
	private String url;

	public FileUpLoadAsyncTask(Context c, String url, ArrayList<File> files) {
		this.context = c;
		this.files = files;
		this.url = url;
	}

	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(context);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("正在上传图片...");
		pd.setCancelable(false);
		pd.show();
	}

	@Override
	protected Boolean doInBackground(HttpResponse... arg0) {
		// 初始化http
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 100 * 1000);
		HttpConnectionParams.setSoTimeout(httpParams, 100 * 1000);
		// 定义HttpClient对象
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpContext httpContext = new BasicHttpContext();
		HttpPost httpPost = new HttpPost(ConfigTools.getInstance()
				.getConfigModel().getServerAddress()
				+ url);

		try {
			CustomMultiPartEntity multipartContent = new CustomMultiPartEntity(
					new ProgressListener() {
						@Override
						public void transferred(long num) {
							publishProgress((int) ((num / (float) totalSize) * 100));
						}
					});

			for (File file : files) {
				multipartContent.addPart("uploaded_file", new FileBody(file));
				totalSize = multipartContent.getContentLength();
			}
			httpPost.setEntity(multipartContent);
			HttpResponse response = httpClient.execute(httpPost, httpContext);
			if (response.getStatusLine().getStatusCode() == 200) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		pd.setProgress((int) (progress[0]));
	}

	@Override
	protected void onPostExecute(Boolean result) {
		pd.dismiss();
		if (result) {
			Toast.makeText(context, "图片上传成功！", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(context, "图片上传失败！", Toast.LENGTH_SHORT).show();
		}
	}
}