package com.fyp.mumarket.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.common.GoodsCategory;
import com.fyp.mumarket.service.common.GoodsCategoryService;
import com.fyp.mumarket.util.ValidateEntityUtil;

/**
 * Item Category Management Controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/goods_category")
@Controller
public class GoodsCategoryController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	/**
	 * Item Category Management page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(GoodsCategory goodsCategory,PageBean<GoodsCategory> pageBean,Model model){
		model.addAttribute("title", "Item category list");
		model.addAttribute("name", goodsCategory.getName());
		model.addAttribute("pageBean", goodsCategoryService.findlist(pageBean, goodsCategory));
		return "admin/goods_category/list";
	}
	
	/**
	 * Category add page
	 * @param goodsCategory
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("title", "Add item category");
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		return "admin/goods_category/add";
	}
	
	/**
	 * Category Addition Form Submission
	 * @param goodsCategory
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(GoodsCategory goodsCategory){
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goodsCategory.getParent() != null && goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		//Data is legal, add to database
		if(goodsCategoryService.save(goodsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_ADD_ERROR);
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
		model.addAttribute("goodsCategorys", goodsCategoryService.findTopCategorys());
		model.addAttribute("goodsCategory", goodsCategoryService.findById(id));
		return "admin/goods_category/edit";
	}
	
	/**
	 * Category Edit Form Submission
	 * @param goodsCategory
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(GoodsCategory goodsCategory){
		if(goodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(goodsCategory);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goodsCategory.getParent() != null && goodsCategory.getParent().getId() == null){
			goodsCategory.setParent(null);
		}
		if(goodsCategory.getId() == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		GoodsCategory existGoodsCategory = goodsCategoryService.findById(goodsCategory.getId());
		if(existGoodsCategory == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		existGoodsCategory.setIcon(goodsCategory.getIcon());
		existGoodsCategory.setName(goodsCategory.getName());
		existGoodsCategory.setParent(goodsCategory.getParent());
		existGoodsCategory.setSort(goodsCategory.getSort());
		//Data is legal, add to database
		if(goodsCategoryService.save(existGoodsCategory) ==null){
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_EDIT_ERROR);
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
			goodsCategoryService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_GOODSCATEGORY_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
