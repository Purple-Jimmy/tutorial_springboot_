package com.io.demo;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author jimmy
 * @date 2018/9/1614:38
 */
public class FileDemo {

    @Test
    public void test1() throws IOException {
        //相对路径:当前文件目录下的文件的路径
        File file1 = new File("io_demo");// io_demo
        System.out.println(file1.getName());
        File file2 = new File("io_demo/helloWorld.txt");
        System.out.println(file2.getAbsoluteFile());// /Users/zhoucheng/tutorial_space/tutorial_springboot_/tutorial_java/io_demo/helloWorld.txt

        if(!file2.exists()){//判断文件是否存在
            Boolean flag = file2.createNewFile();//创建文件
            System.out.println(flag);
        }

        if(file2.exists()){
           // Boolean flag = file2.delete();//删除文件
           // System.out.println(flag+"delete");
        }

       /* file1.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });*/
    }
}
