package com.csoft.model;

import org.json.JSONObject;

public class Employee 
{
	String name,pwd,id;
	int age,lv;
	public Employee()
	{}
	public Employee(JSONObject obj)
	{
		name=obj.optString("name");
		pwd=obj.optString("pwd");
		age=obj.optInt("age");
		lv=obj.optInt("lv");
		id=obj.optString("_id");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	

}
