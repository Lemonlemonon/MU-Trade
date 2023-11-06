package com.fyp.mumarket.listener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
/**
 * session监听类，用于监听session的创建，销毁
 */
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebListener
public class SessionListener implements HttpSessionListener {
	
	private Logger log = LoggerFactory.getLogger(SessionListener.class);
	
	public static long onlineUserCount = 0;
	
	@Override
	public void sessionCreated(HttpSessionEvent se){
		log.info("Entering the session creation event! Current number of online users:" + (++onlineUserCount));
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se){
		log.info("Entering the session destruction event! Current number of online users:" + (--onlineUserCount));
	}
}
