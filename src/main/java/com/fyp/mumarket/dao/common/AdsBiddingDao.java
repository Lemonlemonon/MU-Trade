package com.fyp.mumarket.dao.common;

import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.AdsBidding;
import com.fyp.mumarket.entity.common.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Home Bidding DAO
 * @author Administrator
 *
 */
@Repository
public interface AdsBiddingDao extends JpaRepository<AdsBidding, Long>,JpaSpecificationExecutor<AdsBidding> {

  Optional<AdsBidding> findByIdAndStatus(Long adsBiddingId, Integer status);

  Optional<AdsBidding> findByAdIdAndStatus(Long adId, Integer status);

}
