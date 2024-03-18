package com.fyp.mumarket.service.common;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.dao.common.GoodsBiddingDao;
import com.fyp.mumarket.dao.common.GoodsDao;
import com.fyp.mumarket.dao.common.NoticeDao;
import com.fyp.mumarket.entity.common.Goods;
import com.fyp.mumarket.entity.common.GoodsBidding;
import com.fyp.mumarket.entity.common.MsgVo;
import com.fyp.mumarket.entity.common.Notice;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Home Notice Service
 * @author Administrator
 *
 */
@Service
public class NoticeService {

  @Autowired
  private NoticeDao noticeDao;

  /**
   * Send notification
   * @param vo
   * @return
   */
  public Result<String> sendMsg(MsgVo vo) {
    Notice notice = new Notice();
    notice.setIsRead(0);
    notice.setReceiverId(vo.getReceiverId());
    notice.setSendId(vo.getSendId());
    notice.setContent(vo.getContent());
    notice.setCreateTime(new Date());
    notice.setUpdateTime(new Date());
    noticeDao.save(notice);

    return Result.success("Message sent!");
  }

  /**
   * Read notification
   * @param noticeId
   * @return
   */
  public Result<?> readNotice(Long noticeId) {
    Optional<Notice> noticeOptional = noticeDao.findById(noticeId);
    if (noticeOptional.isPresent()) {
      Notice notice = noticeOptional.get();
      notice.setIsRead(1);    // Set to read
      notice.setUpdateTime(new Date());
      noticeDao.save(notice);
    }
    return Result.success("Message read!");
  }

  /**
   * Find user notice by user id
   * @param userId
   * @return
   */
  public Result<?> queryNotice(Long userId) {
    return Result.success(noticeDao.findNoticesByReceiverId(userId));
  }



}
