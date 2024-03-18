package com.fyp.mumarket.dao.common;

import com.fyp.mumarket.entity.common.GoodsBidding;
import com.fyp.mumarket.entity.common.UserBidding;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
