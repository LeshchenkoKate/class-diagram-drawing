package restaurant1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingUtilities;

public class Registrate {
	
	//public static final Statement statement;
	private static final String url = "jdbc:mysql://localhost:3306/БанкетныйЗал?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
	private static final String user = "root";
	private static final String password = "";
	private static Connection con;
	static Statement statement;
	
	public Registrate() {
		try {
			con = DriverManager.getConnection(url, user, password);
			statement = con.createStatement();
		}catch(SQLException e){ e.printStackTrace();
			
		}
	}
	

	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Registrate();
				
				Enter ent = new Enter();
			}	
		});
	}
}
