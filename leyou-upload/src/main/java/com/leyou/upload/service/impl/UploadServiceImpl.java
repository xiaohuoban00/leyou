package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/gif","image/jpeg","image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            //判断文件类型
            String contentType = multipartFile.getContentType();
            if(!CONTENT_TYPE.contains(contentType)) {
                LOGGER.info("文件类型：" + originalFilename + "不合法");
                return null;
            }
            //判断文件内容
            BufferedImage buffer = ImageIO.read(multipartFile.getInputStream());
            if(buffer==null){
                LOGGER.info("文件内容：" + originalFilename + "不合法");
            }
            //上传
            //multipartFile.transferTo(new File("E:\\leyou-image"+originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), ext, null);
            return "http://image.leyou.com/"+storePath.getFullPath();
            //return "http://image.leyou.com/"+originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误"+originalFilename);
            e.printStackTrace();
        }
        return null;
    }
}
