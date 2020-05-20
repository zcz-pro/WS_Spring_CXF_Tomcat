package com.webservice.his.ba.Utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.SocketException;

/**
 * FTP传输工具
 *
 * @Title: FtpUtils.java
 * @Package com.center.utils
 * @Description: TODO
 * @author Autumn、
 * @date 2018年8月20日
 */
//@Component
public class FtpUtils {

    /**
     * 删除成功标志
     */
    public static final int DELETE_SUCCEED = 250;

    /**
     * FTP服务器IP
     */
    private static String defaultIp;

    /**
     * FTP服务器端口号
     */
    private static Integer defaultPort;

    /**
     * FTP服务器用户名
     */
    private static String defaultAccount;

    /**
     * 默认保存路径
     */
    private static String defaultWorkingDir;

    /**
     * FTP服务器密码
     */
    private static String defaultPassword;

    /**
     * @return the defaultIp
     */
    public static String getDefaultIp() {
        return defaultIp;
    }

    /**
     * @param defaultIp
     *            the defaultIp to set
     */
    @Value("${ftp.default.ip}")
    public void setDefaultIp(String defaultIp) {
        FtpUtils.defaultIp = defaultIp;
    }

    /**
     * @return the defaultPort
     */
    public static Integer getDefaultPort() {
        return defaultPort;
    }

    /**
     * @param defaultPort
     *            the defaultPort to set
     */
    @Value("${ftp.default.port}")
    public void setDefaultPort(Integer defaultPort) {
        FtpUtils.defaultPort = defaultPort;
    }

    /**
     * @return the defaultAccount
     */
    public static String getDefaultAccount() {
        return defaultAccount;
    }

    /**
     * @param defaultAccount
     *            the defaultAccount to set
     */
    @Value("${ftp.default.account}")
    public void setDefaultAccount(String defaultAccount) {
        FtpUtils.defaultAccount = defaultAccount;
    }

    /**
     * @return the defaultPassword
     */
    public static String getDefaultPassword() {
        return defaultPassword;
    }

    /**
     * @param defaultPassword
     *            the defaultPassword to set
     */
    @Value("${ftp.default.password}")
    public void setDefaultPassword(String defaultPassword) {
        FtpUtils.defaultPassword = defaultPassword;
    }

    /**
     * @return the defaultWorkingDir
     */
    public static String getDefaultWorkingDir() {
        return defaultWorkingDir;
    }

    /**
     * @param defaultWorkingDir
     *            the defaultWorkingDir to set
     */
    @Value("${ftp.default.workingDir}")
    public void setDefaultWorkingDir(String defaultWorkingDir) {
        FtpUtils.defaultWorkingDir = defaultWorkingDir;
    }

    /**
     * 初始化配置
     *
     * @param defaultIp
     * @param defaultPort
     * @param defaultAccount
     * @param defaultPassword
     * @param defaultWorkingDir
     */
    public static void initDefaultConfig(String defaultIp, Integer defaultPort, String defaultAccount,
                                         String defaultPassword, String defaultWorkingDir) {
        FtpUtils.defaultIp = defaultIp;
        FtpUtils.defaultPort = defaultPort;
        FtpUtils.defaultAccount = defaultAccount;
        FtpUtils.defaultPassword = defaultPassword;
        FtpUtils.defaultWorkingDir = defaultWorkingDir;
    }

    /**
     * 创建目录
     *
     * @param workingDir
     *            要创建的目录
     * @return 创建结果
     * @throws IOException
     */
    public static Boolean createDirecroty(String workingDir) throws IOException {
        return FtpUtils.createDirecroty(FtpUtils.createFTPClient(), workingDir);
    }

    /**
     * 自定义ftp服务器创建目录
     *
     * @param workingDir
     *            要创建的目录
     * @return 创建结果
     * @throws IOException
     */
    public static Boolean createDirecroty(String ip, Integer port, String account, String password, String workingDir)
            throws IOException {
        return FtpUtils.createDirecroty(FtpUtils.createFTPClient(ip, port, account, password), workingDir);
    }

    /**
     * 自定义ftp客户端创建目录
     *
     * @param workingDir
     *            要创建的目录
     * @return 创建结果
     * @throws IOException
     */
    public static Boolean createDirecroty(FTPClient ftpClient, String workingDir) throws IOException {
        String[] dirs = workingDir.split("/");
        for (int i = 0; i < dirs.length; i++) {
            String dir = dirs[i];
            if (dir.indexOf(".") > -1) {
                if (i != dirs.length - 1) {
                    return false;
                }
                break;
            }
            if (!existFile(ftpClient, FtpUtils.chineseNameConversion(dir))) {
                ftpClient.makeDirectory(dir);
            }
            ftpClient.changeWorkingDirectory(dir);
        }
        return true;
    }

