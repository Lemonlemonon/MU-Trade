package com.fyp.mumarket.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.common.Comment;
import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.service.common.CommentService;
import com.fyp.mumarket.service.common.AdsService;
import com.fyp.mumarket.service.common.StudentService;

/**
 * Comment management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/comment")
@Controller
public class CommentController {

	@Autowired
	private AdsService adsService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * Comment management listing page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Comment comment,PageBean<Comment> pageBean,Model model){
		if(comment.getStudent() != null && comment.getStudent().getSn() != null){
			Student student = studentService.findBySn(comment.getStudent().getSn());
			if(student != null){
				comment.setStudent(student);
			}
		}
		List<Ads> adsList = null;
		if(comment.getAds() != null && comment.getAds().getName() != null){
			adsList = adsService.findListByName(comment.getAds().getName());
		}
		model.addAttribute("title", "Comment list");
		model.addAttribute("content", comment.getContent());
		model.addAttribute("name", comment.getAds() == null ? null : comment.getAds().getName());
		model.addAttribute("sn", comment.getStudent() == null ? null : comment.getStudent().getSn());
		model.addAttribute("pageBean", commentService.findlist(pageBean, comment,adsList));
		return "admin/comment/list";
	}
	
	

	
	/**
	 * Comment deleting
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		commentService.delete(id);
		return Result.success(true);
	}
}
