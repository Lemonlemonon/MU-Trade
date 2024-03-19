package com.fyp.mumarket.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fyp.mumarket.entity.admin.User;
import com.fyp.mumarket.interceptor.constant.SessionConstant;

/**
 * Session unified operation utility class
 * @author Administrator
 *
 */
public class SessionUtil {

	/**
	 * get request method
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attributes =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return attributes == null ? null : attributes.getRequest();
	}
	
	/**
	 * get session method
	 * @return
	 */
	public static HttpSession getSession(){
		HttpServletRequest request = getRequest();
		if(request != null){
			return request.getSession();
		}
		return null;
	}
	
	/**
	 * get value by key
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		HttpSession session = getSession();
		if(session != null){
			return session.getAttribute(key);
		}
		return null;
	}
	
	/**
	 * set session value
	 * @param key
	 * @param object
	 */
	public static void set(String key,Object object){
		HttpSession session = getSession();
		if(session != null){
			session.setAttribute(key,object);
		}
	}
	
	/**
	 * get current logged in user
	 * @return
	 */
	public static User getLoginedUser(){
		HttpSession session = getSession();
		if(session != null){
			Object attribute = session.getAttribute(SessionConstant.SESSION_USER_LOGIN_KEY);
			return attribute == null ? null : (User)attribute;
		}
		return null;
	}
}
