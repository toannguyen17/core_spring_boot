package red.t.app.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index(@RequestParam Optional<String> test, Model model){
		if (test.isPresent()){
			System.out.println(test.get());
			model.addAttribute("test", test.get());
		}
		System.out.println("Kiểm tra tiếng việt");
		return "index";
	}
}
