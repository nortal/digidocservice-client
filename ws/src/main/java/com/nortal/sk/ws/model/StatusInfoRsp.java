package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

/**
 * 8.6 GetStatusInfo
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class StatusInfoRsp extends StatusRspImpl {
    private String statusCode;
    private SignedDocInfo signedDocInfo;

    /**
     * Status code of the last request . In case of successful request, "OK" or an error string.
     * 
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * If "ReturnDocInfo" parameter in the GetSignedDocInfo request was set "true" then SignedDocInfo structure will be
     * returned in the format dessribed in chapter 9.1 .
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
