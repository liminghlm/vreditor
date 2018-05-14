package fuwu.controller;

import fuwu.commen.JsonResult;
import fuwu.po.Media;
import fuwu.service.FtpService;
import fuwu.service.MediaService;
import fuwu.service.ProjectService;
import fuwu.service.ViewService;
import fuwu.util.JsonResultUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

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


    private static final Logger LOGGER = LoggerFactory.getLogger(MediaController.class);

    /**
     * 本接口实现 根据项目id返回主场景基本信息
     * @param response
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMediaById", method = RequestMethod.GET)
    public void getMediaById(HttpServletResponse response, Integer mediaId) {
        LOGGER.info("this is test log");
        Media media=mediaService.getMediaUrlByMediaId(mediaId);

        response.reset();
        response.setContentType("bin");

        response.setContentType("charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.addHeader("Content-Disposition","inline;filename=" + media.getMediaUrl());
        OutputStream fos = null;
        try {
            fos=response.getOutputStream();
            ftpService.downloadFtpFile(media.getMediaUrl(),fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }


    }

}