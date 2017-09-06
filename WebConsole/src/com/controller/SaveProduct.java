package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveProdct")
public class SaveProduct {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String url=request.getParameter("url");
		String itemname=request.getParameter("itemname");
		String cost=request.getParameter("cost");
		String description=request.getParameter("description");
		String foodtype = request.getParameter("foodtype");
		String category = request.getParameter("category");
		String availability = request.getParameter("availability");
		String discount = request.getParameter("discount");
		
		Product e=new Product();
		e.setUrl(url);
		e.setItemname(itemname);
		e.setCost(cost);
		e.setDescription(description);
		e.setFoodtype(foodtype);
		e.setCategory(category);
		e.setAvailability(availability);
		e.setDiscount(discount);
		
		int status=ProductDao.save(e);
		if(status>0){
			out.print("<p>Record saved successfully!</p>");
			request.getRequestDispatcher("index.html").include(request, response);
		}else{
			out.println("Sorry! unable to save record");
		}
		
		out.close();
	}

}
