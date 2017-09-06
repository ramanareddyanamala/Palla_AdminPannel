package com.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ViewProducts")
public class ViewProducts extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<a href='index.html'>Add New Product</a>");
		out.println("<h1>Products List</h1>");
		
		List<Product> list=ProductDao.getAllProducts();
		
		out.print("<table border='1' width='100%'");
		out.print("<tr ><th>Image</th><th>ItemName</th><th>Cost</th><th>Description</th><th>foodtype</th><th>Category</th><th>Availability</th><th>Discount</th><th>Edit</th><th>Delete</th></tr>");
		for(Product e:list){
			String s = "1";
			if(e.getAvailability().equals(s)){
				String s1 ="Yes";
			out.print("<tr><td>"+e.getUrl()+"</td><td>"+e.getItemname()+"</td><td>"+e.getCost()+"</td><td>"+e.getDescription()+"</td><td>"+e.getFoodtype()+"</td><td>"+e.getCategory()+"</td><td>"+s1+"</td><td>"+e.getDiscount()+"</td><td><a href='EditServlet?itemname="+e.getItemname()+"'>edit</a></td><td><a href='DeleteServlet?itemname="+e.getItemname()+"'>delete</a></td></tr>");
			}else{
				String s2 ="No";
				out.print("<tr><td>"+e.getUrl()+"</td><td>"+e.getItemname()+"</td><td>"+e.getCost()+"</td><td>"+e.getDescription()+"</td><td>"+e.getFoodtype()+"</td><td>"+e.getCategory()+"</td><td>"+s2+"</td><td>"+e.getDiscount()+"</td><td><a href='EditServlet?itemname="+e.getItemname()+"'>edit</a></td><td><a href='DeleteServlet?itemname="+e.getItemname()+"'>delete</a></td></tr>");
					
			}
			}
		out.print("</table>");
		
		out.close();
	}
}