    /**
     * 判断ftp服务器文件/文件夹是否存在
     *
     * @param path
     *            路径或文件全路径
     * @return 存在返回true，否则返回false
     * @throws IOException
     */
    public static Boolean existFile(FTPClient ftpClient, String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * 使用默认配置上传文件并使用原文件名
     *
     * @param file
     *            文件
     * @param workingDir
     *            服务器存储的绝对路径
     * @return 上传结果
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Boolean uploadFile(File file, String workingDir) throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(file, workingDir, file.getName());
    }

    /**
     * 使用自定义ftp客户端上传文件并使用原文件名
     *
     * @param ftpClient
     *            ftp客户端
     * @param file
     *            文件
     * @param workingDir
     *            服务器存储的绝对路径
     * @return 上传结果
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Boolean uploadFile(FTPClient ftpClient, File file, String workingDir)
            throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(ftpClient, file, workingDir, file.getName());
    }

    /**
     * 使用默认配置上传文件
     *
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 上传结果
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Boolean uploadFile(File file, String workingDir, String fileName)
            throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(defaultIp, defaultPort, defaultAccount, defaultPassword, file, workingDir, fileName);
    }

    /**
     * 使用自定义ftp客户端上传文件
     *
     * @param ftpClient
     *            ftp客户端
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 上传结果
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Boolean uploadFile(FTPClient ftpClient, File file, String workingDir, String fileName)
            throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(ftpClient, new FileInputStream(file), workingDir, fileName);
    }

    /**
     * 使用默认配置上传文件
     *
     * @param inputStream
     *            文件流
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp服务器文件名
     * @return 上传结果
     * @throws IOException
     */
    public static Boolean uploadFile(InputStream inputStream, String workingDir, String fileName) throws IOException {
        return uploadFile(defaultIp, defaultPort, defaultAccount, defaultPassword, inputStream, workingDir, fileName);
    }

