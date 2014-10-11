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
import com.csoft.model.Group;

public class GroupAdapter extends BaseAdapter
{
	LayoutInflater lf;
	List<Group> data;
	ViewHolder holder;
	public GroupAdapter(Context context,List<Group> data)
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
			convertView=lf.inflate(R.layout.item_group,null);
			holder.name=(TextView) convertView.findViewById(R.id.main_date);
			convertView.setTag(holder); 
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		Group g=data.get(data.size()-position-1);
		holder.name.setText(""+g.getName());
		return convertView;
	}
	static class ViewHolder
	{
		public TextView name;
	}
	

}
