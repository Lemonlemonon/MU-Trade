package com.fyp.mumarket.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Constants used during system runtime.
 * @author Administrator
 *
 */
public class RuntimeConstant {

	//URLs that the backend login interceptor does not need to intercept
	public static List<String> loginExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/photo/view",
			"/cpacha/generate_cpacha",
			"/home/**",
			"/bidding/**",
			"/notice/**"
		);
	//URLs that the permission interceptor does not need to intercept.
	public static List<String> authorityExcludePathPatterns = Arrays.asList(
			"/system/login",
			"/system/index",
			"/system/no_right",
			"/admin/css/**",
			"/admin/fonts/**",
			"/admin/js/**",
			"/admin/images/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/system/logout",
			"/system/update_userinfo",
			"/system/update_pwd",
			"/photo/view",
			"/home/**",
			"/bidding/**",
			"/notice/**"
		);
	//URLs that the frontend global interceptor does not need to intercept
	public static List<String> homeGlobalExcludePathPatterns = Arrays.asList(
			"/system/**",
			"/admin/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/photo/view",
			"/bidding/**",
			"/notice/**"
		);
	//URLs that the frontend login interceptor does not need to intercept
	public static List<String> homeLoginExcludePathPatterns = Arrays.asList(
			"/system/**",
			"/admin/**",
			"/error",
			"/cpacha/generate_cpacha",
			"/photo/view",
			"/home/index/**",
			"/home/goods/**",
			"/home/wanted_goods/**",
			"/home/js/**",
			"/home/css/**",
			"/home/imgs/**",
			"/bidding/**",
			"/notice/**"
		);
}
