package red.t.app.http.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import red.t.model.database.Posts;
import red.t.service.posts.IPostService;

@RestController
public class APIUserController {
	@Autowired
	private IPostService postService;

	@PostMapping(value = "/test", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public Posts test1Post(@RequestBody Posts posts){
		postService.save(posts);
		return posts;
	}
}
