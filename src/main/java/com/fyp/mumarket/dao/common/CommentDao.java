package com.fyp.mumarket.dao.common;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.Comment;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.Student;
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
	 * @param goods
	 * @return
	 */
	List<Comment> findByGoods(Goods goods);
	
	/**
	 * Search by student id and item id
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select c from Comment c where c.goods.id = :goodsId and c.student.id = :studentId")
	Comment find(@Param("goodsId")Long goodsId,@Param("studentId")Long studentId);
	
	
}
