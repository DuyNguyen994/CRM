package Service;

import java.util.List;

import org.apache.catalina.User;

import Entity.RoleEntity;
import Entity.UserEntity;
import Repository.RoleRepository;
import Repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addUser(UserEntity user) {
		int n = user.getFullName().lastIndexOf(" ");
		String fullName = user.getFullName();
		String email = user.getEmail();
		String lastName = (n > 0) ? fullName.substring(0, fullName.indexOf(" ")) : fullName;
		String firstName = (n > 0) ? fullName.substring(fullName.lastIndexOf(" ")) : "";

		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setUserName(email.substring(0, email.indexOf("@")));
		
		return userRepository.addUser(user) > 0;
	}
	
	public boolean updateUser(UserEntity user) {
		int n = user.getFullName().lastIndexOf(" ");
		String fulfName = user.getFullName();
		String email = user.getEmail();
		String lastName = (n > 0) ? fulfName.substring(0, fulfName.indexOf(" ")) : fulfName;
		String firstName = (n > 0) ? fulfName.substring(fulfName.lastIndexOf(" ")) : "";

		user.setLastName(lastName);
		user.setFirstName(firstName);
		user.setUserName(email.substring(0, email.indexOf("@")));
		
		return userRepository.updateUser(user) > 0;
	}

	
	public List<UserEntity> getAllUsers(){
		
		return userRepository.getAllUsers();
	}
	
	public boolean deleteUser(int id) {
		int count = userRepository.deleteById(id);
		
		return count > 0;
	}
	
	public List<RoleEntity> getAllRole(){
		
		return roleRepository.getAllRole();
	}
	
	public UserEntity getUser(int id) {
		
		return userRepository.getUser(id);
	}
}
