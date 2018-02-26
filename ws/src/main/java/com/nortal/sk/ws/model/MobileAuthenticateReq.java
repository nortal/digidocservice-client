package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.GeneralReq;

public interface MobileAuthenticateReq extends GeneralReq {
    String getIdCode();

    String getCountryCode();

    String getPhoneNo();

    String getLanguage();

    String getServiceName();

    String getMessageToDisplay();

    String getSpChallenge();

    String getMessagingMode();

    int getAsyncConfiguration();

    boolean isReturnCertData();

    boolean isReturnRevocationData();
}
