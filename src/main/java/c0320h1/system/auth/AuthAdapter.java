package c0320h1.system.auth;

import c0320h1.system.support.Str;
import javax.servlet.http.HttpServletRequest;

public class AuthAdapter extends AuthInitializer {
	public AuthAdapter(){
		this.provider = new UserProvider();
		this.str      = new Str();
	}

	public AuthAdapter(HttpServletRequest request){
		request.getSession();
		this.request  = request;
		this.provider = new UserProvider();
		this.str      = new Str();
	}
}
