package com.fyp.mutrade.dao.common;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.AdsBidding;
import com.fyp.mutrade.entity.common.UserBidding;

/**
 * Home UserBidding DAO
 * @author Administrator
 *
 */
@Repository
public interface UsersBiddingDao extends JpaRepository<UserBidding, Long>,JpaSpecificationExecutor<UserBidding> {

  Optional<UserBidding> findByUserIdAndBiddingId(Long userId, Long biddingId);

  @Query("select ub from UserBidding ub where biddingId = :biddingId order by biddingPrice desc, createTime desc ")
  List<UserBidding> findByBiddingId(Long biddingId);

  @Query("select ub from UserBidding ub where biddingId = :biddingId order by biddingPrice desc")
  List<UserBidding> findOneByBiddingId(Long biddingId);

}
