package com.csoft.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.csoft.employeemanager.R;
import com.csoft.model.Employee;

public class EmployeeAdapter extends BaseAdapter
{
	LayoutInflater lf;
	List<Employee> data;
	ViewHolder holder;
	public EmployeeAdapter(Context context,List<Employee> data)
	{
		lf=LayoutInflater.from(context);
		this.data=data;
	}
	@Override
	public int getCount() 
	{
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=lf.inflate(R.layout.item_employee,null);
			holder.lv=(TextView) convertView.findViewById(R.id.item_employee_lv);
			holder.name=(TextView) convertView.findViewById(R.id.item_employee_name);
			holder.pwd=(TextView) convertView.findViewById(R.id.item_employee_pwd);
			holder.group=(TextView) convertView.findViewById(R.id.item_employee_group);
			convertView.setTag(holder); 
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		Employee emp=data.get(data.size()-position-1);
		holder.lv.setText("¼¶±ð:"+String.valueOf(emp.getLv()));
		holder.name.setText(""+emp.getName());
		holder.pwd.setText("ÃÜÂë"+emp.getPwd());
		holder.group.setText(emp.getGroupname());
		return convertView;
	}
	static class ViewHolder
	{
		public TextView name,lv,pwd,group;
	}
	

}
