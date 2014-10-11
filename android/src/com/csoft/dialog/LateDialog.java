package com.csoft.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.csoft.application.App;
import com.csoft.conn.Conn;
import com.csoft.employeemanager.R;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class LateDialog extends Dialog
{
    EditText reason;
    Button btn;
    LoadingDialog dialog;
    BootstrapButton signbtn;
	public LateDialog(Context context,BootstrapButton signbtn) 
	{
		super(context);
		dialog=new LoadingDialog(context);
		this.signbtn=signbtn;
	}
	@Override
	public void onCreate(Bundle b)
	{
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_latesign);
		reason=(EditText) findViewById(R.id.dialog_latereason);
		btn=(Button) findViewById(R.id.dialog_latesign_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				dialog.show();
				String userid=App.getInstance().userid;
				String r=reason.getText().toString();
				Conn.addlateSign(userid,r,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int statusCode,String result)
					{
						LateDialog.this.dismiss();
						dialog.dismiss();
						T.show("补充签到成功");
						signbtn.setText("已签到");
						signbtn.setEnabled(false);
					}
					@Override
				    public void onFailure(Throwable e,String result) 
					{
						dialog.dismiss();
						LateDialog.this.dismiss();
						T.show("补充签到失败"+e.toString());
				    }
				});
				
			}
		});
	}

}
