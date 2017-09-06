package com.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/EditServlet2")
@MultipartConfig
public class EditServlet2 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
	
		String itemname=request.getParameter("itemname");
		String cost=request.getParameter("cost");
		String description=request.getParameter("description");
		String foodtype=request.getParameter("foodtype");
		String category = request.getParameter("category");
		String availability = request.getParameter("availability");
       
		  String discount = request.getParameter("discount");
		
		  // Part filePart = request.getPart("photo");
	       //InputStream imageInputStream = filePart.getInputStream();
	       //read imageInputStream
		   //String p ="C:\\Users\\Wave\\Desktop\\JavaScript";
	       //filePart.write(p);
		/*
		Part p =  request.getPart("itemname");
        Scanner scanner  = new Scanner( p.getInputStream());
        String playername = scanner.nextLine(); 
        
        Part photo =  request.getPart("productphoto");
        
        Part p1 =  request.getPart("cost");
        Scanner scanner1  = new Scanner( p1.getInputStream());
        String cost = scanner1.nextLine(); 
        
        Part p2 =  request.getPart("description");
        Scanner scanner2  = new Scanner( p2.getInputStream());
        String description = scanner2.nextLine(); 
        
        Part p3 =  request.getPart("foodtype");
        Scanner scanner3  = new Scanner( p3.getInputStream());
        String foodtype = scanner3.nextLine(); 
        
        Part p4 =  request.getPart("category");
        Scanner scanner4  = new Scanner( p4.getInputStream());
        String category = scanner4.nextLine(); 
        
        Part p5 =  request.getPart("availability");
        Scanner scanner5  = new Scanner( p5.getInputStream());
        String availability = scanner5.nextLine(); 
        
        Part p6 =  request.getPart("discount");
        Scanner scanner6  = new Scanner( p6.getInputStream());
        String discount = scanner6.nextLine(); 
		*/
		Product e=new Product();
		//e.setUrl(p);
		e.setItemname(itemname);
		e.setCost(cost);
		e.setDescription(description);
		e.setFoodtype(foodtype);
		e.setCategory(category);
		System.out.println("availability"+availability);
		 if(availability.equals("Yes")){
				String a ="1";
			    e.setAvailability(a);
		 }else{
			 String a1 ="0";
			    e.setAvailability(a1);
		 }
		e.setDiscount(discount);
		
		int status=ProductDao.update(e);
		if(status>0){
			response.sendRedirect("ViewProducts");
		}else{
			out.println("Sorry! unable to update record");
		}
		
		out.close();
	}

}
