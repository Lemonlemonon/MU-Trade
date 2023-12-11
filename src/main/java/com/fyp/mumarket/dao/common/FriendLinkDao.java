package com.fyp.mumarket.dao.common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.FriendLink;
//Related site DAO
@Repository
public interface FriendLinkDao extends JpaRepository<FriendLink, Long> {
	//Search by id
	@Query("select fl from FriendLink fl where id = :id")
	FriendLink find(@Param("id")Long id);
}
