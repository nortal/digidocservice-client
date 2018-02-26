package com.nortal.sk.client.card.model;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.DecoderException;

import com.nortal.sk.common.util.SkUtils;

public class CardSignStartReqImpl implements CardSignStartReq {
    private X509Certificate cert;
    private String[] claimedRoles;
    private String city;
    private String state;
    private String country;
    private String zip;

    @Override
    public X509Certificate getCert() {
        return cert;
    }

    public void setCert(X509Certificate cert) {
        this.cert = cert;
    }

    @Override
    public String[] getClaimedRoles() {
        return claimedRoles;
    }

    public void setClaimedRoles(String[] claimedRoles) {
        this.claimedRoles = claimedRoles;
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
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public static CardSignStartReqImpl of(String certHex) throws CertificateException, DecoderException {
        CardSignStartReqImpl req = new CardSignStartReqImpl();
        req.setCert(SkUtils.getCert(certHex));
        return req;
    }
}
