package com.fyp.mutrade.dao.common;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.ReportAds;
import com.fyp.mutrade.entity.common.Student;
@Repository
public interface ReportAdsDao extends JpaRepository<ReportAds, Long>,JpaSpecificationExecutor<ReportAds> {
	/**
	 * Find a report by id
	 * @return
	 */
	@Query("select rg from ReportAds rg where id = :id")
	ReportAds find(@Param("id")Long id);
	
	/**
	 * Find a report by a student obj
	 * @param student
	 * @return
	 */
	List<ReportAds> findByStudent(Student student);
	
	/**
	 * Find a report by a item obj
	 * @param ads
	 * @return
	 */
	List<ReportAds> findByAds(Ads ads);
	
	/**
	 * Find by student id and item id
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select rg from ReportAds rg where rg.ads.id = :adsId and rg.student.id = :studentId")
	ReportAds find(@Param("adsId")Long adsId,@Param("studentId")Long studentId);
	
	
}
