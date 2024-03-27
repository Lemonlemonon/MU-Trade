package com.fyp.mutrade.dao.admin;
/**
 * Backend menu database operation layer
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.admin.Menu;

@Repository
public interface MenuDao extends JpaRepository<Menu, Long> {
	@Query("select m from Menu m where m.id = :id")
	Menu find(@Param("id")Long id);
}
