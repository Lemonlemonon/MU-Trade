package com.fyp.mutrade.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.bean.CodeMsg;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.common.SiteSetting;
import com.fyp.mutrade.service.admin.OperaterLogService;
import com.fyp.mutrade.service.common.SiteSettingService;
import com.fyp.mutrade.util.ValidateEntityUtil;
//Site setting controller
@RequestMapping("/admin/site_setting")
@Controller
public class SiteSettingController {
	@Autowired
	private SiteSettingService siteSettingService;
	@Autowired
	private OperaterLogService operaterLogService;
	
	//Site settings form submission
	@RequestMapping(value="/save_setting",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> setting(SiteSetting siteSetting){
		//Validate data using global method
		CodeMsg validate = ValidateEntityUtil.validate(siteSetting);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		//Data validated upload to database
		//Indicate if is the first submission
		if(siteSetting.getId() != null){
			//If not
			SiteSetting find = siteSettingService.find();
			siteSetting.setCreateTime(find.getCreateTime());
		}
		
		if(siteSettingService.save(siteSetting) == null){
			CodeMsg error = CodeMsg.ADMIN_SITESETTING_EDIT_ERROR;
			return Result.error(error);
		}
		
		operaterLogService.add("Edited site setting" + siteSetting);
		return Result.success(true);
	}
	
	//site setting page
	@RequestMapping(value="/setting",method=RequestMethod.GET)
	public String setting(Model model){
		model.addAttribute("siteSetting", siteSettingService.find());
		return "admin/site_setting/setting";
	}
	
	
}
