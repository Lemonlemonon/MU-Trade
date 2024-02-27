package com.fyp.mumarket.dao.common;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.entity.common.WantedGoods;
/**
 * Wanted Goods DAO
 * @author Administrator
 *
 */
@Repository
public interface WantedGoodsDao extends JpaRepository<WantedGoods, Long>,JpaSpecificationExecutor<WantedGoods>{
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	@Query("select wg from WantedGoods wg where id = :id")
	WantedGoods find(@Param("id")Long id);
	
	/**
	 * Find by a student object
	 * @param student
	 * @return
	 */
	public List<WantedGoods> findByStudent(Student student);
}
