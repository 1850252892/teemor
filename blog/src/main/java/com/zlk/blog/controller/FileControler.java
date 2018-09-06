package com.zlk.blog.controller;


import com.alibaba.fastjson.JSON;
import com.zlk.blog.entity.Picture;
import com.zlk.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FileControler {

    @Autowired
    PictureService pictureService;

    @PostMapping(value = "/api/picture")
    public String apiUploadPicture(@RequestBody Picture picture){
        int i=pictureService.insert(picture);
        if (i>0){
            return "T";
        }else {
            return "F";
        }
    }

    @GetMapping(value = "/api/picture")
    public String apiGetPicture(String username){
        List<Picture> list=pictureService.select(username);
        return JSON.toJSONString(list);
    }


    @DeleteMapping(value = "/api/picture")
    public String apiDeletePicture(@RequestBody String bid){
            int i=pictureService.delete(bid);
        if (i>0){
            return "T";
        }else {
            return "F";
        }
    }
}
