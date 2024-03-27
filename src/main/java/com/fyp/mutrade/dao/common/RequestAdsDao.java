package com.fyp.mutrade.dao.common;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.RequestAds;
import com.fyp.mutrade.entity.common.Student;
/**
 * Request Ads DAO
 * @author Administrator
 *
 */
@Repository
public interface RequestAdsDao extends JpaRepository<RequestAds, Long>,JpaSpecificationExecutor<RequestAds>{
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 */
	@Query("select wg from RequestAds wg where id = :id")
	RequestAds find(@Param("id")Long id);
	
	/**
	 * Find by a student object
	 * @param student
	 * @return
	 */
	public List<RequestAds> findByStudent(Student student);
}
