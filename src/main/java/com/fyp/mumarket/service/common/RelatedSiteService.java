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
import com.fyp.mumarket.dao.common.RelatedSiteDao;
import com.fyp.mumarket.entity.common.RelatedSite;

//Related site service
@Service
public class RelatedSiteService {

	@Autowired
	private RelatedSiteDao relatedSiteDao;
	//save method
	public RelatedSite save(RelatedSite relatedSite){
		return relatedSiteDao.save(relatedSite);
	}
	
	//Paginated search for news
	public PageBean<RelatedSite> findList(PageBean<RelatedSite> pageBean,RelatedSite relatedSite){
		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		exampleMatcher = exampleMatcher.withMatcher("name", GenericPropertyMatchers.contains());
		exampleMatcher = exampleMatcher.withIgnorePaths("sort");
		Example<RelatedSite> example = Example.of(relatedSite, exampleMatcher);
		Sort sort = Sort.by(Direction.ASC, "sort");
		PageRequest pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize(), sort);
		Page<RelatedSite> findAll = relatedSiteDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	//Search by id
	public RelatedSite find(Long id){
		return relatedSiteDao.find(id);
	}
	
	//remove by id
	public void delete(Long id){
		relatedSiteDao.deleteById(id);
	}
	
	//Retrieve amount of data in list
	public List<RelatedSite> findList(int size){
		RelatedSite relatedSite = new RelatedSite();
		PageBean<RelatedSite> pageBean = new PageBean<RelatedSite>();
		pageBean.setPageSize(size);
		return findList(pageBean, relatedSite).getContent();
	}
}
