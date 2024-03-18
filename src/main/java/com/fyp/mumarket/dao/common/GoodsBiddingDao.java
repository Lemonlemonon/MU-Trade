package com.fyp.mumarket.dao.common;

import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.GoodsBidding;
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
public interface GoodsBiddingDao extends JpaRepository<GoodsBidding, Long>,JpaSpecificationExecutor<GoodsBidding> {

  Optional<GoodsBidding> findByIdAndStatus(Long goodsBiddingId, Integer status);

  Optional<GoodsBidding> findByGoodIdAndStatus(Long goodId, Integer status);

}
