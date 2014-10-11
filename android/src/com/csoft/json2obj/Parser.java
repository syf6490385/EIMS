package com.csoft.json2obj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.csoft.model.Dailylog;
import com.csoft.model.Dayoff;
import com.csoft.model.Employee;
import com.csoft.model.Group;
import com.csoft.model.Sign;

public class Parser 
{
	public static List<Employee> parserEmployee(String str)
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
	public static List<Dayoff> parserDayoff(String str)
	{
		JSONArray array;
		List<Dayoff> result=new ArrayList<Dayoff>();
		try 
		{
			array = new JSONArray(str);
			for(int i=0;i<array.length();i++)
			{
				Dayoff dayoff=new Dayoff(array.getJSONObject(i));
				result.add(dayoff);
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}
	public static List<Sign> parserSign(String str)
	{
		JSONArray array;
		List<Sign> result=new ArrayList<Sign>();
		try 
		{
			array = new JSONArray(str);
			for(int i=0;i<array.length();i++)
			{
				Sign dayoff=new Sign(array.getJSONObject(i));
				result.add(dayoff);
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}
	public static List<Dailylog> parserDailylog(String str)
	{
		JSONArray array;
		List<Dailylog> result=new ArrayList<Dailylog>();
		try 
		{
			array = new JSONArray(str);
			for(int i=0;i<array.length();i++)
			{
				Dailylog dayoff=new Dailylog(array.getJSONObject(i));
				result.add(dayoff);
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}
	public static List<Group> parseGroup(String str)
	{
		JSONArray array;
		List<Group> result=new ArrayList<Group>();
		try 
		{
			array = new JSONArray(str);
			for(int i=0;i<array.length();i++)
			{
				Group dayoff=new Group(array.getJSONObject(i));
				result.add(dayoff);
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		return result;
		
	}
	private int getDayinThisMonth()
	{
		Calendar c=Calendar.getInstance();
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

}
