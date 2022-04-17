package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InSensitiveRSTest {
		/*  
					 STUDENT table on Oracle DB:
					 -------------------------------
					 
				 SNO     SNAME   SADD    AVG	  
				1001	adi	    hayaat	99.9
		     	1002	priya	hayyat	90.9
				1003	billu	ggg	    33
				1004	arjun	hayyat	70.9
				1005	avyan	gggg	33
				1006    riyansh	kalkal	70
				1	    priyam	chennai	49.88
				2	    adi	    panaji	99.88
				3	    priyam	chennai	49.88
				4	    adi	    goa	    99.88
		 */

	public static void main(String[] args) {
//		final String GET_STUD_QUERY="SELECT * FROM STUDENT";//AVOID USING '*' IN SENSITIVE RS
		final String GET_STUD_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
				PreparedStatement ps=con.prepareStatement(GET_STUD_QUERY,//query
						ResultSet.TYPE_SCROLL_INSENSITIVE,//RS type....>IT WILL NOT REFLECT ANYTHING IF YOU CHANGE ANYTHING IN DB TABLE DURING SLEEP TIME(HERE 30000 miliseconds)
						ResultSet.CONCUR_UPDATABLE//RS mode
						);ResultSet rs=ps.executeQuery();){
			
			if(rs!=null) {
				System.out.println(".......Records top to bottom ......");
				System.out.println(rs.getRow());//to get cursor posion in current row
				
				int count=0;
				while(rs.next()) {
					if(count==0) {
						System.out.println("Modify DB table record in this sleep time");
						Thread.sleep(20000);				
					}
					//rs.refreshRow(); //DONT WRITE THIS METHOD IN THIS CASE OF INSENSITIVE RS
					count++;
					System.out.println(rs.getInt(1)+"\t "+rs.getString(2)+" \t"+rs.getString(3)+"\t "+rs.getFloat(4));
				}//while
								
			}//if
						
		}//try
		catch(SQLException se) {
			System.out.println("Please use valid row number");
		se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
