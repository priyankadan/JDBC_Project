package com.nt.jdbc;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class LogginApp {


	public static void main(String[] args) throws SQLException {
		
		// TODO Auto-generated method stub
	    Statement stmt =null;
	    ResultSet rs=null;
	    Connection con=null;
	    Scanner scn=null;
	    try {
		 scn=new Scanner(System.in);
		 String username=null;
		 String password=null;
		
		 if(scn!=null) {
		System.out.println("Enter username:");//
		username=scn.nextLine();
		System.out.println("Enter password:");
		password=scn.nextLine();
		 }
		
		username="'"+username+"'";
		password="'"+password+"'";
		
		
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		if(con!=null)
			 stmt=con.createStatement();
		
		String query="select count(*) from userInfo where username="+username+" and password="+password;
		System.out.println(query);
		if(stmt!=null) {
		rs=stmt.executeQuery(query);
		
		if(rs!=null) {
			rs.next();
		int count=rs.getInt(1);	
		if(count==0) {
			System.out.println("invalid credential");
		}
		else {
			System.out.println("valid credential");
		}
		}	//end of if block
	    }//end of try
	    }
	    
	    catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		//catch (ClassNotFoundException cnf) {
			// TODO Auto-generated catch block
		//	cnf.printStackTrace();
		//}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//CLOSE JDBC OBJECTS
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch (SQLException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
			try {
				if(stmt!=null)
					stmt.close();
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