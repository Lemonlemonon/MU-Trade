package com.fyp.mutrade.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.bean.CodeMsg;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.dao.common.AdsBiddingDao;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.AdsBidding;
import com.fyp.mutrade.entity.common.Comment;
import com.fyp.mutrade.entity.common.ReportAds;
import com.fyp.mutrade.entity.common.RequestAds;
import com.fyp.mutrade.entity.common.Student;
import com.fyp.mutrade.interceptor.constant.SessionConstant;
import com.fyp.mutrade.service.common.AdsCategoryService;
import com.fyp.mutrade.service.common.AdsService;
import com.fyp.mutrade.service.common.CommentService;
import com.fyp.mutrade.service.common.ReportAdsService;
import com.fyp.mutrade.service.common.RequestAdsService;
import com.fyp.mutrade.service.common.StudentService;
import com.fyp.mutrade.util.SessionUtil;
import com.fyp.mutrade.util.ValidateEntityUtil;
/**
 * Student Center Controller
 * @author Administrator
 *
 */
@RequestMapping("/home/student")
@Controller
public class UserStudentController {

	@Autowired
	private AdsCategoryService adsCategoryService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private AdsService adsService;
	@Autowired
	private RequestAdsService requestAdsService;
	@Autowired
	private ReportAdsService reportAdsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AdsBiddingDao adsBiddingDao;
	/**
	 * Student center page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		model.addAttribute("adsList", adsService.findByStudent(loginedStudent));
		model.addAttribute("requestAdsList", requestAdsService.findByStudent(loginedStudent));
		model.addAttribute("reportAdsList", reportAdsService.findByStudent(loginedStudent));
		return "home/student/index";
	}
	
	/**
	 * Personal detail editing form submission
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/edit_info",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editInfo(Student student){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		loginedStudent.setAcademy(student.getAcademy());
		loginedStudent.setGrade(student.getGrade());
		loginedStudent.setMobile(student.getMobile());
		loginedStudent.setNickname(student.getNickname());
		loginedStudent.setSchool(student.getSchool());
		if(studentService.save(loginedStudent) == null){
			return Result.error(CodeMsg.HOME_STUDENT_EDITINFO_ERROR);
		}
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,loginedStudent);
		return Result.success(true);
	}
	
	/**
	 * Save avatar
	 * @param headPic
	 * @return
	 */
	@RequestMapping(value="/update_head_pic",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updateHeadPic(@RequestParam(name="headPic",required=true)String headPic){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		loginedStudent.setHeadPic(headPic);;
		if(studentService.save(loginedStudent) == null){
			return Result.error(CodeMsg.HOME_STUDENT_EDITINFO_ERROR);
		}
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY,loginedStudent);
		return Result.success(true);
	}
	
