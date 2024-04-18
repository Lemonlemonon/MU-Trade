package com.fyp.mutrade.controller.admin;

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
import com.fyp.mutrade.entity.common.AdsCategory;
import com.fyp.mutrade.service.common.AdsCategoryService;
import com.fyp.mutrade.util.ValidateEntityUtil;

/**
 * Item Category Management Controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/ads_category")
@Controller
public class AdsCategoryController {

	@Autowired
	private AdsCategoryService adsCategoryService;
	
	/**
	 * Item Category Management page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(AdsCategory adsCategory,PageBean<AdsCategory> pageBean,Model model){
		model.addAttribute("title", "Item category list");
		model.addAttribute("name", adsCategory.getName());
		model.addAttribute("pageBean", adsCategoryService.findlist(pageBean, adsCategory));
		return "admin/ads_category/list";
	}
	
	/**
	 * Category add page
	 * @param adsCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("title", "Add item category");
		model.addAttribute("adsCategorys", adsCategoryService.findTopCategorys());
		return "admin/ads_category/add";
	}
	
	/**
	 * Category Addition Form Submission
	 * @param adsCategory
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(AdsCategory adsCategory){
		if(adsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(adsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(adsCategory.getParent() != null && adsCategory.getParent().getId() == null){
			adsCategory.setParent(null);
		}
		//Data is legal, add to database
		if(adsCategoryService.save(adsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_ADSCATEGORY_ADD_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * edit category page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(name="id",required=true)Long id,Model model){
		model.addAttribute("title", "Edit categorys");
		model.addAttribute("adsCategorys", adsCategoryService.findTopCategorys());
		model.addAttribute("adsCategory", adsCategoryService.findById(id));
		return "admin/ads_category/edit";
	}
	
	/**
	 * Category Edit Form Submission
	 * @param adsCategory
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(AdsCategory adsCategory){
		if(adsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(adsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Make sure the current category has a valid parent category
		if(adsCategory.getParent() != null && adsCategory.getParent().getId() == null){
			adsCategory.setParent(null);
		}
		if(adsCategory.getId() == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Check if it exist such category in the database
		AdsCategory existAdsCategory = adsCategoryService.findById(adsCategory.getId());
		if(existAdsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		existAdsCategory.setIcon(adsCategory.getIcon());
		existAdsCategory.setName(adsCategory.getName());
		existAdsCategory.setParent(adsCategory.getParent());
		existAdsCategory.setSort(adsCategory.getSort());
		//Data is legal, add to database
		if(adsCategoryService.save(existAdsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_ADSCATEGORY_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Delete category
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			adsCategoryService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_ADSCATEGORY_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
