package com.fyp.mutrade.controller.admin;
import org.springframework.beans.BeanUtils;
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
import com.fyp.mutrade.entity.common.News;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.common.NewsService;
import com.fyp.mutrade.util.ValidateEntityUtil;
//News controller
@RequestMapping("/admin/news")
@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	@Autowired
	private OperaterLogService operaterLogService;
	//News list request
	@RequestMapping(value="/list")
	public String list(Model model,News news,PageBean<News> pageBean){
		model.addAttribute("title", "New&Announcement");
		model.addAttribute("newsTitle", news.getTitle());
		model.addAttribute("pageBean", newsService.findList(pageBean,news));
		return "admin/news/list";
	}
	
	//Add news request
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/news/add";
	}
	
	//Add news form submission
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(News news){
		//validate data using global method
		CodeMsg validate = ValidateEntityUtil.validate(news);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//If data is empty
		if(newsService.save(news) == null){
			return Result.error(CodeMsg.ADMIN_NEWS_ADD_ERROR);
		}
		operaterLogService.add("Added News: " + news);
		return Result.success(true);
	}
	//New edit request
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("news", newsService.find(id));
		return "admin/news/edit";
	}
	//News edit form submission
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(News news){
		//validate edited data object using global method
		CodeMsg validate = ValidateEntityUtil.validate(news);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//data legal save into database
		News findById = newsService.find(news.getId());
		//Copy specified fields from the submitted news and announcement information to an existing user object; this method will overwrite the existing field content.
		BeanUtils.copyProperties(news, findById, "id","createTime","updateTime");
		if(newsService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_NEWS_EDIT_ERROR);
		}
		operaterLogService.add("Edited News: " + findById);
		return Result.success(true);
	}
	//Delete News by id
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		newsService.delete(id);
		operaterLogService.add("Deleted news, News id: " + id);
		return Result.success(true);
	}
}
