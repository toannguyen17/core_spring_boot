package c0320h1.system.auth;

import c0320h1.model.Users;
import c0320h1.system.auth.form.FormLogin;
import c0320h1.system.support.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
// @Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class AuthInitializer implements Auth, Guard {
	private Users user;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserProvider provider;

	@Autowired
	private Str str;

	public AuthInitializer(){
	}

	@Override
	public boolean check() {
		Users user = user();
		if (user != null){
			return true;
		}
		return false;
	}

	@Override
	public Users user() {
		if (user != null) {
			return user;
		}

		if(session.getAttribute("login_web") == null)
			return null;

		Long id = (Long) session.getAttribute("login_web");

		if (id != 0){
			user = provider.retrieveById(id);
		}

		Recaller recaller;
		if (user == null && (recaller = recaller()) != null) {
			user = userFromRecaller(recaller);

			if (user != null){
				updateSession(user.getId());
			}
		}

		return user;
	}

	@Override
	public void login(Users user, HttpServletResponse response) {
		updateSession(user.getId());

		// cookie token
		ensureRememberTokenIsSet(user);
		createRecallerCookie(user, response);
	}

	@Override
	public Users attempt(FormLogin form, HttpServletResponse response) {
		if ((user = provider.retrieveByCredentials(form)) != null){
			login(user, response);
		}
		return user;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		session.removeAttribute("login_web");

		if (cookies != null && response != null){
			for (Cookie cookie: cookies) {
				String cName = cookie.getName();
				if (cName.equals("remember_web")){
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					// break;
				}
			}
		}

		if (user != null && user.getRememberToken() != null) {
			cycleRememberToken(user);
		}
		user         = null;
		this.request = null;
		// this.session = null;
	}

	private Recaller recaller() {
		if (request == null)
			return null;

		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}

		for (Cookie cookie: cookies) {
			String cName = cookie.getName();
			String cValue = cookie.getValue();

			if (cName.equals("remember_web")){
				return new Recaller(cValue);
			}
		}
		return null;
	}

	private Users userFromRecaller(Recaller recaller) {
		if (!recaller.valid()) {
			return null;
		}

		user = provider.retrieveByToken(recaller.id(), recaller.token());
		return user;
	}

	private void updateSession(long id){
		session.setAttribute("login_web", id);;
	}

	private void ensureRememberTokenIsSet(Users user)
	{
		if (user.getRememberToken() == null) {
			cycleRememberToken(user);
		}
	}

	private void cycleRememberToken(Users user) {
		String token = str.random(60);
		provider.updateRememberToken(user, token);
	}

	private void createRecallerCookie(Users user, HttpServletResponse response) {
		String cookieValue = user.getId() + "|" + user.getRememberToken();
		Cookie cookie = new Cookie("remember_web", cookieValue);
		cookie.setPath("/");
		int max = 3600*24*30;
		cookie.setMaxAge(max);
		response.addCookie(cookie);
	}

	@Override
	public Guard guard() {
		return this;
	}
}
