package fuwu.task;

import fuwu.util.FTPUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

/**
 * @Author: ypc
 * @Date: 2018/5/16 22:48
 * @Description:
 */
public class UploadTask implements Callable<Boolean>{
    private  static final Logger LOGGER = LoggerFactory.getLogger(UploadTask.class);
    private String userName;
    private String passWord;
    private String ftpHost;
    private String  ftpPort;
    private String basePath;
    private String fileName;
    private InputStream inputStream;
    public UploadTask(String userName, String passWord, String ftpHost, String ftpPort, String basePath,String fileName ,InputStream inputStream){
        this.userName=userName;
        this.passWord=passWord;
        this.ftpHost=ftpHost;
        this.ftpPort=ftpPort;
        this.basePath=basePath;
        this.fileName=fileName;
        this.inputStream=inputStream;
    }

    @Override
    public Boolean call() throws Exception {
        boolean result = false;
        FTPClient ftpClient = FTPUtils.buildFTPClient(userName,passWord,ftpHost,ftpPort);
        try {
            int reply;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return true;
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(basePath);

            ftpClient.storeFile(fileName, inputStream);

            inputStream.close();
            ftpClient.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("上传出错");
        } finally {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
