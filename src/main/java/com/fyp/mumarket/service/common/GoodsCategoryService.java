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
import com.fyp.mumarket.dao.common.GoodsCategoryDao;
import com.fyp.mumarket.entity.common.GoodsCategory;

/**
 * Item category management service
 */
@Service
public class GoodsCategoryService {

	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	
	/**
	 * Item category addition/editing, when the ID is not empty, then it is an edit
	 * @param goodsCategory
	 * @return
	 */
	public GoodsCategory save(GoodsCategory goodsCategory){
		return goodsCategoryDao.save(goodsCategory);
	}
	
	/**
	 * Retrieve all parent categories
	 * @return
	 */
	public List<GoodsCategory> findTopCategorys(){
		return goodsCategoryDao.findByParentIsNull();
	}
	
	/**
	 * Retrieve all child categories
	 * @return
	 */
	public List<GoodsCategory> findSecondCategorys(){
		return goodsCategoryDao.findByParentIsNotNull();
	}
	
	/**
	 * Search category list
	 * @param pageBean
	 * @param goodsCategory
	 * @return
	 */
	public PageBean<GoodsCategory> findlist(PageBean<GoodsCategory> pageBean,GoodsCategory goodsCategory){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort");
		Example<GoodsCategory> example = Example.of(goodsCategory, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<GoodsCategory> findAll = goodsCategoryDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public GoodsCategory findById(Long id){
		return goodsCategoryDao.find(id);
	}
	
	/**
	 * Delete category by id
	 * @param id
	 */
	public void delete(Long id){
		goodsCategoryDao.deleteById(id);
	}
	
	/**
	 * Retrieve all category
	 * @return
	 */
	public List<GoodsCategory> findAll(){
		return goodsCategoryDao.findAll();
	}
	
	/**
	 * Retrieve all sub-categories under a top-level category
	 * @param parent
	 * @return
	 */
	public List<GoodsCategory> findChildren(GoodsCategory parent){
		return goodsCategoryDao.findByParent(parent);
	}
	
	/**
	 * Search by category name
	 * @param name
	 * @return
	 */
	public List<GoodsCategory> findByName(String name){
		return goodsCategoryDao.findByName(name);
	}
}
