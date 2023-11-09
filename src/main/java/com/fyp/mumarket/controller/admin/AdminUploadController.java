package com.fyp.mumarket.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fyp.mumarket.bean.CodeMsg;
import com.fyp.mumarket.bean.Result;
import com.fyp.mumarket.util.PathUtil;
import com.fyp.mumarket.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * public upload class
 *
 * @author Administrator
 */
@RequestMapping("/admin/upload")
@Controller
public class AdminUploadController {

    @Value("${fyp.upload.photo.sufix}")
    private String uploadPhotoSufix;

    @Value("${fyp.upload.photo.maxsize}")
    private long uploadPhotoMaxSize;

    //@Value("${fyp.upload.photo.path}")
    //private String uploadPhotoPath;//

    private Logger log = LoggerFactory.getLogger(AdminUploadController.class);

    /**
     * Unified image upload class
     *
     * @param photo
     * @return
     */
    @RequestMapping(value = "/upload_photo", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> uploadPhoto(@RequestParam(name = "photo", required = true) MultipartFile photo) {
        //determine if the file is an image type
        String originalFilename = photo.getOriginalFilename();
        //Retrieve file suffix
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        if (!uploadPhotoSufix.contains(suffix.toLowerCase())) {
            return Result.error(CodeMsg.UPLOAD_PHOTO_SUFFIX_ERROR);
        }
        if (photo.getSize() / 1024 > uploadPhotoMaxSize) {
            CodeMsg codeMsg = CodeMsg.UPLOAD_PHOTO_ERROR;
            codeMsg.setMsg("The image can't be more than " + (uploadPhotoMaxSize / 1024) + "M");
            return Result.error(codeMsg);
        }


        //Prepare to save file
        String uploadPhotoPath = PathUtil.newInstance().getUploadPhotoPath();
        File filePath = new File(uploadPhotoPath);
        if (!filePath.exists()) {
            //If file path doesn't exist, create it
            filePath.mkdir();
        }
        filePath = new File(uploadPhotoPath + "/" + StringUtil.getFormatterDate(new Date(), "yyyyMMdd"));
        //Check if the folder for the current date exists; if not, create it.
        if (!filePath.exists()) {
            //If file path doesn't exist, create it
            filePath.mkdir();
        }
        String filename = StringUtil.getFormatterDate(new Date(), "yyyyMMdd") + "/" + System.currentTimeMillis() + suffix;
        try {
            photo.transferTo(new File(uploadPhotoPath + "/" + filename));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("Image uploaded! saved path: " + uploadPhotoPath + filename);
        return Result.success(filename);
    }
}
