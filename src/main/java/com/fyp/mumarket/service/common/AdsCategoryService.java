package com.fyp.mumarket.service.common;
import java.util.List;

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
import com.fyp.mumarket.dao.common.AdsCategoryDao;
import com.fyp.mumarket.entity.common.AdsCategory;

/**
 * Item category management service
 */
@Service
public class AdsCategoryService {

	@Autowired
	private AdsCategoryDao adsCategoryDao;

	//Item category addition/editing, when the ID is not empty, then it is an edit
	public AdsCategory save(AdsCategory adsCategory){
		return adsCategoryDao.save(adsCategory);
	}
	
	//Retrieve all parent categories
	public List<AdsCategory> findTopCategorys(){
		return adsCategoryDao.findByParentIsNull();
	}
	
	//Retrieve all child categories
	public List<AdsCategory> findSecondCategorys(){
		return adsCategoryDao.findByParentIsNotNull();
	}
	
	//Search category list
	public PageBean<AdsCategory> findlist(PageBean<AdsCategory> pageBean,AdsCategory adsCategory){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort");
		Example<AdsCategory> example = Example.of(adsCategory, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<AdsCategory> findAll = adsCategoryDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	//Find by id
	public AdsCategory findById(Long id){
		return adsCategoryDao.find(id);
	}
	
	//Delete category by id
	public void delete(Long id){
		adsCategoryDao.deleteById(id);
	}
	
	//Retrieve all category
	public List<AdsCategory> findAll(){
		return adsCategoryDao.findAll();
	}
	
	//Retrieve all sub-categories under a top-level category
	public List<AdsCategory> findChildren(AdsCategory parent){
		return adsCategoryDao.findByParent(parent);
	}
	
	//Search by category name
	public List<AdsCategory> findByName(String name){
		return adsCategoryDao.findByName(name);
	}
}
