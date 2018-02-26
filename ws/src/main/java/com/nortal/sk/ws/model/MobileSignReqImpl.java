package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.ws.constant.CountryEnum;
import com.nortal.sk.ws.constant.LanguageEnum;
import com.nortal.sk.ws.constant.MessagingModeEnum;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class MobileSignReqImpl implements MobileSignReq {
    private int sesscode = 0;
    private String signerIDCode = StringUtils.EMPTY;
    private String signersCountry = CountryEnum.EE.getCode();
    private String signerPhoneNo = StringUtils.EMPTY;
    private String serviceName = StringUtils.EMPTY;
    private String additionalDataToBeDisplayed = StringUtils.EMPTY;
    private String language = LanguageEnum.EST.getCode();
    private String role = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String stateOrProvince = StringUtils.EMPTY;
    private String postalCode = StringUtils.EMPTY;
    private String countryName = StringUtils.EMPTY;
    private String signingProfile = StringUtils.EMPTY;
    private String messagingMode = MessagingModeEnum.ASYNC_CLIENT_SERVER.getCode();
    private int asyncConfiguration = 0;
    private boolean returnDocInfo;
    private boolean returnDocData;

    @Override
    public int getSesscode() {
        return sesscode;
    }

    public void setSesscode(int sesscode) {
        this.sesscode = sesscode;
    }

    @Override
    public String getSignerIDCode() {
        return signerIDCode;
    }

    public void setSignerIDCode(String signerIDCode) {
        this.signerIDCode = signerIDCode;
    }

    @Override
    public String getSignersCountry() {
        return signersCountry;
    }

    public void setSignersCountry(String signersCountry) {
        this.signersCountry = signersCountry;
    }

    @Override
    public String getSignerPhoneNo() {
        return signerPhoneNo;
    }

    public void setSignerPhoneNo(String signerPhoneNo) {
        this.signerPhoneNo = signerPhoneNo;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getAdditionalDataToBeDisplayed() {
        return additionalDataToBeDisplayed;
    }

    public void setAdditionalDataToBeDisplayed(String additionalDataToBeDisplayed) {
        this.additionalDataToBeDisplayed = additionalDataToBeDisplayed;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String getSigningProfile() {
        return signingProfile;
    }

    public void setSigningProfile(String signingProfile) {
        this.signingProfile = signingProfile;
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
    public boolean isReturnDocInfo() {
        return returnDocInfo;
    }

    public void setReturnDocInfo(boolean returnDocInfo) {
        this.returnDocInfo = returnDocInfo;
    }

    @Override
    public boolean isReturnDocData() {
        return returnDocData;
    }

    public void setReturnDocData(boolean returnDocData) {
        this.returnDocData = returnDocData;
    }

    public static MobileSignReqImpl of(String signerIDCode, String signerPhoneNo) {
        return of(0, signerIDCode, signerPhoneNo);
    }

    public static MobileSignReqImpl of(int sesscode, String signerIDCode, String signerPhoneNo) {
        MobileSignReqImpl req = new MobileSignReqImpl();
        req.setSesscode(sesscode);
        req.setSignerIDCode(signerIDCode);
        req.setSignerPhoneNo(signerPhoneNo);
        return req;
    }
}
