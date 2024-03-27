package com.fyp.mutrade.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.entity.common.RequestAds;
import com.fyp.mutrade.service.common.RequestAdsService;

/**
 * Request Ads controller
 * @author Administrator
 *
 */
@RequestMapping("/home/request_ads")
@Controller
public class UserRequestAdsController {

	@Autowired
	private RequestAdsService requestAdsService;
	
	/**
	 * Request ads listing page
	 * @param model
	 * @param pageBean
	 * @param RequestAds
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model,PageBean<RequestAds> pageBean,RequestAds RequestAds){
		model.addAttribute("pageBean", requestAdsService.findlist(pageBean, RequestAds));
		return "home/request_ads/list";
	}
}
