package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class FinalizeSignatureReqImpl extends SesscodeReqImpl implements FinalizeSignatureReq {
    private String signatureId = StringUtils.EMPTY;
    private String signatureValue = StringUtils.EMPTY;

    @Override
    public String getSignatureId() {
        return signatureId;
    }

    @Override
    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    @Override
    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }

    public static final FinalizeSignatureReqImpl of(String signatureValue) {
        FinalizeSignatureReqImpl req = new FinalizeSignatureReqImpl();
        req.setSignatureValue(signatureValue);
        return req;
    }
}
