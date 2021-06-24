package com.example.imgparse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author Dorra
 * @date 2021/5/6 11:17
 * @description 自定义的MultipartFile的实现类，主要用于base64上传文件，以下方法都可以根据实际项目自行实现
 */
public class Base64DecodedMultipartFile implements MultipartFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64DecodedMultipartFile.class);

    private final byte[] imgContent;
    private final String header;

    public Base64DecodedMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        return UUID.randomUUID() + "." + header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return UUID.randomUUID() + "." + header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(":")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File dest) {
        try (OutputStream outputStream = new FileOutputStream(dest)) {
            outputStream.write(imgContent);
        } catch (Exception e) {
            LOGGER.debug("error", e);
        }
    }
}