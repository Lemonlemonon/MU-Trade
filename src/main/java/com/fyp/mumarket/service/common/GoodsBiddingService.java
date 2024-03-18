package com.fyp.mumarket.service.common;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.dao.common.GoodsBiddingDao;
import com.fyp.mumarket.dao.common.GoodsDao;
import com.fyp.mumarket.dao.common.NoticeDao;
import com.fyp.mumarket.dao.common.UsersBiddingDao;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.GoodsBidding;
import com.fyp.mumarket.entity.common.Notice;
import com.fyp.mumarket.entity.common.Student;
import com.fyp.mumarket.entity.common.UserBidding;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Home GoodsBidding Service
 * @author Administrator
 *
 */
@Service
public class GoodsBiddingService {

	@Autowired
	private GoodsBiddingDao goodsBiddingDao;
	@Autowired
	private UsersBiddingDao usersBiddingDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private NoticeDao noticeDao;

	public Result<?> stopBidding(Long goodsBiddingId) {
		Optional<GoodsBidding> biddingOptional = goodsBiddingDao.findByIdAndStatus(goodsBiddingId, 0);
		if (!biddingOptional.isPresent()) {
			return Result.error(new CodeMsg(400, "Bidding does not exist!"));
		}
		GoodsBidding goodsBidding = biddingOptional.get();
		// Stop bidding
		goodsBidding.setStatus(2);
		goodsBiddingDao.save(goodsBidding);

		List<UserBidding> userBiddingList = usersBiddingDao.findOneByBiddingId(goodsBiddingId);
		if (!userBiddingList.isEmpty()) {
			UserBidding userBidding = userBiddingList.get(0);
			Notice notice = new Notice();
			notice.setIsRead(0);
			notice.setReceiverId(userBidding.getUserId());
			notice.setSendId("System");
			Optional<Goods> optionalGoods = goodsDao.findById(goodsBidding.getGoodId());
			if (optionalGoods.isPresent()) {
				Goods goods = optionalGoods.get();
//				goods.setGoodsBiddingId(goodsBiddingId);
//				goodsDao.save(goods);
				notice.setContent("You've won the biding for the Ads: " + goods.getName());
				notice.setCreateTime(new Date());
				notice.setUpdateTime(new Date());
				noticeDao.save(notice);
			}
		}

		return Result.success("Congratulation! The bidding is finished! Please contact the buyer!");
	}

	@Transactional(rollbackOn = RuntimeException.class)
	public Result<?> createBidding(Long goodsId) {
		Optional<GoodsBidding> goodsBiddingOptional = goodsBiddingDao.findByGoodIdAndStatus(goodsId, 0);
		if (goodsBiddingOptional.isPresent()) {
			GoodsBidding goodsBidding = goodsBiddingOptional.get();
			goodsBidding.setStatus(1);
			goodsBiddingDao.save(goodsBidding);
		}
		GoodsBidding goodsBidding = new GoodsBidding();
		goodsBidding.setGoodId(goodsId);
		goodsBidding.setCreateTime(new Date());
		goodsBidding.setUpdateTime(new Date());
		goodsBidding.setStatus(0);
		GoodsBidding bidding = goodsBiddingDao.save(goodsBidding);

		// Reset Goods bidding information
		Optional<Goods> optionalGoods = goodsDao.findById(goodsId);
		if (optionalGoods.isPresent()) {
			Goods goods = optionalGoods.get();
			goods.setGoodsBiddingId(bidding.getId());
			goodsDao.save(goods);
		}

		return Result.success("Success!");
	}


























}
