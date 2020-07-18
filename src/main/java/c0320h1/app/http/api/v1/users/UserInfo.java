package c0320h1.app.http.api.v1.users;

import c0320h1.app.http.api.v1.V1Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Test Api
@RestController
public class UserInfo extends V1Api {
	@GetMapping("/user")
	public String index (@RequestParam String name){
		return name;
	}
}
