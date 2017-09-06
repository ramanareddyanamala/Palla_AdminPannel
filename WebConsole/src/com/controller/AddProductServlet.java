package com.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name = "AddPlayerServlet", urlPatterns = {"/addproduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	int id;
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        try {
           
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
            
            Product e=new Product();
    		e.setItemname(playername);
    		e.setCost(cost);
    		e.setDescription(description);
    		e.setFoodtype(foodtype);
    		e.setCategory(category);
    		e.setAvailability(availability);
    		e.setDiscount(discount);
    		
            
           
            // Connect to MySQL
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/palla", "root", "vedas");
            con.setAutoCommit(false);
            if(category.equals("Veg")){
            
            PreparedStatement ps = con.prepareStatement("insert into veg(name,image_data,image_file_name) values(?,?,?)");
            ps.setString(1, playername);
            // size must be converted to int otherwise it results in error
            System.out.println("Photo size.."+photo.getSize());
            //MultipartRequest m=new MultipartRequest(request,"D:/image");  
            ps.setBinaryStream(2, photo.getInputStream(), (int)  photo.getSize());
            ps.setString(3, "Product");
            int rs = ps.executeUpdate();
            if(rs>0){
            	System.out.println("Successfully inserted");
            }
            else{
            	System.out.println("Data is too long!!");
            }
            
            try{
            	PreparedStatement ps1 = con.prepareStatement("select id from veg where name=?");
            	 ps1.setString(1, playername);
            	 ResultSet rs1=ps1.executeQuery();
            	 while(rs1.next()){
            	 id = rs1.getInt(1);
            	 }
            	 try{
            		 PreparedStatement ps12 = con.prepareStatement("insert into vegtable values(?,?,?,?,?,?,?,?)");
            		 String s1 ="http://52.70.91.75/Image/veg?id=";
            		 System.out.println("id value.."+id);
            		 String s2 = String.valueOf(id);
            		 String s = s1+s2;
            		 System.out.println("url value..."+s);
            		 ps12.setString(1, s);
            		 ps12.setString(2, playername);
            		 ps12.setString(3, cost);
            		 ps12.setString(4, description);
            		 ps12.setString(5, foodtype);
            		 ps12.setString(6, category);
            		 if(availability.equals("Yes")){
         				String a ="1";
         				ps12.setString(7, a);
         		     }else{
         			 String a1 ="0";
         			    ps12.setString(7, a1);
         		     }
            		 
            		 ps12.setString(8, discount);
            		 int rs12 = ps12.executeUpdate();
                     if(rs12>0){
                     	System.out.println("Successfully inserted");
                     	out.println("Added Player Successfully. <p> <a href='ViewProducts'>View Products </a>");
                     }
                     else{
                     	System.out.println("Data is too long!!");
                     }
            	 }catch(Exception e1){
            		 e1.printStackTrace();
            	 }
            }catch(Exception e2){
            	e2.printStackTrace();
            }
           
            
         } 
            else if(category.equals("Non-Veg")){
            	
            	 PreparedStatement ps = con.prepareStatement("insert into nonveg(name,image_data,image_file_name) values(?,?,?)");
                 ps.setString(1, playername);
                 // size must be converted to int otherwise it results in error
                 System.out.println("Photo size.."+photo.getSize());
                 //MultipartRequest m=new MultipartRequest(request,"D:/image");  
                 ps.setBinaryStream(2, photo.getInputStream(), (int)  photo.getSize());
                 ps.setString(3, "Product");
                 int rs = ps.executeUpdate();
                 if(rs>0){
                 	System.out.println("Successfully inserted");
                 }
                 else{
                 	System.out.println("Data is too long!!");
                 }
                 
                 try{
                 	PreparedStatement ps1 = con.prepareStatement("select id from veg where name=?");
                 	 ps1.setString(1, playername);
                 	 ResultSet rs1=ps1.executeQuery();
                 	 while(rs1.next()){
                 	 id = rs1.getInt(1);
                 	 }
                 	 try{
                 		 PreparedStatement ps12 = con.prepareStatement("insert into nonvegtable values(?,?,?,?,?,?,?,?)");
                 		 String s1 ="http://52.70.91.75/Image/veg?id=";
                 		 System.out.println("id value.."+id);
                 		 String s2 = String.valueOf(id);
                 		 String s = s1+s2;
                 		 System.out.println("url value..."+s);
                 		 e.setUrl(s);
                 		 ps12.setString(1, s);
                 		 ps12.setString(2, playername);
                 		 ps12.setString(3, cost);
                 		 ps12.setString(4, discount);
                 		 ps12.setString(5, description);
                 		 ps12.setString(6, foodtype);
                 		 ps12.setString(7, category);
                 		 if(availability.equals("Yes")){
              				String a ="1";
              				ps12.setString(8, a);
              		     }else{
              			 String a1 ="0";
              			    ps12.setString(8, a1);
              		     }
                 		
                 		 int rs12 = ps12.executeUpdate();
                          if(rs12>0){
                          	System.out.println("Successfully inserted");
                          	out.println("Added Player Successfully. <p> <a href='ViewProducts'>View Products </a>");
                          }
                          else{
                          	System.out.println("Data is too long!!");
                          }
                 	 }catch(Exception e1){
                 		 e1.printStackTrace();
                 	 }
                 }catch(Exception e2){
                 	e2.printStackTrace();
                 }
                 con.commit();
                 con.close();
            	
            }   
            
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
        finally {   
        	
             try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             out.close();
        }
    }
}
