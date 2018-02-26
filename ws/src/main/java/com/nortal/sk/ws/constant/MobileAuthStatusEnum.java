package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum MobileAuthStatusEnum implements GeneralConstant {
    OUTSTANDING_TRANSACTION,
    USER_AUTHENTICATED,
    NOT_VALID,
    EXPIRED_TRANSACTION,
    USER_CANCEL,
    MID_NOT_READY,
    PHONE_ABSENT,
    SENDING_ERROR,
    SIM_ERROR,
    INTERNAL_ERROR;

    @Override
    public String getCode() {
        return name();
    }
}
