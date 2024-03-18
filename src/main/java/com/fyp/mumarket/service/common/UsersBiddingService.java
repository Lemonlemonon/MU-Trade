package com.fyp.mumarket.service.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.dao.common.GoodsBiddingDao;
import com.fyp.mumarket.dao.common.GoodsDao;
import com.fyp.mumarket.dao.common.UsersBiddingDao;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.GoodsBidding;
import com.fyp.mumarket.entity.common.UserBidding;

/**
 * Home UserBidding Service
 * @author Administrator
 *
 */
@Service
public class UsersBiddingService {

	@Autowired
	private UsersBiddingDao usersBiddingDao;
  @Autowired
  private GoodsBiddingDao goodsBiddingDao;
  @Autowired
  private GoodsDao goodsDao;

  public Result<?> createBidding(UserBidding userBidding) {
    Optional<GoodsBidding> biddingOptional = goodsBiddingDao.findById(userBidding.getBiddingId());
    if (biddingOptional.isPresent()) {
      GoodsBidding goodsBidding = biddingOptional.get();
      if (goodsBidding.getStatus() != 0) {
        return Result.error(new CodeMsg(400, "Sorry, this Ads has stopped bidding!"));
      }
    }


    Optional<Goods> optionalGoods = goodsDao.findByGoodsBiddingId(userBidding.getBiddingId());
    if (optionalGoods.isPresent()) {
      Goods goods = optionalGoods.get();
      if (goods.getStudent().getId().equals(userBidding.getUserId())) {
        return Result.error(new CodeMsg(400, "Sorry, you can not bid on your Ads!"));
      }
    }

    List<UserBidding> userBiddingList = usersBiddingDao.findByBiddingId(userBidding.getBiddingId());
    UserBidding userBidding2 = new UserBidding();
    if (!userBiddingList.isEmpty()) {
      UserBidding userBidding0 = userBiddingList.get(0);
      if (userBiddingList.size() > 1) {
        UserBidding userBidding1 = userBiddingList.get(1);
        if (userBidding0.getBiddingPrice() == userBidding1.getBiddingPrice()) {
          userBidding2 = userBidding1;
        } else if (userBidding0.getBiddingPrice() > userBidding1.getBiddingPrice()){
          userBidding2 = userBidding0;
        }
      } else {
        userBidding2 = userBidding0;
      }
      if (userBidding2.getUserId() == userBidding.getBiddingId()) {
        return Result.error(new CodeMsg(400, "Sorry, your are already the current bidder!"));
      }
      if (userBidding.getBiddingPrice() <= userBidding2.getBiddingPrice()) {
        return Result.error(new CodeMsg(400, "Sorry, your offer is too low!"));
      }
    }

    Optional<UserBidding> userBiddingOptional =
        usersBiddingDao.findByUserIdAndBiddingId(userBidding.getUserId(), userBidding.getBiddingId());
    if (userBiddingOptional.isPresent()) {
      UserBidding userBidding1 = userBiddingOptional.get();

      userBidding1.setBiddingPrice(userBidding.getBiddingPrice());
      userBidding1.setUpdateTime(new Date());
      usersBiddingDao.save(userBidding1);
    } else {
      userBidding.setCreateTime(new Date());
      userBidding.setUpdateTime(new Date());
      usersBiddingDao.save(userBidding);
    }
    return Result.success(new CodeMsg(200, "Your bidding is placed successfully!"));
  }


  public Result<?> findBidding(Long biddingId) {
    List<UserBidding> userBiddingList = usersBiddingDao.findOneByBiddingId(biddingId);
    if (userBiddingList.size() != 0) {
      UserBidding userBidding = userBiddingList.get(0);
      Integer biddingPrice = userBidding.getBiddingPrice();
      // Round to two decimal places
      userBidding.setPrice(new BigDecimal(biddingPrice).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString());

      return Result.success(userBidding);
    }
    return Result.error(new CodeMsg(400, "Error, there are NO bidder currently!"));
  }

















}
