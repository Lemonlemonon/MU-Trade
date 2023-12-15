package com.fyp.mumarket.dao.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.admin.DatabaseBak;
/**
 * Retrieve user information by username
 * @author Administrator
 *
 */
@Repository
public interface DatabaseBakDao extends JpaRepository<DatabaseBak, Long>{

	/**
	 * Retrieve information by id
	 * @param id
	 * @return
	 */
	@Query("select db from DatabaseBak db where id = :id")
	DatabaseBak find(@Param("id")Long id);
	
	
}
