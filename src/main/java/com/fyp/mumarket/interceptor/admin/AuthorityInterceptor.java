package com.fyp.mumarket.interceptor.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.entity.admin.Menu;
import com.fyp.mumarket.entity.admin.User;
import com.fyp.mumarket.util.MenuUtil;
import com.fyp.mumarket.util.SessionUtil;
import com.fyp.mumarket.util.StringUtil;

/**
 * Permission management interceptor
 * @author Administrator
 *
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();
		User loginedUser = SessionUtil.getLoginedUser();
		log.info("Enter the permission control interceptor" + requestURI);
		List<Menu> authorities = loginedUser.getRole().getAuthorities();
		if(!MenuUtil.isExistUrl(requestURI, authorities)){
			//Entering here indicates that the permission does not exist. First, determine if it is an AJAX request
			if(StringUtil.isAjax(request)){
				//Indicates it is an AJAX request
				try {
					log.info("This request has no permission, return a prompt in AJAX style, URL =" + requestURI);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(JSON.toJSONString(CodeMsg.ADMIN_NO_RIGHT));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			//It's a regular request, so it can be directly redirected to the 'no permission' error page.
			try {
				log.info("This request lacks permission, redirect to the 'no permission' error page, URL =" + requestURI);
				response.sendRedirect("/system/no_right");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		log.info("This request meets the permission requirements, allow it" + requestURI);
		return true;
	}
}
