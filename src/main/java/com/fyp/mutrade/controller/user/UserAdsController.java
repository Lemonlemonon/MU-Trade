package com.fyp.mutrade.controller.user;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.AdsCategory;
import com.fyp.mutrade.service.common.AdsCategoryService;
import com.fyp.mutrade.service.common.AdsService;
import com.fyp.mutrade.service.common.CommentService;

/**
 * Home Item controller
 * @author Administrator
 *
 */
@RequestMapping("/home/ads")
@Controller
public class UserAdsController {

	@Autowired
	private AdsCategoryService adsCategoryService;
	@Autowired
	private AdsService adsService;
	@Autowired
	private CommentService commentService;
	/**
	 * Item detail page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String detail(@RequestParam(name="id",required=true)Long id,Model model){
		Ads ads = adsService.findById(id);
		if(ads == null){
			model.addAttribute("msg", "Item does not exsist!");
			return "error/runtime_error";
		}
		model.addAttribute("ads", ads);
		model.addAttribute("commentList", commentService.findByAds(ads));
		//Update item view number
		ads.setViewNumber(ads.getViewNumber() + 1);
		adsService.save(ads);
		return "home/ads/detail";
	}
	
	/**
	 * Search item information by category
	 * @param cid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(@RequestParam(name="cid",required=true)Long cid,PageBean<Ads> pageBean,Model model){
		AdsCategory adsCategory = adsCategoryService.findById(cid);
		if(adsCategory == null){
			model.addAttribute("msg", "Category does not exsist!");
			return "error/runtime_error";
		}
		//Consider if the current category is a sub-category
		List<Long> ids = new ArrayList<Long>();
		ids.add(adsCategory.getId());
		if(adsCategory.getParent() == null){
			//If is a Parent category, now retrieve all its sub-category
			List<AdsCategory> findChildren = adsCategoryService.findChildren(adsCategory);
			for(AdsCategory gc : findChildren){
				ids.add(gc.getId());
			}
		}
		model.addAttribute("pageBean", adsService.findlist(ids, pageBean));
		model.addAttribute("gc",adsCategory);
		return "home/ads/list";
	}
	
	/**
	 * get sold total count
	 * @return
	 */
	@RequestMapping(value="/get_sold_total",method=RequestMethod.POST)
	@ResponseBody
	public Result<Long> getSoldTotal(){
		return Result.success(adsService.getSoldTotalCount());
	}
}
