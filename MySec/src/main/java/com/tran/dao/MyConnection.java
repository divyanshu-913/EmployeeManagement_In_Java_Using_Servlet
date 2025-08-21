package com.tran.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	private static Connection connection;
	private MyConnection(){}
	
	public static Connection getConnection() throws ClassNotFoundException,SQLException
	{
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://@localhost:3306/DIVYANSHU";
		String uname = "root";
		String upass = "1234";
		
		if(connection == null)
		{
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url,uname,upass);
			
		}
		
		return connection;
		
	}
}
