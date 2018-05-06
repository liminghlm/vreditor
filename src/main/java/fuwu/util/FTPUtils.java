package fuwu.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketException;

public class FTPUtils {
    private  static final Logger LOGGER = LoggerFactory.getLogger(FTPUtils.class);
    public static FTPClient buildFTPClient(String userName, String passWord,String ftpHost,String  ftpPort){
        FTPClient ftpClient = new FTPClient();
        try {
            int port = Integer.parseInt(ftpPort);

            ftpClient.connect(ftpHost,port);
            ftpClient.login(userName,passWord);

            ftpClient.setControlEncoding("UTF-8"); // 中文支持


            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                LOGGER.error("未连接到FTP，用户名或密码错误。");

                ftpClient.disconnect();
            }else {
                LOGGER.info("FTP连接成功。");
            }
        }catch (SocketException e){
            LOGGER.error("FTP的IP地址可能错误，请正确配置。",e);
        }catch (IOException e){
            LOGGER.error("FFP的端口错误，请正确配置。",e);
        }
        return ftpClient;
    }
}
