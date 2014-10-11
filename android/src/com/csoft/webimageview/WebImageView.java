package com.csoft.webimageview;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import org.apache.http.Header;

import com.csoft.conn.Conn;
import com.csoft.employeemanager.R;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class WebImageView extends ImageView
{
	String name;
	public WebImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public WebImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public WebImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public void setImageName(String name)
	{
		this.name=name;
	}
	public void loadImage(String name)
	{
		setImageName(name);
		loadImage();
	}
	public void loadImage()
	{
		Conn.getImage(name,new BinaryHttpResponseHandler(){
			@Override
			public void onSuccess(byte[] bytes)
			{
				InputStream input = new ByteArrayInputStream(bytes); 
				Bitmap bitmap = BitmapFactory.decodeStream(input);
				WebImageView.this.setImageBitmap(bitmap);
			}
			@Override
			public void onFailure(int statusCode,Header[] headers, byte[] errorResponse, Throwable e)
			{
				WebImageView.this.setImageResource(R.drawable.wrong);
			}
		});
	}
	
	

}
