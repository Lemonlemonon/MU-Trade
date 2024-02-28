package com.fyp.mumarket.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.entity.common.WantedGoods;
import com.fyp.mumarket.service.common.StudentService;
import com.fyp.mumarket.service.common.WantedGoodsService;

/**
 * Wanted good management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/wanted_goods")
@Controller
public class WantedGoodsController {

	@Autowired
	private WantedGoodsService wantedGoodsService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * Wanted good management listing page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(WantedGoods wantedGoods,PageBean<WantedGoods> pageBean,Model model){
		if(wantedGoods.getStudent() != null && wantedGoods.getStudent().getSn() != null){
			Student student = studentService.findBySn(wantedGoods.getStudent().getSn());
			if(student != null){
				wantedGoods.setStudent(student);
			}
		}
		model.addAttribute("title", "Wanted goods list");
		model.addAttribute("name", wantedGoods.getName());
		model.addAttribute("sn", wantedGoods.getStudent() == null ? null : wantedGoods.getStudent().getSn());
		model.addAttribute("pageBean", wantedGoodsService.findWantedGoodslist(pageBean, wantedGoods));
		return "admin/wanted_goods/list";
	}
	
	/**
	 * Wanted goods deleting
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		wantedGoodsService.delete(id);
		return Result.success(true);
	}
}
