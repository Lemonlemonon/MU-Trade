package com.fyp.mutrade.dao.common;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.Comment;
import com.fyp.mutrade.entity.common.Student;
@Repository
public interface CommentDao extends JpaRepository<Comment, Long>,JpaSpecificationExecutor<Comment> {
	/**
	 * search by id
	 * @return
	 */
	@Query("select c from Comment c where id = :id")
	Comment find(@Param("id")Long id);
	
	/**
	 * search by student
	 * @param student
	 * @return
	 */
	List<Comment> findByStudent(Student student);
	
	/**
	 * search by item
	 * @param ads
	 * @return
	 */
	List<Comment> findByAds(Ads ads);
	
	/**
	 * Search by student id and item id
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select c from Comment c where c.ads.id = :adsId and c.student.id = :studentId")
	Comment find(@Param("adsId")Long adsId,@Param("studentId")Long studentId);
	
	
}
