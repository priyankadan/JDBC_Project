
package myOracleDBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class ConnectDatabaseThroughUserInput {

	public static void main(String[] args) throws Exception {
		Scanner scn = new Scanner(System.in); 
	    System.out.println("Enter server name : ");
	    String  serverName= scn.nextLine();
	    System.out.println("\nEnter port number  : ");
	    String  portNo= scn.nextLine();
	    System.out.println("\nEnter serviceId  :  ");
	    String  serviceId= scn.nextLine();
	    System.out.println("\nEnter username :  ");
	    String  username= scn.nextLine();
	    System.out.println("\nEnter password :  ");
	    String  password= scn.nextLine();
        String url="jdbc:oracle:thin:@"+serverName+":"+portNo+":"+serviceId;
        scn.close();
        System.out.println("\""+url+"\""+","+"\""+username+"\",\""+password+"\"" );
        
        System.out.println(url+","+username+","+password);
        //("jdbc:oracle:thin:@localhost:1521:orcl","SCOTT","tiger");
		
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection con=DriverManager.getConnection(url,username,password);
	    if(con==null) {
			System.out.println("connection not established");
		}
		else
			System.out.println("connection established");
	    }

}
