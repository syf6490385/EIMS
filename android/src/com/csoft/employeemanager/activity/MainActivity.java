package com.csoft.employeemanager.activity;

import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.csoft.calendarview.CalendarPickerView;
import com.csoft.calendarview.CalendarPickerView.OnDateSelectedListener;
import com.csoft.calendarview.CalendarPickerView.SelectionMode;
import com.csoft.employeemanager.R;
import com.csoft.slidingmenu.SlidingMenu;


public class MainActivity extends ActionBarActivity 
{
	private CalendarPickerView calendar;
	private SlidingMenu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCalendar();
        initSlidingMenu();
    }
    private void initSlidingMenu()
    {
    	menu=new SlidingMenu(this);
    	menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffset(600);
        menu.setFadeDegree(0.35f);
		menu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_WINDOW);
		menu.setMenu(R.layout.slidingmenumain);
		menu.setSlidingEnabled(true);
		menu.setFadeEnabled(true);
		menu.setFadeDegree(0.8f);
		menu.setShadowWidth(100);
    }
    private void initCalendar()
    {
    	final Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.set(Calendar.DAY_OF_MONTH, 1);
        final Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.set(Calendar.DAY_OF_MONTH,31);
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        calendar.init(firstCalendar.getTime(),lastCalendar.getTime()) //
            .inMode(SelectionMode.SINGLE)//
         .withSelectedDate(new Date());
        calendar.setOnDateSelectedListener(new OnDateSelectedListener(){

			@Override
			public void onDateSelected(Date date) 
			{
				Intent intent=new Intent(MainActivity.this,DailyLogActivity.class);
				Bundle bundle=new Bundle();
				bundle.putSerializable("date",date);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			@Override
			public void onDateUnselected(Date date) {}	
        });   
    }
    //btn listener
    public void dayoff(View v)
    {
    	Intent intent=new Intent(MainActivity.this,DayoffActivity.class);
		startActivity(intent);
    }
    public void signup(View v)
    {
    	Toast.makeText(this,"Ç©µ½°´Å¥",Toast.LENGTH_SHORT).show();
    }
    public void employeeManager(View v)
    {
    	Intent intent=new Intent();
    	intent.setClass(this,EmployeeManagerActivity.class);
    	startActivity(intent);
    }
    public void logout(View v)
    {
    	SharedPreferences shap=getSharedPreferences("user",0);
    	shap.edit().putBoolean("islogin",false).commit();
    	this.finish();
    }
    
    //------------------------------------------------
    

 
}
