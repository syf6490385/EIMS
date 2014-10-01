package com.csoft.employeemanager.conn;

import com.csoft.model.Employee;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class Conn 
{
	static String baseURL="http://192.168.1.133";
	private static AsyncHttpClient client=new AsyncHttpClient();
	public static void login(Employee emp,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",emp.getName());
		params.put("pwd",emp.getPwd());
		client.get(baseURL+"/user/login",params,handler);
	}
	public static void addEmployee(Employee emp,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",emp.getName());
		params.put("age",String.valueOf(emp.getAge()));
		params.put("pwd",emp.getPwd());
		params.put("lv",String.valueOf(emp.getLv()));
		client.get(baseURL+"/user/add",params,handler);
	}
	public static void getAllEmployee(ResponseHandlerInterface handler)
	{
		client.get(baseURL+"/user/list", handler);
	}
	public static void delEmployee(String id,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("id",id);
		client.get(baseURL+"/user/del",params,handler);
	}
	public static void editEmployee(Employee emp,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("id",emp.getId());
		params.put("newname",emp.getName());
		params.put("age",String.valueOf(emp.getAge()));
		params.put("pwd",emp.getPwd());
		params.put("lv",String.valueOf(emp.getLv()));
		client.get(baseURL+"/user/edit",params,handler);
	}

}
