package com.csoft.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnKeyListener;
import android.widget.EditText;

import com.csoft.conn.Conn;
import com.csoft.dialog.LoadingDialog;
import com.csoft.employeemanager.R;
import com.csoft.model.Employee;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class LoginActivity extends Activity 
{
    EditText name,pwd;
    LoadingDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		init();
		SharedPreferences shap=getSharedPreferences("user",0);
		if(shap.getBoolean("islogin", false))
		{
			 Intent intent=new Intent();
			 intent.setClass(LoginActivity.this,MainActivity.class);
			 startActivity(intent);
			 LoginActivity.this.finish();
		}
	}
	private void init()
	{
		dialog=new LoadingDialog(this);
		name=(EditText) findViewById(R.id.login_name);
		pwd=(EditText) findViewById(R.id.login_pwd);
		pwd.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				if(keyCode==KeyEvent.KEYCODE_ENTER)
				login(null);
				return true;
			}});
	}
	public void login(View v)
	{
		dialog.show();
		final Employee emp=new Employee();
		emp.setName(name.getText().toString());
		emp.setPwd(pwd.getText().toString());
		Conn.login(emp,new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode,String result)
			{
				 dialog.dismiss();
				 if(!result.equals("0"))
				 {
					 //保存登录信息,进入main activity
				     try 
				     {
						 JSONObject obj=new JSONObject(result);
						 SharedPreferences shap=getSharedPreferences("user",0);
						 shap.edit().putString("name",emp.getName()).commit();
						 shap.edit().putString("pwd",emp.getPwd()).commit();
						 shap.edit().putBoolean("islogin",true).commit();
						 shap.edit().putString("userid",obj.optString("_id")).commit();
						 shap.edit().putString("lv",obj.optString("lv")).commit();
						 shap.edit().putString("age",obj.optString("age")).commit();
						 shap.edit().putString("groupid",obj.optString("groupid")).commit();
						 shap.edit().putString("groupname",obj.optString("groupname")).commit();
						 Intent intent=new Intent();
						 intent.setClass(LoginActivity.this,MainActivity.class);
						 startActivity(intent);
						 LoginActivity.this.finish();
					} 
				     catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				 }
				 else
				 {
					 T.show("验证失败");
				 }
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				dialog.dismiss();
				T.show("操作失败,错误信息:"+e.getLocalizedMessage());
		    }
		});
	}
}
