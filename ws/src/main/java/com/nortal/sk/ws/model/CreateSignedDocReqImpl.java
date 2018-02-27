package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.ws.constant.SignedDocFormatEnum;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class CreateSignedDocReqImpl extends SesscodeReqImpl implements CreateSignedDocReq {
    private String signingProfile = StringUtils.EMPTY;
    private String format = SignedDocFormatEnum.BDOC.getCode();
    private String version = SignedDocFormatEnum.BDOC.getVersion();

    @Override
    public String getSigningProfile() {
        return signingProfile;
    }

    public void setSigningProfile(String signingProfile) {
        this.signingProfile = signingProfile;
    }

    @Override
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
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