    /**
     * ftp 上传方法
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @return 上传结果
     * @throws IOException
     * @throws FileNotFoundException
     * @throws Exception
     *
     */
    public static Boolean uploadFile(String ip, Integer port, String account, String password, File file,
                                     String workingDir) throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(ip, port, account, password, new FileInputStream(file), workingDir, file.getName());
    }

    /**
     * ftp 上传方法
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 上传结果
     * @throws IOException
     * @throws FileNotFoundException
     * @throws Exception
     *
     */
    public static Boolean uploadFile(String ip, Integer port, String account, String password, File file,
                                     String workingDir, String fileName) throws FileNotFoundException, IOException {
        return FtpUtils.uploadFile(ip, port, account, password, new FileInputStream(file), workingDir, fileName);
    }

    /**
     * ftp 上传方法
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param inputStream
     *            文件流
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 上传结果
     * @throws IOException
     *
     */
    public static Boolean uploadFile(String ip, Integer port, String account, String password, InputStream inputStream,
                                     String workingDir, String fileName) throws IOException {
        FTPClient ftpClient = createFTPClient(ip, port, account, password);
        return FtpUtils.uploadFile(ftpClient, inputStream, workingDir, fileName);
    }

    /**
     * 使用自定义ftp客户端上传文件
     *
     * @param ftpClient
     *            ftp客户端
     * @param inputStream
     *            文件流
     * @param workingDir
     *            服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 上传结果
     * @throws IOException
     */
    public static Boolean uploadFile(FTPClient ftpClient, InputStream inputStream, String workingDir, String fileName)
            throws IOException {
        boolean result = false;
        if (!FtpUtils.existFile(ftpClient, workingDir)) {
            FtpUtils.createDirecroty(ftpClient, workingDir);
        }
        ftpClient.changeWorkingDirectory(workingDir);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        if (!ftpClient.storeFile(chineseNameConversion(fileName), inputStream)) {
            return result;
        }
        inputStream.close();
        ftpClient.logout();
        result = true;
        return result;
    }

    /**
     * 使用默认配置下载文件并使用原文件名
     *
     * @param file
     *            文件
     * @param workingDir
     *            服务器存储的绝对路径
     * @return 下载结果
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Boolean downloadFile(File file, String workingDir) throws FileNotFoundException, IOException {
        return FtpUtils.downloadFile(file, workingDir, file.getName());
    }

    /**
     * 使用自定义Ftp客户端下载文件并使用原文件名
     *
     * @param ftpClient
     *            ftp客户端
     * @param file
     *            文件
     * @param workingDir
     *            服务器存储的绝对路径
     * @return 下载结果
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Boolean downloadFile(FTPClient ftpClient, File file, String workingDir)
            throws FileNotFoundException, IOException {
        return FtpUtils.downloadFile(ftpClient, file, workingDir, file.getName());
    }

    /**
     * 使用默认配置下载文件
     *
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 下载结果
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Boolean downloadFile(File file, String workingDir, String fileName)
            throws FileNotFoundException, IOException {
        return FtpUtils.downloadFile(defaultIp, defaultPort, defaultAccount, defaultPassword, file, workingDir,
                fileName);
    }

    /**
     * 使用自定义客户端下载文件
     *
     * @param ftpClient
     *            自定义ftp客户端
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return 下载结果
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Boolean downloadFile(FTPClient ftpClient, File file, String workingDir, String fileName)
            throws FileNotFoundException, IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        boolean result = FtpUtils.downloadFile(ftpClient, new FileOutputStream(file), workingDir, fileName);
        if (!result) {
            file.delete();
        }
        return result;
    }

    /**
     * 使用默认配置上传文件
     *
     * @param
     *
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return
     * @throws IOException
     */
    public static Boolean downloadFile(OutputStream outputStream, String workingDir, String fileName)
            throws IOException {
        return downloadFile(defaultIp, defaultPort, defaultAccount, defaultPassword, outputStream, workingDir,
                fileName);
    }

    /**
     * ftp 下载
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @throws IOException
     * @throws FileNotFoundException
     * @throws Exception
     *
     */
    public static Boolean downloadFile(String ip, Integer port, String account, String password, File file,
                                       String workingDir) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        boolean result = FtpUtils.downloadFile(ip, port, account, password, new FileOutputStream(file), workingDir,
                file.getName());
        if (!result) {
            file.delete();
        }
        return result;
    }

    /**
     * ftp 下载方法
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param file
     *            文件
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @throws IOException
     * @throws FileNotFoundException
     * @throws Exception
     *
     */
    public static Boolean downloadFile(String ip, Integer port, String account, String password, File file,
                                       String workingDir, String fileName) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        boolean result = FtpUtils.downloadFile(ip, port, account, password, new FileOutputStream(file), workingDir,
                fileName);
        if (!result) {
            file.delete();
        }
        return result;
    }

    /**
     * ftp 下载方法
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param outputStream
     *            文件流
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @throws SocketException
     * @throws IOException
     *
     */
    public static Boolean downloadFile(String ip, Integer port, String account, String password,
                                       OutputStream outputStream, String workingDir, String fileName) throws SocketException, IOException {
        FTPClient ftpClient = createFTPClient(ip, port, account, password);
        return downloadFile(ftpClient, outputStream, workingDir, fileName);
    }

    /**
     * 使用自定义客户端上传文件
     *
     * @param ftpClient
     *            自定义客户端
     * @param
     *
     * @param workingDir
     *            ftp 服务器存储图片的绝对路径
     * @param fileName
     *            上传到ftp 服务器文件名
     * @return
     * @throws IOException
     */
    public static Boolean downloadFile(FTPClient ftpClient, OutputStream outputStream, String workingDir,
                                       String fileName) throws IOException {
        boolean result = false;
        if (!FtpUtils.existFile(ftpClient, workingDir)) {
            FtpUtils.createDirecroty(ftpClient, workingDir);
        }
        ftpClient.changeWorkingDirectory(workingDir);
        FTPFile[] ftpFiles = ftpClient.listFiles();
        for (FTPFile file : ftpFiles) {
            if (file.getName().equalsIgnoreCase(FtpUtils.chineseNameConversion(fileName))) {
                ftpClient.retrieveFile(file.getName(), outputStream);
                outputStream.close();
                result = true;
            }
        }
        ftpClient.logout();
        return result;
    }

    /**
     * 自定义客户端使用默认配置的工作路径删除文件
     *
     * @param ftpClient
     *            自定义ftp客户端
     * @param fileName
     *            文件名称
     * @return 删除结果
     * @throws SocketException
     * @throws IOException
     */
    public static Boolean deleteFile(FTPClient ftpClient, String fileName) throws SocketException, IOException {
        return deleteFile(ftpClient, defaultWorkingDir, fileName);
    }

    /**
     * 使用默认配置删除文件
     *
     * @param fileName
     *            文件名称
     * @return 删除结果
     * @throws SocketException
     * @throws IOException
     */
    public static Boolean deleteFile(String fileName) throws SocketException, IOException {
        return deleteFile(defaultIp, defaultPort, defaultAccount, defaultPassword, defaultWorkingDir, fileName);
    }

    /**
     * 删除文件
     *
     * @param workingDir
     *            文件所在路径
     * @param fileName
     *            文件名称
     * @return 删除结果
     * @throws SocketException
     * @throws IOException
     */
    public static Boolean deleteFile(String workingDir, String fileName) throws SocketException, IOException {
        return deleteFile(defaultIp, defaultPort, defaultAccount, defaultPassword, workingDir, fileName);
    }

    /**
     * 删除文件
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param workingDir
     *            文件所在路径
     * @param fileName
     *            文件名称
     * @return 删除结果
     * @throws SocketException
     * @throws IOException
     */
    public static Boolean deleteFile(String ip, Integer port, String account, String password, String workingDir,
                                     String fileName) throws SocketException, IOException {
        return deleteFile(createFTPClient(ip, port, account, password), workingDir, fileName);
    }

    /**
     * 删除文件
     *
     * @param ftpClient
     *            自定义ftp客户端
     * @param workingDir
     *            文件所在路径
     * @param fileName
     *            文件名称
     * @return 删除结果
     * @throws IOException
     */
    public static Boolean deleteFile(FTPClient ftpClient, String workingDir, String fileName) throws IOException {
        boolean result = false;
        ftpClient.changeWorkingDirectory(workingDir);
        int flag = ftpClient.dele(FtpUtils.chineseNameConversion(fileName));
        if (flag == DELETE_SUCCEED) {
            result = true;
        }
        return result;
    }

    /**
     * 中文名称转换，处理FTP中文乱码问题
     *
     * @param name
     *            待转换的中文名称
     * @return 转换后的中文名
     * @throws UnsupportedEncodingException
     */
    public static String chineseNameConversion(String name) throws UnsupportedEncodingException {
        if (name == null) {
            return null;
        }
        return new String(name.getBytes("GBK"), "ISO8859-1");
    }

    /**
     * 使用默认配置创建FTPClient
     *
     * @return FTPClient
     * @throws IOException
     * @throws SocketException
     */
    public static FTPClient createFTPClient() throws SocketException, IOException {
        return FtpUtils.createFTPClient(defaultIp, defaultPort, defaultAccount, defaultPassword, defaultWorkingDir);
    }

    /**
     * 使用默认配置创建FTPClient
     *
     * @param workingDir
     *            服务器存储的绝对路径
     * @return FTPClient
     * @throws SocketException
     * @throws IOException
     */
    public static FTPClient createFTPClient(String workingDir) throws SocketException, IOException {
        return FtpUtils.createFTPClient(defaultIp, defaultPort, defaultAccount, defaultPassword, workingDir);
    }

    /**
     * 创建FTPClient
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @return FTPClient
     * @throws IOException
     * @throws SocketException
     */
    public static FTPClient createFTPClient(String ip, Integer port, String account, String password)
            throws SocketException, IOException {
        return FtpUtils.createFTPClient(ip, port, account, password, null);
    }

    /**
     * 创建FTPClient
     *
     * @param ip
     *            ftp 服务器ip地址
     * @param port
     *            ftp 服务器port，默认是21
     * @param account
     *            ftp 服务器用户名
     * @param password
     *            ftp 服务器密码
     * @param workingDir
     *            ftp 服务器存储的绝对路径
     * @return FTPClient
     * @throws IOException
     * @throws SocketException
     */
    public static FTPClient createFTPClient(String ip, Integer port, String account, String password, String workingDir)
            throws SocketException, IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ip, port == null ? 21 : port);
        ftpClient.login(account, password);
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect(); // 如果返回状态不再 200 ~ 300 则认为连接失败
            throw new RuntimeException("FTP链接失败");
        }
        if (workingDir != null && !"".equals(workingDir)) {
            ftpClient.changeWorkingDirectory(workingDir);
        }
        return ftpClient;
    }

}