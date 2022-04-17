package com.nt.jdbc;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;



public class InsertIntoJDBC_PRODUCTdb_MySQL_BYscanner {


	public static void main(String[] args) throws SQLException {
		
		// TODO Auto-generated method stub
	    Statement stmt =null;
	    Connection con=null;
	    Scanner scn=null;
	    try {
		 scn=new Scanner(System.in);
		 System.out.println("Enter PRODUCT ID:");
		int pid=scn.nextInt();
		System.out.println("Enter PRODUCT NAME:");
		String pname=scn.next();
		System.out.println("Enter PRICE:");
		float price=scn.nextFloat();
		System.out.println("Enter QUANTITY:");
		float qty=scn.nextFloat();
		pname="'"+pname+"'";
		//System.out.println("Enter degn3:");
		//String degn3=scn.next();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:mysql:///ntaj915db1","root","root");
		
		if(con!=null)
			 stmt=con.createStatement();
		//String cond="('"+degn1+"','"+degn2+"')";
		//String query="SELECT ENAME, SAL FROM EMP_NIT WHERE JOB IN('MANAGER','ENGINEER')";
		//String query="SELECT ENAME,JOB,SAL FROM EMP_NIT WHERE JOB IN"+cond+"ORDER BY JOB";
		//String query="INSERT INTO EMP_NIT(EMPNO,ENAME,JOB,SAL) VALUES(9,'RADHARADHA','RANIRANI',3000);
		String query="INSERT INTO jdbc_product(pid,pname,price,qty) VALUES("+pid+","+pname+","+price+","+qty+")";

		if(stmt!=null) {
		int count=stmt.executeUpdate(query);
		
		if(count==0) 
			System.out.println("record not inserted");
		
		else 
			System.out.println("record inserted");
		}	//end of if block
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