package com.csoft.employeemanager.activity;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.csoft.employeemanager.R;

public class DailyLogActivity extends Activity 
{
    Date date;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_log);
		init();
		Toast.makeText(this,date.toLocaleString(), Toast.LENGTH_SHORT).show();
		
	}
	private void init()
	{
		date=(Date) getIntent().getSerializableExtra("date");
	}
}
