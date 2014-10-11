package com.csoft.conn;

import com.csoft.model.Employee;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

public class Conn 
{
	static String baseURL="http://192.168.1.103";
	private static AsyncHttpClient client=new AsyncHttpClient();
	//get pic on internet
	public static void getImage(String name,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",name);
		client.get(baseURL+"/user/getpic",params,handler);
	}
	//user control
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
		params.put("groupid",emp.getGroupid());
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
		params.put("groupid",emp.getGroupid());
		client.get(baseURL+"/user/edit",params,handler);
	}
	//------------------------------------------------------
	//sign control
	public static void isSign(String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		client.get(baseURL+"/sign/issign",params,handler);
	}
	public static void addlateSign(String userid,String reason,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		params.put("reason",reason);
		client.get(baseURL+"/sign/addlate",params,handler);
	}
	public static void addSign(String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		client.get(baseURL+"/sign/add",params,handler);
	}
	public static void getAllSign(String year,String month,String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("year",year);
		params.put("month",month);
		params.put("userid",userid);
		client.get(baseURL+"/sign/list",params,handler);
	}
	//--------------------------
	//----------------------------
	//dayoffcontrol;
	public static void getunReplyDayoff(String groupid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("groupid",groupid);
		client.get(baseURL+"/dayoff/getunreply",params,handler);
	}
	public static void addDayoff(String userid,String content,String length,String dayofftime,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		params.put("content",content);
		params.put("length",length);
		params.put("dayofftime",dayofftime);
		client.get(baseURL+"/dayoff/add",params,handler);
	}
	public static void getAllDayoff(String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		client.get(baseURL+"/dayoff/list",params,handler);
	}
	public static void replyDayoff(String dayoffid,String content,String result,String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("dayoffid",dayoffid);
		params.put("content",content);
		params.put("result",result);//是否同意
		params.put("userid",userid);//批富人的id
		client.get(baseURL+"/dayoff/reply",params,handler);
	}
	//------------------------
	//dailylog control
	public static void isDailyed(String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		client.get(baseURL+"/dailylog/isdailyed",params,handler);
	}
	public static void getunReplyDailylog(String groupid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("groupid",groupid);
		client.get(baseURL+"/dailylog/getunreply",params,handler);
	}
	public static void addDailylog(String userid,String content,String selfrate,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("userid",userid);
		params.put("content",content);
		params.put("selfrate",selfrate);
		client.get(baseURL+"/dailylog/add",params,handler);
	}
	public static void getAllDailylog(String year,String month,String userid,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("year",year);
		params.put("month",month);
		params.put("userid",userid);
		client.get(baseURL+"/dailylog/list",params,handler);
	}
	public static void replyDailylog(String dailylogid,String userid,String rate,String reply,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("dailylogid",dailylogid);
		params.put("userid",userid);
		params.put("rate",rate); 
		params.put("reply",reply); 
		client.get(baseURL+"/dailylog/reply",params,handler);
	}
	//
	//this is group control
	public static void addGroup(String name,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",name);
		client.get(baseURL+"/group/add",params,handler);
	}
	public static void delGroup(String name,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",name);
		client.get(baseURL+"/group/del",params,handler);
	}
	public static void editGroup(String name,String newname,ResponseHandlerInterface handler)
	{
		RequestParams params=new RequestParams();
		params.put("name",name);
		params.put("newname",newname);
		client.get(baseURL+"/group/edit",params,handler);
	}
	public static void listGroup(ResponseHandlerInterface handler)
	{
		client.get(baseURL+"/group/list",handler);
	}

}
