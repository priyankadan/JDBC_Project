package myOracleDBConnection;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {
	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter emplyee's job role");
		String desg=sc.next().toUpperCase();
		desg="'"+desg+"'";
		
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		Statement st=con.createStatement();
		
		String querry="SELECT ENAME,SAL FROM EMP_NIT WHERE JOB="+desg;
		//String querry="SELECT * FROM EMP_NIT WHERE JOB="+desg;
		ResultSet rs=st.executeQuery(querry);
		System.out.println(querry);
		boolean isEmpty=true;
		
		while(rs.next())
		{
			isEmpty=false;
			//System.out.print(rs.getRow());
			//System.out.println(rs.getInt(1)+" \t\t"+rs.getString(2)+"\t\t "+rs.getString(3)+"\t\t "+rs.getFloat(4));
			System.out.println(rs.getString("ENAME")+" \t\t"+rs.getFloat("SAL"));

		}
		if(isEmpty) {
			System.out.println("NO RECORD FOUND for"+desg);
		}
				
		System.out.print("\ncompleted");
		sc.close();
		rs.close();
		st.close();
		con.close();
	}

}
