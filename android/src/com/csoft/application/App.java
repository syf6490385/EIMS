package com.csoft.application;

import com.csoft.util.T;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application
{
	static App instance;
	public String userid,lv,age,name,groupid,groupname;
	@Override
	public void onCreate()
	{
		super.onCreate();
		instance=this;
	}
	public static App getInstance()
	{
		return instance;
	}
	public void init()
	{
		SharedPreferences shap=getSharedPreferences("user", 0);
		userid=shap.getString("userid","");
		lv=shap.getString("lv", "");
		age=shap.getString("age","");
		name=shap.getString("name","");
		groupid=shap.getString("groupid","");
		groupname=shap.getString("groupname","");
	}

}
