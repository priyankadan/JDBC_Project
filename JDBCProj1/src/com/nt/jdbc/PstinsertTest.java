 package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PstinsertTest {

	public static void main(String[] args) {
	Scanner sc=null;
	Connection con=null;
	PreparedStatement ps=null;
	try {
		sc=new Scanner(System.in);
		if(sc!=null) 
			System.out.println("Enter no of new product : ");
			int count=sc.nextInt();
		
		//register jdbc driver softwere
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//establish the connection
		
		//for Mysql db
		con=DriverManager.getConnection("jdbc:mysql:///ntaj915db1","root","root");
		
		//for Oracle db
		//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		//create PreparedStatement object representing the pre-compiled sql query
		if(con!=null)
		ps=con.prepareStatement("INSERT INTO jdbc_product(pid,pname,price,qty)" + " VALUES (?,?,?,?)");
		
		
			//read multiple product details and set them to pre compiled INSERT query param values
		for(int i=1;i<=count;++i) {
			//read each product details
			System.out.println("Enter product"+i+"  details: ");
			System.out.println("Enter PRODUCT ID:");
			int pid=sc.nextInt();
			System.out.println("Enter PRODUCT NAME:");
			String pname=sc.next();
			System.out.println("Enter PRICE:");
			float price=sc.nextFloat();
			System.out.println("Enter QUANTITY:");
			float qty=sc.nextFloat();
			
			//set each product details to query param values
			ps.setInt(1, pid);
			ps.setString(2, pname);
			ps.setFloat(3, price);
			ps.setFloat(4, qty);
			
			//execute the sql queries
			//if(ps!=null)
			int result=ps.executeUpdate();
			
			if(result==0)
				System.out.println("RECORD NOT INSERTED");
			else
				System.out.println("RECORD INSERTED");
				
		}//for close
	}//try close
	
	catch (SQLException se) {
		// TODO Auto-generated catch block
		se.printStackTrace();
	}
	
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	finally {
		try {
			if(ps!=null)
				ps.close();
		}
		catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		try {
			if(con!=null)
				con.close();
		}
		catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}//finally close
	
	
}//main close


}//class close	
			
		
	
