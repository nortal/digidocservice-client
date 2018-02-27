package com.nortal.sk.client.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
// TODO: refacto to right modules
public enum StepCodeEnum implements GeneralConstant {
    PREPARING,

    CHECK_CERTIFICATE,

    CARD_SIGN_START,
    CARD_SIGN_FINALIZE,
    SIGNATURE_CONFIRMATION,

    MOBILE_AUTHENTICATE,
    MOBILE_AUTHENTICATE_STATUS,

    START_SESSION,
    CREATE_SIGNED_DOC,
    ADD_DATA_FILE,
    PREPARE_SIGNATURE,
    FINALIZE_SIGNATURE,
    MOBILE_SIGN,
    STATUS_INFO,
    SIGNED_DOC,
    CLOSE_SESSION;

    @Override
    public String getCode() {
        return name();
    }
}
