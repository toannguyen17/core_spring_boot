package c0320h1.app.http.controller.auth;

// import c0320h1.app.interceptors.AuthenticateInterceptor;
import c0320h1.system.foundation.auth.RegistersUsers;
import org.springframework.stereotype.Controller;

import javax.interceptor.Interceptors;

@Controller
// @Interceptors(AuthenticateInterceptor.class)
public class RegisterController extends RegistersUsers {
}
