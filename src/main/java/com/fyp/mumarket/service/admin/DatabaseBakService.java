package com.fyp.mumarket.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fyp.mumarket.bean.PageBean;
import com.fyp.mumarket.dao.admin.DatabaseBakDao;
import com.fyp.mumarket.entity.admin.DatabaseBak;
import com.fyp.mumarket.util.PathUtil;
import com.fyp.mumarket.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Datbase backup service
 */

@Service
public class DatabaseBakService {

    @Autowired
    private OperaterLogService operaterLogService;
    @Autowired
    private DatabaseBakDao databaseBakDao;
    //@Value("${fyp.database.backup.dir}")
    private final String backUpDir = PathUtil.newInstance().getBackUpDir();
    @Value("${fyp.database.backup.username}")
    private String dbUsername;
    @Value("${fyp.database.backup.password}")
    private String dbPwd;
    @Value("${fyp.database.backup.database.name}")
    private String dbName;

    private Logger log = LoggerFactory.getLogger(DatabaseBakService.class);

    /**
     * Paginate and search for database backup records.
     *
     * @param pageBean
     * @return
     */
    public PageBean<DatabaseBak> findList(PageBean<DatabaseBak> pageBean) {
        Pageable pageable = PageRequest.of(pageBean.getCurrentPage() - 1, pageBean.getPageSize());
        Page<DatabaseBak> findAll = databaseBakDao.findAll(pageable);
        pageBean.setContent(findAll.getContent());
        pageBean.setTotal(findAll.getTotalElements());
        pageBean.setTotalPage(findAll.getTotalPages());
        return pageBean;
    }

    /**
     * Add or modify database backup records.
     *
     * @param databaseBak
     * @return
     */
    public DatabaseBak save(DatabaseBak databaseBak) {
        return databaseBakDao.save(databaseBak);
    }

    /**
     * Search by id
     *
     * @param id
     * @return
     */
    public DatabaseBak find(Long id) {
        return databaseBakDao.find(id);
    }

    /**
     * Delete by id
     *
     * @param id
     */
    public void delete(Long id) {
        databaseBakDao.deleteById(id);
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
            DatabaseBak databaseBak = new DatabaseBak();
            databaseBak.setFilename(filename);
            databaseBak.setFilepath(backUpDir);
            save(databaseBak);
            log.info("Database backup successfully");
            operaterLogService.add("Database backup successfully, backup file：" + databaseBak);
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
        DatabaseBak databaseBak = find(id);
        if (databaseBak != null) {
            try {
                String filename = databaseBak.getFilename();
                File file = new File(databaseBak.getFilepath() + databaseBak.getFilename());
                String cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + backUpDir + filename;
                ;
                if (!file.exists()) {
                    cmd = "mysql -u" + dbUsername + " -p" + dbPwd + " " + dbName + " < " + databaseBak.getFilepath() + databaseBak.getFilename();
                }
                String stmt1 = "mysqladmin -u " + dbUsername + " -p" + dbPwd + " create " + dbName;
                String[] cmds = {"cmd", "/c", cmd};
                Runtime.getRuntime().exec(stmt1);
                Process exec = Runtime.getRuntime().exec(cmds);
                log.info(StringUtil.getStringFromInputStream(exec.getErrorStream()));
                log.info("Database restore successfully");
                operaterLogService.add("Database restore successfully, restore file：" + databaseBak);
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
        return databaseBakDao.count();
    }
}
