package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum SigningProfileEnum implements GeneralConstant {
    LT_TM,
    LT; // Not supported yet

    @Override
    public String getCode() {
        return name();
    }
}
