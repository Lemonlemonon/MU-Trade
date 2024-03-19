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
import com.fyp.mumarket.entity.common.RelatedSite;
import com.fyp.mumarket.service.admin.OperaterLogService;
import com.fyp.mumarket.service.common.RelatedSiteService;
import com.fyp.mumarket.util.ValidateEntityUtil;

//Related site controller
@RequestMapping("/admin/related_site")
@Controller
public class RelatedSiteController {

	@Autowired
	private RelatedSiteService relatedSiteService;
	@Autowired
	private OperaterLogService operaterLogService;

	//Site management list page
	@RequestMapping(value="/list")
	public String list(Model model,RelatedSite relatedSite,PageBean<RelatedSite> pageBean){
		model.addAttribute("title", "Related site list");
		model.addAttribute("name", relatedSite.getName());
		model.addAttribute("pageBean", relatedSiteService.findList(pageBean,relatedSite));
		return "admin/related_site/list";
	}
	
	//add site page
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/related_site/add";
	}
	
	//Related site Form Submission
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(RelatedSite relatedSite){
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(relatedSite);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Data is legal, add to database
		if(relatedSiteService.save(relatedSite) == null){
			return Result.error(CodeMsg.ADMIN_RELATEDSITE_ADD_ERROR);
		}
		operaterLogService.add("Added site:" + relatedSite);
		return Result.success(true);
	}
	
	//Related site edit Submission
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(name="id",required=true)Long id){
		model.addAttribute("relatedSite", relatedSiteService.find(id));
		return "admin/related_site/edit";
	}
	
	//Edit related site Form Submission
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(RelatedSite relatedSite){
		//Using global method to validate object
		CodeMsg validate = ValidateEntityUtil.validate(relatedSite);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Data is legal, add to database
		RelatedSite findById = relatedSiteService.find(relatedSite.getId());
		//Send message to log
		BeanUtils.copyProperties(relatedSite, findById, "id","createTime","updateTime");
		if(relatedSiteService.save(findById) == null){
			return Result.error(CodeMsg.ADMIN_RELATEDSITE_EDIT_ERROR);
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
			relatedSiteService.delete(Long.valueOf(id));
		}
		operaterLogService.add("Sites removed id: " + ids);
		return Result.success(true);
	}
}
