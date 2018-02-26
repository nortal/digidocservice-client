package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.ws.constant.CountryEnum;
import com.nortal.sk.ws.constant.LanguageEnum;
import com.nortal.sk.ws.constant.MessagingModeEnum;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileAuthenticateReqImpl implements MobileAuthenticateReq {
    private String idCode = StringUtils.EMPTY;
    private String countryCode = CountryEnum.EE.getCode();
    private String phoneNo = StringUtils.EMPTY;
    private String language = LanguageEnum.EST.getCode();
    private String serviceName = StringUtils.EMPTY;
    private String messageToDisplay = StringUtils.EMPTY;
    private String spChallenge = StringUtils.EMPTY;
    private String messagingMode = MessagingModeEnum.ASYNC_CLIENT_SERVER.getCode();
    private int asyncConfiguration = 0;
    private boolean returnCertData;
    private boolean returnRevocationData;

    @Override
    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getMessageToDisplay() {
        return messageToDisplay;
    }

    public void setMessageToDisplay(String messageToDisplay) {
        this.messageToDisplay = messageToDisplay;
    }

    @Override
    public String getSpChallenge() {
        return spChallenge;
    }

    public void setSpChallenge(String spChallenge) {
        this.spChallenge = spChallenge;
    }

    @Override
    public String getMessagingMode() {
        return messagingMode;
    }

    public void setMessagingMode(String messagingMode) {
        this.messagingMode = messagingMode;
    }

    @Override
    public int getAsyncConfiguration() {
        return asyncConfiguration;
    }

    public void setAsyncConfiguration(int asyncConfiguration) {
        this.asyncConfiguration = asyncConfiguration;
    }

    @Override
    public boolean isReturnCertData() {
        return returnCertData;
    }

    public void setReturnCertData(boolean returnCertData) {
        this.returnCertData = returnCertData;
    }

    @Override
    public boolean isReturnRevocationData() {
        return returnRevocationData;
    }

    public void setReturnRevocationData(boolean returnRevocationData) {
        this.returnRevocationData = returnRevocationData;
    }

    public static MobileAuthenticateReqImpl of(String idCode, String phoneNo) {
        MobileAuthenticateReqImpl req = new MobileAuthenticateReqImpl();
        req.setIdCode(idCode);
        req.setPhoneNo(phoneNo);
        return req;
    }
}
