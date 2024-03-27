package com.fyp.mutrade.util;

import java.util.ArrayList;
import java.util.List;

import com.fyp.mutrade.entity.admin.Menu;

/**
 * Menu utility class
 * @author Administrator
 *
 */
public class MenuUtil {

	/**
	 * Retrieve all top-level menu classes
	 * @param menus
	 * @return
	 */
	public static List<Menu> getTopMenus(List<Menu> menus){
		List<Menu> topMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() == null){
				topMenus.add(menu);
			}
		}
		return topMenus;
	}
	
	/**
	 * Retrieve second-level menu classes
	 * @param menus
	 * @return
	 */
	public static List<Menu> getSecondMenus(List<Menu> menus){
		List<Menu> secondMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() != null && menu.getParent().getParent() == null){
				secondMenus.add(menu);
			}
		}
		return secondMenus;
	}
	
	/**
	 * Retrieve third-level menu classes
	 * @param menus
	 * @return
	 */
	public static List<Menu> getThirdMenus(List<Menu> menus){
		List<Menu> thirdMenus = new ArrayList<Menu>();
		for(Menu menu : menus){
			if(menu.getParent() != null && menu.getParent().getParent() != null){
				thirdMenus.add(menu);
			}
		}
		return thirdMenus;
	}
	
	/**
	 * Retrieve the menu ID based on the menu URL
	 * @param url
	 * @param menus
	 * @return
	 */
	public static Long getMenuIdByUrl(String url,List<Menu> menus){
		if(url == null)return null;
		for(Menu menu : menus){
			if(url.equals(menu.getUrl())){
				return menu.getId();
			}
		}
		return null;
	}
	
	/**
	 * Retrieve all subcategories of a specific menu ID
	 * @param parentId
	 * @param menus
	 * @return
	 */
	public static List<Menu> getChildren(Long parentId,List<Menu> menus){
		List<Menu> children = new ArrayList<Menu>();
		if(parentId != null){
			for(Menu menu : menus){
				if(menu.getParent() != null && menu.getParent().getId().longValue() == parentId.longValue()){
					children.add(menu);
				}
			}
		}
		return children;
	}
	
	/**
	 * Determine if the given URL exists in the specified list
	 * @param url
	 * @param menus
	 * @return
	 */
	public static boolean isExistUrl(String url,List<Menu> menus){
		if(url != null){
			for(Menu menu : menus){
				if(menu.getUrl() != null){
					if(menu.getUrl().contains(url)){
						return true;
					}
				}
			}
		}
		return false;
	}
}
