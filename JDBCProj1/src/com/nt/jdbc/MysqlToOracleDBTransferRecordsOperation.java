package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlToOracleDBTransferRecordsOperation {
	
	private static final String ORACLE_STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(ORA_SNO_SEQ.NEXTVAL,?,?,?)";
	private static final String MYSQL_GET_STUDENTS_QUERY="SELECT SNAME, SADD, AVG FROM STUDENT";

	public static void main(String[] args) {
		
		try (  
				//load JDBC class
				//Class.forName("com.mysql.cj.jdbc.Driver");
				//Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//Establish the connection
				Connection sqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj915db","root","root");
				Connection oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
				
				//Create PreparedStatement object 
				Statement msqlST=sqlCon.createStatement();
				PreparedStatement oraPS=oraCon.prepareStatement(ORACLE_STUDENT_INSERT_QUERY);
				
				//Execute the query on table of mysql db
				ResultSet rs=msqlST.executeQuery(MYSQL_GET_STUDENTS_QUERY);
				){
			//Process the result set(mysql) and insert each record into oracle DB table
			if(rs!=null) {
				while(rs.next()) {
					//get each record of mysql DB table through rs
					String name=rs.getString(1);
					String addrs=rs.getString(2);
					Float avg=rs.getFloat(3);
					
					//set each received record values to insert SQL query to oracle
					oraPS.setString(1,name);
					oraPS.setString(2,addrs);
					oraPS.setFloat(3,avg);
					
					//execute the query
					oraPS.executeUpdate();
					}//while
				System.out.println("Record is transfered from mysql db table to oracle db table.");
			}//if
			
		}//try(All JDBC object will be closed here autmatically)
		
		catch (SQLException se) {				
				se.printStackTrace();
				System.out.println("PROBLEM IN TRANSFERING RECORDS");
			}
		
			catch(Exception e){				
				e.printStackTrace();
				System.out.println("PROBLEM IN TRANSFERING RECORDS");
			}
		
	}

}
