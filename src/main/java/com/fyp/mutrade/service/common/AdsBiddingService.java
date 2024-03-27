package com.fyp.mutrade.service.common;

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

import com.fyp.mutrade.bean.CodeMsg;
import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.dao.common.AdsBiddingDao;
import com.fyp.mutrade.dao.common.AdsDao;
import com.fyp.mutrade.dao.common.NoticeDao;
import com.fyp.mutrade.dao.common.UsersBiddingDao;
import com.fyp.mutrade.entity.common.Ads;
import com.fyp.mutrade.entity.common.AdsBidding;
import com.fyp.mutrade.entity.common.Notice;
import com.fyp.mutrade.entity.common.Student;
import com.fyp.mutrade.entity.common.UserBidding;

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
