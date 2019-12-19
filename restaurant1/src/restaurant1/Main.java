package restaurant1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	private static final String url = "jdbc:mysql://localhost:3306/БанкетныйЗал?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
	private static final String user = "root";
	private static final String password = "";
	private static Connection con;
	private static Statement stmt;

	
	
	public Main() throws ClassNotFoundException {

		try {
			//Class.forName("com.mysql.fabric.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public static void main1(String[] argv) throws ClassNotFoundException {
		Main cc = new Main();
	}
}