package com.fyp.mumarket.controller.admin;

import java.util.List;

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
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.GoodsCategory;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.service.common.GoodsCategoryService;
import com.fyp.mumarket.service.common.GoodsService;
import com.fyp.mumarket.service.common.StudentService;

/**
 * Item management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/goods")
@Controller
public class GoodsController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * Item management page
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Goods goods,PageBean<Goods> pageBean,Model model){
		if(goods.getStudent() != null && goods.getStudent().getSn() != null){
			Student student = studentService.findBySn(goods.getStudent().getSn());
			if(student != null){
				goods.setStudent(student);
			}
		}
		if(goods.getGoodsCategory() != null && goods.getGoodsCategory().getName() != null){
			List<GoodsCategory> goodsCategorys = goodsCategoryService.findByName(goods.getGoodsCategory().getName());
			if(goodsCategorys != null && goodsCategorys.size() > 0){
				goods.setGoodsCategory(goodsCategorys.get(0));
			}
		}
		goods.setStatus(-1);
		model.addAttribute("title", "Item list");
		model.addAttribute("name", goods.getName());
		model.addAttribute("goodsCategoryName", goods.getGoodsCategory() == null ? null : goods.getGoodsCategory().getName());
		model.addAttribute("sn", goods.getStudent() == null ? null : goods.getStudent().getSn());
		model.addAttribute("pageBean", goodsService.findlist(pageBean, goods));
		return "admin/goods/list";
	}
	
	

	/**
	 * Place item
	 * @param id,status
	 * @return
	 */
	@RequestMapping(value="/up_down",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> upDown(@RequestParam(name="id",required=true)Long id ,@RequestParam(name="status",required=true)Integer status){
		Goods goods = goodsService.findById(id);
		if(goods == null){
			return Result.error(CodeMsg.ADMIN_GOODS_NO_EXIST);
		}
		if(goods.getStatus() == status){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_NO_CHANGE);
		}
		if(status != Goods.GOODS_STATUS_UP && status != Goods.GOODS_STATUS_DOWN){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_ERROR);
		}
		if(goods.getStatus() == Goods.GOODS_STATUS_SOLD){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_UNABLE);
		}
		goods.setStatus(status);
		//Update database
		if(goodsService.save(goods) ==null){
			return Result.error(CodeMsg.ADMIN_GOODS_EDIT_ERROR);
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
		Goods goods = goodsService.findById(id);
		if(goods == null){
			return Result.error(CodeMsg.ADMIN_GOODS_NO_EXIST);
		}
		if(goods.getRecommend() == recommend){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_NO_CHANGE);
		}
		if(recommend != Goods.GOODS_RECOMMEND_OFF && recommend != Goods.GOODS_RECOMMEND_ON){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_ERROR);
		}
		if(goods.getStatus() == Goods.GOODS_STATUS_SOLD){
			return Result.error(CodeMsg.ADMIN_GOODS_STATUS_UNABLE);
		}
		goods.setRecommend(recommend);;
		//Update database
		if(goodsService.save(goods) ==null){
			return Result.error(CodeMsg.ADMIN_GOODS_EDIT_ERROR);
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
			goodsService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_GOODS_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
