package com.nortal.sk.client.ws.test;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import com.nortal.sk.client.ws.SimpleSkWsClient;
import com.nortal.sk.ws.model.CheckCertificateReqImpl;
import com.nortal.sk.ws.model.CheckCertificateRsp;

public class checkCertTest {
    private static final String cert = "-----BEGIN CERTIFICATE-----\r\n valid cert plx -----END CERTIFICATE-----";

    public static void main(String[] args) {
        try {
            System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
            X509Certificate xCert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(cert.getBytes()));

            CheckCertificateRsp rsp = new SimpleSkWsClient().checkCertificate(CheckCertificateReqImpl.of(xCert));
            System.out.println(rsp.getStatus());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
