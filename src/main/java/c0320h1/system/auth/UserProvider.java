package c0320h1.system.auth;

import c0320h1.model.Users;
import at.favre.lib.crypto.bcrypt.BCrypt;
import c0320h1.services.users.UsersService;
import c0320h1.system.auth.form.FormLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserProvider {

	@Autowired
	private UsersService service;

	public Users retrieveByCredentials(FormLogin form){
		Optional<Users> optional = service.findByEmail(form.getEmail());
		if (optional.isPresent()){
			Users users = optional.get();

			BCrypt.Result checkPass = BCrypt.verifyer().verify(form.getPassword().toCharArray(), users.getPassword());
			if (checkPass.verified){
				return users;
			}
		}
		return null;
	}

	public Users retrieveById(long id){
		Optional<Users> optional = service.findById(id);
		return optional.orElse(null);
	}

	public Users retrieveByToken(Long id, String token){
		Optional<Users> optional = service.findById(id);

		System.out.println("optional: " + optional.isPresent());

		if (optional.isPresent()){
			Users user = optional.get();
			if (user.getRememberToken() != null && user.getRememberToken().equals(token)){
				return user;
			}
		}

		return null;
	}

	public void updateRememberToken(Users user, String token){
		if (user.getId() == 0)
			return;

		user.setRememberToken(token);
		service.save(user);
	}
}
