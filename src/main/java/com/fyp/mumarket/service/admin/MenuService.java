package com.fyp.mumarket.service.admin;
/**
 * Backend menu operation service
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.dao.admin.MenuDao;
import com.fyp.mumarket.entity.admin.Menu;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	/**
	 * Menu create/edit
	 * @param menu
	 * @return
	 */
	public Menu save(Menu menu){
		return menuDao.save(menu);
	}
	
	/**
	 * Retrieve all menu lists
	 * @return
	 */
	public List<Menu> findAll(){
		return menuDao.findAll();
	}
	
	/**
	 * Search a menu by id
	 * @param id
	 * @return
	 */
	public Menu find(Long id){
		return menuDao.find(id);
	}
	
	/**
	 * delete a menu by id
	 * @param id
	 */
	public void delete(Long id){
		menuDao.deleteById(id);
	}
}
