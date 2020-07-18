package c0320h1.services.users;

import c0320h1.model.Users;
import c0320h1.repository.users.UsersRepository;
import c0320h1.services.IGeneralService;
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
		return null;
	}

	@Override
	public void save(Users users) {
		try {
			repository.save(users);
		}catch (DataIntegrityViolationException e){
			//
		}
	}

	@Override
	public void update(Users users) {
		try {
			repository.save(users);
		}catch (DataIntegrityViolationException e){
			//
		}
	}

	@Override
	public Optional<Users> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public void delete(Users entity) {

	}

	@Override
	public Optional<Users> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
