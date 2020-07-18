package c0320h1.services.users;

import c0320h1.model.Users;
import c0320h1.services.IGeneralService;

import java.util.Optional;

public interface UsersService extends IGeneralService<Users> {
	Optional<Users> findByEmail(String email);
}
