package com.fyp.mumarket.entity.common;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * GoodsBidding entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_goods_bidding")
@EntityListeners(AuditingEntityListener.class)
public class GoodsBidding extends BaseEntity{

  public Long getGoodId() {
    return goodId;
  }

  public void setGoodId(Long goodId) {
    this.goodId = goodId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "GoodsBidding{" +
        "goodId=" + goodId +
        ", status=" + status +
        '}';
  }

  private Long goodId;
  /**
   * Bidding status：0-active，1-stopped
   */
  private Integer status;


}
