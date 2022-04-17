package com.nt.jdbc;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PSUpdateThroughScanner {
	
	//String updateString ="update COFFEES set SALES = ? where COF_NAME = ?";
	private static final String UPDATE_STUDENT_BY_SNO = "UPDATE STUDENT SET SADD= ?,AVG= ? WHERE SNO= ?";


	public static void main(String[] args)  {
		
		
	    PreparedStatement ps =null;
	    Connection con=null;
	    Scanner scn=null;
	    try {
	    	//READ INPUT
		 scn=new Scanner(System.in);
		 int no=0;
		 String newAdds=null;
		 float newAvg=0.0f;
		 if(scn!=null) {
		 System.out.println("ENTER STUDENT NO TO UPDATE HIS DETAILS :");
		no=scn.nextInt();
		System.out.println("ENTER NEW ADDRESS FOR STUDENT :");
		newAdds=scn.next();
		System.out.println("ENTER NEW AVG FOR STUDENT :");
		newAvg=scn.nextFloat();
		 }
		 
		 //REGISTER JDBC DRIVER SOFTWARE[OPTIONAL]
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//ESTABLISH HE CONNECTION
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		//CREATE STATEMENT OBJECT
		if(con!=null)
			ps=con.prepareStatement(UPDATE_STUDENT_BY_SNO);
		
		//DET PRE-COMPILED SQL QUERY PARAM VALUES
		if(ps!=null) {
			ps.setString(1, newAdds);
			ps.setFloat(2, newAvg);
			ps.setInt(3, no);
			
			//ps.setInt(1, no);
			//ps.setString(2, newAdds);
			//ps.setFloat(3, newAvg);
			
			
		}
		
		//SEND AND EXECUTE THE SQL QUERY
		int count=0;
		if(ps!=null) {
		count=ps.executeUpdate();
		}
		
		
		//PROCESS THE RESULT
		if(count==0) 
			System.out.println("record not updated");
		
		else {
			System.out.println("record updated"); 
		}	//end of if block
	    }//end of try

	    
	    catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		//catch (ClassNotFoundException cnf) {
			
		//cnf.printStackTrace();
		//}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//CLOSE JDBC OBJECTS
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