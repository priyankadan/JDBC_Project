package myOracleDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ConnTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//oracle.jdbc.driver.OracleDriver driver=new oracle.jdbc.driver.OracleDriver();
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		//THIS LINE TO CONNECT ORACLE DATABASE SERVER
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		
		
		if(con==null) {
			System.out.println("connection not established");
		}
		else
		{
			System.out.println("connection established");
		
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM SYSTEM.SCHOOL");
			while(rs.next())
			{
				//System.out.print(rs.getRow());
				System.out.println(rs.getInt(1)+" "+rs.getString(2));//+" "+rs.getString(3)+" "+rs.getFloat(4));
				
			}
			System.out.print("\ncompleted");
			rs.close();
			st.close();
			con.close();
		}

	}

}
