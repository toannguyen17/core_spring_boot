package c0320h1.services.posts;

import c0320h1.model.database.Posts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService{
	@Override
	public Iterable<Posts> findAll() {
		return null;
	}

	@Override
	public void save(Posts posts) {

	}

	@Override
	public void update(Posts posts) {

	}

	@Override
	public Optional<Posts> findById(Long id) {
		return Optional.empty();
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public void delete(Posts entity) {

	}
}
