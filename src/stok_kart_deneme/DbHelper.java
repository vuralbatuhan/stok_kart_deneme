package stok_kart_deneme;

import java.sql.*;

public class DbHelper {
	private String userName ="root";
	private String password = "1q2w3e4r";
	private String dbUrl = "jdbc:mysql://localhost:3306/stok_kart?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(dbUrl,userName,password);
    }
    
    public void showErrorMessage(SQLException exception) {
    	System.out.println("ERROR : " + exception.getMessage());
    	System.out.println("Error code : " + exception.getErrorCode());
    } 
}
