package com.nt.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SelectTest5 {

	public static void main(String[] args)   {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//register jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
			//Create statement object
			if(con!=null)
			stmt=con.createStatement();
			//prepare query
			//String query="SELECT * FROM EMP WHERE SAL=(SELECT MAX(SAL)FROM EMP)";
			String query="SELECT * FROM EMP";
			System.out.println(query);
			//send and execute the query
			if(stmt!=null)
			rs=stmt.executeQuery(query);
			//process the ResulrSet object
			if(rs!=null) {
				boolean isEmpty=true;
				while(rs.next()) {
					isEmpty=false;
					System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4));
					
				}
				if(isEmpty) {
					System.out.println("NO RECORD FOUND");
				}
				else 
					System.out.println("RECORD FOUND AND DISPLAYED");
					
			}//if condition close
			}//try block close
			catch(SQLException se) {
				se.printStackTrace();
			}
			/*catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			}*/
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				//close jdbc objs
				try {
					if(rs!=null)
						rs.close();	
					
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(stmt!=null)
						stmt.close();	
					
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				try {
					if(con!=null)
						con.close();	
					
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}//final block end
			}// end of mail()method
		

	}// end of class


