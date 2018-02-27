package com.nortal.sk.ws.constant;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum SignedDocFormatEnum implements GeneralConstant {
    BDOC("2.1"),
    DIGIDOC_XML("DIGIDOC-XML", "1.3");

    private String code;
    private String version;

    private SignedDocFormatEnum(String version) {
        this(null, version);
    }

    private SignedDocFormatEnum(String code, String version) {
        this.version = version;
    }

    @Override
    public String getCode() {
        return StringUtils.isNotBlank(code) ? code : name();
    }

    public String getVersion() {
        return version;
    }
}
