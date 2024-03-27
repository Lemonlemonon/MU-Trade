package com.fyp.mutrade.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.admin.OperaterLogDao;
import com.fyp.mutrade.entity.admin.OperaterLog;
import com.fyp.mutrade.entity.admin.User;
import com.fyp.mutrade.util.SessionUtil;

/**
 * Backend operation class, database operation service
 * @author Administrator
 *
 */
@Service
public class OperaterLogService {
	
	@Autowired
	private OperaterLogDao operaterLogDao;
	
	/**
	 * Add/modify operation log. When the ID is not empty, perform an update; when the ID is empty, automatically create a new record
	 * @param operaterLog
	 * @return
	 */
	public OperaterLog save(OperaterLog operaterLog){
		return operaterLogDao.save(operaterLog);
	}
	
	/**
	 * Retrieve a specified number of operation log records
	 * @param size
	 * @return
	 */
	public List<OperaterLog> findLastestLog(int size){
		return operaterLogDao.findLastestLog(size);
	}
	
	/**
	 * Retrieve a single log record based on ID
	 * @param id
	 * @return
	 */
	public OperaterLog findById(Long id){
		return operaterLogDao.find(id);
	}
	
	/**
	 * return all log record
	 * @return
	 */
	public List<OperaterLog> findAll(){
		return operaterLogDao.findAll();
	}
	
	/**
	 * remove a single log record by id
	 * @param id
	 */
	public void delete(Long id){
		operaterLogDao.deleteById(id);
	}
	
	/**
	 * Delete the whole table
	 */
	public void deleteAll(){
		operaterLogDao.deleteAll();
	}
	
	/**
	 * log adding
	 * @param operater
	 * @param content
	 */
	public void add(String operater,String content){
		OperaterLog operaterLog = new OperaterLog();
		operaterLog.setOperator(operater);
		operaterLog.setContent(content);
		save(operaterLog);
	}
	
	/**
	 * log adding
	 * @param content
	 */
	public void add(String content){
		User loginedUser = SessionUtil.getLoginedUser();
		add(loginedUser == null ? "Unknown(Fail to retrieve user info)" : loginedUser.getUsername(), content);
	}
	
	/**
	 * Paginate and search for logs
	 * @param operaterLog
	 * @param pageBean
	 * @return
	 */
	public PageBean<OperaterLog> findList(OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("operator", GenericPropertyMatchers.contains());
		Example<OperaterLog> example = Example.of(operaterLog, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<OperaterLog> findAll = operaterLogDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Total number of operation logs.
	 * @return
	 */
	public long total(){
		return operaterLogDao.count();
	}
}
