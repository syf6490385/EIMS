package com.csoft.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.csoft.activity.UnreplyActivity;
import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.employeemanager.R;
import com.csoft.model.Dailylog;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ReplyDailylogDialog extends Dialog
{
    EditText reply;
    RatingBar rb;
    Button btn;
    Dailylog d;
    LoadingDialog dialog;
    UnreplyActivity activity;
	public ReplyDailylogDialog(Context context,Dailylog d) 
	{
		super(context);
		this.d=d;
		dialog=new LoadingDialog(context);
		activity=(UnreplyActivity) context;
	}
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_replydailylog);
		reply=(EditText) findViewById(R.id.dialog_daily_reply);
		rb=(RatingBar) findViewById(R.id.dialog_daily_rate);
		btn=(Button) findViewById(R.id.dialog_daily_btn);
		btn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				dialog.show();
				String dailylogid=d.getId();
				String content=reply.getText().toString();
				String userid=App.getInstance().userid;
				String rate=String.valueOf(rb.getRating());
				Conn.replyDailylog(dailylogid,userid,rate,content,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int code,String result)
					{
						ReplyDailylogDialog.this.dismiss();
						dialog.dismiss();
						activity.load();
						T.show("»Ø¸´³É¹¦");
					}
				});
				
			}});
	}

}
