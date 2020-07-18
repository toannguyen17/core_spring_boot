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

	@GetMapping("register")
	public String index(Model model){
		model.addAttribute("formRegister", new FormRegister());
		return view;
	}

	@PostMapping("register")
	public String register(@Valid FormRegister formRegister, BindingResult bindingResult, HttpServletResponse response, Model model){
		System.out.println(formRegister);
		if (!bindingResult.hasErrors() && validator(formRegister, bindingResult)){
			try {
				Users users = create(formRegister);
				auth.guard().login(users, response);
				registered(users);
				return "redirect:" + redirectTo;
			}catch (DataIntegrityViolationException e){
				System.out.println("------------------------------------");
				System.out.println("------------------------------------");
				System.out.println("Đã ton tai");
				bindingResult.rejectValue("email", "email.unique", "Email already exists");
			}
		}

		System.out.println(bindingResult);

		model.addAttribute("formRegister", formRegister);
		return view;
	}

	protected boolean validator(FormRegister form, BindingResult bindingResult)
	{
		boolean check = true;
		if (!form.getPassword().equals(form.getPassword_confirm())){
			bindingResult.rejectValue("password", "password.confirm_password", "Confirm Password does not match.");
			bindingResult.rejectValue("password_confirm", "password.confirm_password", "Confirm Password does not match.");
			check = false;
		}
		return check;
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
