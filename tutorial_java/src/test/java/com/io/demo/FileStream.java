package com.io.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author jimmy
 * @date 2018/9/1620:01
 */
public class FileStream {

    @Test
    public void fileStreamInputTest1() throws IOException {
        //1.获取文件读入的路径
        File file = new File("io_demo/fileStream.txt");
        //2.创建读入对象
        FileInputStream fis = new FileInputStream(file);
        //3.调用方法读取文件内容  注意此方法不能读入中文
        int b = fis.read();
        while(b != -1){
           // System.out.print(b);
            System.out.print((char)b);
            b = fis.read();
        }
        //4.必须手动关闭流
        fis.close();
    }

}
