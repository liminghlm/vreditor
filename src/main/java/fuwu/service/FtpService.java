package fuwu.service;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by LJW on 2018/4/13 - 23:07
 */
public interface FtpService {


    public void downloadFtpFile(String url, OutputStream fos);

    public boolean uploadFile(String mediaUrl, InputStream input);
}
