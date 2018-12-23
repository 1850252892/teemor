package com.zlk.blog.controller;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Upload;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.zlk.blog.entity.Picture;
import com.zlk.blog.model.UploadImg;
import com.zlk.blog.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

    @RequestMapping("/test/upload")
    @ResponseBody
    public UploadImg setImgUrl(@RequestParam("imgFile") MultipartFile file, HttpServletResponse response)
            throws Exception {
        // Get the file and save it somewhere
        byte[] bytes = file.getBytes();
//        System.out.println(new String(bytes));
        String path = "F:\\IMG\\";
        File imgFile = new File(path);
        if (!imgFile.exists()) {
            imgFile.mkdirs();
        }
        String fileName = file.getOriginalFilename();// 文件名称
        System.out.println(path + fileName);

        try (FileOutputStream fos = new FileOutputStream(new File(path + fileName))) {
            int len = 0;
            fos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = "http://localhost/" + fileName;
        String[] values = { value };

        UploadImg imgInfo = new UploadImg();
        imgInfo.setErrno(0);
        imgInfo.setData(values);

        System.out.println(imgInfo.toString());
        return imgInfo;
    }

    private static String accessKey = "YBOHrX9Lcvk73pqUqVIRNybKj4eE4mwBiuQ20VOs";
    private static String secretKey = "GLO5j0STcLseat2DNI0V1-gzlzLxkZZvJjMU4yAQ";
    private static String bucket = "photo";

    /**
     *
     * @Description: 上传图片到七牛云
     *
     *
     * @return 图片的链接地址
     *
     * @version: v1.0.0
     * @author: ZLK
     * @date: 2018/6/28 14:39
     *
     * Modification History:
     * Date         Author          Version            Description
     *---------------------------------------------------------*
     * 2018/6/28      zhoulk          v1.0.0             新建
     */
    @RequestMapping(value = "/uploadImgToQn", method = RequestMethod.POST)
    public @ResponseBody
    UploadImg uploadImgToQi(MultipartHttpServletRequest request) {
        MultipartFile img= request.getFile("imgFile");
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String key= UUID.randomUUID().toString();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
//        File file=new File("/","img");
//        String s=file.getPath();
        String name="";
        try {
//            img.transferTo(file);
            Response response =   uploadManager.put(img.getBytes(),key,upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            name=putRet.key;
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] values = { "http://www.teemor.xyz/"+name };
        UploadImg imgInfo = new UploadImg();
        imgInfo.setErrno(0);
        imgInfo.setData(values);

        return imgInfo;
    }

    UploadImg upToTcent(MultipartFile multipartFile){
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials("AKID8JQMBxpBdkhVGyK2772b1HzoeS5avITb", "8xcmEWbaOH2yCbpstkXntMfulcK3g9lI");
// 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region("ap-chengdu"));
// 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = " my-1253853479";

        String key = null;
        PutObjectRequest putObjectRequest = null;
        try {
            File localFile=null;
            localFile=File.createTempFile("temp",null);
            multipartFile.transferTo(localFile);
             key = UUID.randomUUID().toString();
            putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 本地文件上传


        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        String[] values = { "http://panmiwrml.bkt.clouddn.com/"+key };
        UploadImg imgInfo = new UploadImg();
        imgInfo.setErrno(0);
        imgInfo.setData(values);

        return imgInfo;
    };
}
