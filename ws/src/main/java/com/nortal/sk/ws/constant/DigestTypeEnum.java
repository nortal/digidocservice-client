package com.nortal.sk.ws.constant;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.common.constant.GeneralConstant;

public enum DigestTypeEnum implements GeneralConstant {
    SHA256("SHA-256"),
    SHA512("SHA-512"); // Not supported?

    private String code;

    private DigestTypeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public static DigestTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (DigestTypeEnum type : values()) {
            if (StringUtils.equalsIgnoreCase(type.getCode(), code)) {
                return type;
            }
        }
        return null;
    }
}
