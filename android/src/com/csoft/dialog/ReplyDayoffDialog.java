package com.csoft.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.csoft.activity.UnreplyActivity;
import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.employeemanager.R;
import com.csoft.model.Dayoff;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ReplyDayoffDialog extends Dialog
{
    EditText reply;
    RadioGroup rg;
    Button btn;
    LoadingDialog dialog;
    Dayoff d;
    UnreplyActivity activity;
	public ReplyDayoffDialog(Context context,Dayoff d) 
	{
		super(context);
		dialog=new LoadingDialog(context);
		this.d=d;
		activity=(UnreplyActivity) context;
	}
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_replydayoff);
		reply=(EditText) findViewById(R.id.dialog_dayoff_reply);
		rg=(RadioGroup) findViewById(R.id.dialog_dayoff_group);
		btn=(Button) findViewById(R.id.dialog_dayoff_btn);
		btn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				dialog.show();
				String dayoffid=d.getId();
				String content=reply.getText().toString();
				String result;
				String userid=App.getInstance().userid;
				if(rg.getCheckedRadioButtonId()==R.id.radioButton1)
				result="1";
				else
				result="0";
				Conn.replyDayoff(dayoffid, content, result, userid,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int code,String result)
					{
						ReplyDayoffDialog.this.dismiss();
						dialog.dismiss();
						activity.load();
						T.show("»Ø¸´³É¹¦");
					}
				});
				
			}});
	}

}
