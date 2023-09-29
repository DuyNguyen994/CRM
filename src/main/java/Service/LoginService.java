package Service;

import Repository.LoginRepository;

public class LoginService {
	private LoginRepository loginRespository = new LoginRepository();
	public boolean getLogin(String email, String password) {
		
		return loginRespository.getLogin(email, password);
	}
}
