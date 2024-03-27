package com.fyp.mutrade.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.bean.CodeMsg;
import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.AdsCategory;
import com.fyp.mutrade.entity.common.Student;
import com.fyp.mutrade.service.common.AdsCategoryService;
import com.fyp.mutrade.service.common.AdsService;
import com.fyp.mutrade.service.common.StudentService;

/**
 * Item management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/ads")
@Controller
public class AdsController {

	@Autowired
	private AdsCategoryService adsCategoryService;
	@Autowired
	private AdsService adsService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * Item management page
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Ads ads,PageBean<Ads> pageBean,Model model){
		if(ads.getStudent() != null && ads.getStudent().getSn() != null){
			Student student = studentService.findBySn(ads.getStudent().getSn());
			if(student != null){
				ads.setStudent(student);
			}
		}
		if(ads.getAdsCategory() != null && ads.getAdsCategory().getName() != null){
			List<AdsCategory> adsCategorys = adsCategoryService.findByName(ads.getAdsCategory().getName());
			if(adsCategorys != null && adsCategorys.size() > 0){
				ads.setAdsCategory(adsCategorys.get(0));
			}
		}
		ads.setStatus(-1);
		model.addAttribute("title", "Item list");
		model.addAttribute("name", ads.getName());
		model.addAttribute("adsCategoryName", ads.getAdsCategory() == null ? null : ads.getAdsCategory().getName());
		model.addAttribute("sn", ads.getStudent() == null ? null : ads.getStudent().getSn());
		model.addAttribute("pageBean", adsService.findlist(pageBean, ads));
		return "admin/ads/list";
	}
	
	

	/**
	 * Place item
	 * @param id,status
	 * @return
	 */
	@RequestMapping(value="/up_down",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> upDown(@RequestParam(name="id",required=true)Long id ,@RequestParam(name="status",required=true)Integer status){
		Ads ads = adsService.findById(id);
		if(ads == null){
			return Result.error(CodeMsg.ADMIN_ADS_NO_EXIST);
		}
		if(ads.getStatus() == status){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_NO_CHANGE);
		}
		if(status != Ads.ADS_STATUS_UP && status != Ads.ADS_STATUS_DOWN){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_ERROR);
		}
		if(ads.getStatus() == Ads.ADS_STATUS_SOLD){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_UNABLE);
		}
		ads.setStatus(status);
		//Update database
		if(adsService.save(ads) ==null){
			return Result.error(CodeMsg.ADMIN_ADS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Item recommend or discard recommend
	 * @param id
	 * @param recommend
	 * @return
	 */
	@RequestMapping(value="/recommend",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> recommend(@RequestParam(name="id",required=true)Long id ,@RequestParam(name="recommend",required=true)Integer recommend){
		Ads ads = adsService.findById(id);
		if(ads == null){
			return Result.error(CodeMsg.ADMIN_ADS_NO_EXIST);
		}
		if(ads.getRecommend() == recommend){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_NO_CHANGE);
		}
		if(recommend != Ads.ADS_RECOMMEND_OFF && recommend != Ads.ADS_RECOMMEND_ON){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_ERROR);
		}
		if(ads.getStatus() == Ads.ADS_STATUS_SOLD){
			return Result.error(CodeMsg.ADMIN_ADS_STATUS_UNABLE);
		}
		ads.setRecommend(recommend);;
		//Update database
		if(adsService.save(ads) ==null){
			return Result.error(CodeMsg.ADMIN_ADS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	
	
	/**
	 * Delete item
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			adsService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_ADS_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
