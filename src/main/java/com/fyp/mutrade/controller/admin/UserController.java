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
import com.fyp.mutrade.entity.admin.User;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.admin.RoleService;
import com.fyp.mutrade.service.admin.UserService;
import com.fyp.mutrade.util.ValidateEntityUtil;

/**
 * User management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OperaterLogService operaterLogService;
	/**
	 * User list page
	 * @param model
	 * @param user
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,User user,PageBean<User> pageBean){
		model.addAttribute("title", "Userlist");
		model.addAttribute("username", user.getUsername());
		model.addAttribute("pageBean", userService.findList(user, pageBean));
		return "admin/user/list";
	}
	
	/**
	 * User creation page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("roles", roleService.findAll());
		return "admin/user/add";
	}
	
	/**
	 * User creation form handling
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(User user){
		//Validate entity using global method
		CodeMsg validate = ValidateEntityUtil.validate(user);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(user.getRole() == null || user.getRole().getId() == null){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_EMPTY);
		}
		//Determine is username exist
		if(userService.isExistUsername(user.getUsername(), 0l)){
			return Result.error(CodeMsg.ADMIN_USERNAME_EXIST);
		}
		//Everything passed，create user date into datebase
		if(userService.save(user) == null){
			return Result.error(CodeMsg.ADMIN_USE_ADD_ERROR);
		}
		operaterLogService.add("Created user，username：" + user.getUsername());
		return Result.success(true);
	}
	
	/**
	 * User edition page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("user", userService.find(id));
		return "admin/user/edit";
	}
	
	/**
	 * User edition form handling
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(User user){
		//Validate entity using global method
		CodeMsg validate = ValidateEntityUtil.validate(user);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(user.getRole() == null || user.getRole().getId() == null){
			return Result.error(CodeMsg.ADMIN_USER_ROLE_EMPTY);
		}
		if(user.getId() == null || user.getId().longValue() <= 0){
			return Result.error(CodeMsg.ADMIN_USE_NO_EXIST);
		}
		if(userService.isExistUsername(user.getUsername(), user.getId())){
			return Result.error(CodeMsg.ADMIN_USERNAME_EXIST);
		}
		//Every passed, save in database
		User findById = userService.find(user.getId());
		//Copy the specified fields of the submitted user information into the existing user object
		BeanUtils.copyProperties(user, findById, "id","createTime","updateTime");
		if(userService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_USE_EDIT_ERROR);
		}
		operaterLogService.add("Edited User，Username：" + user.getUsername());
		return Result.success(true);
	}
	
	/**
	 * Remove user
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			userService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
		}
		operaterLogService.add("add user，User ID：" + id);
		return Result.success(true);
	}
}
