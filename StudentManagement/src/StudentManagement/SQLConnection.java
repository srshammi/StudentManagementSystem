package StudentManagement;

import java.sql.*;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class SQLConnection {
	
	 static Connection conn;
	 
	 public static Connection ConnecrDb(){
	 try {
		 Class.forName("com.mysql.jdbc.Driver");
         //Connection conn = DriverManager.getConnection ("jdbc:mysql://localhost:3306/testproject","root","");
         Connection conn = DriverManager.getConnection ("jdbc:mysql:DB//StudentManagement.sql");
		 JOptionPane.showMessageDialog(null, "Connected");
		 return conn;
	 }
	 catch (Exception e){
		 JOptionPane.showMessageDialog(null,"Wrong Entry, Try Again");
		 return null;
	 }
	 }
}