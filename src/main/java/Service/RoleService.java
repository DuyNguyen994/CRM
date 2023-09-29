package Service;

import java.util.List;

import Entity.RoleEntity;
import Repository.RoleRepository;

public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	private RoleRepository repository = new RoleRepository();
	
	public RoleEntity getRole(int id) {
		
		return repository.getRole(id);
	}
	
	public boolean addRole(RoleEntity role) {
		
		return repository.addRole(role) > 0 ;
	}
	
	public boolean deleteRole(int id) {
		
		return repository.deleteRole(id) > 0;
	}
	
	public boolean updateRole(RoleEntity role) {
		
		return repository.updateRole(role) > 0;
	}
	
	public List<RoleEntity> getAllRole(){
		
		return roleRepository.getAllRole();
	}
}
