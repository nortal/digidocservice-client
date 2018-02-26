package com.nortal.sk.ws.model;

/**
 * 8.1 StartSession
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StartSessionRsp extends SesscodeRspImpl {
    private SignedDocInfo signedDocInfo;

    /**
     * If a StartSession request contains a data file or a DigiDoc file, a SignedDocInfo structure will be returned in
     * the format demonstrated in chapter 9.1 in current document
     * 
     * @return
     */
    public SignedDocInfo getSignedDocInfo() {
        return signedDocInfo;
    }

    public void setSignedDocInfo(SignedDocInfo signedDocInfo) {
        this.signedDocInfo = signedDocInfo;
    }
}
