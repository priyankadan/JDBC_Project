package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class TimeStampSurroGatekeyTestForMySQLusingTWR {
	private static final String INSERT_CUSTOMER_QUERY="INSERT INTO JDBC_CUSTOMER(CNAME,CADDRS,bILLAMOUNT,DTOP) VALUES(?,?,?,?)";

	public static void main(String[] args) {
		
		//Scanner sc=null;
		//Connection con=null;
		//PreparedStatement ps=null;
		
		try(Scanner sc=new Scanner(System.in);
				Connection con=DriverManager.getConnection("jdbc:mysql:///ntaj915db1","root","root");
				PreparedStatement ps=con.prepareStatement(INSERT_CUSTOMER_QUERY)) {//TRY WITH RESOURCE
			//read input values
			//sc=new Scanner(System.in);
			String cname=null,caddrs=null;
			float billAmt=0.0f;
			//String dtop=null;   I want to insert system date, not taking as null
			if(sc!=null) {
				System.out.println("Enter customer name: ");
				cname=sc.next();
				System.out.println("Enter customer address: ");
				caddrs=sc.next();				
				System.out.println("Enter customer billAmount: ");
				billAmt=sc.nextFloat();
			}
			//load JDBC class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:mysql:///ntaj915db1","root","root");
			//creat prepared statement ob having pre compiles staement query
			//if(con!=null)
				//ps=con.prepareStatement(INSERT_CUSTOMER_QUERY);
			//set values to query param
			if(ps!=null) {
				ps.setString(1, cname);
				ps.setString(2, caddrs);ps.setFloat(3, billAmt);ps.setTimestamp(4,Timestamp.valueOf(LocalDateTime.now()));
				
			}
			//execute the pre compiled SQL Query
			int count=0;
			if(ps!=null)
				count=ps.executeUpdate();
			//process the results
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");			
			 
		}//try     (sc,  con,  ps  will close automatically here  )
		
		
catch (SQLException se) {
			
			se.printStackTrace();
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
				
	}//main close


	}//class close	
				
			
