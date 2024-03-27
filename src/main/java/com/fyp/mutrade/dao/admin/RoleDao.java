package com.fyp.mutrade.dao.admin;
/**
 * Backend role database operation layer
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.admin.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	@Query("select r from Role r where r.id = :id")
	Role find(@Param("id")Long id);
}
