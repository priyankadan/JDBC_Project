package com.nt.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PS_ScrollableRSTest {
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
		final String GET_STUD_QUERY="SELECT * FROM STUDENT";
		try(
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","SYSTEM","biplov");
				PreparedStatement ps=con.prepareStatement(GET_STUD_QUERY,//query
						ResultSet.TYPE_SCROLL_SENSITIVE,//RS type
						ResultSet.CONCUR_UPDATABLE//RS mode
						);ResultSet rs=ps.executeQuery();){
			
			if(rs!=null) {
				System.out.println(".......Records top to bottom ......");
				System.out.println(rs.getRow());//to get cursor posion in current row
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				System.out.println("================================================================");
				rs.afterLast();
				System.out.println(rs.getRow());//cursor posion at 0
				
				System.out.println("......Records bottom to top......");
				while(rs.previous()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					
				}//while
				System.out.println("================================================================");
				rs.first();
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println(rs.getRow());//cursor posion at 1
				System.out.println("================================================================");
				rs.last();
				System.out.println(rs.getRow());
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");
				rs.relative(-3);
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");
				rs.relative(2);
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");
				rs.absolute(4);
				System.out.println(rs.getRow());
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");
				rs.absolute(-5);
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");
				System.out.println(rs.getRow());
				rs.relative(5);//Exhausted Resultset
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("================================================================");

				
				
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
