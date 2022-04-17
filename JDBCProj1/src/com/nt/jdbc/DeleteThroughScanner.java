
package com.nt.jdbc;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;

  public class DeleteThroughScanner {
	public static void main(String[] args) throws SQLException {
		
		// TODO Auto-generated method stub
	    Statement stmt =null;
	    Connection con=null;
	    Scanner scn=null;
	    try {
		 scn=new Scanner(System.in);
		 System.out.println("Enter EMPNO:");
		int empno=scn.nextInt();
		
		
		/*System.out.println("Enter ENAME:");
		String ename=scn.next();
		System.out.println("Enter JOB:");
		String job=scn.next();
		System.out.println("Enter SAL:");
		double sal=scn.nextDouble();
		ename="'"+ename+"'";
		job="'"+job+"'";*/
		//System.out.println("Enter degn3:");
		//String degn3=scn.next();
		
		//LOAD DRIVER CLASS
		//Class.forName("oracle.jdbc.driver.OracleDriver");
	
		//ESTBLISH CONNECTION
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		if(con!=null)
			 stmt=con.createStatement();
		//String cond="('"+degn1+"','"+degn2+"')";
		//String query="SELECT ENAME, SAL FROM EMP_NIT WHERE JOB IN('MANAGER','ENGINEER')";
		//String query="SELECT ENAME,JOB,SAL FROM EMP_NIT WHERE JOB IN"+cond+"ORDER BY JOB";
		//String query="INSERT INTO EMP_NIT(EMPNO,ENAME,JOB,SAL) VALUES(9,'RADHARADHA','RANIRANI',3000);
		//String query="INSERT INTO EMP_NIT(EMPNO,ENAME,JOB,SAL) VALUES("+empno+","+ename+","+job+","+sal+")";
		String query="DELETE FROM EMP_NIT WHERE EMPNO="+empno;

		if(stmt!=null) {
		int count=stmt.executeUpdate(query);
		
		if(count==0) 
			System.out.println("record not deleted");
		
		else 
			System.out.println("record deleted");
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