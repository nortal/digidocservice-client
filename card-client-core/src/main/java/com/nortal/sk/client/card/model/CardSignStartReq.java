package com.nortal.sk.client.card.model;

import java.security.cert.X509Certificate;

import com.nortal.sk.common.model.GeneralReq;

public interface CardSignStartReq extends GeneralReq {
    X509Certificate getCert();

    String[] getClaimedRoles();

    String getCity();

    String getState();

    String getCountry();

    String getZip();
}
