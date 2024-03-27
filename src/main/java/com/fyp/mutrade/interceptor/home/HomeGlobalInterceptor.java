package com.fyp.mutrade.interceptor.home;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fyp.mutrade.config.SiteConfig;
import com.fyp.mutrade.entity.common.SiteSetting;
import com.fyp.mutrade.service.common.AdsCategoryService;
import com.fyp.mutrade.service.common.RelatedSiteService;
import com.fyp.mutrade.service.common.SiteSettingService;
import com.fyp.mutrade.util.StringUtil;

//Home page global intercepter
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor{

	@Autowired
	private AdsCategoryService adsCategoryService;
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private RelatedSiteService relatedSiteService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		
		if(!StringUtil.isAjax(request)){
			//If it's not an AJAX request, then place the menu information into the page template variable
			request.setAttribute("adsCategorys", adsCategoryService.findAll());
			request.setAttribute("relatedSiteList", relatedSiteService.findList(8));
			SiteSetting siteSetting = siteSettingService.find();
			if(siteSetting != null){
				request.setAttribute("siteName", siteSetting.getSiteName());
				request.setAttribute("siteSetting", siteSetting);
			}
		}
		return true;
	}
}
