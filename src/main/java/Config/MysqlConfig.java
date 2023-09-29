package Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm?allowMultiQueries=true","root","admin123");
		} catch (Exception e) {
			System.out.println("Lỗi kết nối Database " + e.getLocalizedMessage());
			return null;
		}
	}

}
