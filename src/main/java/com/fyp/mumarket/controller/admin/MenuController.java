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
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.admin.Menu;
import com.fyp.mumarket.service.admin.MenuService;
import com.fyp.mumarket.service.admin.OperaterLogService;
import com.fyp.mumarket.util.MenuUtil;
import com.fyp.mumarket.util.ValidateEntityUtil;

/**
 * Backend menu management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	/**
	 * Menu list display page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","Menu list");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("thirdMenus",MenuUtil.getThirdMenus(findAll));
		return "admin/menu/list";
	}
	
	/**
	 * Menu addition page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","Menu list");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		return "admin/menu/add";
	}
	
	/**
	 * Menu addition form submission handling
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Menu menu){
		if(menu == null){
			Result.error(CodeMsg.DATA_ERROR);
		}
		//Use the unified entity validation method to check if it's valid
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(menu.getParent() != null){
			if(menu.getParent().getId() == null){
				menu.setParent(null);
			}
		}
		//Indicates that all validations have passed, and the addition to the database begins
		if(menuService.save(menu) == null){
			Result.error(CodeMsg.ADMIN_MENU_ADD_ERROR);
		}
		//Database addition operation succeeded, log the action
		operaterLogService.add("added menu information[" + menu + "]");
		return Result.success(true);
	}
	
	/**
	 * Menu editing page
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String eidt(Model model,@RequestParam(name="id",required=true)Long id){
		List<Menu> findAll = menuService.findAll();
		model.addAttribute("title","Menu list");
		model.addAttribute("topMenus",MenuUtil.getTopMenus(findAll));
		model.addAttribute("secondMenus",MenuUtil.getSecondMenus(findAll));
		model.addAttribute("menu",menuService.find(id));
		return "admin/menu/edit";
	}
	
	/**
	 * Menu editing page form submission handling
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Menu menu){
		if(menu == null){
			Result.error(CodeMsg.DATA_ERROR);
		}
		if(menu.getId() == null){
			Result.error(CodeMsg.ADMIN_MENU_ID_EMPTY);
		}
		//Use the ValidateEntityUtil to check if it's valid
		CodeMsg validate = ValidateEntityUtil.validate(menu);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(menu.getParent() != null){
			if(menu.getParent().getId() == null){
				menu.setParent(null);
			}
		}
		Menu existMenu = menuService.find(menu.getId());
		if(existMenu == null){
			Result.error(CodeMsg.ADMIN_MENU_ID_ERROR);
		}
		//Indicates that all validations have passed, and the addition to the database begins
		existMenu.setIcon(menu.getIcon());
		existMenu.setName(menu.getName());
		existMenu.setParent(menu.getParent());
		existMenu.setSort(menu.getSort());
		existMenu.setUrl(menu.getUrl());
		existMenu.setButton(menu.isButton());
		existMenu.setShow(menu.isShow());
		if(menuService.save(existMenu) == null){
			Result.error(CodeMsg.ADMIN_MENU_ADD_ERROR);
		}
		//Database addition operation succeeded, log the action
		operaterLogService.add("edited menu information [" + existMenu + "]");
		return Result.success(true);
	}
	
	/**
	 * removed menu information
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			menuService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_MENU_DELETE_ERROR);
		}
		//Database addition operation succeeded, record the log
		operaterLogService.add("removed menu information，Menu ID [" + id + "]");
		return Result.success(true);
	}
}
