package com.fyp.mumarket.entity.common;

/**
 * MsgVo entity class
 * @author Administrator
 *
 */
public class MsgVo {

  private Long receiverId;

  public String getSendId() {
    return sendId;
  }

  public void setSendId(String sendId) {
    this.sendId = sendId;
  }
  
  public MsgVo(Long receiverId, String sendId, String content) {
	    this.receiverId = receiverId;
	    this.sendId = sendId;
	    this.content = content;
	  }

  private String sendId;
  
  private String content;

  public MsgVo() {
  }

  public MsgVo(Long receiverId, String content) {
    this.receiverId = receiverId;
    this.content = content;
  }

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

  @Override
  public String toString() {
    return "MsgVo{" +
        "receiverId=" + receiverId +
        ", content='" + content + '\'' +
        '}';
  }

}
