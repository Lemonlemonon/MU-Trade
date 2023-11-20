package com.fyp.mumarket.dao.common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mumarket.entity.common.Student;
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
	
	/**
	 * Find by student number
	 * @param sn
	 * @return
	 */
	Student findBySn(String sn);
	
	/**
	 * Find by student id
	 * @param id
	 * @return
	 */
	@Query("select s from Student s where id = :id")
	Student find(@Param("id")Long id);
}
