package com.fyp.mutrade.controller.admin;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.fyp.mutrade.entity.admin.OperaterLog;
import com.fyp.mutrade.entity.admin.Role;
import com.fyp.mutrade.entity.admin.User;
import com.fyp.mutrade.interceptor.constant.SessionConstant;
import com.fyp.mutrade.listener.SessionListener;
import com.fyp.mutrade.service.admin.DbBackupService;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.admin.UserService;
import com.fyp.mutrade.util.SessionUtil;
import com.fyp.mutrade.util.StringUtil;
import com.fyp.mutrade.util.ValidateEntityUtil;

/**
 * System controller
 * @author Administrator
 *
 */
@RequestMapping("/system")
@Controller
public class SystemController {

	
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DbBackupService dbBackupService;
	
	private Logger log = LoggerFactory.getLogger(SystemController.class);
	
	/**
	 * Login page
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model){
		return "admin/system/login";
	}
	
	/**
	 * User login form processing
	 * @param request
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> login(HttpServletRequest request,User user,String cpacha){
		if(user == null){
			return Result.error(CodeMsg.DATA_ERROR);
		}
		//validate entity using globe method
		CodeMsg validate = ValidateEntityUtil.validate(user);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//entity valid，validate cpacha
		if(StringUtils.isEmpty(cpacha)){
			return Result.error(CodeMsg.CPACHA_EMPTY);
		}
		//check capcha input is not null，gain input from session
		Object attribute = request.getSession().getAttribute("admin_login");
		if(attribute == null){
			return Result.error(CodeMsg.SESSION_EXPIRED);
		}
		//Indicates the session has not expired, further determine if the verification code entered by the user is correct
		
		if(!cpacha.equalsIgnoreCase(attribute.toString())){
			return Result.error(CodeMsg.CPACHA_ERROR);
		}
		//Indicates the verification code is correct; begin querying the database to verify if the password is correct
		User findByUsername = userService.findByUsername(user.getUsername());
		//Determine if result is empty
		if(findByUsername == null){
			return Result.error(CodeMsg.ADMIN_USERNAME_NO_EXIST);
		}
		//Indicates the user exists; check if the password is correct
		if(!findByUsername.getPassword().equals(user.getPassword())){
			return Result.error(CodeMsg.ADMIN_PASSWORD_ERROR);
		}
		//Indicates the password is correct, check if user's status is available.
		if(findByUsername.getStatus() == User.ADMIN_USER_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_USER_UNABLE);
		}
		//check if user's role available
		if(findByUsername.getRole() == null || findByUsername.getRole().getStatus() == Role.ADMIN_ROLE_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_UNABLE);
		}
		//check if user's authority exist
		if(findByUsername.getRole().getAuthorities() == null || findByUsername.getRole().getAuthorities().size() == 0){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_AUTHORITES_EMPTY);
		}
		//Check if everything passed, permit to login, store user infomation in session
		request.getSession().setAttribute(SessionConstant.SESSION_USER_LOGIN_KEY, findByUsername);
		//Destroy the verification code in the session
		request.getSession().setAttribute("admin_login", null);
		//Write the login record to the log database
		operaterLogService.add("User ["+user.getUsername()+"] at  [" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "] Logined in to the system!");
		log.info("User login successful，user = " + findByUsername);
		return Result.success(true);
	}
	
	/**
	 * System page after login
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("operatorLogs", operaterLogService.findLastestLog(10));
		model.addAttribute("userTotal", userService.total());
		model.addAttribute("operatorLogTotal", operaterLogService.total());
		model.addAttribute("databaseBackupTotal", dbBackupService.total());
		model.addAttribute("onlineUserTotal", SessionListener.onlineUserCount);
		return "admin/system/index";
	}
	
	/**
	 * Destroy Login
	 * @return
	 */
	@RequestMapping(value="/logout")
	public String logout(){
		User loginedUser = SessionUtil.getLoginedUser();
		if(loginedUser != null){
			SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, null);
		}
		return "redirect:login";
	}
	
	/**
	 * No permission page
	 * @return
	 */
	@RequestMapping(value="/no_right")
	public String noRight(){
		return "admin/system/no_right";
	}
	
	/**
	 * Update user information
	 * @return
	 */
	@RequestMapping(value="/update_userinfo",method=RequestMethod.GET)
	public String updateUserInfo(){
		return "admin/system/update_userinfo";
	}
	
	/**
	 * Save user information update
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/update_userinfo",method=RequestMethod.POST)
	public String updateUserInfo(User user){
		User loginedUser = SessionUtil.getLoginedUser();
		loginedUser.setEmail(user.getEmail());
		loginedUser.setMobile(user.getMobile());
		loginedUser.setHeadPic(user.getHeadPic());
		//First store into datebase
		userService.save(loginedUser);
		//update value in the session
		SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, loginedUser);
		return "redirect:update_userinfo";
	}
	
	/**
	 * Password updating page
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.GET)
	public String updatePwd(){
		return "admin/system/update_pwd";
	}
	
	/**
	 * Password updating form submit
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/update_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updatePwd(@RequestParam(name="oldPwd",required=true)String oldPwd,
			@RequestParam(name="newPwd",required=true)String newPwd
			){
		User loginedUser = SessionUtil.getLoginedUser();
		if(!loginedUser.getPassword().equals(oldPwd)){
			return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_ERROR);
		}
		if(StringUtils.isEmpty(newPwd)){
			return Result.error(CodeMsg.ADMIN_USER_UPDATE_PWD_EMPTY);
		}
		loginedUser.setPassword(newPwd);
		//Save to database
		userService.save(loginedUser);
		//Update session
		SessionUtil.set(SessionConstant.SESSION_USER_LOGIN_KEY, loginedUser);
		return Result.success(true);
	}
	
	/**
	 * Log management list
	 * @param model
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/operator_log_list")
	public String operatorLogList(Model model,OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		model.addAttribute("pageBean", operaterLogService.findList(operaterLog, pageBean));
		model.addAttribute("operator", operaterLog.getOperator());
		model.addAttribute("title", "Log list");
		return "admin/system/operator_log_list";
	}
	
	/**
	 * Remove selected logs
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete_operator_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(String ids){
		if(!StringUtils.isEmpty(ids)){
			String[] splitIds = ids.split(",");
			for(String id : splitIds){
				operaterLogService.delete(Long.valueOf(id));
			}
		}
		return Result.success(true);
	}
	
	/**
	 * Remove all logs
	 * @return
	 */
	@RequestMapping(value="/delete_all_operator_log",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteAll(){
		operaterLogService.deleteAll();
		return Result.success(true);
	}
}
