package com.fyp.mumarket.service.common;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.common.FriendLinkDao;
import com.fyp.mumarket.entity.common.FriendLink;

//Related site service
@Service
public class FriendLinkService {

	@Autowired
	private FriendLinkDao friendLinkDao;
	//save method
	public FriendLink save(FriendLink friendLink){
		return friendLinkDao.save(friendLink);
	}
	
	//Paginated search for news
	public PageBean<FriendLink> findList(PageBean<FriendLink> pageBean,FriendLink friendLink){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort");
		Example<FriendLink> example = Example.of(friendLink, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<FriendLink> findAll = friendLinkDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	//Search by id
	public FriendLink find(Long id){
		return friendLinkDao.find(id);
	}
	
	//remove by id
	public void delete(Long id){
		friendLinkDao.deleteById(id);
	}
	
	//Retrieve amount of data in list
	public List<FriendLink> findList(int size){
		FriendLink friendLink = new FriendLink();
		PageBean<FriendLink> pageBean = new PageBean<FriendLink>();
		pageBean.setPageSize(size);
		return findList(pageBean, friendLink).getContent();
	}
}
