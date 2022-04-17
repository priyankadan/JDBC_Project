package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CsForProcureCallTest3 {
	public static final String CALL_P_GET_EMP_DETAILS_BY_ID="{call P_GET_EMP_DETAILS_BY_ID(?,?,?,?)}";

	public static void main(String[] args) {
try( Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		CallableStatement cs=con.prepareCall(CALL_P_GET_EMP_DETAILS_BY_ID);
		){
	//read input values
	int no=0;
	if(sc!=null){
		System.out.println("Enter Employee ID : ");
		no=sc.nextInt(); 
	}//if
	
	if(cs!=null) {
		//register ouput param jdbc types
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.registerOutParameter(4, Types.VARCHAR);

		
		//set value to IN param
		cs.setInt(1, no);
		
		//call Pl/SQL procedure
		cs.execute();
		
		//gather results from OUT param
		
		System.out.println("Employee name:: "+cs.getString(2));
		System.out.println("Employee designation:: "+cs.getString(3));
		System.out.println("Employee salary:: "+cs.getString(4));
			
	}//if
	
}//try
catch(SQLException se) {
	se.printStackTrace();
}
catch(Exception e) {
	e.printStackTrace();
}//catch
	}//main

}//class
