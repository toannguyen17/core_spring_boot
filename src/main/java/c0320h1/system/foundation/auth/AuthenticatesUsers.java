package c0320h1.system.foundation.auth;

import c0320h1.model.Users;
import c0320h1.system.auth.Auth;
import c0320h1.system.auth.form.FormLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

public abstract class AuthenticatesUsers {
	protected String redirectTo = "/";
	protected String username   = "email";
	protected String loginForm  = "auth/login";

	@Autowired
	private Auth auth;

	/**
	 * Handle a login request to the application.
	 */

	@GetMapping("/login")
	public String index(){
		return loginForm;
	}

	@PostMapping("/login")
	public String login(@Valid @RequestParam("login") FormLogin form, BindingResult bindingResult, HttpServletResponse response, Model model) {

		if (!bindingResult.hasErrors()){
			Users users = null;
			if ((users = auth.guard().attempt(form, response)) != null) {
				authenticated(users);
				return "redirect:" + redirectTo;
			}

			bindingResult.rejectValue("login", "login.invalid", "The account or password is incorrect.");
		}

		model.addAttribute(username, form.getEmail());
		return loginForm;
	}

	protected void authenticated(Users users)
	{
		//
	}


	/**
	 * Log the user out of the application.
	 */
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (auth.check()){
			loggedOut(auth.user());
			auth.logout(request, response);
		}

		session.invalidate();
		return "redirect:" + redirectTo;
	}

	/**
	 * The user has logged out of the application.
	 */
	protected void loggedOut(Users users)
	{
		//
	}
}
