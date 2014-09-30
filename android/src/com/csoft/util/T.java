package com.csoft.util;

import android.content.Context;
import android.widget.Toast;

import com.avos.avoscloud.LogUtil.log;
import com.csoft.application.App;

public class T 
{
	static String TAG="诚创员工管理";
	public static void log(String str)
	{
		log.e(TAG,str);
	}
	public static void show(String str)
	{
		Context context=App.getInstance().getApplicationContext();
		Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
	}

}
