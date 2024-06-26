package com.fyp.mutrade.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fyp.mutrade.bean.PageBean;
import com.fyp.mutrade.dao.admin.DbBackupDao;
import com.fyp.mutrade.entity.admin.DbBackup;
import com.fyp.mutrade.util.PathUtil;
import com.fyp.mutrade.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Datbase backup service
 */

@Service
public class DbBackupService {

    @Autowired
    private OperaterLogService operaterLogService;
    @Autowired
    private DbBackupDao dbBackupDao;
    //@Value("${fyp.database.backup.dir}")
    private final String backUpDir = PathUtil.newInstance().getBackUpDir();
    @Value("${fyp.database.backup.username}")
    private String dbUsername;
    @Value("${fyp.database.backup.password}")
    private String dbPwd;
    @Value("${fyp.database.backup.database.name}")
    private String dbName;

    private Logger log = LoggerFactory.getLogger(DbBackupService.class);

    /**
     * Paginate and search for database backup records.
     *
     * @param pageBean
     * @return
     */
    public PageBean<DbBackup> findList(PageBean<DbBackup> pageBean) {
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize());
        Page<DbBackup> findAll = dbBackupDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * Add or modify database backup records.
     *
     * @param dbBackup
     * @return
     */
    public DbBackup save(DbBackup dbBackup) {
        return dbBackupDao.save(dbBackup);
    }

    /**
     * Search by id
     *
     * @param id
     * @return
     */
    public DbBackup find(Long id) {
        return dbBackupDao.find(id);
    }

    /**
     * Delete by id
     *
     * @param id
     */
    public void delete(Long id) {
        dbBackupDao.deleteById(id);
    }

    /**
     * Backup datebase
     */
    public void backup() {
        File path = new File(backUpDir);
        if (!path.exists()) {
            path.mkdir();
        }
        try {
            String filename = dbName + "_" + StringUtil.getFormatterDate(new Date(), "yyyyMMddHHmmss") + ".sql";
            String cmd = "mysqldump -u" + dbUsername + " -p" + dbPwd + " " + dbName + " -r " + backUpDir + filename;
            Runtime.getRuntime().exec(cmd);
            DbBackup dbBackup = new DbBackup();
            dbBackup.setFilename(filename);
            dbBackup.setFilepath(backUpDir);
            save(dbBackup);
            log.info("Database backup successfully");
            operaterLogService.add("Database backup successfully, backup file：" + dbBackup);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Restore database
     *
     * @param id
     */
    public void restore(Long id) {
        DbBackup dbBackup = find(id);
        if (dbBackup != null) {
            try {
                String filename = dbBackup.getFilename();
                File file = new File(dbBackup.getFilepath() + dbBackup.getFilename());
                String cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + backUpDir + filename;
                ;
                if (!file.exists()) {
                    cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + dbBackup.getFilepath() + dbBackup.getFilename();
                }
                String stmt1 = "mysqladmin -u " + dbUsername + " -p" + dbPwd + " create " + dbName;
                String[] cmds = {"cmd", "/c", cmd};
                Runtime.getRuntime().exec(stmt1);
                Process exec = Runtime.getRuntime().exec(cmds);
                log.info(StringUtil.getStringFromInputStream(exec.getErrorStream()));
                log.info("Database restore successfully");
                operaterLogService.add("Database restore successfully, restore file：" + dbBackup);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Total backup count
     *
     * @return
     */
    public long total() {
        return dbBackupDao.count();
    }
}
