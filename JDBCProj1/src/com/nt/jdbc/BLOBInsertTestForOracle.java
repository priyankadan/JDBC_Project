package com.nt.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;

public class BLOBInsertTestForOracle {
	private static final String INSERT_ACTOR_QUERY="INSERT INTO JDBC_ACTOR_TAB VALUES(ACTORID_SEQ.NEXTVAL,?,?,?)";

	public static void main(String[] args) {
		
		try(   Scanner sc= new Scanner(System.in);				
				//Establish the connection
				//Connection sqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov"); 
		
		//Create PreparedStatement object 
		//Statement msqlST=sqlCon.createStatement();
		PreparedStatement ps=con.prepareStatement(INSERT_ACTOR_QUERY);
				){
			
			//read input values
			
			String actorName = null,actorAddr = null,photoLocation=null;
			
			
			if(sc!=null) {
			System.out.println("Enter Actor name:");
			actorName=sc.next();
			System.out.println("Enter Actor address :");
			actorAddr=sc.next();
			System.out.println("Enter photo loction:");
			photoLocation=sc.next().replace('?',' ').trim();
			}
			
			//create stream pointing to photo file
			//InputStream is= new FileInputStream("C:\\Users\\biplov\\Pictures\\keyboardDPwithhand.jpg");
			//   "C:\\pics\\priyankFB_DP.jpg"
			InputStream is= new FileInputStream(photoLocation);
			
			//set query param values
			if(ps!=null) {
			ps.setString(1,actorName);
			ps.setString(2,actorAddr);
			ps.setBinaryStream(3,is);
			}
			
			//execute the queries
			int count=0;
			if(ps!=null)
			 count=ps.executeUpdate();
			if(count==0)				
					System.out.println("RECORD NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
				
			
					
				}//try
		catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//main

}//class
