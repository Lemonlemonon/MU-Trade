package com.fyp.mumarket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fyp.mumarket.service.admin.DbBackupService;

/**
 * Auto backup timer
 * @author Administrator
 *
 */
@Configuration
@EnableScheduling
public class BackUpSchedule {

	@Autowired
	private DbBackupService dbBackupService;
	
	private Logger log = LoggerFactory.getLogger(BackUpSchedule.class);
	
	//@Scheduled(initialDelay=10000,fixedRate=5000)
	@Scheduled(cron="0 0 1 * * ?")//"Execute backup task every day at 1:00 AM
	public void backUpDatabase(){
		log.info("Start executing scheduled database backup task!");
		dbBackupService.backup();
	}
}
