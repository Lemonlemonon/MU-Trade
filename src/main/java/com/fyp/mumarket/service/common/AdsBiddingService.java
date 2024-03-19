package com.fyp.mumarket.service.common;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.dao.common.AdsBiddingDao;
import com.fyp.mumarket.dao.common.AdsDao;
import com.fyp.mumarket.dao.common.NoticeDao;
import com.fyp.mumarket.dao.common.UsersBiddingDao;
import com.fyp.mumarket.entity.common.Ads;
import com.fyp.mumarket.entity.common.AdsBidding;
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
 * Home AdsBidding Service
 * @author Administrator
 *
 */
@Service
public class AdsBiddingService {

	@Autowired
	private AdsBiddingDao adsBiddingDao;
	@Autowired
	private UsersBiddingDao usersBiddingDao;
	@Autowired
	private AdsDao adsDao;
	@Autowired
	private NoticeDao noticeDao;

	public Result<?> stopBidding(Long adsBiddingId) {
		Optional<AdsBidding> biddingOptional = adsBiddingDao.findByIdAndStatus(adsBiddingId, 0);
		if (!biddingOptional.isPresent()) {
			return Result.error(new CodeMsg(400, "Bidding does not exist!"));
		}
		AdsBidding adsBidding = biddingOptional.get();
		// Stop bidding
		adsBidding.setStatus(2);
		adsBiddingDao.save(adsBidding);

		List<UserBidding> userBiddingList = usersBiddingDao.findOneByBiddingId(adsBiddingId);
		if (!userBiddingList.isEmpty()) {
			UserBidding userBidding = userBiddingList.get(0);
			Notice notice = new Notice();
			notice.setIsRead(0);
			notice.setReceiverId(userBidding.getUserId());
			notice.setSendId("System");
			Optional<Ads> optionalAds = adsDao.findById(adsBidding.getAdId());
			if (optionalAds.isPresent()) {
				Ads ads = optionalAds.get();
//				ads.setAdsBiddingId(adsBiddingId);
//				adsDao.save(ads);
				notice.setContent("You've won the biding for the Ads: " + ads.getName());
				notice.setCreateTime(new Date());
				notice.setUpdateTime(new Date());
				noticeDao.save(notice);
			}
		}

		return Result.success("Congratulation! The bidding is finished! Please contact the buyer!");
	}

	@Transactional(rollbackOn = RuntimeException.class)
	public Result<?> createBidding(Long adsId) {
		Optional<AdsBidding> adsBiddingOptional = adsBiddingDao.findByAdIdAndStatus(adsId, 0);
		if (adsBiddingOptional.isPresent()) {
			AdsBidding adsBidding = adsBiddingOptional.get();
			adsBidding.setStatus(1);
			adsBiddingDao.save(adsBidding);
		}
		AdsBidding adsBidding = new AdsBidding();
		adsBidding.setAdId(adsId);
		adsBidding.setCreateTime(new Date());
		adsBidding.setUpdateTime(new Date());
		adsBidding.setStatus(0);
		AdsBidding bidding = adsBiddingDao.save(adsBidding);

		// Reset Ads bidding information
		Optional<Ads> optionalAds = adsDao.findById(adsId);
		if (optionalAds.isPresent()) {
			Ads ads = optionalAds.get();
			ads.setAdsBiddingId(bidding.getId());
			adsDao.save(ads);
		}

		return Result.success("Success!");
	}


























}
