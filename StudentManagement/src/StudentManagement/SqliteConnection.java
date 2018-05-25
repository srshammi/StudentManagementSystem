package StudentManagement;

import java.sql.*;
import java.sql.Connection;


public class SqliteConnection {

	Connection conn=null;
	public static Connection dbConnector()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DB\\StudentManagement.sqlite");			
			
			//JOptionPane.showMessageDialog(null, "Connected");
			
			return conn;
		}catch(Exception e)
		{
			//JOptionPane.showMessageDialog(null,"Wrong Entry, Try Again");
			return null;
		}
	}


}


