package c0320h1.services.users;

import c0320h1.model.Users;
import c0320h1.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersRepository repository;

	@Override
	public Iterable<Users> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(Users users) {
		repository.save(users);
	}

	@Override
	public void update(Users users) {
		repository.save(users);
	}

	@Override
	public Optional<Users> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete(Users entity) {
		repository.deleteById(entity.getId());
	}

	@Override
	public Optional<Users> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
