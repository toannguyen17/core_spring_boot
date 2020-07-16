package red.t.repository;

import red.t.model.database.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Posts, Long> {
}
