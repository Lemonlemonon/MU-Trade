package com.fyp.mutrade.service.common;
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

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.common.NewsDao;
import com.fyp.mutrade.entity.common.News;
//News service
@Service
public class NewsService {
	@Autowired
	private NewsDao newsDao;
	//Create new News object
	public News save(News news){
		return newsDao.save(news);
	}
	
	//Paginated search for news
	public PageBean<News> findList(PageBean<News> pageBean,News news){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("title", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort","viewNumber");
		Example<News> example = Example.of(news, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<News> findAll = newsDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}

	// get a list of specified numbers of news
	public List<News> findList(int size){
		News news = new News();
		PageBean<News> pageBean = new PageBean<News>();
		pageBean.setPageSize(size);
		return findList(pageBean, news).getContent();
	}
	
	//search by id
	public News find(Long id){
		return newsDao.find(id);
	}
	
	//delete by id
	public void delete(Long id){
		newsDao.deleteById(id);
	}
}
