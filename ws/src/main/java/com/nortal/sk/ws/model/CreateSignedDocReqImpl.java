package com.nortal.sk.ws.model;

import com.nortal.sk.ws.constant.SignedDocFormatEnum;

public class CreateSignedDocReqImpl extends SesscodeReqImpl implements CreateSignedDocReq {
    private String format = SignedDocFormatEnum.BDOC.getCode();
    private String version = SignedDocFormatEnum.BDOC.getVersion();

    @Override
    public String getFormat() {
        return format;
    }

    public void getFormat(String format) {
        this.format = format;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
