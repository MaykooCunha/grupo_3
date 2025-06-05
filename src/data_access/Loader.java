package Dao;

import java,sql.Connection;

public class Dao {

	private Connection con; 
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/refrigeradbq";
	private String user = "root";
	private String password = "143094";
	
	public Connection conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password); 
			return con;
		} catch	(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

}
public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("ERRO NA CONEXÃO:");
            throw new RuntimeException("Falha na conexão", e);
        }
    }
}
