package com.csoft.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.dialog.LoadingDialog;
import com.csoft.dialog.ReplyDailylogDialog;
import com.csoft.dialog.ReplyDayoffDialog;
import com.csoft.employeemanager.R;
import com.csoft.helper.UnreplyDailylogAdapter;
import com.csoft.helper.UnreplyDayoffAdapter;
import com.csoft.json2obj.Parser;
import com.csoft.model.Dailylog;
import com.csoft.model.Dayoff;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class UnreplyActivity extends Activity 
{
    LoadingDialog dialog;
    ListView lv;
    List<Dayoff> dayoffData;
    List<Dailylog> dailylogData;
    UnreplyDailylogAdapter dailylogAdapter;
    UnreplyDayoffAdapter dayoffAdapter;
    Context context;
    ReplyDailylogDialog dailyDialog;
    ReplyDayoffDialog dayoffDialog;
    String groupid;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unreply);
		init();
		load();
	}
	public void load()
	{
		dialog.show();
		Conn.getunReplyDailylog(groupid,new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(int code,String result)
			{
				dailylogData=Parser.parserDailylog(result);
				Conn.getunReplyDayoff(groupid,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int code,String result)
					{
					 dayoffData=Parser.parserDayoff(result);
					 dailylogAdapter=new UnreplyDailylogAdapter(context,dailylogData);
					 dayoffAdapter=new UnreplyDayoffAdapter(context,dayoffData);
					 dialog.dismiss();
					 unreadDailylog(null);
					}
				});
			}
		});
	}
	private void init()
	{
		context=this;
		dialog=new LoadingDialog(this);
		lv=(ListView) findViewById(R.id.unreply_listview);
		groupid=App.getInstance().groupid;
		TextView title=(TextView) findViewById(R.id.unreply_title);
		title.setText(App.getInstance().groupname);
	}
    //listener
	public void unreadDailylog(View v)
	{
		lv.setAdapter(dailylogAdapter);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				dailyDialog=new ReplyDailylogDialog(context,dailylogData.get(arg2));
				dailyDialog.show();
				
			}});
	}
	public void unreadDayoff(View v)
	{
		lv.setAdapter(dayoffAdapter);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				dayoffDialog=new ReplyDayoffDialog(context,dayoffData.get(arg2));
				dayoffDialog.show();
				
			}});
	}
	
}
