package com.fyp.mumarket.util;


import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 */
public class PathUtil {
    public static PathUtil newInstance() {
        PathUtil instance = new PathUtil();
        return instance;
    }

    //Retrieve the upload path
    public String getUploadPhotoPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        //If the upload directory is located at "src/main/resources/upload/", you can obtain it as follows.
        File upload = new File(path.getAbsolutePath(), "src/main/resources/upload/");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String uploadPath = upload.getAbsolutePath();
        return uploadPath;
    }

    //Retrieve the backup path
    public String getBackUpDir() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        //If the upload directory is located at "src/main/resources/upload/", you can obtain it as follows.
        File backup = new File(path.getAbsolutePath(), "src/main/resources/backup/");
        if (!backup.exists()) {
            backup.mkdirs();
        }
        String backUpDir = backup.getAbsolutePath();
        return backUpDir;
    }

}
