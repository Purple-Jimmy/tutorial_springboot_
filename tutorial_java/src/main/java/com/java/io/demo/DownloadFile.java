package com.java.io.demo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 下载网络文件
 * io缺点:所有的缓存字节都会直接存储在内存中
 * nio:数据可以直接移动到文件系统而不需要复制任何字节到程序的内存栈中
 * @Author: jimmy
 * @Date: 2018/12/15
 */
public class DownloadFile {

    public static void main(String[] args) throws IOException {
        DownloadFile downloadFile = new DownloadFile();
        String url = "https://www.baidu.com/";
        String savePath = "F:\\tutorial_space\\tutorial_springboot_\\tutorial_java\\io_demo\\";
       // downloadFile.downloadForJDK6(url,savePath);
       // downloadFile.downloadForJDK7(url,savePath+"io_jdk6.txt");
       // downloadFile.downloadForNIO(url,savePath+"nio_test.txt");
       // 文件恢复下载
       // downloadFile.downloadForNIO("http://112.25.72.5/cos/program_set/js/dmp_programset_json_20181214_js.txt",savePath+"js.txt");
          downloadFile.recoveryDownload("http://112.25.72.5/cos/program_set/js/dmp_programset_json_20181214_js.txt",savePath+"js.txt");
    }

    /**
     * io jdk1.6
     * 每一次用read()方法读取一个字节时,都会调用一次底层文件系统,所以每当JVM调用read()的时候,
     * 程序执行上下文都会从用户模式切换到内核模式,执行结束后再切换回来
     * 从性能角度来看,这种上下文切换的成本是高昂的:比如我们在读取一个字节数很高的文件时,大量的上下文切换将会很影响程序性能
     * 使用BufferedInputStream来规避这种情况
     * 在使用BufferedInputStream的时候,read()方法会根据我们设置的buffer size一次性读取等量的字节(jkd1.8里是默认8192个字节)
     */
    public void downloadForJDK6(String url,String savePath) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        try {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            fileOutputStream.close();
        }
    }

    /**
     * io jdk1.7
     */
    public void downloadForJDK7(String url,String savePath) throws IOException {
        InputStream in = new URL(url).openStream();
        try {
            Files.copy(in, Paths.get(savePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }

    /**
     * nio
     * @param url
     * @param savePath
     */
    public void downloadForNIO(String url,String savePath) throws IOException {
        InputStream in = new URL(url).openStream();
        //创建一个ReadableByteChannel来读取网络文件
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);
        //通过ReadableByteChannel读取到的字节会流动到一个FileChannel中，然后再关联一个本地文件进行下载操作
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        try {
            FileChannel fileChannel = fileOutputStream.getChannel();
            //transferFrom()方法就可以把ReadableByteChannel获取到的字节写入本地文件
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } finally {
            in.close();
            fileOutputStream.close();
        }
    }

    /**
     * 恢复下载
     * @param fileUrl
     * @param savePath
     */
    public void recoveryDownload(String fileUrl,String savePath) throws IOException {
        //创建url对象
        URL url = new URL(fileUrl);
        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.setRequestMethod("GET");
        //查询已下载的数量
        File existFile = new File("F:\\tutorial_space\\tutorial_springboot_\\tutorial_java\\io_demo\\js.txt");
        long existFileSize = 0;
        if(existFile.exists()){
            existFileSize = existFile.length();
        }
        System.out.println(existFileSize);
        /**
         * Range头域可以请求实体的一个或者多个子范围
         * 表示头500个字节:bytes=0-499 　　
         * 表示第二个500字节:bytes=500-999 　　
         * 表示最后500个字节:bytes=-500 　　
         * 表示500字节以后的范围:bytes=500- 　　
         * 第一个和最后一个字节:bytes=0-0,-1 　　
         * 同时指定几个范围:bytes=500-600,601-999 　　
         */
        httpConnection.addRequestProperty("Range","bytes=" + existFileSize + "-");
        httpConnection.connect();
        //206 一般代表断点续传
        int code = httpConnection.getResponseCode();
        System.out.println(code);
        if(code == 206){
            //本方法用来获取响应正文的大小,但因为设置了range请求头,那么这个方法返回的就是剩余的大小
            long fileSize = httpConnection.getContentLengthLong();
            InputStream in = httpConnection.getInputStream();
            //创建一个ReadableByteChannel来读取网络文件
            ReadableByteChannel readableByteChannel = Channels.newChannel(in);
            //文件如果不存在就创建,存在就追加到尾部
            FileOutputStream fileOutputStream = new FileOutputStream(savePath,true);
            try {
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            } finally {
                in.close();
                fileOutputStream.close();
            }
        }
    }
}



