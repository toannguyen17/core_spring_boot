package c0320h1.app.http.controller.api.v1;

import c0320h1.services.posts.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import c0320h1.model.database.Posts;

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
