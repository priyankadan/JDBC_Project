package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class DateInsertTest {
	private static final String INSERT_PERSON_DATES="INSERT INTO JDBC_PERSON_DATES VALUES (?,?,?,?,?)";
	
	public static void main(String [] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		try {
		sc=new Scanner(System.in);
		String sdob=null, sdoj=null, sdom=null, pname=null;
		int pid=0;
				if(sc!=null) {
					
					System.out.println("Enter person ID : ");
					pid=sc.nextInt();
					
					System.out.println("Enter Person name: ");
					pname=sc.next();
					
					System.out.println("Enter DOB (dd-MM-yyyy): ");
					sdob=sc.next();

					System.out.println("Enter DOJ (MM-dd-yyyy): ");
				    sdoj=sc.next();

					System.out.println("Enter DOM (yyyy-MM-dd): ");
					 sdom=sc.next();

					
			
		}
				//if converting String Date values to java.sql.Date class object
				
				//for DOB:
				
				//to java.util.Date class object
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date udob=sdf.parse(sdob);
				
				//to java.sql.Date class object
				long ms=udob.getTime();
				java.sql.Date sqdob=new java.sql.Date(ms);
				
				
				//for DOJ:
				SimpleDateFormat sdf1=new SimpleDateFormat("MM-dd-yyyy");
				java.util.Date udoj=sdf1.parse(sdoj);
				
				//to java.sql.Date class object
				long ms1=udoj.getTime();
				java.sql.Date sqdoj=new java.sql.Date(ms1);
				
				
				//for DOM:			
				//to java.sql.Date class object				
				java.sql.Date sqdom=java.sql.Date.valueOf(sdom);
				
				//register JDBC driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
				//con=DriverManager.getConnection("jdbc:mysql:///ntaj915db1","root","root");
				//create PreparedStatement object having pre-compiled sql query
				if(con!=null)
				ps=con.prepareStatement(INSERT_PERSON_DATES);
				
				//set values to query param
				ps.setInt(1, pid);
				ps.setString(2, pname);
				ps.setDate(3, sqdob);
				ps.setDate(4, sqdoj);
				ps.setDate(5, sqdom);
				
				//execute the sql queries
				int count=0;
				if(ps!=null)
				count=ps.executeUpdate();
				//process the result
				if(count==0)
					System.out.println("RECORD NOT INSERTED");
				else
					System.out.println("RECORD INSERTED");
		}//try end
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
				
			
