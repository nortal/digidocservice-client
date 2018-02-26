package com.nortal.sk.ws.model;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * 7.3 CheckCertificate - request
 * 
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class CheckCertificateReqImpl implements CheckCertificateReq {
    private String certificate = StringUtils.EMPTY;
    private boolean returnRevocationData;

    @Override
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Override
    public boolean isReturnRevocationData() {
        return returnRevocationData;
    }

    public void setReturnRevocationData(boolean returnRevocationData) {
        this.returnRevocationData = returnRevocationData;
    }

    public static CheckCertificateReqImpl of(X509Certificate certificate) throws CertificateEncodingException {
        return of(Base64.encodeBase64String(certificate.getEncoded()));
    }

    public static CheckCertificateReqImpl of(String certificate) {
        CheckCertificateReqImpl req = new CheckCertificateReqImpl();
        req.setCertificate(certificate);
        return req;
    }

}
