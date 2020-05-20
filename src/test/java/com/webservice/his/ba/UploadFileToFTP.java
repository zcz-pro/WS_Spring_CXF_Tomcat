package com.webservice.his.ba;

import com.webservice.his.ba.Utils.FtpClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by admin on 2020/4/14.
 */
public class UploadFileToFTP {
    @Test
    public void test01(){
        System.out.println("上传文件开始");
        String ip="172.20.209.193";
        String username="admin";
        String password="z";
//        FtpClient client=FtpClient.createFtpCli(ip,username,password);
        FtpClient client=new FtpClient();
        InputStream is=null;
        try {
            File file=new File("C:\\Users\\admin\\Desktop\\分拣机接口文档_苏州祺康.pdf");
            is=new FileInputStream(file);
            client.connect();
//            client.upload("test2.pdf",new File("C:\\Users\\admin\\Desktop\\分拣机接口文档_苏州祺康.pdf"));
            String random=String.format("%04d",new Random().nextInt(9999));
            String pdfName=System.currentTimeMillis()+random+".pdf";
            client.uploadFileToDailyDir(pdfName,is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("上传文件结束");
    }
}
