package com.nortal.sk.ws.model;

import org.apache.commons.lang3.StringUtils;

import com.nortal.sk.ws.constant.SigningProfileEnum;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class PrepareSignatureReqImpl extends SesscodeReqImpl implements PrepareSignatureReq {
    private String signersCertificate = StringUtils.EMPTY;
    private String signersTokenId = StringUtils.EMPTY;
    private String role = StringUtils.EMPTY;
    private String city = StringUtils.EMPTY;
    private String state = StringUtils.EMPTY;
    private String postalCode = StringUtils.EMPTY;
    private String country = StringUtils.EMPTY;
    private String signingProfile = SigningProfileEnum.LT_TM.getCode();

    @Override
    public String getSignersCertificate() {
        return signersCertificate;
    }

    public void setSignersCertificate(String signersCertificate) {
        this.signersCertificate = signersCertificate;
    }

    @Override
    public String getSignersTokenId() {
        return signersTokenId;
    }

    public void setSignersTokenId(String signersTokenId) {
        this.signersTokenId = signersTokenId;
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
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getSigningProfile() {
        return signingProfile;
    }

    public void setSigningProfile(String signingProfile) {
        this.signingProfile = signingProfile;
    }

    public static PrepareSignatureReqImpl of(String certHex) {
        PrepareSignatureReqImpl req = new PrepareSignatureReqImpl();
        req.setSignersCertificate(certHex);
        return req;
    }
}
