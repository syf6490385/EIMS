package com.csoft.application;

import android.app.Application;

public class App extends Application
{
	static App instance;
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

}
