package com.fyp.mutrade.dao.common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.News;
//News management DAO
@Repository
public interface NewsDao extends JpaRepository<News, Long> {
	//search by id
	@Query("select n from News n where id = :id")News find(@Param("id")Long id);
}
