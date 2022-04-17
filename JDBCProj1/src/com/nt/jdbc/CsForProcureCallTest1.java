package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;


public class CsForProcureCallTest1 {
	public static final String CALL_PROCEDURE="{call first_pro(?,?)}";

	public static void main(String[] args) {
try( Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		CallableStatement cs=con.prepareCall(CALL_PROCEDURE);
		){
	//read input values
	int no=0;
	if(sc!=null){
		System.out.println("Enter number : ");
		no=sc.nextInt(); 
	}//if
	
	if(cs!=null) {
		//register ouput param jdbc types
		cs.registerOutParameter(2, Types.INTEGER);
		
		//set value to IN param
		cs.setInt(1, no);;
		//call Pl/SQL procedure
		cs.execute();
		//gether results from out param
		int result=cs.getInt(2);
		System.out.println("Square values:: "+result);
			
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
