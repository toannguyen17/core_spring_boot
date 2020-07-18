//package c0320h1.app.interceptors;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Priority;
//import javax.interceptor.Interceptor;
//import javax.interceptor.InterceptorBinding;
//import javax.interceptor.Interceptors;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//
//@Component
//public class AuthenticateInterceptor implements HandlerInterceptor {
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println("preHandle Log time Interceptor: " + request.getServletPath() + new Date());
//		return true;
//	}
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//	                       ModelAndView modelAndView) throws Exception {
//		System.out.println("postHandle Log time Interceptor: " + request.getServletPath() + new Date());
//
//	}
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		System.out.println("afterCompletion Log time Interceptor: " + request.getServletPath() + new Date());
//
//	}
//}
