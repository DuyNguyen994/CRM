package Service;

import java.util.List;

import Entity.ProjectEntity;
import Repository.ProjectRepository;

public class ProjectService {
	private ProjectRepository projectRepository = new ProjectRepository();
	
	public List<ProjectEntity> getAllProject(){
		
		return projectRepository.getAllProject();
	}
	
	public boolean addProject(ProjectEntity project) {
		
		return projectRepository.addProject(project) > 0;
	}
	
	public boolean updateProject(ProjectEntity project) {
		
		return projectRepository.updateProject(project) > 0;
	}
	
	public boolean deleteProject(int id) {
		
		return projectRepository.deleteProject(id) > 0;
	}
	
	public ProjectEntity getProject(int id) {
		
		return projectRepository.getProject(id);
	}
}
