package com.fyp.mumarket.interceptor.home;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson.JSON;
import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.interceptor.constant.SessionConstant;
import com.fyp.mumarket.util.StringUtil;

//Home page login intercepter
@Component
public class HomeLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(HomeLoginInterceptor.class);
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(attribute == null){
			log.info("User have not login or session invaild, redirect to login page, current URL=" + requestURI);
			//First, determine if it is an AJAX request
			if(StringUtil.isAjax(request)){
				//Indicates it is an AJAX request
				try {
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			//Indicates is a regular request, so it can be directly redirected to the login page
			//The user is not yet logged in or the session has expired. Redirect to the login page.
			try {
				response.sendRedirect("/home/index/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		log.info("The request meets the login requirements, allowed to pass" + requestURI);
		return true;
	}
}
