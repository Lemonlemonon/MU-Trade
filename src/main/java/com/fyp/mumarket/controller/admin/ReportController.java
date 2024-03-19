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
import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.ReportAds;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.service.common.AdsService;
import com.fyp.mumarket.service.common.ReportAdsService;
import com.fyp.mumarket.service.common.StudentService;

/**
 * Controller for report management page
 * @author Administrator
 *
 */
@RequestMapping("/admin/report")
@Controller
public class ReportController {

	@Autowired
	private AdsService adsService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ReportAdsService reportAdsService;
	
	/**
	 * Controller for report management page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(ReportAds reportAds,PageBean<ReportAds> pageBean,Model model){
		if(reportAds.getStudent() != null && reportAds.getStudent().getSn() != null){
			Student student = studentService.findBySn(reportAds.getStudent().getSn());
			if(student != null){
				reportAds.setStudent(student);
			}
		}
		List<Ads> adsList = null;
		if(reportAds.getAds() != null && reportAds.getAds().getName() != null){
			adsList = adsService.findListByName(reportAds.getAds().getName());
		}
		model.addAttribute("title", "Reportlist");
		model.addAttribute("content", reportAds.getContent());
		model.addAttribute("name", reportAds.getAds() == null ? null : reportAds.getAds().getName());
		model.addAttribute("sn", reportAds.getStudent() == null ? null : reportAds.getStudent().getSn());
		model.addAttribute("pageBean", reportAdsService.findlist(pageBean, reportAds,adsList));
		return "admin/report/list";
	}
	
	

	
	/**
	 * Report deleting action
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		reportAdsService.delete(id);
		return Result.success(true);
	}
}
