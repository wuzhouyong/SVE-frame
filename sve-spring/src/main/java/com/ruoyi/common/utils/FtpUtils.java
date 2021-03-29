package com.ruoyi.common.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FtpUtils {

//    private String host = "172.31.0.9";
    private String host = "127.0.0.1";
    private int port = 21;
    private String username = "testftp";
    private String password = "abc123...";

    public FtpUtils(String host, String port, String username, String password) {
        this.host = host;
        this.port = Integer.parseInt(port);
        this.username = username;
        this.password = password;
    }

    public FtpUtils() {

    }

    public FTPClient ftpLogin() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(host, port);
            boolean login = ftpClient.login(username, password);
            if (login) {
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();//设置成被动FTP模式
                ftpClient.setRemoteVerificationEnabled(false);
                System.out.println(">>>>>>>>FTP登录成功>>>>>>>>>>>>>");
            } else {
                System.out.println(">>>>>>>>FTP登录失败>>>>>>>>>>>>>");
            }
            return ftpClient;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] downloadFile(String remoteFullPath) {
        FTPClient ftpClient = ftpLogin();
        try {
            //切换文件路径//切换文件路径
            String remotePath = remoteFullPath.substring(0, remoteFullPath.lastIndexOf("/"));
            String fileName = remoteFullPath.substring(remoteFullPath.lastIndexOf("/") + 1);
            boolean isChange = ftpClient.changeWorkingDirectory(remotePath);

            InputStream inputStream = ftpClient.retrieveFileStream(new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));

            if (inputStream != null) {
                String patch = remotePath + "/" + fileName;
                System.out.println(">>>>>>>>FTP文件下载成功！" + patch);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                inputStream.close();
                out.flush();
                return out.toByteArray();
            } else {
                throw new RuntimeException(">>>>>>>FTP文件下载失败！请检查！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.completePendingCommand();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }

    public void downloadFile(String showName,String remoteFullPath, HttpServletResponse response) {
        FTPClient ftpClient = ftpLogin();
        InputStream inputStream = null;
        try {
            //切换文件路径//切换文件路径
            String remotePath = remoteFullPath.substring(0, remoteFullPath.lastIndexOf("/"));
            String fileName = remoteFullPath.substring(remoteFullPath.lastIndexOf("/") + 1);
            boolean isChange = ftpClient.changeWorkingDirectory(remotePath);

            inputStream = ftpClient.retrieveFileStream(new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            String patch = remotePath + "/" + fileName;
            System.out.println(">>>>>>>>FTP文件下载成功！" + patch);

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + URLEncoder.encode(showName, "UTF-8"));

            OutputStream out = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            inputStream.close();
            out.write(buf);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.completePendingCommand();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FTPClient ftpClient = ftpLogin();
        FileInputStream inputStream = null;
        try {
            //切换文件路径
            ftpClient.makeDirectory(remotePath);
            ftpClient.changeWorkingDirectory(remotePath);
            inputStream = new FileInputStream(new File(localPath + localFileName));
            //可上传多文件
            boolean isUpload = ftpClient.storeFile(remoteFileName, inputStream);

            if (isUpload) {
                System.out.println(">>>>>>>>FTP文件上传成功!");
            } else {
                System.out.println(">>>>>>>>FTP文件上传失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String uploadFile(String fileBase, String remotePath, String fileName) {
        String patch = "";
        FTPClient ftpClient = ftpLogin();
        try {
            //byte[] bytes = new BASE64Decoder().decodeBuffer(fileBase);
            Base64.Decoder dec = Base64.getDecoder();
            byte[] bytes = dec.decode(fileBase);
            InputStream inputStream = new ByteArrayInputStream(bytes);


            //切换文件路径
            ftpClient.makeDirectory(remotePath);
            ftpClient.changeWorkingDirectory(remotePath);

            //可上传多文件
            boolean isUpload = ftpClient.storeFile(new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), inputStream);
            inputStream.close();
            if (isUpload) {
                patch = remotePath + "/" + fileName;
                System.out.println(">>>>>>>>FTP文件上传成功!" + patch);
            } else {
                System.out.println(">>>>>>>>FTP文件上传失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return patch;
    }

    /**
     * 检测后缀是否有效
     *
     * @param fileName
     * @return
     */
    public boolean checkSuffix(String fileName) {
        int lpoint = fileName.lastIndexOf(".");
        if (lpoint == -1 || lpoint == 0)
            return false;

        String ex = fileName.substring(lpoint).toLowerCase();
        switch (ex) {
            case ".json":
                return false;
            default:
                return true;
        }

    }
}
