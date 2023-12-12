package com.fyp.mumarket.controller.admin;
import org.springframework.beans.BeanUtils;
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
import com.fyp.mumarket.entity.common.FriendLink;
import com.fyp.mumarket.service.admin.OperaterLogService;
import com.fyp.mumarket.service.common.FriendLinkService;
import com.fyp.mumarket.util.ValidateEntityUtil;

//Related site controller
@RequestMapping("/admin/friend_link")
@Controller
public class FriendLinkController {

	@Autowired
	private FriendLinkService friendLinkService;
	@Autowired
	private OperaterLogService operaterLogService;

	//Site management list page
	@RequestMapping(value="/list")
	public String list(Model model,FriendLink friendLink,PageBean<FriendLink> pageBean){
		model.addAttribute("title", "Related site list");
		model.addAttribute("name", friendLink.getName());
		model.addAttribute("pageBean", friendLinkService.findList(pageBean,friendLink));
		return "admin/friend_link/list";
	}
	
	//add site page
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/friend_link/add";
	}
	
	//Related site Form Submission
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(FriendLink friendLink){
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(friendLink);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Data is legal, add to database
		if(friendLinkService.save(friendLink) == null){
			return Result.error(CodeMsg.ADMIN_FRIENDLINK_ADD_ERROR);
		}
		operaterLogService.add("Added site:" + friendLink);
		return Result.success(true);
	}
	
	//Related site edit Submission
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("friendLink", friendLinkService.find(id));
		return "admin/friend_link/edit";
	}
	
	//Edit related site Form Submission
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(FriendLink friendLink){
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(friendLink);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Data is legal, add to database
		FriendLink findById = friendLinkService.find(friendLink.getId());
		//Send message to log
		BeanUtils.copyProperties(friendLink, findById, "id","createTime","updateTime");
		if(friendLinkService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_FRIENDLINK_EDIT_ERROR);
		}
		operaterLogService.add("Edited site URL：" + findById);
		return Result.success(true);
	}
	
	//Delete related site
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="ids",required=true)String ids){
		String[] split = ids.split(",");
		for(String id : split){
			friendLinkService.delete(Long.valueOf(id));
		}
		operaterLogService.add("Sites removed id: " + ids);
		return Result.success(true);
	}
}
