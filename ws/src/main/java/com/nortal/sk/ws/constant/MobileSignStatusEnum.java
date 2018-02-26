package com.nortal.sk.ws.constant;

import com.nortal.sk.common.constant.GeneralConstant;

/**
 * Status of the mobile signing process: <br />
 * - REQUEST_OK – initial message was received;<br />
 * - EXPIRED_TRANSACTION – timeout – the user did not enter the signing PIN during given period of time;<br />
 * - USER_CANCEL – the user refused or cancelled the signing process;<br />
 * - SIGNATURE – signature was created;<br />
 * - NOT_VALID – signature created but not valid;<br />
 * - OUTSTANDING_TRANSACTION – signing in process, please make new request;<br />
 * - MID_NOT_READY – Mobile - ID functionality of the phone is not yet ready;<br />
 * - PHONE_ABSENT – Delivery of the message was not successful, mobile phone is probably switched off or out of
 * coverage;<br />
 * - SENDING_ERROR – other error when sending message (phone is incapable of receiving the message, error in messaging
 * server etc.);<br />
 * - SIM_ERROR – SIM application error;<br />
 * - REVOKED CERTIFICATE – certificate status revoked;<br />
 * - INTERNAL_ERROR – technical error
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public enum MobileSignStatusEnum implements GeneralConstant {
    REQUEST_OK,
    EXPIRED_TRANSACTION,
    USER_CANCEL,
    SIGNATURE,
    NOT_VALID,
    OUTSTANDING_TRANSACTION,
    MID_NOT_READY,
    PHONE_ABSENT,
    SENDING_ERROR,
    SIM_ERROR,
    REVOKED_CERTIFICATE,
    INTERNAL_ERROR;

    @Override
    public String getCode() {
        return name();
    }
}
