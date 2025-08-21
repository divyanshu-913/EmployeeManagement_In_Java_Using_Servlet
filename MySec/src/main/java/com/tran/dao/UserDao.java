package com.tran.dao;

import java.sql.SQLException;
import java.util.List;

import com.tran.model.Employee;

public interface UserDao {


	String addRecord(Employee employee) throws SQLException;
	
	
	String updateRecord(Employee employee) throws SQLException;
	
	String deleteRecord(Employee employee) throws SQLException;

	Employee findRecordByEmpId(Integer empid) throws SQLException; 

	public List<Employee> findAllRecords() throws SQLException;
		
	
}
