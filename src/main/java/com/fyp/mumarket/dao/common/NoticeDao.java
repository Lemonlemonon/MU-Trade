package com.fyp.mumarket.dao.common;

import com.fyp.mumarket.entity.common.GoodsBidding;
import com.fyp.mumarket.entity.common.Notice;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Home Notice DAO
 * @author Administrator
 *
 */
@Repository
public interface NoticeDao extends JpaRepository<Notice, Long>,JpaSpecificationExecutor<Notice> {


  List<Notice> findNoticesByReceiverId(Long receiverId);

}
