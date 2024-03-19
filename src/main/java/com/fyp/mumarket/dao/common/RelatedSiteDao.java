package com.fyp.mumarket.dao.common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.RelatedSite;
//Related site DAO
@Repository
public interface RelatedSiteDao extends JpaRepository<RelatedSite, Long> {
	//Search by id
	@Query("select fl from RelatedSite fl where id = :id")
	RelatedSite find(@Param("id")Long id);
}
