package com.csoft.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.csoft.employeemanager.R;
import com.csoft.model.Dailylog;
import com.csoft.util.T;

public class DailyLogActivity extends Activity 
{
    Dailylog dailylog;
    TextView content,reply,replyname,time,replytime;
    RatingBar selfrb,replyrb;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_log);
		init();
		T.show(dailylog.getContent());
	}
	private void init()
	{
		dailylog=(Dailylog) getIntent().getSerializableExtra("dailylog");
		content=(TextView) findViewById(R.id.daily_content);
		reply=(TextView) findViewById(R.id.daily_replycontent);
		replyname=(TextView) findViewById(R.id.daily_replyname);
		selfrb=(RatingBar) findViewById(R.id.daily_selfrate);
		replyrb=(RatingBar) findViewById(R.id.daily_replyrate);
		time=(TextView) findViewById(R.id.daily_time);
		replytime=(TextView) findViewById(R.id.daily_replytime);
		
		
		content.setText(dailylog.getContent());
		selfrb.setRating(Float.parseFloat(dailylog.getSelfrate()));
		time.setText(dailylog.getDate().toLocaleString());
		if(dailylog.getIsreplyed().equals("1"))
		{
			reply.setText(dailylog.getReply());
			replyname.setText(dailylog.getReplyname());
			replyrb.setRating(Float.parseFloat(dailylog.getReplyrate()));
			replytime.setText(dailylog.getReplydate().toLocaleString());
			
		}
	}
}
