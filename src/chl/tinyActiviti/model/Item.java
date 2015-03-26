package chl.tinyActiviti.model;

import java.sql.Date;
import java.util.ArrayList;

public class Item {
	private ArrayList<String> refItem = new ArrayList<String>();
	private String thisItem = null;
	private String type = null;
	private String name = null; //not the attribute of "name" but its type
	private Date deadline = null;
	private String assignee = null;
	private boolean done = false;
	private boolean available = false;
	
	public ArrayList<String> getrefItem()
	{
		return refItem;
	}
	public void setrefItem(String refItem)
	{
		this.refItem.add(refItem);
	}
	
	public String getthisItem()
	{
		return thisItem;
	}
	public void setthisItem(String thisItem)
	{
		this.thisItem = thisItem;
	}
	
	public String gettype()
	{
		return type;
	}
	public void settype(String type)
	{
		this.type = type;
	}
	
	public String getname()
	{
		return name;
	}
	public void setname(String name)
	{
		this.name = name;
	}
	
	public Date getdeadline()
	{
		return deadline;
	}
	public void setdeadline(Date deadline)
	{
		this.deadline = deadline;
	}
	
	public String getassignee()
	{
		return assignee;
	}
	public void setassignee(String assignee)
	{
		this.assignee = assignee;
	}
	
	public boolean getdone()
	{
		return done;
	}
	public void setdone(boolean done)
	{
		this.done = done;
	}
	
	public boolean getavailable()
	{
		return available;
	}
	public void setavailable(boolean available)
	{
		this.available = available;
	}
}
