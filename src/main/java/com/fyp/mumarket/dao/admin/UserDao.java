package com.fyp.mumarket.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mumarket.entity.admin.User;

/**
 * User database processing layer
 * @author Administrator
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	/**
	 * Retrieve user information by username
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * Retrieve user information by id
	 * @param id
	 * @return
	 */
	@Query("select u from User u where id = :id")
	public User find(@Param("id")Long id);
	
}
