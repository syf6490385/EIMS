package com.csoft.dialog;


import java.util.regex.Pattern;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csoft.employeemanager.R;
import com.csoft.employeemanager.conn.Conn;
import com.csoft.model.Employee;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class EmployeeEditDialog extends Dialog
{
    Employee emp;
    LoadingDialog dialog;
    Context context;
    EditText name;
	EditText lv;
	EditText pwd;
	EditText pwd_confirm;
	EditText age;
	Button btn;
	onEmployeeDialogListener listener;
	boolean isCreate;
	public EmployeeEditDialog(Context context,onEmployeeDialogListener listener)
	{
		super(context);
		this.context=context;
		this.listener=listener;
		isCreate=true;
	}
	public EmployeeEditDialog(Context context,Employee emp,onEmployeeDialogListener listener) 
	{
		super(context);
		this.emp=emp;
		this.context=context;
		this.listener=listener;
		isCreate=false;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.dialog_employee_edit);
		dialog=new LoadingDialog(context);
		name=(EditText) findViewById(R.id.emp_name);
		lv=(EditText) findViewById(R.id.emp_lv);
		pwd=(EditText) findViewById(R.id.emp_pwd);
		pwd_confirm=(EditText) findViewById(R.id.emp_pwd_confirm);
		age=(EditText) findViewById(R.id.emp_age);
		btn=(Button) findViewById(R.id.dialog_employee_confirm);
		//
		if(!isCreate)
		{
		name.setText(emp.getName());
		age.setText(String.valueOf(emp.getAge()));
		lv.setText(String.valueOf(emp.getLv()));
		pwd.setHint("请输入新密码");
		pwd_confirm.setHint("请确认新密码");
		pwd.setText(emp.getPwd());
		pwd_confirm.setText(emp.getPwd());
		} else { emp=new Employee();}
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
				emp.setName(name.getText().toString());
				emp.setLv(Integer.parseInt(lv.getText().toString()));
				emp.setAge(Integer.parseInt(age.getText().toString()));
				emp.setPwd(pwd.getText().toString());
				if(!isCreate)
				{
				Conn.editEmployee(emp,new AsyncHttpResponseHandler(){
					@Override
					public void onSuccess(int statusCode,String result)
					{
						//fix this bug result json obj is not check
						EmployeeEditDialog.this.dismiss();
						listener.onEmployeeDialogClose(true);
					}
					@Override
				    public void onFailure(Throwable e,String result) 
					{	 
						EmployeeEditDialog.this.dismiss();
						listener.onEmployeeDialogClose(false);
						T.show("操作失败");
				    }
				});
				}
				else
				{
					Conn.addEmployee(emp,new AsyncHttpResponseHandler(){
						@Override
						public void onSuccess(int statusCode,String result)
						{
							//fix this bug result json obj is not check
							EmployeeEditDialog.this.dismiss();
							listener.onEmployeeDialogClose(true);
						}
						@Override
					    public void onFailure(Throwable e,String result) 
						{	 
							EmployeeEditDialog.this.dismiss();
							listener.onEmployeeDialogClose(false);
							T.show("操作失败"+e.toString());
					    }
					});
				}
			}
		});
	}
    public interface onEmployeeDialogListener
    {
    	public void onEmployeeDialogClose(boolean isSucceed);
    }
    private boolean isLegal()
    {
    	if(name.getText().equals(""))return false;
    	if(age.getText().equals(""))return false;
    	if(lv.getText().equals(""))return false;
    	if(pwd.getText().equals(""))return false;
    	if(pwd_confirm.getText().equals(""))return false;
    	if(!pwd.getText().toString().equals(pwd_confirm.getText().toString())) return false;
    	if(!isNum(lv.getText().toString()))return false;
    	if(!isNum(age.getText().toString()))return false;
    	return true;
    }
    private boolean isNum(String str)
    {
    	Pattern pattern = Pattern.compile("[0-9]*"); 
        return pattern.matcher(str).matches();    
    }
}

