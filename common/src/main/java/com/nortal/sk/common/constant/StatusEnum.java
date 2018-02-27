package com.nortal.sk.common.constant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum StatusEnum implements GeneralConstant {
    OK;

    @Override
    public String getCode() {
        return name();
    }

}
