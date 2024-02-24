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
import com.fyp.mumarket.dao.common.ReportGoodsDao;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.ReportGoods;
import com.fyp.mumarket.entity.common.Student;

@Service
public class ReportGoodsService {

	@Autowired
	private ReportGoodsDao reportGoodsDao;
	
	/**
	 * Add/edit a report; when id is not null, proceed edit
	 * @param goods
	 * @return
	 */
	public ReportGoods save(ReportGoods reportGoods){
		return reportGoodsDao.save(reportGoods);
	}
	
	
	
	
	/**
	 * Delete a report
	 * @param id
	 */
	public void delete(Long id){
		reportGoodsDao.deleteById(id);
	}
	
	
	
	/**
	 * Find by student object
	 * @param student
	 * @return
	 */
	public List<ReportGoods> findByStudent(Student student){
		return reportGoodsDao.findByStudent(student);
	}
	
	/**
	 * Find by student id and item id
	 * @param id
	 * @param studentId
	 * @return
	 */
	public ReportGoods find(Long goodsId,Long studentId){
		return reportGoodsDao.find(goodsId, studentId);
	}
	
	/**
	 * Find by report id
	 * @param id
	 * @return
	 */
	public ReportGoods find(Long id){
		return reportGoodsDao.find(id);
	}
	
	/**
	 * Report searching
	 * @param pageBean
	 * @param reportGoods
	 * @param goodsList
	 * @return
	 */
	public PageBean<ReportGoods> findlist(PageBean<ReportGoods> pageBean,ReportGoods reportGoods,List<Goods> goodsList){
		
		Specification<ReportGoods> specification = new Specification<ReportGoods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ReportGoods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("content"), "%" + (reportGoods.getContent() == null ? "" : reportGoods.getContent()) + "%");
				if(reportGoods.getStudent() != null && reportGoods.getStudent().getId() != null){
					Predicate eqal1 = criteriaBuilder.equal(root.get("student"), reportGoods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,eqal1);
				}
				if(goodsList != null && goodsList.size() >0 ){
					In<Object> in = criteriaBuilder.in(root.get("goods"));
					in.value(goodsList);
					predicate = criteriaBuilder.and(predicate,in);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<ReportGoods> findAll = reportGoodsDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
}
