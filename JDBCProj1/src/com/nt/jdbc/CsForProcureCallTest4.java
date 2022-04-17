package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



/*CREATE OR REPLACE PROCEDURE P_AUTHENTICATION 
(
  UNAME IN VARCHAR2 
, PWD IN VARCHAR2 
, RESULT OUT VARCHAR2 
) AS 
CNT NUMBER(2);
BEGIN
  SELECT COUNT (*) INTO CNT FROM USERINFO WHERE USERNAME=UNAME AND PASSWORD=PWD;
  IF(CNT<>0)THEN
  RESULT:='VALID CREDENTIAL';
  ELSE
  RESULT:='INVLID CREDENTIAL';
  END IF;
END P_AUTHENTICATION;*/


public class CsForProcureCallTest4 {
	public static final String CALL_PROCEDURE_QUERY="{CALL P_AUTHENTICATION(?,?,?)}";

	public static void main(String[] args) {
try( Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
		CallableStatement cs=con.prepareCall(CALL_PROCEDURE_QUERY);
		){
	
	//read input values
	String user=null, pass=null;
	System.out.println("Enter Username::");
	user=sc.next();
	System.out.println("Enter Password::");
	pass=sc.next();
	
	//register OUT param with jdbc datatype
	cs.registerOutParameter(3, Types.VARCHAR);
	
	//set values to IN pram
	cs.setString(1,user);
	cs.setString(2, pass);
	
	//call PL/SQL procedure
	cs.execute();
	
	//gather result from OUT param
	String result=cs.getString(3);
	System.out.println("Result:::"+result);
	
	
}//try
catch(SQLException se) {
	System.out.println("Employee not found");
	se.printStackTrace();
}
catch(Exception e) {
	e.printStackTrace();
}//catch
	}//main

}//class
