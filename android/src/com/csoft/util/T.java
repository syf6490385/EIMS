package com.csoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

import android.util.Log;
import android.widget.Toast;
import com.csoft.application.App;

public class T 
{
	static String TAG="诚创员工管理";
	public static void log(String str)
	{
		Log.e(TAG,str);
	}
	public static void show(String str)
	{
		Context context=App.getInstance().getApplicationContext();
		Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
	}
	public static Date parseDate(String str)
	{
		 String format="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		 SimpleDateFormat sdf=new SimpleDateFormat(format,Locale.CHINA);
		 try 
		 {
			Date time=sdf.parse(str);
			T.log(time.toLocaleString());
			return time;
		 } 
		 catch (ParseException e) 
		 {
			T.show(e.toString());
			return new Date();
		 }
		 
	}

}
