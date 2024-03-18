package com.fyp.mumarket.entity.common;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * User bidding entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_users_bidding")
@EntityListeners(AuditingEntityListener.class)
public class UserBidding extends BaseEntity{

  private Long userId;
  private Long biddingId;
  private Integer biddingPrice;
  private String userName;

  @Transient
  private String price;


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getBiddingId() {
    return biddingId;
  }

  public void setBiddingId(Long biddingId) {
    this.biddingId = biddingId;
  }

  public Integer getBiddingPrice() {
    return biddingPrice;
  }

  public void setBiddingPrice(Integer biddingPrice) {
    this.biddingPrice = biddingPrice;
  }

  @Override
  public String toString() {
    return "UserBidding{" +
        "userId=" + userId +
        ", biddingId=" + biddingId +
        ", biddingPrice=" + biddingPrice +
        '}';
  }

}
