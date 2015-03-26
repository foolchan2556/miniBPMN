package chl.tinyActiviti.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import chl.tinyActiviti.model.*;

public class Admin {
	private ArrayList<User> users;
	private User user;
	DBOperation dbo = null;
	ResultSet rs = null;
	
	public Admin() throws SQLException
	{
		dbo = new DBOperation();
	}
	
	public ArrayList<User> getUsers() throws SQLException
	{
		users = new ArrayList<User>();
		String sql = "select * from user order by power";
		rs = dbo.Query(sql);
		while(rs.next())
		{
			user = new User();
			user.setid(rs.getString(1));
			user.setpassword(rs.getString(2));
			user.setpower(rs.getString(3));
			users.add(user);
		}
		user = new User();
		user.setid("new");
		user.setpassword(null);
		user.setpower(null);
		users.add(user);
		return users;
	}

	public boolean delUser(String text) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from user where id = '"+text+"'";
		if(dbo.sqlOp(sql)!=0)
			return true;
		else
			return false;
	}

	public boolean addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into user values('"+user.getid()+"','"+user.getpassword()+"','"+user.getpower()+"')";
		if(dbo.sqlOp(sql)!=0)
			return true;
		else
			return false;
	}

	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update user set password = '"+user.getpassword()+"', power='"+user.getpower()+"' where id = '"+user.getid()+"'";
		if(dbo.sqlOp(sql)!=0)
			return true;
		else
			return false;
	}
}
