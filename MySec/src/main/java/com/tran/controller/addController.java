package com.tran.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tran.dao.UserDao;
import com.tran.dao.UserDaoImpl;
import com.tran.model.Employee;

@WebServlet("/addController")

public class addController extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String empIdStr = req.getParameter("empid");
        String empName = req.getParameter("empName");
        String salaryStr = req.getParameter("salary");

        int empid = 0;
        double salary = 0.0;

//        if(empIdStr != null && !empIdStr.isEmpty()) {
//            empid = Integer.parseInt(empIdStr);
//        } else {
//            out.println("Employee ID is missing");
//            return;
//        }
//
//        if(salaryStr != null && !salaryStr.isEmpty()) {
//            salary = Double.parseDouble(salaryStr);
//        } else {
//            out.println("Salary is missing");
//            return;
//        }

        Employee employee = new Employee();
        employee.setEmpid(empid);
        employee.setEmpName(empName);
        employee.setSalary(salary);

        try {
        	
            UserDao userDao = new UserDaoImpl();
            String msg = userDao.addRecord(employee);

            ServletContext sc = getServletContext();
            sc.setAttribute("message", msg);

            System.out.println(msg);
            out.println("<br><hr>");
            out.println( msg);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("❌ Error: " + e.getMessage());
        }
    }
}

