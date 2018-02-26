package com.nortal.sk.ws.model;

import com.nortal.sk.common.model.StatusRspImpl;

/**
 * 8.5 MobileSign
 * 
 * @see <a href="https://www.sk.ee/upload/files/DigiDocService_spec_eng.pdf">DigiDocService_spec_eng.pdf</a>
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileSignRsp extends StatusRspImpl implements ChallengeRsp {
    private String statusCode;
    private String challengeID;

    /**
     * If the request is successful, 0 is returned, otherwise an error code.
     * 
     * @return
     */
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(String challengeID) {
        this.challengeID = challengeID;
    }
}
