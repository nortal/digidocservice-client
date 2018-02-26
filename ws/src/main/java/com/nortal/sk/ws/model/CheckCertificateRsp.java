package com.nortal.sk.ws.model;

/**
 * 7.3 CheckCertificate - response
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class CheckCertificateRsp extends SesscodeRspImpl implements SesscodeRsp {
    private String userIDCode;
    private String userGivename;
    private String userSurname;
    private String userCountry;
    private String userOrganisation;
    private String userCN;
    private String issuer;
    private String keyUsage;
    private String enhancedKeyUsage;
    private String revocationData;

    /**
     * Certificate's validity information: - GOOD – certificate is valid - REVOKED – certificate has been revoked -
     * UNKNOWN – certificate has never been issued or issuer is unknown - EXPIRED – certificate has been expired -
     * SUSPENDED – certificate has been suspended
     */
    @Override
    public String getStatus() {
        return super.getStatus();
    }

    /**
     * Certifi cate owner's Personal Identification Code. In case certificate has been issued by SK, this value will be
     * taken from certificate subject's serial number field
     * 
     * @return
     */
    public String getUserIDCode() {
        return userIDCode;
    }

    public void setUserIDCode(String userIDCode) {
        this.userIDCode = userIDCode;
    }

    /**
     * Certificate owner's given name, this value will be taken from certificates ubject's G (given name) field.
     * 
     * @return
     */
    public String getUserGivename() {
        return userGivename;
    }

    public void setUserGivename(String userGivename) {
        this.userGivename = userGivename;
    }

    /**
     * Certificate owner's surname, this value will be taken from certificate subject's S (surname) field
     * 
     * @return
     */
    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    /**
     * Certificate owner's country, this value will be taken from certificate subject's C (country) field. ISO 3166 2 -
     * letter country codes are used
     * 
     * @return
     */
    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    /**
     * Certificate owner's organization, this value will be taken from certificate subject's O ( Organization ) field.
     * 
     * @return
     */
    public String getUserOrganisation() {
        return userOrganisation;
    }

    public void setUserOrganisation(String userOrganisation) {
        this.userOrganisation = userOrganisation;
    }

    /**
     * Certificate owner's common name, this value will be taken from certificate subject's CN (Common name) field.
     * 
     * @return
     */
    public String getUserCN() {
        return userCN;
    }

    public void setUserCN(String userCN) {
        this.userCN = userCN;
    }

    /**
     * Certificate issuer's common name, this value will be taken from certificate issuers's CN (Common name) field.
     * 
     * @return
     */
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Usage of the (secret) key related to the certificate.
     * 
     * @return
     */
    public String getKeyUsage() {
        return keyUsage;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    /**
     * Enhanced key usage
     * 
     * @return
     */
    public String getEnhancedKeyUsage() {
        return enhancedKeyUsage;
    }

    public void setEnhancedKeyUsage(String enhancedKeyUsage) {
        this.enhancedKeyUsage = enhancedKeyUsage;
    }

    /**
     * Certificate's validity information (OCSP service's response) in Base64 format. Returned only if request parameter
     * ReturnRevocationData has been set to TRUE, otherwise empty string is returned.
     * 
     * @return
     */
    public String getRevocationData() {
        return revocationData;
    }

    public void setRevocationData(String revocationData) {
        this.revocationData = revocationData;
    }
}
