package com.nortal.sk.client.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class FileModel {
    private static MimetypesFileTypeMap MIME_TYPES = new MimetypesFileTypeMap();

    private String name;
    private String mimeType;
    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getSize() {
        return content != null ? content.length : -1;
    }

    public static FileModel of(File file) throws IOException {
        return of(file.getName(), new FileInputStream(file));
    }

    public static FileModel of(String name, InputStream is) throws IOException {
        return of(name, null, is);
    }

    public static FileModel of(String name, String mimeType, InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        IOUtils.copyLarge(is, os);
        return of(name, mimeType, os.toByteArray());
    }

    public static FileModel of(String name, byte[] content) {
        return of(name, null, content);
    }

    public static FileModel of(String name, String mimeType, byte[] content) {
        FileModel result = new FileModel();
        result.setName(name);
        result.setContent(content);
        result.setMimeType(StringUtils.isBlank(mimeType) && StringUtils.isNotBlank(name) ? MIME_TYPES.getContentType(name) : mimeType);
        return result;
    }
}
