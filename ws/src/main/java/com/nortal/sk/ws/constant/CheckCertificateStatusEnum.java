package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum CheckCertificateStatusEnum implements GeneralConstant {
    GOOD,
    REVOKED,
    UNKNOWN,
    EXPIRED,
    SUSPENDED;

    @Override
    public String getCode() {
        return name();
    }
}
