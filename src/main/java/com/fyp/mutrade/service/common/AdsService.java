package com.fyp.mutrade.service.common;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.common.AdsDao;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.Student;
/**
 * Item management service
 */
@Service
public class AdsService {

	@Autowired
	private AdsDao adsDao;
	
	/**
	 * Item addition/editing, when the ID is not empty, then it is an edit
	 * @param ads
	 * @return
	 */
	public Ads save(Ads ads){
		return adsDao.save(ads);
	}
	
	
	/**
	 * Search category list
	 * @param pageBean
	 * @param ads
	 * @return
	 */
	public PageBean<Ads> findlist(PageBean<Ads> pageBean,Ads ads){
		
		Specification<Ads> specification = new Specification<Ads>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Ads> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (ads.getName() == null ? "" : ads.getName()) + "%");
				if(ads.getStudent() != null && ads.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), ads.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				if(ads.getStatus() != -1){
					Predicate equal2 = criteriaBuilder.equal(root.get("status"), ads.getStatus());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				if(ads.getAdsCategory() != null && ads.getAdsCategory().getId() != null){
					Predicate equal2 = criteriaBuilder.equal(root.get("adsCategory"), ads.getAdsCategory().getId());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime","recommend","flag","viewNumber");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Ads> findAll = adsDao.findAll(specification, pageable);
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
	public Ads findById(Long id){
		return adsDao.find(id);
	}
	
	/**
	 * Delete by id
	 * @param id
	 */
	public void delete(Long id){
		adsDao.deleteById(id);
	}
	
	/**
	 * Retrieve all item
	 * @return
	 */
	public List<Ads> findAll(){
		return adsDao.findAll();
	}
	
	/**
	 * Find item by student object
	 * @param student
	 * @return
	 */
	public List<Ads> findByStudent(Student student){
		return adsDao.findByStudent(student);
	}
	
	/**
	 * Search by student id and item id
	 * @param id
	 * @param studentId
	 * @return
	 */
	public Ads find(Long id,Long studentId){
		return adsDao.find(id, studentId);
	}
	
	/**
	 * Retrieve list by category
	 * @param cids
	 * @param pageBean
	 * @return
	 */
	public PageBean<Ads> findlist(List<Long> cids,PageBean<Ads> pageBean){
		List<Ads> findList = adsDao.findList(cids,pageBean.getOffset(), pageBean.getPageSize());
		pageBean.setContent(findList);
		pageBean.setTotal(adsDao.getTotalCount(cids));
		pageBean.setTotalPage(Integer.valueOf(pageBean.getTotal() / pageBean.getPageSize()+""));
		long totalPage = pageBean.getTotal() % pageBean.getPageSize();
		if(totalPage != 0){
			pageBean.setTotalPage(pageBean.getTotalPage() + 1);
		}
		return pageBean;
	}
	
	/**
	 * Retrieve the total count of items with a specified status
	 * @param status
	 * @return
	 */
	public Long getTotalCount(Integer status){
		return adsDao.getTotalCount(status);
	}
	
	/**
	 * Retrieve total count of sold item
	 * @return
	 */
	public Long getSoldTotalCount(){
		return getTotalCount(Ads.ADS_STATUS_SOLD);
	}
	
	/**
	 * Find item by name
	 * @param name
	 * @return
	 */
	public List<Ads> findListByName(String name){
		return adsDao.findListByName(name);
	}
	
	/**
	 * Retrieve total item count
	 * @return
	 */
	public long total(){
		return adsDao.count();
	}
}
