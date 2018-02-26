package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum MessagingModeEnum implements GeneralConstant {
    ASYNC_CLIENT_SERVER("asynchClientServer"),
    ASYNC_SERVER_SERVER("asynchServerServer");

    private String code;

    private MessagingModeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
