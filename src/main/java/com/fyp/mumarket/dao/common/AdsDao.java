package com.fyp.mumarket.dao.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.Student;
@Repository
public interface AdsDao extends JpaRepository<Ads, Long>,JpaSpecificationExecutor<Ads> {
	/**
	 * Retrieve by id
	 * @return
	 */
	@Query("select g from Ads g where id = :id")
	Ads find(@Param("id")Long id);
	
	/**
	 * Retrieve by a student object
	 * @param student
	 * @return
	 */
	List<Ads> findByStudent(Student student);
	
	/**
	 * Find by item id and student id
	 * @param id
	 * @param userId
	 * @return
	 */
	@Query("select g from Ads g where id = :id and g.student.id = :studentId")
	Ads find(@Param("id")Long id,@Param("studentId")Long studentId);
	
	/**
	 * Find list of item by category
	 * @param cids
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT * from fyp_ads where ads_category_id IN :cids and `status` = 1 ORDER BY create_time desc,flag desc,recommend desc limit :offset,:pageSize",nativeQuery=true)
	List<Ads> findList(@Param("cids")List<Long> cids,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	/**
	 * Retrieve the total count based on category search
	 * @param cids
	 * @return
	 */
	@Query(value="SELECT count(*) from fyp_ads where ads_category_id IN :cids and `status` = 1 ",nativeQuery=true)
	Long getTotalCount(@Param("cids")List<Long> cids);
	
	/**
	 * Get the total count of items with a specified status
	 * @param status
	 * @return
	 */
	@Query("SELECT count(g) from Ads g where g.status = :status ")
	Long getTotalCount(@Param("status")Integer status);
	
	/**
	 * Perform a 'like' search based on item names
	 * @param name
	 * @return
	 */
	@Query(value="select * from fyp_ads where name like %:name%",nativeQuery=true)
	List<Ads> findListByName(@Param("name")String name);

	/**
	 * Find Ads by bidding id
	 * @param biddingId
	 * @return
	 */
	Optional<Ads> findByAdsBiddingId(@Param("biddingId") Long biddingId);
}
