package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import Config.MysqlConfig;
import Entity.RoleEntity;
import Entity.UserEntity;


public class UserRepository {
	private UserEntity user = new UserEntity();
	
	public int addUser(UserEntity user) {
		int count = 0;
		String query = "INSERT INTO Users (email, pwd, firstName, lastName, fullName,userName, phone, id_role)\r\n"
									+ "	VALUES (?,?,?,?,?,?,?,?)";	
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement =connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFullName());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPhone());
			statement.setInt(8, user.getIdRole());
			
			count = statement.executeUpdate();			
		}catch(Exception e){
			System.out.println("Lỗi thêm dữ liệu User" + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();			
			}catch(SQLException e){
				System.out.println("Lỗi đóng kết nối "+ e.getLocalizedMessage());
			}
		}		
		return count;
	}
	
	public List<UserEntity> getAllUsers(){
		String query = "SELECT * FROM Users u JOIN Role r ON r.id = u.id_role";
		Connection connection = MysqlConfig.getConnection();
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserEntity user = new UserEntity();
				user.setEmail(resultSet.getString("email"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setFullName(resultSet.getString("fullName"));
				user.setImage(resultSet.getString("image"));
				user.setPhone(resultSet.getString("phone"));
				user.setPwd(resultSet.getString("pwd"));
				user.setUserName(resultSet.getString("userName"));
				
				user.setId(resultSet.getInt("id"));
				user.setIdRole(resultSet.getInt("id_role"));
				
				RoleEntity role = new RoleEntity();
				role.setId(resultSet.getInt("id_role"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
				
				listUser.add(user);				
			}
		} catch (Exception e) {
			System.out.println("Loi lay danh sach User " + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Error " + e2.getLocalizedMessage() );
			}
		}
		return listUser;
	}
	
	public int deleteById(int id) {
		String query = "DELETE FROM Users u WHERE u.id = ?";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt( 1, id);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Lỗi xóa ID " + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("lỗi connect " + e2.getLocalizedMessage());
			}
		}
		return count;
		
	}
	
	public int updateUser(UserEntity user){
	
		String query = "UPDATE Users SET email = ?, pwd = ?, firstName = ?, lastName = ?, fullName = ?, userName = ?, phone = ?, id_role = ? WHERE id = ?";
		int count = 0;
		Connection connection = MysqlConfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPwd());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getFullName());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPhone());
			statement.setInt(8, user.getIdRole());
			statement.setInt(9, user.getId());
			
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("loi update" + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("loi ket noi update" + e2.getLocalizedMessage());
			}
		}
		return count;
	}

	public UserEntity getUser(int id) {
		String query = "SELECT * FROM Users u JOIN Role r ON u.id_role = r.id WHERE u.id = ?";
		Connection connection = MysqlConfig.getConnection();
		UserEntity userView = new UserEntity();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				userView.setId(resultSet.getInt("id"));
				userView.setIdRole(resultSet.getInt("id_role"));

				userView.setEmail(resultSet.getString("email"));
				userView.setFirstName(resultSet.getString("firstName"));
				userView.setFullName(resultSet.getString("fullName"));
				userView.setImage(resultSet.getString("image"));
				userView.setLastName(resultSet.getString("lastName"));
				userView.setPhone(resultSet.getString("phone"));
				userView.setPwd(resultSet.getString("pwd"));
				userView.setUserName(resultSet.getString("userName"));

				RoleEntity role = new RoleEntity();
				role.setName(resultSet.getString("name"));
				userView.setRole(role);
			}
		} catch (Exception e) {
			
			System.out.println("loi xem User" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("loi connect xem User" + e.getLocalizedMessage());
			}
		}
		return userView;
	}
	
	
	
}
