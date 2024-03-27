package com.fyp.mutrade.service.common;
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

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.common.CommentDao;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.Comment;
import com.fyp.mutrade.entity.common.Student;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	/**
	 * Save comment
	 * @param ads
	 * @return
	 */
	public Comment save(Comment comment){
		return commentDao.save(comment);
	}
	
	
	
	
	/**
	 * Delete comment by id
	 * @param id
	 */
	public void delete(Long id){
		commentDao.deleteById(id);
	}
	
	
	
	/**
	 * search comment by student id
	 * @param student
	 * @return
	 */
	public List<Comment> findByStudent(Student student){
		return commentDao.findByStudent(student);
	}
	
	/**
	 * Search comment by item
	 * @param ads
	 * @return
	 */
	public List<Comment> findByAds(Ads ads){
		return commentDao.findByAds(ads);
	}
	
	/**
	 * Search by student id and item id
	 * @param id
	 * @param studentId
	 * @return
	 */
	public Comment find(Long adsId,Long studentId){
		return commentDao.find(adsId, studentId);
	}
	
	/**
	 * Search by id
	 * @param id
	 * @return
	 */
	public Comment find(Long id){
		return commentDao.find(id);
	}
	
	/**
	 * Search comment with filter
	 * @param pageBean
	 * @param comment
	 * @param adsList
	 * @return
	 */
	public PageBean<Comment> findlist(PageBean<Comment> pageBean,Comment comment,List<Ads> adsList){
		
		Specification<Comment> specification = new Specification<Comment>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Comment> root,
					CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.like(root.get("content"), "%" + (comment.getContent() == null ? "" : comment.getContent()) + "%");
				if(comment.getStudent() != null && comment.getStudent().getId() != null){
					Predicate equal1 = criteriaBuilder.equal(root.get("student"), comment.getStudent().getId());
					predicate = criteriaBuilder.and(predicate,equal1);
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
		Page<Comment> findAll = commentDao.findAll(specification, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Retrieve total comment count
	 * @return
	 */
	public long total(){
		return commentDao.count();
	}
}
