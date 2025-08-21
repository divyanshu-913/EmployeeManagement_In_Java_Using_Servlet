package com.tran.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tran.dao.UserDao;
import com.tran.dao.UserDaoImpl;
import com.tran.model.Employee;


@WebServlet("/DeleteController")

public class DeleteController extends HttpServlet {

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
			
			res.setContentType("text/html");
			
			PrintWriter out = res.getWriter();
			
			 String empIdStr = req.getParameter("empid");

		        try {
		            int empid = Integer.parseInt(empIdStr);


		            Employee employee = new Employee();
		            employee.setEmpid(empid);


		            UserDao userDao = new UserDaoImpl();
		            String msg = userDao.deleteRecord(employee);

		            out.println("<h3>" + msg + "</h3>");

		        } catch (NumberFormatException e) 
	
		        {
		            out.println("<h3>Invalid input. Please enter correct values.</h3>");
		        }
		        catch (Exception e) {
		            e.printStackTrace();
		            out.println("<h3>❌ Error: " + e.getMessage() + "</h3>");
		        }
		    }
}
