package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*
CREATE OR REPLACE PROCEDURE P_STUDENT_DETAILS_BY_NO_NEW 
(
  NO IN student.sno%TYPE 
,o_c_dbuser OUT SYS_REFCURSOR
) AS 
BEGIN
    OPEN o_c_dbuser FOR
    SELECT SNAME NAME,SADD ADDRESS,AVG AVARAGE FROM STUDENT WHERE SNO=NO;
END P_STUDENT_DETAILS_BY_NO_NEW;  */

public class CsForProcureCall_Get_StudentDetails_ByID_Test5_NEW {
	public static final String CALL_P_GET_STUDENT_DETAILS_BY_NO="{call P_STUDENT_DETAILS_BY_NO_NEW(?,?)}";

	public static void main(String[] args) {
try( Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		CallableStatement cs=con.prepareCall(CALL_P_GET_STUDENT_DETAILS_BY_NO);
		){
	//read input values
	int num=0;
	if(sc!=null){
		System.out.println("Enter Student NO : ");
		num=sc.nextInt(); 
	}//if
	
	if(cs!=null) {
		//register ouput param jdbc types
		//cs.registerOutParameter(2, Types.VARCHAR);
		//cs.registerOutParameter(3, Types.VARCHAR);
		//cs.registerOutParameter(4, Types.FLOAT);

		
		//set value to IN param
		cs.setInt(1, num);
		
		 cs.registerOutParameter(2, OracleTypes.CURSOR);
		
		//call Pl/SQL procedure
		cs.execute();
		
		//gather results from OUT param
		// get refcursor and convert it to ResultSet
        ResultSet resultSet = (ResultSet) cs.getObject(2);
        while (resultSet.next()) {
        /*
         *  NAME OUT VARCHAR2 
, ADDRESS OUT VARCHAR2 
, AVARAGE OUT NUMBER 
         */
        	System.out.println("Student name:: "+resultSet.getString("NAME"));
    		System.out.println("Student address:: "+resultSet.getString("ADDRESS"));
    		System.out.println("Student avarage:: "+resultSet.getString("AVARAGE"));
        }

		
		/*System.out.println("Student name:: "+cs.getString(2));
		System.out.println("Student address:: "+cs.getString(3));
		System.out.println("Student avarage:: "+cs.getFloat(4));
		*/
			
	}//if
	
}//try
catch(SQLException se) {
	System.out.println("INVALID STUDENT NUMBER");
	se.printStackTrace();
}
catch(Exception e) {
	e.printStackTrace();
}//catch
	}//main

}//class
