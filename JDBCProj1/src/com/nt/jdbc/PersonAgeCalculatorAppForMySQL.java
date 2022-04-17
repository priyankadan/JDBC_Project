package com.nt.jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Connection;

public class PersonAgeCalculatorAppForMySQL {
	
	public static final String AGE_CALCULATOR_QUERY=" SELECT DATEDIFF(NOW(),DOB)/365 FROM JDBC_PERSON_DATES WHERE PID = ? ";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			int pid=0;
			if(sc!=null) {
			System.out.println("Enter person ID :");
			pid=sc.nextInt();
		}
		//register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish connection
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ915DB1","root","root");
		
		if(con!=null)
			ps=con.prepareStatement(AGE_CALCULATOR_QUERY);
		
		//set query param values
		if(ps!=null)
			ps.setInt(1,pid);
		//execute the query
		rs=ps.executeQuery();
		//process the resultSet
		if(rs.next()) {
			System.out.println("Person's age is :"+rs.getInt(1));
			}
		else {
			System.out.println("Record not found.");
		}
		}//try
		
catch (SQLException se) {
			
			se.printStackTrace();
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch (SQLException se) {
				
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				
				se.printStackTrace();
			}
		}//finally close
		
		
	}//main close


	}//class close	
				
			

			
			
