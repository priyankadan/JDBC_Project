package com.nt.jdbc;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PSbulkUpdateThroughScanner {
	
	//String updateString ="update COFFEES set SALES = ? where COF_NAME = ?";//for an example
	
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
			 System.out.println("Total no of students need to update now : ");
			  int loopCount=scn.nextInt();
			for(int i=1;i<=loopCount;++i) {
				 System.out.println("TO UPDATE THEIR DETAILS");
				
			 System.out.println("ENTER STUDENT"+i+" NO  :");
			no=scn.nextInt();
			System.out.println("ENTER NEW ADDRESS FOR STUDENT :");
			newAdds=scn.next();
			System.out.println("ENTER NEW AVG FOR STUDENT :");
			newAvg=scn.nextFloat();
			
			if(con!=null)
				ps=con.prepareStatement(UPDATE_STUDENT_BY_SNO);
			
			int count=0;
			if(ps!=null) {
				ps.setString(1, newAdds);
				ps.setFloat(2, newAvg);
				ps.setInt(3, no);
				
				//SEND AND EXECUTE THE SQL QUERY
				
				count=ps.executeUpdate();
				if(count==0) 
					System.out.println("record not updated");
							
				else 
					System.out.println("record updated"); 
			
			}
			}
			
			
			
			
			
		 }
		 
		 //REGISTER JDBC DRIVER SOFTWARE[OPTIONAL]
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//ESTABLISH HE CONNECTION
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		//CREATE STATEMENT OBJECT
		/*if(con!=null)
			ps=con.prepareStatement(UPDATE_STUDENT_BY_SNO);  */
		
		//DET PRE-COMPILED SQL QUERY PARAM VALUES
		/* int count=0;
		if(ps!=null) {
			ps.setString(1, newAdds);
			ps.setFloat(2, newAvg);
			ps.setInt(3, no);
			
			//SEND AND EXECUTE THE SQL QUERY
			
			count=ps.executeUpdate();
						
		}*/
		
		
		//PROCESS THE RESULT
	/*	if(count==0) 
			System.out.println("record not updated");
		
		else {
			System.out.println("record updated"); 
		}	//end of if block*/
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