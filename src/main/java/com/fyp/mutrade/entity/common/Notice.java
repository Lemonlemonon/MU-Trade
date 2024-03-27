package com.fyp.mutrade.entity.common;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Notice entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_notice")
@EntityListeners(AuditingEntityListener.class)
public class Notice extends  BaseEntity{

  private Long receiverId;

  public String getSendId() {
    return sendId;
  }

  public void setSendId(String sendId) {
    this.sendId = sendId;
  }

  private String sendId;
  
  private String content;
  
  /**
   * isRead status：0-unread，1-read
   */
  private Integer isRead;

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getIsRead() {
    return isRead;
  }

  public void setIsRead(Integer isRead) {
    this.isRead = isRead;
  }

  @Override
  public String toString() {
    return "Notice{" +
        "receiverId=" + receiverId +
        ", content='" + content + '\'' +
        ", isRead=" + isRead +
        '}';
  }

}
