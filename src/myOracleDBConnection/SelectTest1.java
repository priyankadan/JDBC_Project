package myOracleDBConnection;

import java.sql.*;
import java.sql.DriverManager;

public class SelectTest1 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM EMP_NIT");
		//ResultSet rs=st.executeQuery("SELECT ENAME, SAL FROM EMP_NIT WHERE JOB = MANAGER ");
		//System.out.print(rs.getRow());
		//System.out.println(rs.getInt(1)+"\T\T "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		
		while(rs.next())
		{
			//System.out.print(rs.getRow());
			System.out.println(rs.getInt(1)+" \t\t"+rs.getString(2)+"\t\t "+rs.getString(3)+"\t\t "+rs.getFloat(4));
			
		}
		System.out.print("\ncompleted");
		rs.close();
		st.close();
		con.close();
	

	}

}
