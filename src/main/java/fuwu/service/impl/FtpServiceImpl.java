package fuwu.service.impl;


import fuwu.service.FtpService;
import fuwu.task.DownloadTask;
import fuwu.task.UploadTask;
import fuwu.util.FTPUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.SocketException;
import java.util.concurrent.*;


/**
 * Created by LJW on 2018/4/13 - 23:10
 */
@Service
public class FtpServiceImpl implements FtpService {



    private static final Logger LOGGER = LoggerFactory.getLogger(FtpServiceImpl.class);

    private ExecutorService downloadExecutorService = new ThreadPoolExecutor(20,30,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    private ExecutorService uploadExecutorService = new ThreadPoolExecutor(20,30,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());


    @Value("${ftp.UseName}")
    private String userName;

    @Value("${ftp.PassWord}")
    private String passWord;

    @Value("${ftp.Host}")
    private String ftpHost;

    @Value("${ftp.Port}")
    private String  ftpPort;

    @Value("${ftp.BasePath}")
    private String basePath;



    public  void downloadFtpFile(String mediaUrl,OutputStream fos){

        Future<Boolean> result = downloadExecutorService.submit(new DownloadTask(basePath+mediaUrl,fos,userName,passWord,ftpHost,ftpPort));

        try {
            result.get(3,TimeUnit.MINUTES);
        } catch (Exception e) {
          LOGGER.error("任务下载失败{},{},",mediaUrl,fos,e);
        }
    }


    public  String uploadFile(String fileName,InputStream inputStream) throws InterruptedException, ExecutionException, TimeoutException {

        String suffix=fileName.substring(fileName.lastIndexOf("."));
        String realFileName= String.valueOf(System.currentTimeMillis())+String.valueOf(RandomUtils.nextLong())+suffix;
        Future<Boolean> result =uploadExecutorService.submit(new UploadTask(userName,passWord,ftpHost,ftpPort,basePath,realFileName,inputStream));

        result.get(3,TimeUnit.MINUTES);

        return realFileName;

    }
}




