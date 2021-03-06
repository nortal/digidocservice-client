package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum ContentTypeEnum implements GeneralConstant {
    HASHCODE,
    EMBEDDED_BASE64;

    @Override
    public String getCode() {
        return name();
    }
}
