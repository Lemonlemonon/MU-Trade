package com.fyp.mumarket.dao.common;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.ReportGoods;
import com.fyp.mumarket.entity.common.Student;
@Repository
public interface ReportGoodsDao extends JpaRepository<ReportGoods, Long>,JpaSpecificationExecutor<ReportGoods> {
	/**
	 * Find a report by id
	 * @return
	 */
	@Query("select rg from ReportGoods rg where id = :id")
	ReportGoods find(@Param("id")Long id);
	
	/**
	 * Find a report by a student obj
	 * @param student
	 * @return
	 */
	List<ReportGoods> findByStudent(Student student);
	
	/**
	 * Find a report by a item obj
	 * @param goods
	 * @return
	 */
	List<ReportGoods> findByGoods(Goods goods);
	
	/**
	 * Find by student id and item id
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select rg from ReportGoods rg where rg.goods.id = :goodsId and rg.student.id = :studentId")
	ReportGoods find(@Param("goodsId")Long goodsId,@Param("studentId")Long studentId);
	
	
}
