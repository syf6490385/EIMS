package com.csoft.dialog;


import java.util.regex.Pattern;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.csoft.conn.Conn;
import com.csoft.employeemanager.R;
import com.csoft.model.Employee;
import com.csoft.model.Group;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GroupEditDialog extends Dialog
{
    Group g;
    LoadingDialog dialog;
    Context context;
    EditText name;
	Button btn;
	onGroupDialogListener listener;
	boolean isCreate;
	public GroupEditDialog(Context context,onGroupDialogListener listener)
	{
		super(context);
		this.context=context;
		this.listener=listener;
		isCreate=true;
	}
	public GroupEditDialog(Context context,Group g,onGroupDialogListener listener) 
	{
		super(context);
		this.g=g;
		this.context=context;
		this.listener=listener;
		isCreate=false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_group_edit);
		dialog=new LoadingDialog(context);
		name=(EditText) findViewById(R.id.dialog_group_name);
		btn=(Button) findViewById(R.id.group_editgroupbtn);
		//
		if(!isCreate)
		{
		name.setText(g.getName());
		} else { g=new Group();}
		//
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if(!isLegal()) 
				{
					T.show("输入不合法");
					return;
				}
				if(!isCreate)
				{
					T.show("name"+g.getName()+"new:"+name.getText().toString());
				Conn.editGroup(g.getName(),name.getText().toString(),new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int statusCode,String result)
					{
						//fix this bug result json obj is not check
						GroupEditDialog.this.dismiss();
						listener.onEmployeeDialogClose(true);
					}
					@Override
				    public void onFailure(Throwable e,String result) 
					{	 
						GroupEditDialog.this.dismiss();
						listener.onEmployeeDialogClose(false);
						T.show("操作失败");
				    }
				});
				}
				else
				{
					Conn.addGroup(name.getText().toString(),new AsyncHttpResponseHandler(){
						@Override
						public void onSuccess(int statusCode,String result)
						{
							//fix this bug result json obj is not check
							GroupEditDialog.this.dismiss();
							listener.onEmployeeDialogClose(true);
						}
						@Override
					    public void onFailure(Throwable e,String result) 
						{	 
							GroupEditDialog.this.dismiss();
							listener.onEmployeeDialogClose(false);
							T.show("操作失败"+e.toString());
					    }
					});
				}
			}
		});
	}
    public interface onGroupDialogListener
    {
    	public void onEmployeeDialogClose(boolean isSucceed);
    }
    private boolean isLegal()
    {
    	if(name.getText().equals("")||name.getText()==null)return false;
    	return true;
    }
}

