package com.tran.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.tran.dao.*;
import com.tran.model.Employee;

public class UserDaoImpl implements UserDao{

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String sql;
	
	public UserDaoImpl() throws ClassNotFoundException,SQLException
	{
		connection = MyConnection.getConnection();
		
	}

	@Override
	public String addRecord(Employee employee) throws SQLException
	{
		sql = "insert into employee values(?,?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employee.getEmpid());
		preparedStatement.setString(2, employee.getEmpName());
		preparedStatement.setDouble(3, employee.getSalary());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Added Succesffuly. :)";
	}
	
	@Override
	public String updateRecord(Employee employee) throws SQLException
	{
		sql = "update employee set salary = ? where empid = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(2, employee.getEmpid());
		preparedStatement.setDouble(1, employee.getSalary());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Updated Succesffuly. :)\n";
	}
	
	@Override
	public String deleteRecord(Employee employee) throws SQLException
	{
		sql = "delete from employee where empid = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, employee.getEmpid());
		Integer count = preparedStatement.executeUpdate();
		return count + ". Record Deleted Succesffuly. :)\n";
	}
	
	@Override
	public Employee findRecordByEmpId(Integer empid) throws SQLException 
	{
		Employee employee=null;
		sql = "select * from employee where empid=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, empid);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			employee = new Employee();
			employee.setEmpid(resultSet.getInt(1));
			employee.setEmpName(resultSet.getString(2));
			employee.setSalary(resultSet.getDouble(3));
			return employee;
		}
		else {
			return null;
		}
	}
	

	@Override
	public List<Employee> findAllRecords() {
	    List<Employee> list = new ArrayList<>();
	    String sql = "SELECT * FROM employee";
	    try {
	        PreparedStatement ps = connection.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Employee e = new Employee();
	            e.setEmpid(rs.getInt(1));
	            e.setEmpName(rs.getString(2));
	            e.setSalary(rs.getDouble(3));
	            list.add(e);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
}
