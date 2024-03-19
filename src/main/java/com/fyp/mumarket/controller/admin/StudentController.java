package com.fyp.mumarket.controller.admin;

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
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.service.common.StudentService;

/**
 * Student management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/student")
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/**
	 * Student management page
	 * @param name
	 * @param pageBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Student student,PageBean<Student> pageBean,Model model){
		model.addAttribute("title", "Student list");
		model.addAttribute("sn", student.getSn());
		model.addAttribute("pageBean", studentService.findlist(pageBean, student));
		return "admin/student/list";
	}
	
	

	/**
	 * Edit student account status
	 * @param id,status
	 * @return
	 */
	@RequestMapping(value="/update_status",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> upDown(@RequestParam(name="id",required=true)Long id ,@RequestParam(name="status",required=true)Integer status){
		Student student = studentService.findById(id);
		if(student == null){
			return Result.error(CodeMsg.ADMIN_ADS_NO_EXIST);
		}
		if(student.getStatus() == status){
			return Result.error(CodeMsg.ADMIN_STUDENT_STATUS_NO_CHANGE);
		}
		if(status != Student.STUDENT_STATUS_ENABLE && status != Student.STUDENT_STATUS_UNABLE){
			return Result.error(CodeMsg.ADMIN_STUDENT_STATUS_ERROR);
		}
		student.setStatus(status);
		//Update database
		if(studentService.save(student) ==null){
			return Result.error(CodeMsg.ADMIN_STUDENT_EDIT_ERROR);
		}
		return Result.success(true);
	}
	
	
	/**
	 * Delete student account
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id){
		try {
			studentService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_STUDENT_DELETE_ERROR);
		}
		return Result.success(true);
	}
}
