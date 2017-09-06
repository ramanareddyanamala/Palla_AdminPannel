package com.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	public static Connection getConnection1(){
		Connection con=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/palla","root","vedas");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	public static int save(Product e){
		int status=0;
		try{
			Connection con=ProductDao.getConnection1();
			PreparedStatement ps=con.prepareStatement("insert into vegtable(url,itemname,cost,description,foodtype,category,availability,discount) values (?,?,?,?,?,?,?,?)");
			ps.setString(1,e.getUrl());
			ps.setString(2,e.getItemname());
			ps.setString(3,e.getCost());
			ps.setString(4,e.getDescription());
			ps.setString(5, e.getFoodtype());
			ps.setString(6, e.getCategory());
			ps.setString(7, e.getAvailability());
			ps.setString(8, e.getDiscount());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return status;
	}
	public static int update(Product e){
		int status=0;
		String s = e.getCategory();
		if(s.equals("Veg")){
		try{
			Connection con=ProductDao.getConnection1();
			PreparedStatement ps=con.prepareStatement("update vegtable set itemname=?,cost=?,description=?,foodtype=?,category=?,availability=?,discount=? where itemname=?");
			ps.setString(1,e.getItemname());
			ps.setString(2,e.getCost());
			ps.setString(3,e.getDescription());
			ps.setString(4,e.getFoodtype());
			ps.setString(5,e.getCategory());
			System.out.println(e.getAvailability());
			ps.setString(6, e.getAvailability());
			ps.setString(7, e.getDiscount());
			//ps.setString(8, e.getUrl());
			ps.setString(8, e.getItemname());
			
			status=ps.executeUpdate();
			
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		}
		else{
			try{
				Connection con=ProductDao.getConnection1();
				PreparedStatement ps=con.prepareStatement("update nonvegtable set itemname=?,cost=?,description=?,foodtype=?,category=?,availability=?,discount=? where itemname=?");
				ps.setString(1,e.getItemname());
				ps.setString(2,e.getCost());
				ps.setString(3,e.getDescription());
				ps.setString(4,e.getFoodtype());
				ps.setString(5,e.getCategory());
				System.out.println(e.getAvailability());
				ps.setString(6, e.getAvailability());
				ps.setString(7, e.getDiscount());
				//ps.setString(8, e.getUrl());
				ps.setString(8, e.getItemname());
				
				status=ps.executeUpdate();
				con.close();
				
			}catch(Exception ex){ex.printStackTrace();}
		}
		return status;
	}
		
	public static int delete(String itemname){
		int status=0;
		
		try{
			Connection con=ProductDao.getConnection1();
			PreparedStatement ps=con.prepareStatement("delete from vegtable where itemname=?");
			ps.setString(1,itemname);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return status;
	}
	public static Product getEmployeeById(String itemname){
		Product e=new Product();
		
		try{
			Connection con=ProductDao.getConnection1();
			PreparedStatement ps=con.prepareStatement("select * from vegtable where itemname=?");
			ps.setString(1,itemname);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e.setUrl(rs.getString(1));
				e.setItemname(rs.getString(2));
				e.setCost(rs.getString(3));
				e.setDescription(rs.getString(4));
				e.setFoodtype(rs.getString(5));
				e.setCategory(rs.getString(6));
				e.setAvailability(rs.getString(7));
				e.setDiscount(rs.getString(8));
			}
			con.close();
		}catch(Exception ex){ex.printStackTrace();}
		
		return e;
	}
	public static List<Product> getAllProducts(){
		List<Product> list=new ArrayList<Product>();
		
		try{
			Connection con=ProductDao.getConnection1();
			PreparedStatement ps=con.prepareStatement("select * from vegtable");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Product e=new Product();
				e.setUrl(rs.getString(1));
				e.setItemname(rs.getString(2));
				e.setCost(rs.getString(3));
				e.setDescription(rs.getString(4));
				e.setFoodtype(rs.getString(5));
				e.setCategory(rs.getString(6));
				e.setAvailability(rs.getString(7));
				e.setDiscount(rs.getString(8));
				list.add(e);
			}
			con.close();
		}catch(Exception e){e.printStackTrace();}
		
		return list;
	}
}
