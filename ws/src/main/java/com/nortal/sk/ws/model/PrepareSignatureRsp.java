package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

public class PrepareSignatureRsp extends StatusRspImpl {
    private String signatureId;
    private String signedInfoDigest;

    public String getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public String getSignedInfoDigest() {
        return signedInfoDigest;
    }

    public void setSignedInfoDigest(String signedInfoDigest) {
        this.signedInfoDigest = signedInfoDigest;
    }
}
