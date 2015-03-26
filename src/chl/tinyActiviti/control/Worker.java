package chl.tinyActiviti.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import chl.tinyActiviti.model.*;

public class Worker {
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Item> item_o_a, item_o_ua, item_o_d, item_uo_a, item_uo_ua, item_uo_d;
	DBOperation dbo1 = null;
	DBOperation dbo2 = null;
	ResultSet rs = null;
	
	public Worker() throws SQLException
	{
		dbo1 = new DBOperation();
		dbo2 = new DBOperation();
		
	}
	
	public boolean setDone(String thisItem) throws SQLException
	{
		if(dbo1.sqlOp("update process set done = 1 where thisItem = '"+thisItem+"'")!=0)
			return true;
		else
			return false;
	}
	
	public ArrayList<Item> queryItems(int overdue, int state) throws SQLException
	{
		ArrayList<Item> retItems = new ArrayList<Item>();
		items = this.getItems();
		Iterator<Item> it = items.iterator();
		
		item_o_a = new ArrayList<Item>();
		item_o_ua = new ArrayList<Item>();
		item_o_d = new ArrayList<Item>();
		item_uo_a = new ArrayList<Item>();
		item_uo_ua = new ArrayList<Item>();
		item_uo_d = new ArrayList<Item>();
		
		while(it.hasNext())
		{
			Item temp = it.next();
			if(temp.getdeadline().before( new Date()))
			{
				if(!temp.getavailable())
					item_o_ua.add(temp);
				else if(temp.getavailable()&&!temp.getdone())
					item_o_a.add(temp);
				else
					item_o_d.add(temp);
			}
			else
			{
				if(!temp.getavailable())
					item_uo_ua.add(temp);
				else if(temp.getavailable()&&!temp.getdone())
					item_uo_a.add(temp);
				else
					item_uo_d.add(temp);
			}	
		}
		
		if(overdue == 0)
		{
			if(state == 0)
				retItems = item_uo_ua;
			else if(state == 1)
				retItems = item_uo_a;
			else if(state == 2)
				retItems = item_uo_d;
			else
			{
				retItems = item_uo_a;
				retItems.addAll(item_uo_ua);
				retItems.addAll(item_uo_d);
			}
		}
		else if(overdue == 1)
		{
			if(state == 0)
				retItems = item_o_ua;
			else if(state == 1)
				retItems = item_o_a;
			else if(state == 2)
				retItems = item_o_d;
			else
			{
				retItems = item_o_a;
				retItems.addAll(item_o_ua);
				retItems.addAll(item_o_d);
			}
		}
		else
		{
			if(state == 0)
			{
				retItems = item_o_ua;
				retItems.addAll(item_o_a);
			}
			else if(state == 1)
			{
				retItems = item_o_a;
				retItems.addAll(item_uo_a);
			}
			else if(state == 2)
			{
				retItems = item_o_d;
				retItems.addAll(item_uo_d);
			}
			else
			{
				retItems = item_o_a ;
				retItems.addAll(item_o_ua);
				retItems.addAll(item_o_d);
				retItems.addAll(item_uo_a);
				retItems.addAll(item_uo_ua);
				retItems.addAll(item_uo_d);
			}
		}
		return retItems;
	}
	
	public ArrayList<Item> getItems() throws SQLException
	{
		items = new ArrayList<Item>();
		String sql = "select * from process where assignee = '"+Session.loginUser+"'";
		rs = dbo1.Query(sql);
		while(rs.next())
		{
			Item item = new Item();
			item.setthisItem(rs.getString(1));
			item.settype(rs.getString(2));
			item.setname(rs.getString(3));
			item.setdeadline(rs.getDate(5));
			if(rs.getInt(6)==1)
				item.setdone(true);
			else
				item.setdone(false);
			item.setavailable(true);
			//select done from process where thisItem in (select refItem from reflist where thisItem = '')
			ResultSet checkDone = dbo2.Query("select done from process where thisItem in (select refItem from reflist where thisItem = '"+item.getthisItem()+"')");
			while(checkDone.next())
			{
				System.out.println(checkDone.getInt(1));
				if(checkDone.getInt(1)==0)
					item.setavailable(false);
			}
			//items.add(rs.getString(1));
			items.add(item);
		}
		return items;
	}
}
