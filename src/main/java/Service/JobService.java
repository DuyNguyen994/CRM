package Service;

import java.util.List;

import Entity.JobEntity;
import Repository.JobRepository;

public class JobService {
	private JobRepository jobRepository = new JobRepository();
	
	public List<JobEntity> getAllJob(){
		
		return jobRepository.getAllJob();
	}
	public boolean addJob(JobEntity job) {
		
		return jobRepository.addJob(job) != -1;
	}
	public boolean delJob(int id) {
		
		return jobRepository.deleteJob(id) > 0;
	}
	
	public boolean updateJob(JobEntity job) {
		
		return jobRepository.updateJob(job) != -1;
	}
	
	public JobEntity getJob(int id) {
		
		return jobRepository.getJob(id);
	}
}
