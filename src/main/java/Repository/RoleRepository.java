package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.MysqlConfig;
import Entity.RoleEntity;

public class RoleRepository {
	
	public List<RoleEntity> getAllRole() {
		List<RoleEntity> listRole = new ArrayList<RoleEntity>();
		String query = "SELECT * FROM Role";
		Connection connection = MysqlConfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				RoleEntity role = new RoleEntity();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				
				listRole.add(role);
			}
		} catch (Exception e) {
			System.out.println("lỗi getallrole" + e.getLocalizedMessage());
		}finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("lỗi kết nối getallrole" + e2.getLocalizedMessage());
			}
		}
		return listRole;
	}
	
	public int addRole(RoleEntity role) {
		String query = "INSERT INTO Role(name, description) VALUES (?,?)";
		Connection connection = MysqlConfig.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, role.getName());
			preparedStatement.setString(2, role.getDescription());
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}
	public int deleteRole(int id) {
		String query = "DELETE FROM Role where id = ?";
		Connection connection = MysqlConfig.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	public int updateRole(RoleEntity role) {
		String query = "UPDATE Role SET name = ?,description = ? WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, role.getName());
			preparedStatement.setString(2, role.getDescription());
			preparedStatement.setInt(3, role.getId());
			
			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println(e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	public RoleEntity getRole(int id) {
		String query = "SELECT * FROM Role WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		RoleEntity role = new RoleEntity();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		return role;
	
	}

	
}
