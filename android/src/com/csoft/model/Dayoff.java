package com.csoft.model;

import java.util.Date;

import org.json.JSONObject;

import com.csoft.util.T;

public class Dayoff 
{
	String id,userid,username,time,length,dayofftime,content;
	String reply,replyid,replyname,replytime,result;//0 未批复 1 许可 2不许可
	Date date,replydate;
	public Dayoff(JSONObject obj)
	{
		id=obj.optString("_id");
		result=obj.optString("result");
		userid=obj.optString("userid");
		time=obj.optString("time");
		length=obj.optString("length");
		dayofftime=obj.optString("dayofftime");
		content=obj.optString("content");
		date=T.parseDate(time);
		if(!result.equals("0"))
		{
			reply=obj.optString("reply");
			replyid=obj.optString("replyid");
			replyname=obj.optString("replyname");
			replytime=obj.optString("replytime");
			replydate=T.parseDate(replytime);
		}	
		
		
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getDayofftime() {
		return dayofftime;
	}
	public void setDayofftime(String dayofftime) {
		this.dayofftime = dayofftime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public String getReplyname() {
		return replyname;
	}
	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
