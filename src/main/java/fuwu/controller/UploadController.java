package fuwu.controller;


import fuwu.po.Project;
import fuwu.service.FtpService;
import fuwu.service.ProjectService;
import fuwu.service.impl.FtpServiceImpl;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

/**
 * Created by LJW on 2018/4/15 - 15:30
 */
public class UploadController {
//    @Autowired
//    private FtpService ftpService;

//    @Autowired
//    private ProjectService projectService;

//    @Value("${ftp.localPath}")
//    private String localPath;

//    @ResponseBody
//    @RequestMapping("/upload")
//    public OperationResult uploadFile(){
//
//        return  null;
//    }

    public static void main(String[] args) {
        UploadController  uploadController=new UploadController();
        uploadController.test();
    }

    public  void test() {
        String path="E:\\tu\\dwe.jpg";
        String fileName = "dwe.jpg";
        FtpServiceImpl ftpService=new FtpServiceImpl();

        try {
            FileInputStream in = new FileInputStream(new File(path));
            ftpService.uploadFile(fileName,in);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
