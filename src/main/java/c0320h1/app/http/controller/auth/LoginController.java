package c0320h1.app.http.controller.auth;

import c0320h1.model.Users;
import c0320h1.system.foundation.auth.AuthenticatesUsers;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController extends AuthenticatesUsers {
	@Override
	protected void authenticated(Users users)
	{
		// Code processed after successful login
	}
}
