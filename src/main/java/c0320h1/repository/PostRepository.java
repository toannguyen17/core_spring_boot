package c0320h1.repository;

import c0320h1.model.database.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Posts, Long> {
}
