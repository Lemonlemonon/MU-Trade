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
import com.fyp.mumarket.entity.common.RequestAds;
import com.fyp.mumarket.service.common.StudentService;
import com.fyp.mumarket.service.common.RequestAdsService;

/**
 * Request ad management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/request_ads")
@Controller
public class RequestAdsController {

	@Autowired
	private RequestAdsService requestAdsService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * Request ad management listing page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(RequestAds requestAds,PageBean<RequestAds> pageBean,Model model){
		if(requestAds.getStudent() != null && requestAds.getStudent().getSn() != null){
			Student student = studentService.findBySn(requestAds.getStudent().getSn());
			if(student != null){
				requestAds.setStudent(student);
			}
		}
		model.addAttribute("title", "Request ads list");
		model.addAttribute("name", requestAds.getName());
		model.addAttribute("sn", requestAds.getStudent() == null ? null : requestAds.getStudent().getSn());
		model.addAttribute("pageBean", requestAdsService.findRequestAdslist(pageBean, requestAds));
		return "admin/request_ads/list";
	}
	
	/**
	 * Request ads deleting
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		requestAdsService.delete(id);
		return Result.success(true);
	}
}
