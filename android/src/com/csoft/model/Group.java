package com.csoft.model;

import org.json.JSONObject;

public class Group 
{
      String id;
      String name;
      public Group()
      {}
      public Group(JSONObject obj)
      {
    	  id=obj.optString("_id");
    	  name=obj.optString("name");
      }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
      
}
