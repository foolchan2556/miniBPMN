package chl.tinyActiviti.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;//for debug

public class DBOperation 
{
	Connection con = null;
	Statement stmt = null;
	public DBOperation() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ce) {
			System.out.println("SQLException:"+ce.getMessage());
		}
		con=DriverManager.getConnection("jdbc:mysql://localhost/tinyactiviti", "root", "19901111");
		stmt = con.createStatement();
	}
	public ResultSet Query(String sql) throws SQLException
	{
		return stmt.executeQuery(sql);
	}
	
	public int sqlOp(String sql) throws SQLException
	{
		return stmt.executeUpdate(sql);
	}
		
	/*public static void main(String[] args)
	{
		DBOperation dbo = null;
		try {
			dbo = new DBOperation();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dbo.con!=null)
			System.out.print("connected!");
		else
			System.out.print("failed");
	}*/ //for debug
}
