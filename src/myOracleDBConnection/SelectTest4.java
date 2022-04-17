package myOracleDBConnection;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class SelectTest4 {

	public static void main(String[] args) throws SQLException {
		
		// TODO Auto-generated method stub
	    Statement stmt =null;
	    Connection con=null;
	    ResultSet rs=null;
	    Scanner scn=null;
	    try {
		 scn=new Scanner(System.in);
		System.out.println("Enter degn1:");
		String degn1=scn.next();
		System.out.println("Enter degn2:");
		String degn2=scn.next();
		//System.out.println("Enter degn3:");
		//String degn3=scn.next();
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		if(con!=null)
			 stmt=con.createStatement();
		String cond="('"+degn1+"','"+degn2+"')";
		//String query="SELECT ENAME, SAL FROM EMP_NIT WHERE JOB IN('MANAGER','ENGINEER')";
		String query="SELECT ENAME,JOB,SAL FROM EMP_NIT WHERE JOB IN"+cond+"ORDER BY JOB";
		
		if(stmt!=null) {
		 rs=stmt.executeQuery(query);
		}
		
		if(rs!=null) {
		boolean isEmpty=true;
		while(rs.next()) {
			isEmpty=false;
			System.out.println(rs.getString("ENAME")+" \t\t"+rs.getString("JOB")+"\t\t"+rs.getFloat("SAL"));
			
		}
		if(isEmpty) {
			System.out.println("NO RECORD FOUND");
		}
		System.out.print("\ncompleted");
	    }
	    }
	    
	    catch(SQLException se) {
	    	se.printStackTrace();
	    }
	     
	    catch(Exception e) {
	    	e.printStackTrace();
	    }
	    if(con!=null) {
	    	con.close();
	    	
	    }
		scn.close();
		rs.close();
		
		
		
		
		

	}

}
