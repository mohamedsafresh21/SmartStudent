package create;


	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class DatabaseConnection {
	    private static final String URL = "jdbc:mysql://localhost:3306/ssdb";
	    private static final String USER = "root"; // replace with your MySQL username
	    private static final String PASSWORD = "1234"; // replace with your MySQL password

	    public static Connection getConnection() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            return DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	}

