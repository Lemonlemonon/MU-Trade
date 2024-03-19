package com.fyp.mumarket.controller.user;

import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.common.MsgVo;
import com.fyp.mumarket.service.common.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home Notice controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/notice")
public class UserNoticeController {

  @Autowired
  private NoticeService noticeService;

  /**
   * Retrieve user messages
   * @param userId
   * @return
   */
  @GetMapping
  public Result<?> queryNotice(@RequestParam Long userId) {
    return Result.success(noticeService.queryNotice(userId));
  }

  /**
   * Send message(bidding notification and user messages)
   * @param vo
   * @return
   */
  @PostMapping
  public Result<?> sendMsg(@RequestBody MsgVo vo) {
    return Result.success(noticeService.sendMsg(vo));
  }

  /**
   * Set notification as read
   * @param noticeId
   * @return
   */
  @PutMapping
  public Result<?> read(@RequestParam Long noticeId) {
    return Result.success(noticeService.readNotice(noticeId));
  }



}
