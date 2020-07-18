package c0320h1.repository.users;

import c0320h1.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
	Optional<Users> findByEmail(String email);
}
