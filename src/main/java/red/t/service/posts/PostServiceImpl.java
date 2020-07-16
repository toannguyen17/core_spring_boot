package red.t.service.posts;

import red.t.model.database.Posts;
import red.t.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	private PostRepository postRepository;

	@Override
	public Iterable<Posts> findAll() {
		return null;
	}

	@Override
	public void save(Posts posts) {
		postRepository.save(posts);
	}

	@Override
	public Posts findById(Long id) {
		Optional<Posts> posts = postRepository.findById(id);
		if (posts.isPresent())
			return posts.get();
		return null;
	}

	@Override
	public void deleteById(Long id) {

	}
}
