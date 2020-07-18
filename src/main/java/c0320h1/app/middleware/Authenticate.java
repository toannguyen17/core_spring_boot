package c0320h1.app.middleware;

import c0320h1.system.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticate extends HandlerInterceptorAdapter {
	private String redirectTo = "/login";

	@Autowired
	private Auth auth;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(!auth.check()){
			response.sendRedirect(redirectTo);
			return false;
		}
		return true;
	}
}
