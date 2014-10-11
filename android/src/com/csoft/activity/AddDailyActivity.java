package com.csoft.activity;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.dialog.LoadingDialog;
import com.csoft.employeemanager.R;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AddDailyActivity extends Activity 
{
    EditText content;
    RatingBar rb;
    LoadingDialog dialog;
    TextView time;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_daily);
		init();
	}
	private void init()
	{
		content=(EditText) findViewById(R.id.dailylog_add_content);
		rb=(RatingBar) findViewById(R.id.dailylog_add_selfrate);
		dialog=new LoadingDialog(this);
		time=(TextView) findViewById(R.id.adddaily_time);
		time.setText("提交日志时间:"+Calendar.getInstance().getTime().toLocaleString());
	}
	public void dailylog(View v)
	{
		dialog.show();
		String userid=App.getInstance().userid;
		String c=content.getText().toString();
		String rate=String.valueOf(rb.getRating());
		Conn.addDailylog(userid,c,rate,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 T.show("成功");
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
