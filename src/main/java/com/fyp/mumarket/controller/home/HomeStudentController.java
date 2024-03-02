package com.fyp.mumarket.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.constant.SessionConstant;
import com.fyp.mumarket.entity.common.Comment;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.ReportGoods;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.entity.common.WantedGoods;
import com.fyp.mumarket.service.common.CommentService;
import com.fyp.mumarket.service.common.GoodsCategoryService;
import com.fyp.mumarket.service.common.GoodsService;
import com.fyp.mumarket.service.common.ReportGoodsService;
import com.fyp.mumarket.service.common.StudentService;
import com.fyp.mumarket.service.common.WantedGoodsService;
import com.fyp.mumarket.util.SessionUtil;
import com.fyp.mumarket.util.ValidateEntityUtil;

/**
 * Student Center Controller
 * @author Administrator
 *
 */
@RequestMapping("/home/student")
@Controller
public class HomeStudentController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private WantedGoodsService wantedGoodsService;
	@Autowired
	private ReportGoodsService reportGoodsService;
	@Autowired
	private CommentService commentService;
	/**
	 * Student center page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		model.addAttribute("goodsList", goodsService.findByStudent(loginedStudent));
		model.addAttribute("wantedGoodsList", wantedGoodsService.findByStudent(loginedStudent));
		model.addAttribute("reportGoodsList", reportGoodsService.findByStudent(loginedStudent));
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
	 * Goods publish page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/publish",method=RequestMethod.GET)
	public String publish(Model model){
		return "home/student/publish";
	}
	
	/**
	 * Goods publish form submission
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/publish",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> publish(Goods goods){
		CodeMsg validate = ValidateEntityUtil.validate(goods);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goods.getGoodsCategory() == null || goods.getGoodsCategory().getId() == null || goods.getGoodsCategory().getId().longValue() == -1){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_CATEGORY_EMPTY);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		goods.setStudent(loginedStudent);
		if(goodsService.save(goods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Goods editing page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_goods",method=RequestMethod.GET)
	public String publish(@RequestParam(name="id",required=true)Long id,Model model){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Goods goods = goodsService.find(id, loginedStudent.getId());
		if(goods == null){
			model.addAttribute("msg", "Item does not exsist!");
			return "error/runtime_error";
		}
		model.addAttribute("goods", goods);
		return "home/student/edit_goods";
	}
	
	/**
	 * Goods editing form submission
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/edit_goods",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editGoods(Goods goods){
		CodeMsg validate = ValidateEntityUtil.validate(goods);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(goods.getGoodsCategory() == null || goods.getGoodsCategory().getId() == null || goods.getGoodsCategory().getId().longValue() == -1){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_CATEGORY_EMPTY);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Goods existGoods = goodsService.find(goods.getId(), loginedStudent.getId());
		if(existGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		existGoods.setBuyPrice(goods.getBuyPrice());
		existGoods.setContent(goods.getContent());
		existGoods.setGoodsCategory(goods.getGoodsCategory());
		existGoods.setName(goods.getName());
		existGoods.setPhoto(goods.getPhoto());
		existGoods.setSellPrice(goods.getSellPrice());
		if(goodsService.save(existGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Setting for enable/disable goods flag
	 * @param id
	 * @param flag
	 * @return
	 */
	@RequestMapping(value="/update_flag",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updateFlag(@RequestParam(name="id",required=true)Long id,
			@RequestParam(name="flag",required=true,defaultValue="0")Integer flag){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Goods existGoods = goodsService.find(id, loginedStudent.getId());
		if(existGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		existGoods.setFlag(flag);
		if(goodsService.save(existGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Edit goods status
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/update_status",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> updateStatus(@RequestParam(name="id",required=true)Long id,
			@RequestParam(name="status",required=true,defaultValue="2")Integer status){
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Goods existGoods = goodsService.find(id, loginedStudent.getId());
		if(existGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		existGoods.setStatus(status);
		if(goodsService.save(existGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Wanted goods publishing page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/publish_wanted",method=RequestMethod.GET)
	public String publishWanted(Model model){
		return "home/student/publish_wanted";
	}
	
	/**
	 * Wanted goods publish form submission
	 * @param wantedGoods
	 * @return
	 */
	@RequestMapping(value="/publish_wanted",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> publishWanted(WantedGoods wantedGoods){
		CodeMsg validate = ValidateEntityUtil.validate(wantedGoods);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		wantedGoods.setStudent(loginedStudent);
		if(wantedGoodsService.save(wantedGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		return Result.success(true);
	}
	
	/**
	 * Wanted goods Editing page
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_wanted_goods",method=RequestMethod.GET)
	public String editWantedGoods(@RequestParam(name="id",required=true)Long id,Model model){
		WantedGoods wantedGoods = wantedGoodsService.find(id);
		if(wantedGoods == null){
			model.addAttribute("msg", "Item does not exsist!");
			return "error/runtime_error";
		}
		model.addAttribute("wantedGoods", wantedGoods);
		return "home/student/edit_wanted";
	}
	
	/**
	 * Wanted goods Editing form submission
	 * @param wantedGoods
	 * @return
	 */
	@RequestMapping(value="/edit_wanted_goods",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> editWantedGoods(WantedGoods wantedGoods){
		CodeMsg validate = ValidateEntityUtil.validate(wantedGoods);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(wantedGoods.getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		WantedGoods existWantedGoods = wantedGoodsService.find(wantedGoods.getId());
		if(existWantedGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(existWantedGoods.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		existWantedGoods.setContent(wantedGoods.getContent());
		existWantedGoods.setName(wantedGoods.getName());
		existWantedGoods.setSellPrice(wantedGoods.getSellPrice());
		existWantedGoods.setTradePlace(wantedGoods.getTradePlace());
		if(wantedGoodsService.save(existWantedGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_PUBLISH_ERROR);
		}
		
		return Result.success(true);
	}
	
	/**
	 * Wanted goods deleting
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete_wanted",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> deleteWanted(@RequestParam(name="id",required=true)Long id){
		WantedGoods wantedGoods = wantedGoodsService.find(id);
		if(wantedGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(wantedGoods.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		wantedGoodsService.delete(id);
		return Result.success(true);
	}
	
	/**
	 * Report goods
	 * @param reportGoods
	 * @return
	 */
	@RequestMapping(value="/report_goods",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> reportGoods(ReportGoods reportGoods){
		CodeMsg validate = ValidateEntityUtil.validate(reportGoods);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
			return Result.error(validate);
		}
		if(reportGoods.getGoods() == null || reportGoods.getGoods().getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		ReportGoods find = reportGoodsService.find(reportGoods.getGoods().getId(), loginedStudent.getId());
		if(find != null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_GOODS);
		}
		reportGoods.setStudent(loginedStudent);
		if(reportGoodsService.save(reportGoods) == null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORT_GOODS_ERROR);
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
		ReportGoods reportGoods = reportGoodsService.find(id);
		if(reportGoods == null){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(reportGoods.getStudent().getId().longValue() != loginedStudent.getId().longValue()){
			return Result.error(CodeMsg.HOME_STUDENT_REPORTED_NO_EXIST);
		}
		reportGoodsService.delete(id);
		return Result.success(true);
	}
	
	/**
	 * Retrieve personal goods status
	 * @return
	 */
	@RequestMapping(value="/get_stats",method=RequestMethod.POST)
	@ResponseBody
	public Result<Map<String, Integer>> getStats(){
		Map<String, Integer> ret = new HashMap<String, Integer>();
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		List<Goods> findByStudent = goodsService.findByStudent(loginedStudent);
		Integer goodsTotal = findByStudent.size();//Total goods has been made
		Integer soldGoodsTotal = 0;
		Integer downGoodsTotal = 0;
		Integer upGoodsTotal = 0;
		for(Goods goods : findByStudent){
			if(goods.getStatus() == Goods.GOODS_STATUS_SOLD){
				soldGoodsTotal++;
			}
			if(goods.getStatus() == Goods.GOODS_STATUS_DOWN){
				downGoodsTotal++;
			}
			if(goods.getStatus() == Goods.GOODS_STATUS_UP){
				upGoodsTotal++;
			}
		}
		ret.put("goodsTotal", goodsTotal);
		ret.put("soldGoodsTotal", soldGoodsTotal);
		ret.put("downGoodsTotal", downGoodsTotal);
		ret.put("upGoodsTotal", upGoodsTotal);
		return Result.success(ret);
	}
	
	/**
	 * Goods commenting
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
		if(comment.getGoods() == null || comment.getGoods().getId() == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
		}
		Student loginedStudent = (Student)SessionUtil.get(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		Goods find = goodsService.findById(comment.getGoods().getId());
		if(find == null){
			return Result.error(CodeMsg.HOME_STUDENT_GOODS_NO_EXIST);
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
