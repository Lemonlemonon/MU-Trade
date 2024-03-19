package com.fyp.mumarket.dao.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.admin.DbBackup;
/**
 * Retrieve user information by username
 * @author Administrator
 *
 */
@Repository
public interface DbBackupDao extends JpaRepository<DbBackup, Long>{

	/**
	 * Retrieve information by id
	 * @param id
	 * @return
	 */
	@Query("select db from DbBackup db where id = :id")
	DbBackup find(@Param("id")Long id);
	
	
}
