package chl.tinyActiviti.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import chl.tinyActiviti.model.*;

public class Manager {
	private ArrayList<String> workers = new ArrayList<String>();
	DBOperation dbo = null;
	ResultSet rs = null;
	
	public Manager() throws SQLException
	{
		dbo = new DBOperation();
	}
	
	public ArrayList<String> getWorkers() throws SQLException
	{
		String sql = "select id from user where power = 'worker'";
		rs = dbo.Query(sql);
		while(rs.next())
		{
			workers.add(rs.getString(1));
		}
		return workers;
	}
	
	public boolean deployProc(ArrayList<Item> items) throws SQLException
	{
		Iterator<Item> it = items.iterator();
		while(it.hasNext())
		{
			Item item = it.next();
			if(item.getdeadline()==null || item.getassignee()==null)
				return false;
			else
			{
				//insert into process values(item.getthisItem,item.gettype,item.getname,item.getassignee,item.getdeadline,0)
				String sql_proc = "insert into process values('"+item.getthisItem()+"','"+item.gettype()+"','"+item.getname()+"','"+item.getassignee()+"','"+item.getdeadline()+"',0)";
				if(dbo.sqlOp(sql_proc)==0)
					return false;
				
				Iterator<String> refl = item.getrefItem().iterator();
				while(refl.hasNext())
				{
					String ref = refl.next();
					String sql_ref = "insert into reflist values('"+item.getthisItem()+"','"+ref+"')";
					if(dbo.sqlOp(sql_ref)==0)
						return false;
				}
			}
		}
		return true;
	}
	
}
