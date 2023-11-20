package com.fyp.mumarket.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.common.StudentDao;
import com.fyp.mumarket.entity.common.Student;
/**
 * Student management service
 */
@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	/**
	 * Find by student number
	 * @param sn
	 * @return
	 */
	public Student findBySn(String sn){
		return studentDao.findBySn(sn);
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public Student findById(Long id){
		return studentDao.find(id);
	}
	
	/**
	 * "Student modification/editing, when the ID is passed, it is editing; if the ID is empty, it is adding
	 * @param student
	 * @return
	 */
	public Student save(Student student){
		return studentDao.save(student);
	}
	
	/**
	 * Retrieve student list
	 * @param pageBean
	 * @param student
	 * @return
	 */
	public PageBean<Student> findlist(PageBean<Student> pageBean,Student student){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("sn", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("status");
		Example<Student> example = Example.of(student, exampleMatcher);
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Student> findAll = studentDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Delete by id
	 * @param id
	 */
	public void delete(Long id){
		studentDao.deleteById(id);
	}
	
	/**
	 * Retrieve total count of student
	 * @return
	 */
	public long total(){
		return studentDao.count();
	}
}
