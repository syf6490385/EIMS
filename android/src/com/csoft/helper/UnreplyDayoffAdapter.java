package com.csoft.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csoft.employeemanager.R;
import com.csoft.model.Dayoff;

public class UnreplyDayoffAdapter extends BaseAdapter
{
	List<Dayoff> data;
	LayoutInflater lf;
	List<View> viewlist;
	public UnreplyDayoffAdapter(Context context,List<Dayoff> data)
	{
		lf=LayoutInflater.from(context);
		this.data=data;
		viewlist=new ArrayList<View>();
		for(int i=0;i<data.size();i++)
		{
			viewlist.add(lf.inflate(R.layout.item_unread_dayoff,null));
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View v=viewlist.get(position);
		TextView name,content,length,time;
		ImageView img;
		name=(TextView) v.findViewById(R.id.item_unread_dayoff_name);
		content=(TextView) v.findViewById(R.id.item_unread_dayoff_content);
		length=(TextView) v.findViewById(R.id.item_unread_dayoff_dayofftime);
		time=(TextView) v.findViewById(R.id.item_unread_dayoff_time);
		img=(ImageView) v.findViewById(R.id.item_unread_dayoff_pic);
		Dayoff d=data.get(position);
		name.setText(d.getUsername());
		content.setText(d.getContent());
		length.setText(d.getDayofftime()+","+d.getLength());
		time.setText(d.getDate().toLocaleString());
		return v;
	}

}
