package com.fyp.mutrade.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.fyp.mutrade.bean.CodeMsg;
import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.admin.Menu;
import com.fyp.mutrade.entity.admin.Role;
import com.fyp.mutrade.service.admin.MenuService;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.admin.RoleService;
import com.fyp.mutrade.util.MenuUtil;
import com.fyp.mutrade.util.ValidateEntityUtil;

/**
 * Backend role management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {

	
	private Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * Role search with pages
	 * @param model
	 * @param role
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,Role role,PageBean<Role> pageBean){
		model.addAttribute("title", "Role list");
		model.addAttribute("name", role.getName());
		model.addAttribute("pageBean", roleService.findByName(role, pageBean));
		return "admin/role/list";
	}
	
	/**
	 * Role addition page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("thirdMenus",MenuUtil.getThirdMenus(findAll));
		return "admin/role/add";
	}
	
	/**
	 * Role addition form submission handling
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Role role){
		//validate entity using globle method
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(roleService.save(role) == null){
			return Result.error(CodeMsg.ADMIN_ROLE_ADD_ERROR);
		}
		log.info("Created role ["+role+"]");
		operaterLogService.add("Created role ["+role.getName()+"]");
		return Result.success(true);
	}
	
	/**
	 * Role editing page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(name="id",required=true)Long id,Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("thirdMenus",MenuUtil.getThirdMenus(findAll));
		Role role = roleService.find(id);
		model.addAttribute("role", role);
		model.addAttribute("authorities",JSONArray.toJSON(role.getAuthorities()).toString());
		return "admin/role/edit";
	}
	
	/**
	 * "Role edit form submission handling
	 * @param request
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Role role){
		//validate using validate entity
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		Role existRole = roleService.find(role.getId());
		if(existRole == null){
			return Result.error(CodeMsg.ADMIN_ROLE_NO_EXIST);
		}
		existRole.setName(role.getName());
		existRole.setRemark(role.getRemark());
		existRole.setStatus(role.getStatus());
		existRole.setAuthorities(role.getAuthorities());
		if(roleService.save(existRole) == null){
			return Result.error(CodeMsg.ADMIN_ROLE_EDIT_ERROR);
		}
		log.info("Edit role ["+role+"]");
		operaterLogService.add("Edit role ["+role.getName()+"]");
		return Result.success(true);
	}
	
	/**
	 * Remove role
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			roleService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			return Result.error(CodeMsg.ADMIN_ROLE_DELETE_ERROR);
		}
		log.info("Edited role ID ["+id+"]");
		operaterLogService.add("Removed role ID ["+id+"]");
		return Result.success(true);
	}
}
