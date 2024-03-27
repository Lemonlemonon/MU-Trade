package com.fyp.mutrade.service.common;
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

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.common.RequestAdsDao;
import com.fyp.mutrade.entity.common.RequestAds;
import com.fyp.mutrade.entity.common.Student;

@Service
public class RequestAdsService {

	@Autowired
	private RequestAdsDao requestAdsDao;
	
	/**
	 * Request ads modification/editing, when the ID is passed, it is editing; if the ID is empty, it is adding
	 * @param requestAds
	 * @return
	 */
	public RequestAds save(RequestAds requestAds){
		return requestAdsDao.save(requestAds);
	}
	
	/**
	 *  Find by student object
	 * @param student
	 * @return
	 */
	public List<RequestAds> findByStudent(Student student){
		return requestAdsDao.findByStudent(student);
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	public RequestAds find(Long id){
		return requestAdsDao.find(id);
	}
	
	/**
	 * Delete by id
	 * @param id
	 */
	public void delete(Long id){
		requestAdsDao.deleteById(id);
	}
	
	/**
	 * Display request ads list in pages
	 * @param pageBean
	 * @param RequestAds
	 * @return
	 */
	public PageBean<RequestAds> findlist(PageBean<RequestAds> pageBean,RequestAds RequestAds){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("viewNumber");
		Example<RequestAds> example = Example.of(RequestAds, exampleMatcher);
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<RequestAds> findAll = requestAdsDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	public PageBean<RequestAds> findRequestAdslist(PageBean<RequestAds> pageBean,RequestAds requestAds){
		
		Specification<RequestAds> specification = new Specification<RequestAds>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RequestAds> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (requestAds.getName() == null ? "" : requestAds.getName()) + "%");
				if(requestAds.getStudent() != null && requestAds.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), requestAds.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<RequestAds> findAll = requestAdsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Return count for request ads
	 * @return
	 */
	public long total(){
		return requestAdsDao.count();
	}
}
