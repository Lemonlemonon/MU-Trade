package com.fyp.mumarket.service.admin;
/**
 * Backend role operation service
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.admin.RoleDao;
import com.fyp.mumarket.entity.admin.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * Create/modify role
	 * @param role
	 * @return
	 */
	public Role save(Role role){
		return roleDao.save(role);
	}
	
	/**
	 * Retrieve all role list
	 * @return
	 */
	public List<Role> findAll(){
		return roleDao.findAll();
	}
	
	/**
	 * Paginate and search for role list by name
	 * @param role
	 * @param pageBean
	 * @return
	 */
	public PageBean<Role> findByName(Role role,PageBean<Role> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching().withMatcher("name", GenericPropertyMatchers.contains());
		withMatcher = withMatcher.withIgnorePaths("status");
		Example<Role> example = Example.of(role, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<Role> findAll = roleDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * Retrieve a role by ID
	 * @param id
	 * @return
	 */
	public Role find(Long id){
		return roleDao.find(id);
	}
	
	/**
	 * Delete a record by ID
	 * @param id
	 */
	public void delete(Long id){
		roleDao.deleteById(id);
	}
}
