package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
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
