package com.nortal.sk.client.ws;

import java.security.cert.X509Certificate;

import org.junit.Assert;
import org.junit.Test;

import com.nortal.sk.ws.BaseWsTest;
import com.nortal.sk.ws.constant.CheckCertificateStatusEnum;
import com.nortal.sk.ws.model.CheckCertificateReqImpl;
import com.nortal.sk.ws.model.CheckCertificateRsp;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

/**
 * @author Lauri Lättemäe <lauri.lattemae@nortal.com>
 */
public class CheckCertificateTest extends BaseWsTest {
    public static final String COMMON_NAME = "digidocservice-client";
    public static final String ORGANIZATION = "Nortal";
    public static final String COUNTRY = "EE";
    public static final long YEAR = 365 * 24 * 60 * 60;

    protected SimpleSkWsClient skWsClient = new SimpleSkWsClient();

    @Test
    public void checkCertificateTest1() throws Exception {
        CheckCertificateRsp rsp = skWsClient.checkCertificate(CheckCertificateReqImpl.of(generateCert()));
        Assert.assertNotNull(rsp);
        Assert.assertEquals(COMMON_NAME, rsp.getUserCN());
        Assert.assertEquals(ORGANIZATION, rsp.getUserOrganisation());
        Assert.assertEquals(COUNTRY, rsp.getUserCountry());
        Assert.assertEquals(CheckCertificateStatusEnum.UNKNOWN, CheckCertificateStatusEnum.valueOf(rsp.getStatus()));
    }

    private X509Certificate generateCert() throws Exception {
        CertAndKeyGen certGen = new CertAndKeyGen("RSA", "SHA256WithRSA", null);
        certGen.generate(2048);
        return certGen.getSelfCertificate(new X500Name(String.format("CN=%s,O=%s,C=%s", COMMON_NAME, ORGANIZATION, COUNTRY)), YEAR);
    }
}
