package c0320h1.system.auth;

import c0320h1.model.Users;
import c0320h1.system.auth.form.FormLogin;

import javax.servlet.http.HttpServletResponse;

public interface Guard {
	void login(Users user, HttpServletResponse response);
	Users attempt(FormLogin form, HttpServletResponse response);
}
