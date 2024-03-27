package com.fyp.mutrade.dao.admin;
/**
 * Backend operation log database operation layer.
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.admin.OperaterLog;

@Repository
public interface OperaterLogDao extends JpaRepository<OperaterLog, Long> {
	
	/**
	 * Retrieve operation log based on ID
	 * @param id
	 * @return
	 */
	@Query("select ol from OperaterLog ol where id = :id")
	OperaterLog find(@Param("id")Long id);
	
	/**
	 * Retrieve the most recent specified number of operation logs
	 * @param size
	 * @return
	 */
	@Query(value="select * from fyp_operater_log order by create_time desc limit 0,:size",nativeQuery=true)
	List<OperaterLog> findLastestLog(@Param("size")int size);
}
