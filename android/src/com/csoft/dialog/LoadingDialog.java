package com.csoft.dialog;

import com.csoft.employeemanager.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.internal.widget.ProgressBarICS;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class LoadingDialog extends Dialog
{
    ProgressBar pb;
    Context context;
	public LoadingDialog(Context context) {
		super(context,R.style.dialog_Translucent_NoTitle);
		this.context=context;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		pb=new ProgressBar(context);
		this.setContentView(pb);
		setCanceledOnTouchOutside(true);
		
	}
	

}
