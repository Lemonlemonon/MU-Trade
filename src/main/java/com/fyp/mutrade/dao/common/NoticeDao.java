package com.fyp.mutrade.dao.common;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fyp.mutrade.entity.common.AdsBidding;
import com.fyp.mutrade.entity.common.Notice;

/**
 * Home Notice DAO
 * @author Administrator
 *
 */
@Repository
public interface NoticeDao extends JpaRepository<Notice, Long>,JpaSpecificationExecutor<Notice> {


  List<Notice> findNoticesByReceiverId(Long receiverId);

}
