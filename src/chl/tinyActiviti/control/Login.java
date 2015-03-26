package chl.tinyActiviti.control;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.*;//for debug

import chl.tinyActiviti.model.*;

public class Login {
	public static boolean check(User user) throws SQLException
	{
		DBOperation dbo = null;
		ResultSet rs = null;
		try
		{
			dbo = new DBOperation();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		rs = dbo.Query("select * from user where id='"+user.getid()+"' and password='"+user.getpassword()+"'");
		if(rs.next())
		{
			Session.loginUser = rs.getString(1);
			Session.loginPower = rs.getString(3);
			System.out.print(Session.loginUser);
			System.out.print(Session.loginPower);
			return true;
		}
		else
			return false;
	}
	
	/*public static void main(String[] args)
	{
		User user = new User();
		user.setid("admin");
		user.setpassword("admin");
		try {
			if(login.check(user))
				System.out.print("success");
			else
				System.out.print("fail");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/ //for debug
}
