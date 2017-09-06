package com.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Update Products</h1>");
		String sid=request.getParameter("itemname");
		//int id=Integer.parseInt(sid);
		
		Product e=ProductDao.getEmployeeById(sid);
		
		out.print("<form action='EditServlet2' method='post'>");
		out.print("<table>");
		//out.print("<tr><td>Image:</td><td><input type='file' name='photo' value='"+e.getUrl()+"'/></td></tr>");
		out.print("<tr><td>ItemName:</td><td><input type='text' name='itemname' value='"+e.getItemname()+"'/></td></tr>");
		out.print("<tr><td>Cost:</td><td><input type='text' name='cost' value='"+e.getCost()+"'/></td></tr>");
		out.print("<tr><td>Description:</td><td><input type='text' name='description' value='"+e.getDescription()+"'/></td></tr>");
		out.print("<tr><td>FoodType:</td><td><input type='text' name='foodtype' value='"+e.getFoodtype()+"'/></td></tr>");
		out.print("<tr><td>Availability:</td><td>");
		out.print("<select name='category' style='width:150px'>");
		out.print("<option>Veg</option>");
		out.print("</select>");
		out.print("</td></tr>");
		
		out.print("<tr><td>Discount:</td><td><input type='text' name='discount' value='"+e.getDiscount()+"'/></td></tr>");
		out.print("<tr><td>Availability:</td><td>");
		out.print("<select name='availability' style='width:150px'>");
		out.print("<option>Yes</option>");
		out.print("<option>No</option>");
		out.print("</select>");
		out.print("</td></tr>");
		
		out.print("<tr><td colspan='2'><input type='submit' value='Edit &amp; Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
	}
}
