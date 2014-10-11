package com.csoft.model;

import org.json.JSONObject;

public class Sign 
{
	String id,userid,username,time,islate,reason;
	public Sign(JSONObject obj)
	{
		id=obj.optString("_id");
		userid=obj.optString("userid");
		username=obj.optString("username");
		time=obj.optString("time");
		islate=obj.optString("islate");
		if(islate.equals("1"))
		{
			reason=obj.optString("reason");
		}
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}
}
