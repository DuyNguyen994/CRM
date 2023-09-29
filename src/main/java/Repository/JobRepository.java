package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Config.MysqlConfig;
import Entity.JobEntity;
import Entity.ProjectEntity;
import Entity.StatusEntity;
import Entity.UserEntity;

public class JobRepository {
	public List<JobEntity> getAllJob() {
		String query = "SELECT j.*,u.fullName,p.name,s.name FROM Job_Status_Users jsu\r\n"
				+ "JOIN Job j ON jsu.id_job = j.id\r\n"
				+ "JOIN Users u ON jsu.id_user = u.id\r\n"
				+ "JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "JOIN Project p ON j.id_project = p.id";
		
		Connection connection = MysqlConfig.getConnection();
		List<JobEntity> list = new ArrayList<JobEntity>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				
				JobEntity job = new JobEntity();
				job.setId(resultSet.getInt("id"));
				job.setName(resultSet.getString("j.name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				job.setIdProject(resultSet.getInt("id_project"));
				
				UserEntity user = new UserEntity();
				user.setFullName(resultSet.getString("fullName"));
				job.setUser(user);
				
				ProjectEntity project = new ProjectEntity();
				project.setName(resultSet.getString("p.name"));
				job.setProject(project);
				
				StatusEntity status = new StatusEntity();
				status.setName(resultSet.getString("s.name"));
				job.setStatus(status);
				
				list.add(job);			
			}
		} catch (Exception e) {
			
			System.out.println(" loi getalljob " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println(" loi connect getalljob " + e.getLocalizedMessage());
			}
		}
		return list;
	}
	public int addJob(JobEntity job) {
		String query = "START TRANSACTION;"
				+ "INSERT INTO Job(name, startDate, endDate, id_project) VALUES (?, ?, ?, ?);\r\n"
				+ "INSERT INTO Job_Status_Users(id_user, id_status, id_job) VALUES (?, ?, last_insert_id());\r\n"
				+ "COMMIT;";
		Connection connection = MysqlConfig.getConnection();
		int n = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, job.getName());
			preparedStatement.setString(2, job.getStartDate());
			preparedStatement.setString(3, job.getEndDate());
	
			preparedStatement.setInt(4, job.getIdProject());
			preparedStatement.setInt(5, job.getUser().getId());
			preparedStatement.setInt(6, job.getIdProject());
			
			preparedStatement.execute();
			
			n = preparedStatement.getUpdateCount();
			
		} catch (Exception e) {
			
			System.out.println("loi addjob " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				System.out.println("loi connect addjob " + e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	public int deleteJob(int id) {
		String query = ""
				+ "DELETE jsu.*,j.*,pu.*\r\n"
				+ "FROM Job_Status_Users jsu\r\n"
				+ "         JOIN Job j ON jsu.id_job = j.id\r\n"
				+ "         JOIN Users u ON jsu.id_user = u.id\r\n"
				+ "         JOIN Status s ON jsu.id_status = s.id\r\n"
				+ "         JOIN Project p ON j.id_project = p.id\r\n"
				+ "         JOIN Project_Users pu ON j.id_project = pu.id_project\r\n"
				+ "WHERE jsu.id_job = j.id AND pu.id_user = jsu.id_user\r\n"
				+ "        AND j.id_project = pu.id_project\r\n"
				+ "        AND j.id = ?;"
				+ "";
		Connection connection = MysqlConfig.getConnection();
		int n = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);

			n = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			System.out.println("loi delJob" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("loi connect delJob" + e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	public int updateJob(JobEntity job) {
		String query = "START TRANSACTION;\r\n"
				+ "    UPDATE Job\r\n"
				+ "        SET name = ?, startDate = ?, endDate = ?,id_project = ? WHERE id = ?;\r\n"
				+ "    UPDATE Job_Status_Users\r\n"
				+ "        SET id_user = ?, id_status = ? WHERE id_job = ?;\r\n"
				+ "COMMIT;";
		
		Connection connection = MysqlConfig.getConnection();
		int n = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, job.getName());
			preparedStatement.setString(2, job.getStartDate());
			preparedStatement.setString(3, job.getEndDate());
			preparedStatement.setInt(4, job.getIdProject());
			preparedStatement.setInt(5, job.getId());
			
			preparedStatement.setInt(6, job.getUser().getId());
			preparedStatement.setInt(7, job.getStatus().getId());
			preparedStatement.setInt(8, job.getId());

			preparedStatement.execute();

			n = preparedStatement.getUpdateCount();
			
		} catch (Exception e) {			
			System.out.println("loi updateJob" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {				
				System.out.println("loi connect updateJob" + e.getLocalizedMessage());
			}
		}
		return n;
	}
	
	public JobEntity getJob(int id) {
		String query = "SELECT j.*,u.id,u.fullName,p.id,p.name,s.id,s.name\r\n"
							+ "FROM Job_Status_Users jsu\r\n"
							+ "         JOIN Job j ON jsu.id_job = j.id\r\n"
							+ "         JOIN Users u ON jsu.id_user = u.id\r\n"
							+ "         JOIN Status s ON jsu.id_status = s.id\r\n"
							+ "         JOIN Project p ON j.id_project = p.id\r\n"
							+ "WHERE j.id = ?";
		Connection connection = MysqlConfig.getConnection();
		JobEntity job = new JobEntity();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				job.setId(resultSet.getInt("j.id"));
				job.setName(resultSet.getString("j.name"));
				job.setStartDate(resultSet.getString("startDate"));
				job.setEndDate(resultSet.getString("endDate"));
				job.setIdProject(resultSet.getInt("id_project"));

				UserEntity user = new UserEntity();
				user.setId(resultSet.getInt("u.id"));
				user.setFullName(resultSet.getString("fullName"));
				job.setUser(user);

				ProjectEntity project = new ProjectEntity();
				project.setId(resultSet.getInt("p.id"));
				project.setName(resultSet.getString("p.name"));
				job.setProject(project);

				StatusEntity status = new StatusEntity();
				status.setId(resultSet.getInt("s.id"));
				status.setName(resultSet.getString("s.name"));
				job.setStatus(status);
			}
				
		} catch (Exception e) {
			
			System.out.println("loi getJob" + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("loi connect getJob" + e.getLocalizedMessage());
			}
		}
		return job;

	}
}
