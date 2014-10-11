package com.csoft.helper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import com.csoft.employeemanager.R;
import com.csoft.swipemenulistview.SwipeMenu;
import com.csoft.swipemenulistview.SwipeMenuCreator;
import com.csoft.swipemenulistview.SwipeMenuItem;

public class EmployeeListCreator implements SwipeMenuCreator
{
	Context context;
    public EmployeeListCreator(Context context)
    {
    	this.context=context;
    }
	@Override
	public void create(SwipeMenu menu) 
	{
		SwipeMenuItem openItem = new SwipeMenuItem(context.getApplicationContext());
		openItem.setBackground(R.color.blue);
		openItem.setWidth(dp2px(90));
		openItem.setTitle("±à¼­");
		openItem.setTitleSize(18);
		openItem.setTitleColor(Color.WHITE);
		menu.addMenuItem(openItem);
		SwipeMenuItem deleteItem = new SwipeMenuItem(context.getApplicationContext());
		deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,0x3F, 0x25)));
		deleteItem.setWidth(dp2px(90));
		deleteItem.setTitle("É¾³ý");
		deleteItem.setTitleSize(18);
		deleteItem.setTitleColor(Color.WHITE);
		menu.addMenuItem(deleteItem);
	}
	private int dp2px(int dp) 
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}

}
