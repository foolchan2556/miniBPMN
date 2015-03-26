package chl.tinyActiviti.model;

public class User
{
	String id = null;
	String password = null;
	String power = null;
	
	public String getid()
	{
		return id;
	}
	
	public String getpassword()
	{
		return password;
	}
	
	public String getpower()
	{
		return power;
	}
	
	public void setid(String id)
	{
		this.id = id;
	}
	
	public void setpassword(String password)
	{
		this.password = password;
	}
	
	public void setpower(String power)
	{
		this.power = power;
	}
}