package com.csoft.json2obj;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.csoft.model.Employee;

public class Parser 
{
	public static List<Employee> parser(String str)
	{
		JSONArray array;
		List<Employee> result=new ArrayList<Employee>();
		try 
		{
			array = new JSONArray(str);
			for(int i=0;i<array.length();i++)
			{
				Employee emp=new Employee(array.getJSONObject(i));
				result.add(emp);
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}

}
