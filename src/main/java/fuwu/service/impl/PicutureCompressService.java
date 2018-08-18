package fuwu.service.impl;

import fuwu.controller.MediaController;
import fuwu.mapper.MediaMapper;
import fuwu.mapper.ViewMapper;
import fuwu.po.Media;
import fuwu.po.View;
import fuwu.service.FtpService;
import fuwu.util.FTPUtils;
import fuwu.util.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service
public class PicutureCompressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PicutureCompressService.class);

    @Value("${local_tmp_file_path}")
    String localFilePath;

    @Autowired
    MediaMapper mediaMapper;
    @Autowired
    ViewMapper viewMapper;
    @Autowired
    FtpService ftpService;

    public boolean compressAndUpdate(View view) throws Exception{

        Media media = mediaMapper.getMediaUrlByMediaId(view.getMediaId());
        String mediaUrl = media.getMediaUrl();
        String mediaName = media.getMediaName();
        File file=new File(localFilePath+mediaUrl);
        ftpService.downloadFtpFile(mediaUrl,new FileOutputStream(file));
        String miniName= ImageUtil.getMiniImage(file,localFilePath,300,200,false);
        InputStream inputStream=new FileInputStream(localFilePath+miniName);
        miniName=ftpService.uploadFile(miniName,inputStream);
        Media miniMedia = new Media(null,miniName,null,6,0);
        if(mediaMapper.addMedia(miniMedia)==1&&viewMapper.updateViewMiniId(miniMedia.getId(),view.getId())==1){
            view.setViewMiniId(miniMedia.getId());
            return true;
        }
//        Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
        return false;
    }
}

