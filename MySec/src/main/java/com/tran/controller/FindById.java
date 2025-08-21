package com.tran.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tran.dao.UserDao;
import com.tran.dao.UserDaoImpl;
import com.tran.model.Employee;


@WebServlet("/FindById")
public class FindById extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
		{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		 int empid = Integer.parseInt(req.getParameter("empid"));

		 
		 try {
			 UserDao userDao = new UserDaoImpl();
	         Employee msg = userDao.findRecordByEmpId(empid);

	         
		    if (msg != null) {
		    	out.println("<html>");
		    	out.println("<head><title>Employee Details</title></head>");
		    	out.println("<body style='font-family: Arial; text-align:center;'>");

		    	out.println("<h2 style='color: green;'>Employee Details</h2>");

		    	out.println("<table border='1' style='border-collapse: collapse; width: 50%; margin: 0 auto;'>");
		    	out.println("<tr style='background-color: lightgreen;'>");
		    	out.println("<th style='padding:10px;'>Employee ID</th>");
		    	out.println("<th style='padding:10px;'>Name</th>");
		    	out.println("<th style='padding:10px;'>Salary</th>");
		    	out.println("</tr>");

		    	out.println("<tr>");
		    	out.println("<td style='padding:8px;'>" + msg.getEmpid() + "</td>");
		    	out.println("<td style='padding:8px;'>" + msg.getEmpName() + "</td>");
		    	out.println("<td style='padding:8px;'>" + msg.getSalary() + "</td>");
		    	out.println("</tr>");
		    	out.println("</table>");

		    	// 👇 Back Button
		    	out.println("<br><a href='index.html' style='display:inline-block; background:gray; color:white; padding:8px 15px; border-radius:5px; text-decoration:none;'>Back</a>");

		    	out.println("</body>");
		    	out.println("</html>");

		    } else {
		        out.println("<h2 style='color:red;'>No Employee found with ID: " + empid + "</h2>");
		    }
		    
		 }
		 catch (ClassNotFoundException | SQLException e) 
		 {
	            e.printStackTrace();
	            out.println("Error: " + e.getMessage());
	     }
    }
}

