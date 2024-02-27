package com.fyp.mumarket.service.common;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.common.WantedGoodsDao;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.entity.common.WantedGoods;

@Service
public class WantedGoodsService {

	@Autowired
	private WantedGoodsDao wantedGoodsDao;
	
	/**
	 * Wanted goods modification/editing, when the ID is passed, it is editing; if the ID is empty, it is adding
	 * @param wantedGoods
	 * @return
	 */
	public WantedGoods save(WantedGoods wantedGoods){
		return wantedGoodsDao.save(wantedGoods);
	}
	
	/**
	 *  Find by student object
	 * @param student
	 * @return
	 */
	public List<WantedGoods> findByStudent(Student student){
		return wantedGoodsDao.findByStudent(student);
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public WantedGoods find(Long id){
		return wantedGoodsDao.find(id);
	}
	
	/**
	 * Delete by id
	 * @param id
	 */
	public void delete(Long id){
		wantedGoodsDao.deleteById(id);
	}
	
	/**
	 * Display wanted goods list in pages
	 * @param pageBean
	 * @param WantedGoods
	 * @return
	 */
	public PageBean<WantedGoods> findlist(PageBean<WantedGoods> pageBean,WantedGoods WantedGoods){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("viewNumber");
		Example<WantedGoods> example = Example.of(WantedGoods, exampleMatcher);
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<WantedGoods> findAll = wantedGoodsDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	public PageBean<WantedGoods> findWantedGoodslist(PageBean<WantedGoods> pageBean,WantedGoods wantedGoods){
		
		Specification<WantedGoods> specification = new Specification<WantedGoods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<WantedGoods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (wantedGoods.getName() == null ? "" : wantedGoods.getName()) + "%");
				if(wantedGoods.getStudent() != null && wantedGoods.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), wantedGoods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<WantedGoods> findAll = wantedGoodsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Return count for wanted goods
	 * @return
	 */
	public long total(){
		return wantedGoodsDao.count();
	}
}
