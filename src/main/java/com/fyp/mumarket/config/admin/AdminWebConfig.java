package com.fyp.mumarket.config.admin;
/**
 * Configuration class used to set up interceptors
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fyp.mumarket.constant.RuntimeConstant;
import com.fyp.mumarket.interceptor.admin.AdminLoginInterceptor;
import com.fyp.mumarket.interceptor.admin.AuthorityInterceptor;
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
	
	@Autowired
	private AdminLoginInterceptor loginInterceptor;
	
	@Autowired
	private AuthorityInterceptor authorityInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.loginExcludePathPatterns);
	    registry.addInterceptor(authorityInterceptor).addPathPatterns("/**").excludePathPatterns(RuntimeConstant.authorityExcludePathPatterns);
	}

}
