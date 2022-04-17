package com.nt.jdbc;

import java.util.Date;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Connection;

public class PersonAgeCalculatorAppUsingJavaCode {
	
	//public static final String AGE_CALCULATOR_QUERY=" SELECT DATEDIFF(NOW(),DOB)/365 FROM JDBC_PERSON_DATES WHERE PID = ? ";
	public static final String GET_DOB_QUERY=" SELECT DOB FROM JDBC_PERSON_DATES WHERE PID = ? ";


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
		//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ915DB1","root","root");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		if(con!=null)
			ps=con.prepareStatement(GET_DOB_QUERY);
		
		//set query param values
		if(ps!=null)
			ps.setInt(1,pid);
		
		//execute the query
		rs=ps.executeQuery();
		
		//process the resultSet
		/*if(rs.next()) {
			System.out.println("Person's age is :"+rs.getInt(1));
			}*/
		java.sql.Date sqdob=null;
		if(rs.next()) {
			sqdob=rs.getDate(1);
			java.util.Date sysDate= new Date();
			long dobMS=sqdob.getTime();
			long sysDateMS=sysDate.getTime();
			float age=((sysDateMS-dobMS)/(1000.0f*60.0f*60.0f*24.0f*365.25f));//365.25 for leap year 365 for normal year
			System.out.println("Person age :"+age);
			
			
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
				
			

			
			
