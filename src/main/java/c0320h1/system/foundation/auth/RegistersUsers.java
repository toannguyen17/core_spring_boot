package c0320h1.system.foundation.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import c0320h1.model.Users;
import c0320h1.services.users.UsersService;
import c0320h1.system.auth.Auth;
import c0320h1.system.auth.form.FormRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public abstract class RegistersUsers {
	protected String redirectTo = "/";
	protected String view       = "auth/register";

	@Autowired
	private Auth auth;

	@Autowired
	private UsersService service;

	@ModelAttribute("register")
	public FormRegister formRegister() {
		return new FormRegister();
	}

	@GetMapping("register")
	public String index(){
		return view;
	}

	@PostMapping("register")
	public String register(@Valid @RequestParam FormRegister form, BindingResult bindingResult, HttpServletResponse response, Model model){
		if (!bindingResult.hasErrors() && validator(form)){
			try {
				Users users = create(form);
				auth.guard().login(users, response);
				registered(users);
				return "redirect:" + redirectTo;
			}catch (DataIntegrityViolationException e){
				System.out.println("------------------------------------");
				System.out.println("------------------------------------");
				System.out.println("DDax ton tai");
				bindingResult.rejectValue("login", "login.invalid", "The account or password is incorrect.");
			}
		}

		model.addAttribute("register", form);
		return view;
	}

	protected boolean validator(FormRegister form)
	{
		if (!form.getPassword().equals(form.getPassword_confirm()))
		{
			return false;
		}
		return true;
	}

	private Users create(FormRegister form)
	{
		String password = BCrypt.withDefaults().hashToString(12, form.getPassword().toCharArray());

		Users users = new Users();

		users.setEmail(form.getEmail());
		users.setPassword(password);
		users.setLastName(form.getLast_name());
		users.setFirstName(form.getFirst_name());

		service.save(users);
		return users;
	}

	protected void registered(Users user)
	{
		//
	}
}
