package com.fyp.mumarket.dao.common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fyp.mumarket.entity.common.SiteSetting;
//site setting dao
@Repository
public interface SiteSettingDao extends JpaRepository<SiteSetting, Long> {
	@Query("select ss from SiteSetting ss where id = 1")SiteSetting find();//search by id
}
