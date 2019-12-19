package src;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
//import java.sql.Statement;


public class DBConection {

	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost:3306/diplom?useLegacyDatetimeCode=false&serverTimezone=America/New_York";

	static final String USER = "admin";
	static final String PASSWORD = "root";

	//private static Statement stmt;
	CallableStatement callableStatement = null;
	Connection connection = null;

	static Statement statement;
	
	
	public DBConection() throws ClassNotFoundException {

		try {
			// Class.forName("com.mysql.fabric.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
			//statement = connection.createStatement();
			

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		//return true;
	}

	public String addAttribute(String name, String Type, String AccessModifier) throws SQLException {

		String Attribute;

		try {
			String SQL = "{call addAttribute (?, ?, ?)}";
			callableStatement = connection.prepareCall(SQL);

			// int developerID = 1;
			callableStatement.setString(1, name);
			callableStatement.setString(2, Type);
			callableStatement.setString(3, AccessModifier);

			// callableStatement.registerOutParameter(2, Types.VARCHAR);

			// System.out.println("Executing procedure...");
			callableStatement.execute();

			// Attribute = callableStatement.getString(2);
			Attribute = AccessModifier +" "+ Type +" "+ name +";";
			System.out.println("Attribute: " + Attribute);

		} finally {
			if (callableStatement != null) {
				callableStatement.close();
			}
			if (connection != null) {
				connection.close();
			}

		}
		//ResultSet query = DBConection.statement.executeQuery("SELECT * FROM AccessModifier ORDER BY idAccessModifier DESC LIMIT 1;");

		return Attribute;
	}
		public String addMethod(String name, String AccessModifier) throws SQLException {

			String Method;

			try {
				String SQL = "{call addMethod (?, ?)}";
				callableStatement = connection.prepareCall(SQL);

				// int developerID = 1;
				callableStatement.setString(1, name);
				callableStatement.setString(2, AccessModifier);

				// callableStatement.registerOutParameter(2, Types.VARCHAR);

				// System.out.println("Executing procedure...");
				callableStatement.execute();

				// Attribute = callableStatement.getString(2);
				Method = AccessModifier +" "+ name + "()" +";";
				System.out.println("Method: " + Method);

			} finally {
				if (callableStatement != null) {
					callableStatement.close();
				}
				if (connection != null) {
					connection.close();
				}

			}

		return Method;

	}
}
