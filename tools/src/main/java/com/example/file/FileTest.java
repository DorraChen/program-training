package com.example.file;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author clz
 * @data 2020/11/9 14:22
 * @description 计算文件的hash512(sha512就是hash512)签名
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Pictures\\Saved Pictures\\v2-c9e3353d4d4ed55015303b57ff2d6fce_1440w.jpg");
        FileInputStream fis = new FileInputStream(file);

        File file1 = new File("C:\\Users\\admin\\Pictures\\Saved Pictures\\v2-c9e3353d4d4ed55015303b57ff2d6fce_1440w - 副本.jpg");
        FileInputStream fis1 = new FileInputStream(file1);

        String sha = DigestUtils.sha512Hex("clz");
        String sha1 = DigestUtils.sha512Hex("clz");
        String filesha = DigestUtils.sha512Hex(fis);
        String filesha1 = DigestUtils.sha512Hex(fis1);
        System.out.println(sha);
        System.out.println(sha1);
        System.out.println(filesha);
        System.out.println(filesha1);
    }
}
