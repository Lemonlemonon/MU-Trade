package com.fyp.mumarket.service.common;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
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
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.common.ReportAdsDao;
import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.ReportAds;
import com.fyp.mumarket.entity.common.Student;

@Service
public class ReportAdsService {

	@Autowired
	private ReportAdsDao reportAdsDao;
	
	/**
	 * Add/edit a report; when id is not null, proceed edit
	 * @param ads
	 * @return
	 */
	public ReportAds save(ReportAds reportAds){
		return reportAdsDao.save(reportAds);
	}
	
	
	
	
	/**
	 * Delete a report
	 * @param id
	 */
	public void delete(Long id){
		reportAdsDao.deleteById(id);
	}
	
	
	
	/**
	 * Find by student object
	 * @param student
	 * @return
	 */
	public List<ReportAds> findByStudent(Student student){
		return reportAdsDao.findByStudent(student);
	}
	
	/**
	 * Find by student id and item id
	 * @param id
	 * @param studentId
	 * @return
	 */
	public ReportAds find(Long adsId,Long studentId){
		return reportAdsDao.find(adsId, studentId);
	}
	
	/**
	 * Find by report id
	 * @param id
	 * @return
	 */
	public ReportAds find(Long id){
		return reportAdsDao.find(id);
	}
	
	/**
	 * Report searching
	 * @param pageBean
	 * @param reportAds
	 * @param adsList
	 * @return
	 */
	public PageBean<ReportAds> findlist(PageBean<ReportAds> pageBean,ReportAds reportAds,List<Ads> adsList){
		
		Specification<ReportAds> specification = new Specification<ReportAds>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ReportAds> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("content"), "%" + (reportAds.getContent() == null ? "" : reportAds.getContent()) + "%");
				if(reportAds.getStudent() != null && reportAds.getStudent().getId() != null){
					Predicate eqal1 = criteriaBuilder.equal(root.get("student"), reportAds.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,eqal1);
				}
				if(adsList != null && adsList.size() >0 ){
					In<Object> in = criteriaBuilder.in(root.get("ads"));
					in.value(adsList);
					predicate = criteriaBuilder.and(predicate,in);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<ReportAds> findAll = reportAdsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
}
