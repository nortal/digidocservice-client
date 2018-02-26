package com.nortal.sk.ws.model;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com
 */
public class MobileAuthenticateRsp extends SesscodeRspImpl implements ChallengeRsp {
    private String userIDCode;
    private String userGivenname;
    private String userSurname;
    private String userCountry;
    private String userCN;
    private String certificateData;
    private String challengeID;
    private String challenge;
    private String revocationData;

    public String getUserIDCode() {
        return userIDCode;
    }

    public void setUserIDCode(String userIDCode) {
        this.userIDCode = userIDCode;
    }

    public String getUserGivenname() {
        return userGivenname;
    }

    public void setUserGivenname(String userGivenname) {
        this.userGivenname = userGivenname;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserCN() {
        return userCN;
    }

    public void setUserCN(String userCN) {
        this.userCN = userCN;
    }

    public String getCertificateData() {
        return certificateData;
    }

    public void setCertificateData(String certificateData) {
        this.certificateData = certificateData;
    }

    @Override
    public String getChallengeID() {
        return challengeID;
    }

    public void setChallengeID(String challengeID) {
        this.challengeID = challengeID;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getRevocationData() {
        return revocationData;
    }

    public void setRevocationData(String revocationData) {
        this.revocationData = revocationData;
    }
}
