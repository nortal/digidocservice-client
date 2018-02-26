package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

/**
 * 8.3 CreateSignedDoc
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class SignedDocInfoRsp extends StatusRspImpl {
    private SignedDocInfo signedDocInfo;

    public SignedDocInfo getSignedDocInfo() {
        return signedDocInfo;
    }

    public void setSignedDocInfo(SignedDocInfo signedDocInfo) {
        this.signedDocInfo = signedDocInfo;
    }
}