	/**
	 * Ads publish page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/publish",method=RequestMethod.GET)
	public String publish(Model model){
		return "home/student/publish";
	}
	
	/**
	 * Ads publish form submission
	 * @param ads
	 * @return
	 */
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> publish(Ads ads){
		CodeMsg validate = ValidateEntityUtil.validate(ads);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(ads.getAdsCategory() == null || ads.getAdsCategory().getId() == null || ads.getAdsCategory().getId().longValue() == -1){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_CATEGORY_EMPTY);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		ads.setStudent(loginedStudent);

		//set up ads bidding information when publish
		AdsBidding adsBidding = new AdsBidding();
		adsBidding.setStatus(0);
		adsBidding.setCreateTime(new Date());
		adsBidding.setUpdateTime(new Date());
		AdsBidding bidding = adsBiddingDao.save(adsBidding);

		ads.setAdsBiddingId(bidding.getId());
		Ads g = adsService.save(ads);
		if(g == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		bidding.setAdId(g.getId());
		adsBiddingDao.save(bidding);
		
		return Result.success(true);
	}
	
	/**
	 * Ads editing page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_ads",method=RequestMethod.GET)
	public String publish(@RequestParam(name="id",required=true)Long id,Model model){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Ads ads = adsService.find(id, loginedStudent.getId());
		if(ads == null){
			model.addAttribute("msg", "Item does not exsist!");
			return "error/runtime_error";
		}
		model.addAttribute("ads", ads);
		return "home/student/edit_ads";
	}
	
	/**
	 * Ads editing form submission
	 * @param ads
	 * @return
	 */
	@RequestMapping(value="/edit_ads",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editAds(Ads ads){
		CodeMsg validate = ValidateEntityUtil.validate(ads);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(ads.getAdsCategory() == null || ads.getAdsCategory().getId() == null || ads.getAdsCategory().getId().longValue() == -1){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_CATEGORY_EMPTY);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Ads existAds = adsService.find(ads.getId(), loginedStudent.getId());
		if(existAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		existAds.setBuyPrice(ads.getBuyPrice());
		existAds.setContent(ads.getContent());
		existAds.setAdsCategory(ads.getAdsCategory());
		existAds.setName(ads.getName());
		existAds.setPhoto(ads.getPhoto());
		existAds.setSellPrice(ads.getSellPrice());
		if(adsService.save(existAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Setting for enable/disable ads flag
	 * @param id
	 * @param flag
	 * @return
	 */
	@RequestMapping(value="/update_flag",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updateFlag(@RequestParam(name="id",required=true)Long id,
			@RequestParam(name="flag",required=true,defaultValue="0")Integer flag){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Ads existAds = adsService.find(id, loginedStudent.getId());
		if(existAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		existAds.setFlag(flag);
		if(adsService.save(existAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Edit ads status
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/update_status",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updateStatus(@RequestParam(name="id",required=true)Long id,
			@RequestParam(name="status",required=true,defaultValue="2")Integer status){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Ads existAds = adsService.find(id, loginedStudent.getId());
		if(existAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		existAds.setStatus(status);
		if(adsService.save(existAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Request ads publishing page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/publish_request",method=RequestMethod.GET)
	public String publishRequest(Model model){
		return "home/student/publish_request";
	}
	
	/**
	 * Request ads publish form submission
	 * @param requestAds
	 * @return
	 */
	@RequestMapping(value="/publish_request",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> publishRequest(RequestAds requestAds){
		CodeMsg validate = ValidateEntityUtil.validate(requestAds);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		requestAds.setStudent(loginedStudent);
		if(requestAdsService.save(requestAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Request ads Editing page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_request_ads",method=RequestMethod.GET)
	public String editRequestAds(@RequestParam(name="id",required=true)Long id,Model model){
		RequestAds requestAds = requestAdsService.find(id);
		if(requestAds == null){
			model.addAttribute("msg", "Item does not exsist!");
			return "error/runtime_error";
		}
		model.addAttribute("requestAds", requestAds);
		return "home/student/edit_request";
	}
	
	/**
	 * Request ads Editing form submission
	 * @param requestAds
	 * @return
	 */
	@RequestMapping(value="/edit_request_ads",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editRequestAds(RequestAds requestAds){
		CodeMsg validate = ValidateEntityUtil.validate(requestAds);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(requestAds.getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		RequestAds existRequestAds = requestAdsService.find(requestAds.getId());
		if(existRequestAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(existRequestAds.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		existRequestAds.setContent(requestAds.getContent());
		existRequestAds.setName(requestAds.getName());
		existRequestAds.setSellPrice(requestAds.getSellPrice());
		existRequestAds.setTradePlace(requestAds.getTradePlace());
		if(requestAdsService.save(existRequestAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		
		return Result.success(true);
	}
	
	/**
	 * Request ads deleting
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_request",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteRequest(@RequestParam(name="id",required=true)Long id){
		RequestAds requestAds = requestAdsService.find(id);
		if(requestAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(requestAds.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		requestAdsService.delete(id);
		return Result.success(true);
	}
	
	/**
	 * Report ads
	 * @param reportAds
	 * @return
	 */
	@RequestMapping(value="/report_ads",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> reportAds(ReportAds reportAds){
		CodeMsg validate = ValidateEntityUtil.validate(reportAds);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(reportAds.getAds() == null || reportAds.getAds().getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		ReportAds find = reportAdsService.find(reportAds.getAds().getId(), loginedStudent.getId());
		if(find != null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_ADS);
		}
		reportAds.setStudent(loginedStudent);
		if(reportAdsService.save(reportAds) == null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORT_ADS_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Delete report
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_report",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteReport(@RequestParam(name="id",required=true)Long id){
		ReportAds reportAds = reportAdsService.find(id);
		if(reportAds == null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(reportAds.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
		}
		reportAdsService.delete(id);
		return Result.success(true);
	}
	
	/**
	 * Retrieve personal ads status
	 * @return
	 */
	@RequestMapping(value="/get_stats",method=RequestMethod.POST)
	@ResponseBody
	public Result<Map<String, Integer>> getStats(){
		Map<String, Integer> ret = new HashMap<String, Integer>();
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		List<Ads> findByStudent = adsService.findByStudent(loginedStudent);
		Integer adsTotal = findByStudent.size();//Total ads has been made
		Integer soldAdsTotal = 0;
		Integer downAdsTotal = 0;
		Integer upAdsTotal = 0;
		for(Ads ads : findByStudent){
			if(ads.getStatus() == Ads.ADS_STATUS_SOLD){
				soldAdsTotal++;
			}
			if(ads.getStatus() == Ads.ADS_STATUS_DOWN){
				downAdsTotal++;
			}
			if(ads.getStatus() == Ads.ADS_STATUS_UP){
				upAdsTotal++;
			}
		}
		ret.put("adsTotal", adsTotal);
		ret.put("soldAdsTotal", soldAdsTotal);
		ret.put("downAdsTotal", downAdsTotal);
		ret.put("upAdsTotal", upAdsTotal);
		return Result.success(ret);
	}
	
	/**
	 * Ads commenting
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> comment(Comment comment){
		CodeMsg validate = ValidateEntityUtil.validate(comment);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(comment.getAds() == null || comment.getAds().getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Ads find = adsService.findById(comment.getAds().getId());
		if(find == null){
			return Result.error(CodeMsg.HOME_STUDENT_ADS_NO_EXIST);
		}
		comment.setStudent(loginedStudent);
		if(commentService.save(comment) == null){
			return Result.error(CodeMsg.HOME_STUDENT_COMMENT_ADD_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Password changing section
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value="/edit_pwd",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editPwd(@RequestParam(name="oldPwd",required=true)String oldPwd,
			@RequestParam(name="newPwd",required=true)String newPwd){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(!loginedStudent.getPassword().equals(oldPwd)){
			return Result.error(CodeMsg.HOME_STUDENT_EDITPWD_OLD_ERROR);
		}
		loginedStudent.setPassword(newPwd);
		if(studentService.save(loginedStudent) == null){
			return Result.error(CodeMsg.HOME_STUDENT_EDITINFO_ERROR);
		}
		SessionUtil.set(SessionConstant.SESSION_STUDENT_LOGIN_KEY, loginedStudent);
		return Result.success(true);
	}
}
