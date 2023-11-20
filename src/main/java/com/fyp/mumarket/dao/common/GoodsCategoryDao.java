package com.fyp.mumarket.dao.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mumarket.entity.common.GoodsCategory;
@Repository
public interface GoodsCategoryDao extends JpaRepository<GoodsCategory, Long> {
	/**
	 * Retrieve category has no parents(First level menu)
	 * @return
	 */
	List<GoodsCategory> findByParentIsNull();
	
	/**
	 * Retrieve category has parents(Second level menu)
	 * @return
	 */
	List<GoodsCategory> findByParentIsNotNull();
	
	/**
	 * Retrieve by id
	 * @return
	 */
	@Query("select gc from GoodsCategory gc where id = :id")
	GoodsCategory find(@Param("id")Long id);
	
	/**
	 * Retrieve child category from a parent category
	 * @param parent
	 * @return
	 */
	List<GoodsCategory> findByParent(GoodsCategory parent);
	
	/**
	 * Retrieve category by name
	 * @param name
	 * @return
	 */
	List<GoodsCategory> findByName(String name);
}
