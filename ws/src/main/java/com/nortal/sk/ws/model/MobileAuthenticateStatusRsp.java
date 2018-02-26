package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

public class MobileAuthenticateStatusRsp extends StatusRspImpl {
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
