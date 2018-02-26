package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum CountryEnum implements GeneralConstant {
    EE;

    @Override
    public String getCode() {
        return name();
    }
}
