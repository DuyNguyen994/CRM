package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.MysqlConfig;
import Entity.ProjectEntity;

public class ProjectRepository {
	
	public List<ProjectEntity> getAllProject() {
		String query = "SELECT * FROM Project";
		Connection connection = MysqlConfig.getConnection();
		List<ProjectEntity> list = new ArrayList<ProjectEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectEntity project = new ProjectEntity();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));

				list.add(project);
			}
		} catch (Exception e) {
		
			System.out.println( "Loi getallproject" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("Loi connect getallproject" + e.getLocalizedMessage());
			}
		}
		return list;
	}
	
	public int addProject(ProjectEntity project) {
		String query = "INSERT INTO Project(name, startDate, endDate) VALUES (?,?,?)";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, project.getName());
			preparedStatement.setString(2, project.getStartDate());
			preparedStatement.setString(3, project.getEndDate());

			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println("Loi addProject" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("Loi connect" + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public int updateProject(ProjectEntity project) {
		String query = "UPDATE Project SET name = ?, startDate = ?, endDate = ? WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, project.getName());
			preparedStatement.setString(2, project.getStartDate());
			preparedStatement.setString(3, project.getEndDate());
			preparedStatement.setInt(4, project.getId());

			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println("loi updatePJ " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("loi connect" + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public int deleteProject(int id) {
		String query = "DELETE FROM Project where id = ?";
		Connection connection = MysqlConfig.getConnection();
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			count = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println("loi delProject" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("loi connect" + e.getLocalizedMessage());
			}
		}
		return count;
	}
	
	public ProjectEntity getProject(int id) {
		String query = "SELECT * FROM Project WHERE id = ?";
		Connection connection = MysqlConfig.getConnection();
		ProjectEntity project = new ProjectEntity();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("startDate"));
				project.setEndDate(resultSet.getString("endDate"));
			}
		} catch (Exception e) {
			
			System.out.println("loi getProject" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("loi connect" + e.getLocalizedMessage());
			}
		}
		return project;

	}
	
}
