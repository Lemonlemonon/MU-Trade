package com.fyp.mumarket.controller.user;

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
import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.News;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.interceptor.constant.SessionConstant;
import com.fyp.mumarket.service.common.AdsCategoryService;
import com.fyp.mumarket.service.common.AdsService;
import com.fyp.mumarket.service.common.NewsService;
import com.fyp.mumarket.service.common.StudentService;
import com.fyp.mumarket.util.SessionUtil;
import com.fyp.mumarket.util.ValidateEntityUtil;

/**
 * Home page index controller
 * @author Administrator
 */
@RequestMapping("/home/index")
@Controller
public class IndexController {
	@Autowired
	private AdsService adsService;
	@Autowired
	private AdsCategoryService adsCategoryService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private NewsService newsService;
	/**
	 * index page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model,PageBean<Ads> pageBean,Ads ads){
		pageBean.setPageSize(12);
		ads.setStatus(Ads.ADS_STATUS_UP);
		model.addAttribute("pageBean", adsService.findlist(pageBean, ads));
		model.addAttribute("name",ads.getName());
		model.addAttribute("newsList",newsService.findList(3));
		return "home/index/index";
	}
	
	/**
	 * News detail page
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/news_detail")
	public String index(Model model,@RequestParam(name="id",required=true)Long id){
		News news = newsService.find(id);
		model.addAttribute("news",news);
		news.setViewNumber(news.getViewNumber()+1);
		newsService.save(news);
		return "home/index/news_detail";
	}
	
	/**
	 * User login paGe
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "home/index/login";
	}
	
	/**
	 * Logout
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, null);
		return "redirect:login";
	}
	
	/**
	 * Verify student id
	 * @param sn
	 * @return
	 */
	@RequestMapping(value="/check_sn",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> checkSn(@RequestParam(name="sn",required=true)String sn){
		Student student = studentService.findBySn(sn);
		return Result.success(student == null);
	}
	
	/**
	 * Sign up form submit
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> register(Student student){
		CodeMsg validate = ValidateEntityUtil.validate(student);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Pass verification
		if(studentService.findBySn(student.getSn()) != null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_ERROR);
		}
		//Indicating sign up success, posting user information into session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}
	
	/**
	 * Login form submit
	 * @param sn
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(@RequestParam(name="sn",required=true)String sn,
			@RequestParam(name="password",required=true)String password){
		Student student = studentService.findBySn(sn);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_REGISTER_SN_EXIST);
		}
		student = studentService.save(student);
		if(student == null){
			return Result.error(CodeMsg.HOME_STUDENT_SN_NO_EXIST);
		}
		//Student number exist, now verify password
		if(!student.getPassword().equals(password)){
			return Result.error(CodeMsg.HOME_STUDENT_PASSWORD_ERROR);
		}
		//Check if the account is banned
		if(student.getStatus() != Student.STUDENT_STATUS_ENABLE){
			return Result.error(CodeMsg.HOME_STUDENT_UNABLE);
		}
		//Indicating sign up success, posting user information into session
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, student);
		return Result.success(true);
	}

}
