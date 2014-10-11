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
import com.csoft.model.Dailylog;
import com.csoft.model.Dayoff;

public class UnreplyDailylogAdapter extends BaseAdapter
{
	List<Dailylog> data;
	LayoutInflater lf;
	List<View> viewlist;
	public UnreplyDailylogAdapter(Context context,List<Dailylog> data)
	{
		lf=LayoutInflater.from(context);
		this.data=data;
		viewlist=new ArrayList<View>();
		for(int i=0;i<data.size();i++)
		{
			viewlist.add(lf.inflate(R.layout.item_dailylog,null));
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
		TextView name,content,selfrate,time;
		ImageView img;
		name=(TextView) v.findViewById(R.id.item_daily_name);
		content=(TextView) v.findViewById(R.id.item_daily_content);
		selfrate=(TextView) v.findViewById(R.id.item_daily_selfrate);
		time=(TextView) v.findViewById(R.id.item_daily_time);
		img=(ImageView) v.findViewById(R.id.item_daily_pic);
		Dailylog d=data.get(position);
		name.setText(d.getUsername());
		content.setText(d.getContent());
		selfrate.setText("×ÔÎÒÆÀ·Ö:"+d.getSelfrate());
		time.setText(d.getDate().toLocaleString());
		return v;
	}

}
