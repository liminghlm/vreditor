package fuwu.task;

import fuwu.util.FTPUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.Callable;

public class DownloadTask implements Callable<Boolean> {

    private  static final Logger LOGGER = LoggerFactory.getLogger(DownloadTask.class);

    private String mediaUrl;
    private OutputStream fos;
    private String userName;
    private String passWord;
    private String ftpHost;
    private String  ftpPort;


    public DownloadTask(String mediaUrl, OutputStream fos,String userName, String passWord,String ftpHost,String  ftpPort){
        this.fos=fos;
        this.mediaUrl=mediaUrl;
        this.userName = userName;
        this.passWord=passWord;
        this.ftpHost=ftpHost;
        this.ftpPort=ftpPort;
    }

    @Override
    public Boolean call() throws Exception {
        boolean result = false;
        FTPClient ftpClient = FTPUtils.buildFTPClient(userName,passWord,ftpHost,ftpPort);

        InputStream inputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {

            // 进入FTP服务器工作目录
            ftpClient.enterLocalPassiveMode();
            inputStream = ftpClient.retrieveFileStream(mediaUrl);

            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(fos);
            int bytesRead = 0;
            byte[] b = new byte[10 * 1024];
            while ((bytesRead = bis.read(b)) != -1){
                bos.write(b, 0, bytesRead);
            }
            bos.flush();
            result = true ;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(bis);
                ftpClient.completePendingCommand();
                IOUtils.closeQuietly(bos);
                ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}