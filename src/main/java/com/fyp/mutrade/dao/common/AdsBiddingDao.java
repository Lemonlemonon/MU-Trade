package com.fyp.mutrade.dao.common;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.AdsBidding;
import com.fyp.mutrade.entity.common.Student;

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
