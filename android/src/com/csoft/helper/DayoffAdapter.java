package com.csoft.helper;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csoft.employeemanager.R;
import com.csoft.helper.EmployeeAdapter.ViewHolder;
import com.csoft.model.Dayoff;
import com.csoft.model.Employee;

public class DayoffAdapter extends BaseAdapter
{
    List<Dayoff> data;
    Context context;
    LayoutInflater lf;
    public DayoffAdapter(Context context,List<Dayoff> data)
    {
    	this.context=context;
    	this.lf=LayoutInflater.from(context);
    	this.data=data;
    }
	@Override
	public int getCount() 
	{
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
		ViewHolder holder;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=lf.inflate(R.layout.item_dayoff,null);
			holder.content=(TextView) convertView.findViewById(R.id.item_dayoffcontent);
			holder.uptime=(TextView) convertView.findViewById(R.id.item_dayoff_uptime);
			holder.length=(TextView) convertView.findViewById(R.id.item_dayofflength);
			holder.time=(TextView) convertView.findViewById(R.id.item_dayofftime);
			holder.pic=(ImageView) convertView.findViewById(R.id.item_dayoffpic);
			convertView.setTag(holder); 
		}
		else
		{
			holder=(ViewHolder)convertView.getTag();
		}
		Dayoff d=data.get(data.size()-position-1);
		holder.content.setText(d.getContent());
		holder.uptime.setText(d.getTime());
		holder.length.setText("请假时长:约"+d.getLength()+"小时");
		holder.time.setText(d.getDayofftime()+d.getLength());
		if(d.getResult().equals("0"))
		{
			holder.pic.setImageResource(R.drawable.why);
		}
		if(d.getResult().equals("1"))
		{
			holder.pic.setImageResource(R.drawable.right);
		}
		if(d.getResult().equals("2"))
		{
			holder.pic.setImageResource(R.drawable.wrong);
		}
		return convertView;
	}
	static class ViewHolder
	{
		public TextView content,time,length,uptime;
		public ImageView pic;
	}

}
