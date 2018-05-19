package fuwu.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by LJW on 2018/4/13 - 23:07
 */
public interface FtpService {


    public void downloadFtpFile(String url, OutputStream fos);

    public String uploadFile(String mediaUrl, InputStream input) throws InterruptedException, ExecutionException, TimeoutException;
}
