package com.fyp.mutrade.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.common.UserBidding;
import com.fyp.mutrade.service.common.AdsBiddingService;
import com.fyp.mutrade.service.common.UsersBiddingService;

/**
 * Home Bidding controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/bidding")
public class UserBiddingController {

  @Autowired
  private AdsBiddingService adsBiddingService;
  @Autowired
  private UsersBiddingService usersBiddingService;

  /**
   * Create bidding
   * @param userBidding
   * @return
   */
  @PostMapping("/create-bidding")
  public Result<?> createBidding(@RequestBody UserBidding userBidding) {
    return usersBiddingService.createBidding(userBidding);
  }

  /**
   * Find the current bidding user of an Ads
   * @param biddingId
   * @return
   */
  @GetMapping("/top_price")
  public Result<?> findBidding(@RequestParam Long biddingId) {
    return usersBiddingService.findBidding(biddingId);
  }

  /**
   * Stop bidding
   * @param adsBiddingId Ads bidding id
   * @param status Bidding status：0-Active，1-Stopped
   * @return
   */
  @PutMapping("/stop-bidding")
  public Result<?> stopBidding(@RequestParam Long adsBiddingId) {
    return adsBiddingService.stopBidding(adsBiddingId);
  }

  /**
   * Reset bidding
   * @param adsId
   * @return
   */
  @PostMapping("/reset-bidding")
  public Result<?> createBidding(@RequestParam Long adsId) {
    return adsBiddingService.createBidding(adsId);
  }




}
