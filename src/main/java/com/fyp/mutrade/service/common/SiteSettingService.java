package com.fyp.mutrade.service.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.mutrade.dao.common.SiteSettingDao;
import com.fyp.mutrade.entity.common.SiteSetting;
//Site setting service
@Service
public class SiteSettingService {
	@Autowired
	private SiteSettingDao siteSettingDao;
	public SiteSetting save(SiteSetting siteSetting){
		return siteSettingDao.save(siteSetting);
	}
	public SiteSetting find(){
		return siteSettingDao.find();
	}
	
}
