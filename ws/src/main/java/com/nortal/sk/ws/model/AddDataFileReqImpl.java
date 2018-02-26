package com.nortal.sk.ws.model;

import com.nortal.sk.ws.constant.ContentTypeEnum;
import com.nortal.sk.ws.constant.DigestTypeEnum;

public class AddDataFileReqImpl extends SesscodeReqImpl implements AddDataFileReq {
    private String fileName;
    private String mimeType;
    private String contentType = ContentTypeEnum.HASHCODE.getCode();
    private int size;
    private String digestType = DigestTypeEnum.SHA256.getCode();
    private String digestValue;
    private String content;

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getDigestType() {
        return digestType;
    }

    public void setDigestType(String digestType) {
        this.digestType = digestType;
    }

    @Override
    public String getDigestValue() {
        return digestValue;
    }

    public void setDigestValue(String digestValue) {
        this.digestValue = digestValue;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
