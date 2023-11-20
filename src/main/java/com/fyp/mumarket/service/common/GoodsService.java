package com.fyp.mumarket.service.common;
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

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.common.GoodsDao;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.Student;
/**
 * Item management service
 */
@Service
public class GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	/**
	 * Item addition/editing, when the ID is not empty, then it is an edit
	 * @param goods
	 * @return
	 */
	public Goods save(Goods goods){
		return goodsDao.save(goods);
	}
	
	
	/**
	 * Search category list
	 * @param pageBean
	 * @param goods
	 * @return
	 */
	public PageBean<Goods> findlist(PageBean<Goods> pageBean,Goods goods){
		
		Specification<Goods> specification = new Specification<Goods>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Goods> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + (goods.getName() == null ? "" : goods.getName()) + "%");
				if(goods.getStudent() != null && goods.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), goods.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
				}
				if(goods.getStatus() != -1){
					Predicate equal2 = criteriaBuilder.equal(root.get("status"), goods.getStatus());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				if(goods.getGoodsCategory() != null && goods.getGoodsCategory().getId() != null){
					Predicate equal2 = criteriaBuilder.equal(root.get("goodsCategory"), goods.getGoodsCategory().getId());
					predicate = criteriaBuilder.and(predicate,equal2);
				}
				return predicate;
			}
		};
		Sort sort = Sort.by(Direction.DESC, "createTime","recommend","flag","viewNumber");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<Goods> findAll = goodsDao.findAll(specification, pageable);
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
	public Goods findById(Long id){
		return goodsDao.find(id);
	}
	
	/**
	 * Delete by id
	 * @param id
	 */
	public void delete(Long id){
		goodsDao.deleteById(id);
	}
	
	/**
	 * Retrieve all item
	 * @return
	 */
	public List<Goods> findAll(){
		return goodsDao.findAll();
	}
	
	/**
	 * Find item by student object
	 * @param student
	 * @return
	 */
	public List<Goods> findByStudent(Student student){
		return goodsDao.findByStudent(student);
	}
	
	/**
	 * Search by student id and item id
	 * @param id
	 * @param studentId
	 * @return
	 */
	public Goods find(Long id,Long studentId){
		return goodsDao.find(id, studentId);
	}
	
	/**
	 * Retrieve list by category
	 * @param cids
	 * @param pageBean
	 * @return
	 */
	public PageBean<Goods> findlist(List<Long> cids,PageBean<Goods> pageBean){
		List<Goods> findList = goodsDao.findList(cids,pageBean.getOffset(), pageBean.getPageSize());
		pageBean.setContent(findList);
		pageBean.setTotal(goodsDao.getTotalCount(cids));
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
		return goodsDao.getTotalCount(status);
	}
	
	/**
	 * Retrieve total count of sold item
	 * @return
	 */
	public Long getSoldTotalCount(){
		return getTotalCount(Goods.GOODS_STATUS_SOLD);
	}
	
	/**
	 * Find item by name
	 * @param name
	 * @return
	 */
	public List<Goods> findListByName(String name){
		return goodsDao.findListByName(name);
	}
	
	/**
	 * Retrieve total item count
	 * @return
	 */
	public long total(){
		return goodsDao.count();
	}
}
