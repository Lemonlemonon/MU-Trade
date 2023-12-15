package com.fyp.mumarket.interceptor.admin;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.alibaba.fastjson.JSON;
import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.config.SiteConfig;
import com.fyp.mumarket.constant.SessionConstant;
import com.fyp.mumarket.entity.admin.Menu;
import com.fyp.mumarket.entity.admin.User;
import com.fyp.mumarket.util.MenuUtil;
import com.fyp.mumarket.util.StringUtil;

//Back-end login intercepter
@Component
public class AdminLoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(AdminLoginInterceptor.class);
	@Autowired
	private SiteConfig siteConfig;
	
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
		if(attribute == null){
			log.info("The user is not yet logged in or the session has expired. Redirect to the login page. Current URL =" + requestURI);
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
				response.sendRedirect("/system/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		log.info("The request meets the login requirements, so it is allowed to pass" + requestURI);
		if(!StringUtil.isAjax(request)){
			//If it's not an AJAX request, then place menu information into the page template variable
			User user = (User)attribute;
			List<Menu> authorities = user.getRole().getAuthorities();
			request.setAttribute("userTopMenus", MenuUtil.getTopMenus(authorities));
			List<Menu> secondMenus = MenuUtil.getSecondMenus(user.getRole().getAuthorities());
			request.setAttribute("userSecondMenus", secondMenus);
			request.setAttribute("userThirdMenus", MenuUtil.getChildren(MenuUtil.getMenuIdByUrl(requestURI,secondMenus),authorities));
			request.setAttribute("siteName", siteConfig.getSiteName());
			request.setAttribute("siteUrl", siteConfig.getSiteUrl());
		}
		return true;
	}
}
