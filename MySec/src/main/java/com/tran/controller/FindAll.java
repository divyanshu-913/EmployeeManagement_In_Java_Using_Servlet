package com.tran.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tran.dao.UserDao;
import com.tran.dao.UserDaoImpl;
import com.tran.model.Employee;

@WebServlet("/FindAll")
public class FindAll extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServerException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            UserDao userDao = new UserDaoImpl();
            List<Employee> employees = userDao.findAllRecords();

            out.println("<html>");
            out.println("<head><title>All Employees</title></head>");
            out.println("<body style='font-family: Arial; text-align:center;'>");

            out.println("<h2 style='color: blue;'>All Employee Records</h2>");

            if (employees == null || employees.isEmpty()) {
                out.println("<p>No employees found.</p>");
            } else {
                out.println("<table border='1' style='border-collapse: collapse; width: 70%; margin: 0 auto;'>");
                out.println("<tr style='background-color: lightblue;'>");
                out.println("<th style='padding:10px;'>Employee ID</th>");
                out.println("<th style='padding:10px;'>Name</th>");
                out.println("<th style='padding:10px;'>Salary</th>");
                out.println("</tr>");

                // loop for all employees
                for (Employee emp : employees) {
                    out.println("<tr>");
                    out.println("<td style='padding:8px;'>" + emp.getEmpid() + "</td>");
                    out.println("<td style='padding:8px;'>" + emp.getEmpName() + "</td>");
                    out.println("<td style='padding:8px;'>" + emp.getSalary() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            // Back button
            out.println("<br><a href='index.html' style='display:inline-block; background:gray; color:white; padding:8px 15px; border-radius:5px; text-decoration:none;'>Back</a>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error fetching employee records!</p>");
        }
    }
}
