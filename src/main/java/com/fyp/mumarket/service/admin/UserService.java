package com.fyp.mumarket.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.admin.UserDao;
import com.fyp.mumarket.entity.admin.User;

/**
 * User management service
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * Search user by id
	 * @param id
	 * @return
	 */
	public User find(Long id){
		return userDao.find(id);
	}
	
	/**
	 * Search user by username
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}
	
	/**
	 * Create/modify user
	 * @param user
	 * @return
	 */
	public User save(User user){
		return userDao.save(user);
	}
	
	/**
	 * "Paginate and search for user
	 * @param user
	 * @param pageBean
	 * @return
	 */
	public PageBean<User> findList(User user,PageBean<User> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("username", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status","sex");
		Example<User> example = Example.of(user, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<User> findAll = userDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Determine whether the username exists
	 * @param username
	 * @param id
	 * @return
	 */
	public boolean isExistUsername(String username,Long id){
		User user = userDao.findByUsername(username);
		if(user != null){
			//Indicates that the username exists, now proceed to check if it belongs to the user being edited.
			if(user.getId().longValue() != id.longValue()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove user by id
	 * @param id
	 */
	public void delete(Long id){
		userDao.deleteById(id);
	}
	
	/**
	 * Return total user count
	 * @return
	 */
	public long total(){
		return userDao.count();
	}
}
