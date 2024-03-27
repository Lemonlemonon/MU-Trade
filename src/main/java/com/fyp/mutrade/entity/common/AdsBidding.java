package com.fyp.mutrade.entity.common;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * AdsBidding entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_ads_bidding")
@EntityListeners(AuditingEntityListener.class)
public class AdsBidding extends BaseEntity{

  public Long getAdId() {
    return adId;
  }

  public void setAdId(Long adId) {
    this.adId = adId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "AdsBidding{" +
        "adId=" + adId +
        ", status=" + status +
        '}';
  }

  private Long adId;
  /**
   * Bidding status：0-active，1-stopped
   */
  private Integer status;


}
