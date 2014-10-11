package com.csoft.conn;

import java.io.BufferedReader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

import android.os.Environment;


public class BaseConn 
{
	 //这是基础http连接工具类,注释部分是一个post参数传递的例子
	/* parameters e.g:
	 * List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
     * postParameters.add(new BasicNameValuePair("method","login"));
     *  postParameters.add(new BasicNameValuePair("username","admin"));
        postParameters.add(new BasicNameValuePair("pwd","admin"));
     */
	static int Connection_TimeOut=3000;
	public static String post(String url,List<NameValuePair>postParameters)
	{
		String result="";//这是获得的结果string
		HttpClient client=new DefaultHttpClient();//建立一个client端
		
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,Connection_TimeOut); //连接服务器超时
		client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,Connection_TimeOut); //服务器响应超时
	    HttpPost request=new HttpPost(url);//建立一个request请求
	    request.addHeader("Content-type", "application/x-www-form-urlencoded");//http头
        try
        { 
        if(postParameters!=null)request.setEntity(new UrlEncodedFormEntity(postParameters));//设置请求正文
        HttpResponse response;//这是一个应答类
        response = client.execute(request);//执行请求
        //读取应答的结果
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        line = in.readLine();//流读取
        while(line!=null)
        {
        	//通过循环将读取到的结果进行拼接成最终结果
            result=result+line;
            line=in.readLine();
        }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return null;
        }
        //返回应答信息供解密实例化
		try {
			return new String(result.getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	//
	public static String get(String url,List<NameValuePair>postParameters)
	{
		url=url+"?";
		for(int i=0;i<postParameters.size();i++)
		{
			url=url+postParameters.get(i).getName()+"="+postParameters.get(i).getValue();
			if(i!=postParameters.size()-1) url=url+"&";
		}
		String result=post(url,null);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	public static File downloadbyPost(String url,List<NameValuePair>postParameters,String path,String filename)
	{
		  Double max,current=0d;
		 HttpClient client=new DefaultHttpClient();
		 client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,Connection_TimeOut); //连接服务器超时
	     client.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT,Connection_TimeOut); //服务器响应超时
		 HttpPost request=new HttpPost(url);
	     try
	     {
	     if(postParameters!=null)
	     {
	     request.setEntity(new UrlEncodedFormEntity(postParameters));
	     }
	     request.addHeader("Content-type", "application/x-www-form-urlencoded");
	     HttpResponse response;
	     response = client.execute(request);
	     max=(double) response.getEntity().getContentLength();
	     //Log.v("download","we got file size is"+response.getEntity().getContentLength());
	     InputStream is=response.getEntity().getContent(); 
	     java.io.File temp=new java.io.File(Environment.getExternalStorageDirectory().getPath()+path);
	     temp.mkdirs();
	     FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+path+"/"+filename);
	     byte[] buf=new byte[128];
	     int numread = is.read(buf);
	     while(numread!=-1)
	     {
	    	 fos.write(buf, 0, numread);
	    	 numread=is.read(buf);
	    	 current+=128;
	     }
	     is.close();
	     fos.close();
	     }
	     catch(Exception e)
	     {
	    	e.printStackTrace(); 
	     }
	     return new File(Environment.getExternalStorageDirectory().getPath()+path+"/"+filename);
	 }	
	public static File downloadByURL(String urlstr,String path,String filename) throws Exception
	{
		 java.io.File temp=new java.io.File(Environment.getExternalStorageDirectory().getPath()+path);
	     temp.mkdirs();
		java.net.URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(Connection_TimeOut);
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+path+"/"+filename);
	     byte[] buf=new byte[128];
	     int numread = is.read(buf);
	     while(numread!=-1)
	     {
	    	 fos.write(buf, 0, numread);
	    	 numread=is.read(buf);
	     }
	     is.close();
	     fos.close();
		return new File(Environment.getExternalStorageDirectory().getPath()+path+"/"+filename);
	}	
}
	

 

