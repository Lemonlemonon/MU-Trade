package com.fyp.mumarket.controller.admin;

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

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.entity.admin.DatabaseBak;
import com.fyp.mumarket.service.admin.DatabaseBakService;
import com.fyp.mumarket.service.admin.OperaterLogService;

/**
 * Database backup management controller
 * @author Administrator
 *
 */
@RequestMapping("/admin/database_bak")
@Controller
public class DatabaseBakController {

	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private DatabaseBakService databaseBakService;
	
	@Value("${fyp.database.backup.dir}")
	private String backUpDir;
	
	private Logger log = LoggerFactory.getLogger(DatabaseBakController.class);
	
	/**
	 * Database backup management controller page
	 * @param model
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model,PageBean<DatabaseBak> pageBean){
		model.addAttribute("title", "Backup list");
		model.addAttribute("pageBean", databaseBakService.findList(pageBean));
		return "admin/database_bak/list";
	}
	
	/**
	 * Backup operation
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(){
		databaseBakService.backup();
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
				DatabaseBak databaseBak = databaseBakService.find(Long.valueOf(id));
				if(databaseBak != null){
					databaseBakService.delete(Long.valueOf(id));
					File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
					if(!file.exists()){
						//This indicates that the file does not exist. Search for the file again based on the configuration file's path
						file = new File(backUpDir + databaseBak.getFilename());
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
		databaseBakService.restore(id);
		return Result.success(true);
	}
}
