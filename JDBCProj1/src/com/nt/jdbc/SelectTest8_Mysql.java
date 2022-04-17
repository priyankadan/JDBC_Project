package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest8_Mysql {
	

	public static void main(String[] args) throws Exception  {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		//LOAD JDBC DRIVER CLASS
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try {
			//ESTABLISH THE CONNECTION
			//con=DriverManager.getConnection("jdbc:mysql:///NTAJ915DB","root","root");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/NTAJ915DB","root","root");
			//CREATE STAEMENT
			if(con!=null)
			st=con.createStatement();
			
			//PREPARE SQL QUERY
			String query="SELECT * FROM student";
			//EXECUTE THE QUERY
			if(st!=null)
				rs=st.executeQuery(query);
			//PROCESS THE RESULTSET OBJECT
			if(rs!=null) {
				boolean isEmpty=true;
				while(rs.next()) {
					isEmpty=false;
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				}//end of while
				if(isEmpty) {
				System.out.println("NO RECORD FOUND");	
				}
				else {
					System.out.println("RECORD FOUND AND DISPLAYED");
				}//end of else 
			}//end of if
		}//end of try
			
			
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
				if(st!=null)
					st.close();
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