package com.kh.common.interceptor;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
	private static final String USER_INFO = "userInfo";

	// handler : url 요청 시 실행되는 컨트롤러와 해당 함수 정보가 있다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("No_1, preHandle");
		String requestURI = request.getRequestURI();
		// requestURL : URL => http://127.0.0.1:8080/login
		log.info("1번 requestURI : " + requestURI);

		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		// Bean : com.kh.controller.LoginController@13ed2e22
		log.info("1번 Bean: " + method.getBean());

		// Method : public void com.kh.controller.LoginController.login()
		log.info("1번 Method: " + methodObj);
		// ========================================================================
		//
		// session 값을 가져온다.
		HttpSession session = request.getSession();
		// session 값에 로그인 해서 성공한 사용자 정보가 들어있다.
		if (session.getAttribute("userInfo") != null) {
			// session 값에 사용자 정보 삭제
			session.removeAttribute("userInfo");
			log.info("1번 session 사용자 정보 삭제 완료 ");
//			response.sendRedirect("/");
//			return false;

		}
		// 조건이 맞지 않으면 Controller로 못 가게 함
		return true;
	}

	// Controller 종료 시 진행
	// ModelAndView : 두 가지 (Model, View Resolver 정보를 가지고 있는 객체)
	// => View Resolver는 retrun "login/register" 이런 거임!
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("3번, postHandle");
		String requestURL = request.getRequestURI();
		// requestURL : /login

		log.info("3번, requestURL : " + requestURL);
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();

		// Bean: com.zeus.controller.Logincontroller@13ed2e22
		log.info("3번, Bean: " + method.getBean());

		// Method: public void com.zeus.controller.Logincontroller.login()
		log.info("3번, Method: " + methodObj);
		// ========================================================================
		//
		// session 값을 가져온다.
		HttpSession session = request.getSession();
		// ModelMap => model이랑 같음
		ModelMap modelMap = modelAndView.getModelMap();

		// Cotroller에서 ModelAndView에 담은 user 객체를 가져온다.
		Object member = modelMap.get("user");

		if (member != null) {
			log.info("3번, member != null");
			session.setAttribute("userInfo", member);
			response.sendRedirect("/");
		}
	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		log.info("No_4, afterCompletion");
//	}

}
