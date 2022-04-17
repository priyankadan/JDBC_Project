package myOracleDBConnection;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter emplyee avg sal starts from : ");
		float startAvg=sc.nextFloat();
		System.out.println("Enter emplyee avg sal till : ");
		float endAvg=sc.nextFloat();
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		Statement st=con.createStatement();
		String querry="SELECT ENAME , SAL FROM EMP_NIT WHERE SAL>="+startAvg +"AND SAL<="+endAvg;
		ResultSet rs=st.executeQuery(querry);
		System.out.println(querry);
		boolean isEmpty=true;
		
		while(rs.next())
		{
			isEmpty=false;
			//System.out.print("total no of emp: "+rs.getRow());
			//System.out.println(rs.getInt(1)+" \t\t"+rs.getString(2)+"\t\t "+rs.getString(3)+"\t\t "+rs.getFloat(4));
			System.out.println(rs.getString("ENAME")+" \t\t"+rs.getFloat("SAL"));

		}
		System.out.print("total no of emp: "+rs.getRow());
		if(isEmpty) {
			System.out.println("NO RECORD FOUND");
		}
				
		System.out.print("\ncompleted");
		sc.close();
		rs.close();
		st.close();
		con.close();
	}




	}


