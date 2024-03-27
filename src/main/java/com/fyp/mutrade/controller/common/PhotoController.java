package com.fyp.mutrade.controller.common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fyp.mutrade.util.PathUtil;
//Image viewing controller
@RequestMapping("photo")
@Controller
public class PhotoController {

    @Autowired
    private ResourceLoader resourceLoader;
    //Universal image viewer method
    @RequestMapping(value = "/view")
    @ResponseBody
    public ResponseEntity<?> viewPhoto(@RequestParam(name = "filename", required = true) String filename) {
        String uploadPhotoPath = PathUtil.newInstance().getUploadPhotoPath();
        Resource resource = resourceLoader.getResource("file:" + uploadPhotoPath + "/" + filename);
        try {
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
