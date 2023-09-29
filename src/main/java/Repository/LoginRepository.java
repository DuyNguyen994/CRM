package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Config.MysqlConfig;

public class LoginRepository {
	public boolean getLogin(String email, String password) {
		 String query = "SELECT *\r\n"
					+ "FROM Users u\r\n"
					+ "WHERE u.email = ? and u.pwd = ?";
		 Connection connection = MysqlConfig.getConnection();
		 boolean isSuccess = false;
		 
		 try {
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1,email);
			 statement.setString(2,password);
			 
			 ResultSet resultSet = statement.executeQuery();
			 isSuccess = resultSet.next();
		} catch (Exception e) {
			System.out.println("Lỗi connect " + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi connect " + e2.getLocalizedMessage());
			}
		}
		 return isSuccess;
	}
}
