package com.yoosal.zqmh.control;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by ibm on 14-1-15.
 */
public class FileUtil {
    public static String uploadFile(HttpServletRequest request, MultipartFile img, String navTabId) {
        // 文件保存目录路径
        String savePath = getSavePath(request);
        String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // 定义允许上传的文件扩展名
        HashMap<String, String> picMap = new HashMap<String, String>();
        picMap.put("gif", "gif");
        picMap.put("jpg", "jpg");
        picMap.put("jpeg", "jpeg");
        picMap.put("png", "png");
        picMap.put("bmp", "bmp");

        HashMap<String, String> videoMap = new HashMap<String, String>();
        videoMap.put("wav", "wav");
        videoMap.put("mp4", "mp4");
        videoMap.put("rmvb", "rmvb");
        videoMap.put("wmv", "wmv");

        // 创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        if (img.isEmpty()) {
            return MessageUtil.errorMessage("上传文件为空！", navTabId, MessageUtil.CallBackType.refreshCurrent).toString();
        } else {
            String fileName = img.getOriginalFilename();
            // 检查扩展名
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!picMap.containsKey(fileExt) && !videoMap.containsKey(fileExt)) {
                return MessageUtil.errorMessage("上传文件扩展名是不允许的扩展名。", navTabId, MessageUtil.CallBackType.refreshCurrent).toString();
            }

            long fileSize = img.getSize();
            // 检查文件大小
            if ((picMap.containsKey(fileExt) && fileSize > 4000000) || (videoMap.containsKey(fileExt) && fileSize > 100000000)) {
                return MessageUtil.errorMessage("上传文件大小超过限制。", navTabId, MessageUtil.CallBackType.refreshCurrent).toString();
            }


            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

            //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
            //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
            File file = new File(savePath + newFileName);
            try {
                FileUtils.copyInputStreamToFile(img.getInputStream(), file);
            } catch (IOException e) {
                return MessageUtil.errorMessage("上传失败！", navTabId, MessageUtil.CallBackType.refreshCurrent).toString();
            }
            return "/upload/" + ymd + "/" + newFileName;
        }
    }

    public static String getSavePath(HttpServletRequest request) {
        // 文件保存目录路径
        String savePath = request.getRealPath("//") + "//upload//";
        // 创建文件夹
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        savePath += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath;
    }
}
