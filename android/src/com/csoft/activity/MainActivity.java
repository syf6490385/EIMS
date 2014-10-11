package com.csoft.activity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.csoft.application.App;
import com.csoft.calendarview.CalendarCellView;
import com.csoft.calendarview.CalendarPickerView;
import com.csoft.calendarview.CalendarPickerView.OnDateSelectedListener;
import com.csoft.calendarview.CalendarPickerView.SelectionMode;
import com.csoft.conn.Conn;
import com.csoft.dialog.LateDialog;
import com.csoft.dialog.LoadingDialog;
import com.csoft.employeemanager.R;
import com.csoft.json2obj.Parser;
import com.csoft.model.Dailylog;
import com.csoft.model.Dayoff;
import com.csoft.slidingmenu.SlidingMenu;
import com.csoft.util.T;
import com.csoft.webimageview.WebImageView;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class MainActivity extends ActionBarActivity 
{
	private CalendarPickerView calendar;
	private SlidingMenu menu;
	LoadingDialog dialog;
	TextView username,userlv,date;
	BootstrapButton signbtn;
	List<Dailylog> dailyData;
	List<Dayoff> dayoffData;
	WebImageView userpic;
	Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setTitleColor(getResources().getColor(android.R.color.holo_blue_bright));
        setTitle("诚创科技"); 
        initCalendar();
        initSlidingMenu();
        init();
        load();
    }
    private void load()
    {
    	dialog.show();
    	Calendar c=Calendar.getInstance();
    	String year=String.valueOf(c.get(Calendar.YEAR));
    	String month=String.valueOf(c.get(Calendar.MONTH)+1);
    	String userid=App.getInstance().userid;
    	Conn.getAllDailylog(year,month,userid,new AsyncHttpResponseHandler(){
    		@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 dailyData=Parser.parserDailylog(result);
				 setCalendarUI();
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				dialog.dismiss();
				T.show("failure"+e.getLocalizedMessage());
		    }
    	});
    	Conn.isSign(userid,new AsyncHttpResponseHandler(){
    		@Override
			public void onSuccess(int statusCode,String result)
			{
				 if(result.equals("1"))
				 {
					 signbtn.setEnabled(false);
					 signbtn.setText("已签到");
				 }
			}
    	});
    }
    private void setCalendarUI()
    {
    	for(int i=0;i<dailyData.size();i++)
    	{
    		Dailylog d=dailyData.get(i);
    		CalendarCellView c=calendar.getCellbyDay(d.getDayinMonth());
    			c.setBackgroundResource(R.drawable.right);
    	}
    	
    	
    }
    private void init()
    {
    	dialog=new LoadingDialog(this);
    	App app=App.getInstance();
    	app.init();
    	username=(TextView) findViewById(R.id.main_username);
    	userlv=(TextView) findViewById(R.id.main_userlv);
    	username.setText(app.name);
    	userlv.setText("级别:"+app.lv+"("+app.groupname+")");
    	signbtn=(BootstrapButton) findViewById(R.id.main_singup);
    	userpic=(WebImageView) findViewById(R.id.main_userpic);
    	userpic.loadImage(app.name);
    	context=this;
    	date=(TextView)findViewById(R.id.main_date);
    	Calendar c=Calendar.getInstance();
    	Date d=c.getTime();
    	date.setText((d.getYear()+1900)+"年"+(d.getMonth()+1)+"月"+d.getDate()+"日");
    }
    private void initSlidingMenu()
    {
    	menu=new SlidingMenu(this);
    	menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffset(550);
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
        lastCalendar.set(Calendar.DAY_OF_MONTH,32);
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        calendar.init(firstCalendar.getTime(),lastCalendar.getTime()) //
            .inMode(SelectionMode.SINGLE)//
         .withSelectedDate(new Date());
        calendar.setOnDateSelectedListener(new OnDateSelectedListener(){
			@Override
			public void onDateSelected(Date date) 
			{
				int day=date.getDate();
				Dailylog d=findDailylogbyDay(day);
				if(d!=null)
				{
					Intent intent=new Intent();
					intent.setClass(MainActivity.this,DailyLogActivity.class);
					Bundle bundle=new Bundle();
					bundle.putSerializable("dailylog",d);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else
				{
					T.show("尚无工作日志");
				}
			}
			@Override
			public void onDateUnselected(Date date) {}	
        });
    }
    //btn listener
    public void replyAll(View v)
    {
    	if(App.getInstance().lv.equals("1")) 
        {
    	  T.show("权限不足");
    	  return;
    	}
    	Intent intent=new Intent();
    	intent.setClass(this,UnreplyActivity.class);
    	startActivity(intent);
    }
    public void dayoff(View v)
    {
    	Intent intent=new Intent(MainActivity.this,DayoffActivity.class);
		startActivity(intent);
    }
    public void dailylog(View v)
    {
    	Intent intent=new Intent(MainActivity.this,AddDailyActivity.class);
		startActivity(intent);
    }
    public void signup(View v)
    {
    	Date date=Calendar.getInstance().getTime();
    	int h=date.getHours();
    	int m=date.getMinutes();
    	if(h==8&&m>30||h>8)
    	{
    		LateDialog dialog=new LateDialog(context,signbtn);
    		dialog.show();
    	}
    	else
    	{
    	dialog.show();
    	Conn.addSign(App.getInstance().userid,new AsyncHttpResponseHandler(){
    		@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 T.show("签到成功");
				 signbtn.setEnabled(false);
				 signbtn.setText("已签到");
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				dialog.dismiss();
				T.show("failure"+e.getLocalizedMessage());
		    }
    	});
    	}
    }
    public void employeeManager(View v)
    {
    	Intent intent=new Intent();
    	intent.setClass(this,EmployeeManagerActivity.class);
    	startActivity(intent);
    }
    public void groupManager(View v)
    {
    	Intent intent=new Intent();
    	intent.setClass(this,GroupActivity.class);
    	startActivity(intent);
    }
    public void about(View v)
    {
    	T.show("by syf6490385");
    }
    public void logout(View v)
    {
    	SharedPreferences shap=getSharedPreferences("user",0);
    	shap.edit().putBoolean("islogin",false).commit();
    	this.finish();
    	Intent intent=new Intent();
    	intent.setClass(this,LoginActivity.class);
    	startActivity(intent);
    }
    
    //------------------------------------------------
    private Dailylog findDailylogbyDay(int day)
    {
    	for(int i=0;i<dailyData.size();i++)
    	{
    		Dailylog d=dailyData.get(i);
    		if(d.getDate().getDate()==day) return d;
    	}
    	return null;
    }

 
}
