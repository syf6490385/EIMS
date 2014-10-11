package com.csoft.model;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

import com.csoft.util.T;

public class Dailylog implements Serializable 
{
	String id,username,userid,content,selfrate,time,isreplyed;//0Î´»Ø¸´ 1ÒÑ»Ø¸´
	String reply,replyrate,replyname,replytime,replyid;
	Date date,replydate;
	public int getDayinMonth()
	{
		return date.getDate();
	}
	public Dailylog(JSONObject obj)
	{
		id=obj.optString("_id");
		username=obj.optString("username");
		userid=obj.optString("userid");
		content=obj.optString("content");
		selfrate=obj.optString("selfrate");
		time=obj.optString("time");
		isreplyed=obj.optString("isreplyed");
		date=T.parseDate(time);
		if(isreplyed.equals("1"))
		{
			reply=obj.optString("reply");
			replyrate=obj.optString("replyrate");
			replyname=obj.optString("replyname");
			replytime=obj.optString("replytime");
			replyid=obj.optString("replyid");
			replydate=T.parseDate(replytime);
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSelfrate() {
		return selfrate;
	}
	public void setSelfrate(String selfrate) {
		this.selfrate = selfrate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getIsreplyed() {
		return isreplyed;
	}
	public void setIsreplyed(String isreplyed) {
		this.isreplyed = isreplyed;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReplyrate() {
		return replyrate;
	}
	public void setReplyrate(String replyrate) {
		this.replyrate = replyrate;
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
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
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
	
	

}
