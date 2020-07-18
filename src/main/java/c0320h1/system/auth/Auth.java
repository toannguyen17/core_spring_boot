package c0320h1.system.auth;

import c0320h1.model.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Auth {
	boolean check();
	Users user();
	void logout(HttpServletRequest request, HttpServletResponse response);

	Guard guard();
}
