package com.csoft.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.csoft.conn.Conn;
import com.csoft.dialog.EmployeeEditDialog;
import com.csoft.dialog.GroupEditDialog;
import com.csoft.dialog.LoadingDialog;
import com.csoft.dialog.EmployeeEditDialog.onEmployeeDialogListener;
import com.csoft.employeemanager.R;
import com.csoft.helper.EmployeeAdapter;
import com.csoft.helper.EmployeeListCreator;
import com.csoft.helper.GroupAdapter;
import com.csoft.json2obj.Parser;
import com.csoft.model.Employee;
import com.csoft.model.Group;
import com.csoft.swipemenulistview.SwipeMenu;
import com.csoft.swipemenulistview.SwipeMenuListView;
import com.csoft.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.csoft.util.T;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class GroupActivity extends Activity 
{
	SwipeMenuListView listview;
	Context context;
	LoadingDialog loaddialog;
	GroupEditDialog editdialog;
	List<Group> data;
	GroupAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		init();
		load();
	}
	private void init()
	{
		context=this;
		listview=(SwipeMenuListView) findViewById(R.id.group_swipmenulistview);
		loaddialog=new LoadingDialog(context);
		listview.setMenuCreator(new EmployeeListCreator(context));
		listview.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				switch(index)
				{
				case 0:		
					editdialog=new GroupEditDialog(context,data.get(data.size()-position-1),new GroupEditDialog.onGroupDialogListener() {
						
						@Override
						public void onEmployeeDialogClose(boolean isSucceed) {
							load();
							
						}
					});
					editdialog.show();
                    break;
				case 1:
					loaddialog.show();
					Conn.delGroup(data.get(data.size()-position-1).getName(),new AsyncHttpResponseHandler(){
						@Override
						public void onSuccess(int statusCode,String result)
						{
							load();
						}
						@Override
					    public void onFailure(Throwable e,String result) 
						{
							loaddialog.dismiss();
							T.show("failure"+e.getLocalizedMessage());
					    }
					});
					break;
				}
			}});
	}
	private void load()
	{
		loaddialog.show();
		Conn.listGroup(new AsyncHttpResponseHandler()
		{
			@Override
			public void onSuccess(int statusCode,String result)
			{
				loaddialog.dismiss();
				data=Parser.parseGroup(result);
				adapter=new GroupAdapter(context,data);
				listview.setAdapter(adapter);
			}
			@Override
		    public void onFailure(Throwable e,String result) 
			{
				loaddialog.dismiss();
				T.show("²Ù×÷Ê§°Ü,´íÎóÐÅÏ¢:"+e.getLocalizedMessage());
		    }
		});
	}
	//btn listener
	public void addGroup(View v)
	{
		editdialog=new GroupEditDialog(context,new GroupEditDialog.onGroupDialogListener() {
			
			@Override
			public void onEmployeeDialogClose(boolean isSucceed) {
				load();
				
			}
		});
		editdialog.show();
	}

	
}
