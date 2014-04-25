package com.xframework.ui;

import com.xframework.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * * UI 工具组件
 * 
 * @author 荣
 * 
 */
public class SuperUI {

	/**
	 * 弹出一个等待dialog
	 * 
	 * @param context
	 * @return Alertdialog
	 */
	public static AlertDialog openWaitDialog(Context context, String title,
			String msg) {
		Builder builder = new Builder(context);
		LayoutInflater inflater = LayoutInflater.from(context);

		View view = inflater.inflate(R.layout.layout_openwaitdialog, null);
		TextView textview = (TextView) view.findViewById(R.id.item_openwaitdialog_text);
		textview.setText(msg);
		builder.setTitle(title);
		builder.setView(view);
		AlertDialog loadWaitDialog = builder.create();
		loadWaitDialog.setCanceledOnTouchOutside(false);
		loadWaitDialog.show();

		return loadWaitDialog;

	}

	/**
	 *  弹出一个带确认的dialog
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 */
	public static void openAlertDialog(Context context, String title,
			String msg, String okButton, OnClickListener ok) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage("\n" + msg + "\n");
		builder.setNegativeButton(okButton, ok);

		AlertDialog loadWaitDialog = builder.create();
		loadWaitDialog.setCanceledOnTouchOutside(false);
		loadWaitDialog.show();
	}

	/**
	 *  弹出一个带确认和取消的dialog
	 * 
	 * @param context
	 * @param title
	 * @param msg
	 */
	public static AlertDialog openConfirmDialog(Context context, String title,
			String msg, String okbutton, OnClickListener ok, String nobutton,
			OnClickListener no) {
		Builder builder = new Builder(context);
		builder.setTitle(title);
		builder.setMessage("\n" + msg + "\n");
		builder.setNegativeButton(okbutton, ok);
		builder.setNeutralButton(nobutton, no);

		AlertDialog loadWaitDialog = builder.create();
		loadWaitDialog.setCanceledOnTouchOutside(false);
		loadWaitDialog.show();
		return loadWaitDialog;
	}
}
