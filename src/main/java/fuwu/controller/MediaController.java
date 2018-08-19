package fuwu.controller;

import fuwu.aop.ForLog;
import fuwu.commen.JsonResult;
import fuwu.em.GlobalErrorEnum;
import fuwu.po.Media;
import fuwu.service.FtpService;
import fuwu.service.MediaService;
import fuwu.service.ProjectService;
import fuwu.service.ViewService;
import fuwu.util.JsonResultUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by LJW on 2018/4/20 - 11:59
 */

@Controller
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ViewService viewService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private FtpService ftpService;

    @Value("${ftpSizeMax}")
    private Long ftpSizeMax ;

    private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);
    private DiskFileItemFactory factory = new DiskFileItemFactory();
    @PostConstruct
    public void init(){
        factory.setSizeThreshold(4096);
    }
    /**
     * 本接口实现 根据项目id返回主场景基本信息
     *
     * @param response
     * @param
     * @return
     */

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/getMediaById", method = RequestMethod.GET)
    public void getMediaById(HttpServletResponse response, Integer mediaId) {
        Media media = mediaService.getMediaUrlByMediaId(mediaId);

        response.reset();
        response.setContentType("bin");

        response.setContentType("charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.addHeader("Content-Disposition", "inline;filename=" + media.getMediaUrl());
        OutputStream fos = null;
        try {
            fos = response.getOutputStream();
            ftpService.downloadFtpFile(media.getMediaUrl(), fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }


    }

    @ResponseBody
    @ForLog
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public JsonResult createMedia(HttpServletRequest request, Integer mediaType) {

        try {
             List<FileItem> items = getFileListWithRequest(request);
            for(FileItem fileItem : items) {
            if(invalid(fileItem))
                continue;
            String fileName=fileItem.getName();
            InputStream inputStream = fileItem.getInputStream();
            String realFileName = ftpService.uploadFile(fileName, inputStream);
            Media media = new Media(null,realFileName,fileName,mediaType,0);
            int successCount = mediaService.addMedia(media);

            return successCount>0 ? JsonResultUtil.createSucess(mediaService.getMediaUrlByMediaId(media.getId()))
                    :JsonResultUtil.createError(GlobalErrorEnum.ERROR);
        }
        } catch (Exception e) {
            LOGGER.error("上传任务失败：",e);
        }
        return JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }
    @ResponseBody
    @ForLog
    @RequestMapping(value = "/createText",method = RequestMethod.POST)
    public JsonResult createText(String mediaText){
        Media media=new Media(null,mediaText,null,1,0);
        int successCount = mediaService.addMedia(media);
        return successCount>0 ? JsonResultUtil.createSucess(mediaService.getMediaUrlByMediaId(media.getId()))
                :JsonResultUtil.createError(GlobalErrorEnum.ERROR);
    }


    private boolean invalid(FileItem fileItem) {
        return fileItem.getName()==null||"".equals(fileItem.getName());
    }

    private List<FileItem> getFileListWithRequest(HttpServletRequest request) throws FileUploadException {
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(ftpSizeMax);
        List<FileItem> items = upload.parseRequest(request);
        return items;
    }


}

