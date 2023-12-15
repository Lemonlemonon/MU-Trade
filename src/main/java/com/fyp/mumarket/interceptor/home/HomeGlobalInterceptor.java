package com.fyp.mumarket.interceptor.home;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fyp.mumarket.config.SiteConfig;
import com.fyp.mumarket.entity.common.SiteSetting;
import com.fyp.mumarket.service.common.FriendLinkService;
import com.fyp.mumarket.service.common.GoodsCategoryService;
import com.fyp.mumarket.service.common.SiteSettingService;
import com.fyp.mumarket.util.StringUtil;

//Home page global intercepter
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor{

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private SiteConfig siteConfig;
	@Autowired
	private FriendLinkService friendLinkService;
	@Autowired
	private SiteSettingService siteSettingService;
	@Override
	public boolean  preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		
		if(!StringUtil.isAjax(request)){
			//If it's not an AJAX request, then place the menu information into the page template variable
			request.setAttribute("goodsCategorys", goodsCategoryService.findAll());
			request.setAttribute("friendLinkList", friendLinkService.findList(8));
			SiteSetting siteSetting = siteSettingService.find();
			if(siteSetting != null){
				request.setAttribute("siteName", siteSetting.getSiteName());
				request.setAttribute("siteSetting", siteSetting);
			}
		}
		return true;
	}
}
