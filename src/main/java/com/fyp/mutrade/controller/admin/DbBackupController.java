package com.fyp.mutrade.controller.admin;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.bean.Result;
import com.fyp.mutrade.entity.admin.DbBackup;
import com.fyp.mutrade.service.admin.DbBackupService;
import com.fyp.mutrade.service.admin.OperaterLogService;

/**
 * Database backup management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/db_backup")
@Controller
public class DbBackupController {

	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private DbBackupService dbBackupService;
	
	@Value("${fyp.database.backup.dir}")
	private String backUpDir;
	
	private Logger log = LoggerFactory.getLogger(DbBackupController.class);
	
	/**
	 * Database backup management controller page
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,PageBean<DbBackup> pageBean){
		model.addAttribute("title", "Backup list");
		model.addAttribute("pageBean", dbBackupService.findList(pageBean));
		return "admin/db_backup/list";
	}
	
	/**
	 * Backup operation
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(){
		dbBackupService.backup();
		return Result.success(true);
	}
	
	/**
	 * Delete backup records and files
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(String ids){
		if(!StringUtils.isEmpty(ids)){
			String[] splitIds = ids.split(",");
			for(String id : splitIds){
				DbBackup dbBackup = dbBackupService.find(Long.valueOf(id));
				if(dbBackup != null){
					dbBackupService.delete(Long.valueOf(id));
					File file = new File(dbBackup.getFilepath() + dbBackup.getFilename());
					if(!file.exists()){
						//This indicates that the file does not exist. Search for the file again based on the configuration file's path
						file = new File(backUpDir + dbBackup.getFilename());
					}
					file.delete();
					log.info("Deleted backup file，Backup ID="+id);
				}
			}
		}
		return Result.success(true);
	}
	
	/**
	 * Restore backup files
	 * @param id
	 * @return
	 */
	@RequestMapping(value="restore",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> restore(@RequestParam(name="id",required=true)Long id){
		dbBackupService.restore(id);
		return Result.success(true);
	}
}
