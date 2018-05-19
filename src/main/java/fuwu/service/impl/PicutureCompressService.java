package fuwu.service.impl;

import fuwu.controller.MediaController;
import fuwu.mapper.MediaMapper;
import fuwu.po.Media;
import fuwu.po.View;
import fuwu.service.FtpService;
import net.coobird.thumbnailator.Thumbnails;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PicutureCompressService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PicutureCompressService.class);

    @Value("local_tmp_file_path")
    String localFilePath;

    @Autowired
    MediaMapper mediaMapper;
    @Autowired
    FtpService ftpService;

    public boolean compressAndUpdate(View view) throws FileNotFoundException {

        Media media = mediaMapper.getMediaUrlByMediaId(view.getMediaId());

        String mediaUrl = media.getMediaUrl();
        String mediaName = media.getMediaName();


        ftpService.downloadFtpFile(mediaUrl,new FileOutputStream(new File(localFilePath+mediaName)));

//        Thumbnails.of(file.getInputStream()).size(WIDTH, HEIGHT).toFile(des);
        return false;
    }
}
