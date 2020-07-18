package c0320h1.app;

import c0320h1.app.middleware.Authenticate;
import c0320h1.app.middleware.Guestable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class HttpMiddleware extends WebMvcConfigurerAdapter {

	@Bean
	Guestable guestable() {
		return new Guestable();
	}

	@Bean
	Authenticate authenticate() {
		return new Authenticate();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Guest middleware
		InterceptorRegistration guestable = registry.addInterceptor(guestable());
		guestable.addPathPatterns("/login", "/register");

		// Auth middleware
		InterceptorRegistration authenticate = registry.addInterceptor(authenticate());
		authenticate.addPathPatterns("/user");
	}
}
