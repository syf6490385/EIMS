package com.csoft.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mirko.android.datetimepicker.date.DatePickerDialog;
import mirko.android.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import mirko.android.datetimepicker.time.RadialPickerLayout;
import mirko.android.datetimepicker.time.TimePickerDialog;
import mirko.android.datetimepicker.time.TimePickerDialog.OnTimeSetListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.dialog.LoadingDialog;
import com.csoft.employeemanager.R;
import com.csoft.helper.DayoffAdapter;
import com.csoft.json2obj.Parser;
import com.csoft.model.Dayoff;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class DayoffActivity extends Activity 
{
    EditText content;
    TextView length;
    ListView lv;
    LoadingDialog dialog;
    BootstrapButton startTime,endTime,starth,endh;
    int startyear=0,startMonth=0,startDay=0;
    int endyear=0,endMonth=0,endDay=0;
    int startH,startM,endH,endM;
    List<Dayoff> data;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dayoff);
		init();
		load();
	}
	private void load()
	{
		dialog.show();
		Conn.getAllDayoff(App.getInstance().userid,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 data=Parser.parserDayoff(result);
				 lv.setAdapter(new DayoffAdapter(DayoffActivity.this,data));
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				dialog.dismiss();
				T.show("failure"+e.getLocalizedMessage());
		    }
		});
	}
	private void init()
	{
		startTime=(BootstrapButton) findViewById(R.id.dayoff_starttime);
		endTime=(BootstrapButton) findViewById(R.id.dayoff_endtime);
		content=(EditText) findViewById(R.id.dialog_group_name);
		length=(TextView) findViewById(R.id.dayoff_length);
		lv=(ListView) findViewById(R.id.dayoff_listview);
		starth=(BootstrapButton) findViewById(R.id.dayoff_starthour);
		endh=(BootstrapButton) findViewById(R.id.dayoff_endhour);
		dialog=new LoadingDialog(this);
	}
	
	//btn listener
	public void startHour(final View v)
	{
		TimePickerDialog timedialog=TimePickerDialog.newInstance(new OnTimeSetListener(){

			@Override
			public void onTimeSet(RadialPickerLayout view, int hourOfDay,
					int minute) {
				BootstrapButton b=(BootstrapButton) v;
				startH=hourOfDay;
				startM=minute;
				b.setText(hourOfDay+"时"+minute+"分");
				
			}},12,0,true);
		timedialog.show(getFragmentManager(),"timepicker");
	}
	public void endHour(final View v)
	{
		TimePickerDialog timedialog=TimePickerDialog.newInstance(new OnTimeSetListener(){

			@Override
			public void onTimeSet(RadialPickerLayout view, int hourOfDay,
					int minute) {
				BootstrapButton b=(BootstrapButton) v;
				endH=hourOfDay;
				endM=minute;
				b.setText(hourOfDay+"时"+minute+"分");
				
			}},12,0,true);
		timedialog.show(getFragmentManager(),"timepicker");
	}
	public void start_click(View v)
	{
		Calendar mCalendar = Calendar.getInstance();
		final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new OnDateSetListener() {
			public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				startyear=year;
                startMonth=month;
                startDay=day;
				startTime.setText(year+"年"+(month+1)+"月"+day+"日");
				length.setText("约"+getLength()+"时");
			}
		}, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show(getFragmentManager(),"datepicker");
	}
	public void end_click(View v)
	{
		Calendar mCalendar = Calendar.getInstance();
		final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(new OnDateSetListener() {
			public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) 
			{
				
                   endyear=year;
                   endMonth=month;
                   endDay=day;
				   endTime.setText(year+"年"+(month+1)+"月"+day+"日");
				   length.setText("约"+getLength()+"小时");
			}
		}, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show(getFragmentManager(),"datepicker");
	}
	public void dayoff(View v)
	{
		dialog.show();
		if(getLength()<=0) {T.show("输入错误");return;}
		String userid=App.getInstance().userid;
		String d_content=content.getText().toString();
		String d_length=""+getLength();//请假时长 以小时计算
		String dayofftime="";//请假起始时间
		Date startDate;
		Calendar c=Calendar.getInstance();
		c.set(startyear,startMonth, startDay, startH, startM);
		startDate=c.getTime();
		dayofftime=startDate.toLocaleString();
		Conn.addDayoff(userid,d_content,d_length,dayofftime,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 T.show("成功");
				 load();
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				dialog.dismiss();
				T.show("failure"+e.getLocalizedMessage());
		    }
		});
		
	}
	private int getLength()
	{
		Date startDate,endDate;
		Calendar c=Calendar.getInstance();
		c.set(startyear,startMonth, startDay, startH, startM);
		startDate=c.getTime();
		c.clear();
		c.set(endyear,endMonth,endDay,endH,endM);
		endDate=c.getTime();
		T.show(startDate.toLocaleString()+","+endDate.toLocaleString());
		
			 long betweenTime =endDate.getTime()-startDate.getTime(); 
             float result=betweenTime/1000.0f/60.0f/60.0f; 
             return Math.round(result);
	}
}




