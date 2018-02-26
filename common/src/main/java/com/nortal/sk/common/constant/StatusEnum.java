package com.nortal.sk.common.constant;

public enum StatusEnum implements GeneralConstant {
    OK;

    @Override
    public String getCode() {
        return name();
    }

}
