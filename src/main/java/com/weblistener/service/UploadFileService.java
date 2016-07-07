package com.weblistener.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFileService {

    private final static String IMG_DESC_PATH =File.separator+"records"+File.separator;

    public String uploadFile(CommonsMultipartFile multipartFile, String path) throws IOException {
        String fileName = createUri(multipartFile, path);
        File file = new File(path+fileName);
        System.out.println(file.getAbsolutePath());
        try{
            multipartFile.transferTo(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileName;
    }
    /**
     * 为图片产生uri 返回给前端
     * @param file
     * @return
     */

    public static String createUri(MultipartFile file,String path){
//        String imgPath = request.getSession().getServletContext().getRealPath("")+IMG_DESC_PATH;
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));
        String newName = uuid()+extName;
        return newName;
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
