package com.fyp.mumarket.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mumarket.entity.common.AdsCategory;
@Repository
public interface AdsCategoryDao extends JpaRepository<AdsCategory, Long> {
	/**
	 * Retrieve category has no parents(First level menu)
	 * @return
	 */
	List<AdsCategory> findByParentIsNull();
	
	/**
	 * Retrieve category has parents(Second level menu)
	 * @return
	 */
	List<AdsCategory> findByParentIsNotNull();
	
	/**
	 * Retrieve by id
	 * @return
	 */
	@Query("select gc from AdsCategory gc where id = :id")
	AdsCategory find(@Param("id")Long id);
	
	/**
	 * Retrieve child category from a parent category
	 * @param parent
	 * @return
	 */
	List<AdsCategory> findByParent(AdsCategory parent);
	
	/**
	 * Retrieve category by name
	 * @param name
	 * @return
	 */
	List<AdsCategory> findByName(String name);
}
